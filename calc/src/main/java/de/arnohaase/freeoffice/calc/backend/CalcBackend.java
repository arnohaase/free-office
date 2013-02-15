package de.arnohaase.freeoffice.calc.backend;

import de.arnohaase.freeoffice.calc.backend.type.CellValueType;


public interface CalcBackend {
	/**
	 * represents an undefined value of a cell, e.g. because of division by zero or a syntax error in 
	 *  a formula.
	 */
	Object UNDEFINED = new Object();
	
	int getMaxNumRows();
	int getMaxNumCols();
	
    int getNumRows();
    int getNumCols();
    
    /**
     * returns true iff this cell has not been touched yet. This allows for optimizations.
     */
    boolean isPristine(int row, int col);
    
    CellValueType getType(int row, int col);
    
    /**
     * This is what the user input to the cell, i.e. the (potentially unformatted) literal value or the 
     *  source code of a formula.
     */
    String getRawContent(int row, int col);
    
    /**
     * This is the actual data that this cell represents as an object the type of which conforms to the cell
     *  type - or the UNDEFINED constant.
     */
    Object getValue(int row, int col);
    
    /**
     * This applies a given command, returning the undo command.<br>
     * 
     * NB Concurrency: It is the caller's responsibility to call this method 'sequentially', i.e. for every pair
     *  of calls A and B, one must 'happen-before' the other in the sense of the Java Memory Model (17.4.5).<br>
     *  
     * Implementations may (and should for non-trivial operations, e.g. recalculation of significant parts of a
     *  spread sheet) do the actual processing of a command asynchronously. It is the implementation's responsibility
     *  to handle new 'applyCommand' calls happening before the processing of previous commands is finished.
     */
    CellModifyCommand applyCommand(CellModifyCommand cmd);
    
    /**
     * NB Concurrency: CellListeners are guaranteed to be called for every change to every cell's value. Implementations
     *  may call them during the processing of an applyCommand call, i.e. before that call is finished, or from a different
     *  thread.<br>
     * 
     * When a CellListener is called, the triggering changes must be reflected in the CalcBackend's API.<br>
     * 
     * There is no guaranteed 'happens-before' relationship between CellListener events. In particular, during processing
     *  of one CellListener event, the value of the cell in question may have been changed again, e.g. by a different command.<br>
     *  
     * TODO is it the backend's responsibility to decouple processing from calculating threads, or should that be the frontend's
     *  responsibility?
     */
    void addCellListener(CellListener l);
    void removeCellListener(CellListener l);
}
