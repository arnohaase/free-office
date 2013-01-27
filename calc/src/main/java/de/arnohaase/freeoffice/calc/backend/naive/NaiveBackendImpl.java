package de.arnohaase.freeoffice.calc.backend.naive;

import java.util.HashMap;
import java.util.Map;

import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;
import de.arnohaase.freeoffice.calc.backend.CellValueType;
import de.arnohaase.freeoffice.calc.backend.MutableCalcBackend;


public class NaiveBackendImpl implements MutableCalcBackend {
	final int MAX_COLS = 100_000;
	
	private final Map<Integer, CellImpl> cells = new HashMap<>();
	private int numRows=0;
	private int numCols=0;

	@Override
	public void modify(CellModifyCommand cmd) {
	    cmd.doIt(this);
	}

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
	
	@Override
	public CellValueType getType(int row, int col) {
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
}
