package de.arnohaase.freeoffice.calc.backend.naive;

import java.util.HashMap;
import java.util.Map;

import de.arnohaase.freeoffice.calc.backend.CalcBackend;
import de.arnohaase.freeoffice.calc.backend.CellListener;
import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;
import de.arnohaase.freeoffice.calc.backend.cmd.MakePristineCommand;
import de.arnohaase.freeoffice.calc.backend.cmd.SetCellCommand;
import de.arnohaase.freeoffice.calc.backend.cmd.SetContentCommand;
import de.arnohaase.freeoffice.calc.backend.cmd.SetTypeCommand;
import de.arnohaase.freeoffice.calc.backend.type.CellValueType;
import de.arnohaase.freeoffice.util.EqHelper;


public class NaiveBackendImpl implements CalcBackend {
    private volatile CellListener[] listeners = new CellListener[0];
    
	final int MAX_COLS = 100_000;
	
	private final Map<Integer, CellImpl> cells = new HashMap<>();
	private int numRows=0;
	private int numCols=0;

	@Override
	public int getNumRows() {
	    return numRows;
	}

	@Override
	public int getNumCols() {
	    return numCols;
	}

	private int key(int row, int col) {
		return row * MAX_COLS + col;
	}
	
	private CellImpl getCell(int row, int col) {
		final CellImpl result = cells.get(key(row, col));
		if (result == null) {
			return CellImpl.INITIAL;
		}
		return result;
	}
	
	private CellImpl getInitializedCell(int row, int col) {
	    final CellImpl existing = getCell(row, col);
	    if (existing != CellImpl.INITIAL) {
	        return existing;
	    }
	    final CellImpl result = new CellImpl();
	    cells.put(key(row, col), result);
	    return result;
	}
	
	@Override
	public CellValueType<?> getType(int row, int col) {
		return getCell(row, col).getType();
	}

	@Override
	public String getRawContent(int row, int col) {
		return getCell(row, col).getRaw();
	}

	@Override
	public Object getValue(int row, int col) {
		return getCell(row, col).getValue();
	}

    @Override
    public int getMaxNumRows() {
        return Integer.MAX_VALUE / MAX_COLS;
    }

    @Override
    public int getMaxNumCols() {
        return MAX_COLS;
    }

    @Override
    public boolean isPristine(int row, int col) {
        return getCell(row, col) == CellImpl.INITIAL;
    }

    @Override
    public CellModifyCommand applyCommand(CellModifyCommand cmd) {
        final CellModifyCommand undoCommand = cmd.createUndoCommand(this);
        
        if(cmd instanceof MakePristineCommand) {
            doMakePristine((MakePristineCommand) cmd);
        }
        else if (cmd instanceof SetCellCommand) {
            doSetCell((SetCellCommand) cmd);
        }
        else if (cmd instanceof SetContentCommand) {
            doSetContent((SetContentCommand) cmd);
        }
        else if(cmd instanceof SetTypeCommand) {
            doSetType((SetTypeCommand) cmd);
        }
        else {
            throw new IllegalArgumentException("unsupported command type: " + cmd.getClass().getName());
        }
        
        return undoCommand;
    }

    private void refreshValue(int row, int col, CellImpl cell) {
        final Object oldValue = cell.getValue();
        final Object newValue = cell.getType().parse(cell.getRaw(), cell.getFormat());
        
        cell.setValue(newValue);
        if (EqHelper.neq(oldValue, newValue)) {
            fireCellChange(row, col);
        }
    }
    
    private void fireCellChange(int row, int col) {
        for (CellListener l: listeners) {
            l.cellValueChanged(row, col);
        }
    }
    
    private void doSetType(SetTypeCommand cmd) {
        final CellImpl cell = getInitializedCell(cmd.getRow(), cmd.getCol());
        cell.setType(cmd.getType());
        refreshValue(cmd.getRow(), cmd.getCol(), cell);
    }

    private void doSetContent(SetContentCommand cmd) {
        final CellImpl cell = getInitializedCell(cmd.getRow(), cmd.getCol());
        cell.setRaw(cmd.getRawContent());
        refreshValue(cmd.getRow(), cmd.getCol(), cell);
    }

    private void doSetCell(SetCellCommand cmd) {
        final CellImpl cell = getInitializedCell(cmd.getRow(), cmd.getCol());
        cell.setType(cmd.getType());
        cell.setRaw(cmd.getRawContent());
        refreshValue(cmd.getRow(), cmd.getCol(), cell);
    }

    private void doMakePristine(MakePristineCommand cmd) {
        cells.remove(key(cmd.getRow(), cmd.getCol())); 
      //TODO this does not undo the change in 'numRows' and 'numCols'. Is it better to return a memento rather than an 'undo command'
      //      and move the 'undo' logic into the back ends?
    }
    
    @Override
    public synchronized void addCellListener(CellListener l) {
        // synchronization to prevent race conditions for *modifications* of the listener list
        
        final CellListener[] newListeners = new CellListener[listeners.length + 1];
        System.arraycopy(listeners, 0, newListeners, 0, listeners.length);
        newListeners[listeners.length] = l;
    }

    @Override
    public synchronized void removeCellListener(CellListener l) {
        throw new UnsupportedOperationException("TODO");
    }
}
