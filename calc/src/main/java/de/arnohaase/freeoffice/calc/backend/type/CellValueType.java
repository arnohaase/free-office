package de.arnohaase.freeoffice.calc.backend.type;

import de.arnohaase.freeoffice.calc.backend.format.BackendFormat;


/**
 * This interface represents a 'free-office calc' data type. This is *not* about
 *  presentation but about the actual value of a cell.
 * 
 * @author arno
 */
public interface CellValueType <T> {
    T parse(String s, BackendFormat f);
    
    //TODO should operations and logic go here, or should logic 'switch' over the types? 
}
