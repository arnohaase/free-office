package de.arnohaase.freeoffice.calc.backend.cmd;

import de.arnohaase.freeoffice.calc.backend.CalcBackend;
import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;


public class MakePristineCommand implements CellModifyCommand {
    private final int row;
    private final int col;
    
    public MakePristineCommand(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public CellModifyCommand createUndoCommand(CalcBackend backend) {
        if(backend.isPristine(row, col)) {
            return this;
        }
        
        return new SetCellCommand(row, col, null, null).createUndoCommand(backend);
    }
}
