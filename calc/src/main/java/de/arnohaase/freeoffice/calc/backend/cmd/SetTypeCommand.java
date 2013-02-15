package de.arnohaase.freeoffice.calc.backend.cmd;

import de.arnohaase.freeoffice.calc.backend.CalcBackend;
import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;
import de.arnohaase.freeoffice.calc.backend.type.CellValueType;


public class SetTypeCommand implements CellModifyCommand {
    private final int row;
    private final int col;
    private final CellValueType<?> type;
    
    public SetTypeCommand(int row, int col, CellValueType<?> type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public CellValueType<?> getType() {
        return type;
    }

    @Override
    public CellModifyCommand createUndoCommand(CalcBackend backend) {
        return new SetCellCommand(row, col, null, null).createUndoCommand(backend);
    }
}
