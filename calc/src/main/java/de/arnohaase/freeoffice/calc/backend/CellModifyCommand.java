package de.arnohaase.freeoffice.calc.backend;


public interface CellModifyCommand {
    /**
     * @return a memento that allows undoing
     */
    Object doIt(MutableCalcBackend backend);
    void undo(MutableCalcBackend backend, Object memento);
}
