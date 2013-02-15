package de.arnohaase.freeoffice.calc.backend;


/**
 * Callback interface for notifications of changes to cell values.<br>
 * 
 * For performance reasons, a single event is used to notify of an arbitrary number of cell changes.
 * 
 * @author arno
 */
public interface CellListener {
    void cellValueChanged(int row, int col);
    //TODO collective method for an entire range - int[]? or range object --> 'insert row'?
}
