package de.arnohaase.freeoffice.calc.backend.type;

import java.util.Date;

import de.arnohaase.freeoffice.calc.backend.format.BackendFormat;


//TODO day type
public class TimestampType implements CellValueType<Date> {
    public static final TimestampType INSTANCE = new TimestampType();
    
    private TimestampType () {}

    @Override
    public Date parse(String s, BackendFormat f) {
        return f.parseTimestamp(s);
    }
}
