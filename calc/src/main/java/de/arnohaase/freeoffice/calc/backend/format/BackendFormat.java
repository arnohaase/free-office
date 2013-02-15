package de.arnohaase.freeoffice.calc.backend.format;

import java.util.Date;


/**
 * Details for formatting and parsing of values, i.e. converting them from and to their 
 *  string representations.<br>
 *  
 * This is largely necessary for the front end, but some backend operations require this 
 *  as well. 
 * 
 * @author arno
 */
public interface BackendFormat {
    Integer parseInteger(String s);
    Double parseDouble(String s);
    
    Date parseTimestamp(String s);
}
