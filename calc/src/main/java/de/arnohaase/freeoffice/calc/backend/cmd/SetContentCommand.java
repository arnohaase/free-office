package de.arnohaase.freeoffice.calc.backend.cmd;

import de.arnohaase.freeoffice.calc.backend.CalcBackend;
import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;


public class SetContentCommand implements CellModifyCommand {
    private final int row;
    private final int col;
    private final String rawContent;
    
    public SetContentCommand(int row, int col, String rawContent) {
        this.row = row;
        this.col = col;
        this.rawContent = rawContent;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    
    public String getRawContent() {
        return rawContent;
    }
    
    @Override
    public CellModifyCommand createUndoCommand(CalcBackend backend) {
        return new SetCellCommand(row, col, null, null).createUndoCommand(backend);
    }
}
