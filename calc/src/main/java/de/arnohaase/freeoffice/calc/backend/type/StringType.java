package de.arnohaase.freeoffice.calc.backend.type;

import de.arnohaase.freeoffice.calc.backend.format.BackendFormat;


public class StringType implements CellValueType<String> {
    public static final StringType INSTANCE = new StringType();
    
    private StringType () {}

    @Override
    public String parse(String s, BackendFormat f) {
        return s;
    }
}
