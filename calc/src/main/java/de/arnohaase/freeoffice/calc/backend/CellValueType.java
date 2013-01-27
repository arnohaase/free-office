package de.arnohaase.freeoffice.calc.backend;

/**
 * This enum represents the type of the actual *value* of a cell. This type is 
 *  a static attribute of a cell, and values input to it are coerced
 *  into the appropriate type.<br>
 *  
 * The data can result from either a literal value input to the cell or as the 
 *  result of a formula.
 * 
 * @author arno
 */
public enum CellValueType {
    string, integer, floatingpoint, fixedpoint1, fixedpoint2, date, timestamp;
}
//TODO fixedpoint as a data type! --> fixedpoint0 .. fixedpoint9 as enum values? Or introduce a parameter?!