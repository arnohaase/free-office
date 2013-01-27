package de.arnohaase.freeoffice.calc.backend;


public interface CalcBackend {
	/**
	 * represents an undefined value of a cell, e.g. because of division by zero or a syntax error in 
	 *  a formula.
	 */
	Object UNDEFINED = new Object();
	
	void modify(CellModifyCommand cmd);
	
    int getNumRows();
    int getNumCols();
    
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
}
