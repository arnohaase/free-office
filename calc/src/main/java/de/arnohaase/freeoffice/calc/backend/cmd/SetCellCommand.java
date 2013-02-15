package de.arnohaase.freeoffice.calc.backend.cmd;

import de.arnohaase.freeoffice.calc.backend.CalcBackend;
import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;
import de.arnohaase.freeoffice.calc.backend.type.CellValueType;


public class SetCellCommand implements CellModifyCommand {
    private final int row;
    private final int col;
    private final CellValueType<?> type;
    private final String rawContent;

    public SetCellCommand(int row, int col, CellValueType<?> type, String rawContent) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.rawContent = rawContent;
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

    public String getRawContent() {
        return rawContent;
    }

    @Override
    public CellModifyCommand createUndoCommand(CalcBackend backend) {
        if(backend.isPristine(row, col)) {
            return new MakePristineCommand(row, col);
        }
        return new SetCellCommand(row, col, backend.getType(row, col), backend.getRawContent(row, col));
    }
}
