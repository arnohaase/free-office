package de.arnohaase.freeoffice.calc.backend;


public interface CellModifyCommand {
    //TODO should commands contain their logic, operating on a 'mutable backend' interface,
    // or should every backend know all commands and implement them specifically?
    
    CellModifyCommand createUndoCommand(CalcBackend backend);
}
