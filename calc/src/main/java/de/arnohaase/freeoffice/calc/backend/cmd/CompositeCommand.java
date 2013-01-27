package de.arnohaase.freeoffice.calc.backend.cmd;

import java.util.List;

import de.arnohaase.freeoffice.calc.backend.CellModifyCommand;
import de.arnohaase.freeoffice.calc.backend.MutableCalcBackend;


public final class CompositeCommand implements CellModifyCommand {
    private final List<CellModifyCommand> inner;

	public CompositeCommand(List<CellModifyCommand> inner) {
		this.inner = inner;
	}
    
    public List<CellModifyCommand> getInner() {
		return inner;
	}
    
    @Override
    public Object doIt(MutableCalcBackend backend) {
        final Object[] result = new Object[inner.size()];

        for(int i=0; i<inner.size(); i++) {
            result[i] = inner.get(i).doIt(backend);
    	}
        return result;
    }
    
    @Override
    public void undo(MutableCalcBackend backend, Object memento) {
        for (int i=inner.size()-1; i>=0; i--) {
            inner.get(i).undo(backend, ((Object[]) memento)[i]);
        }
    }
}
