package de.arnohaase.freeoffice.calc.backend.type;

import de.arnohaase.freeoffice.calc.backend.format.BackendFormat;


//TODO fixed point types

public class DoubleType implements CellValueType<Double> {
    public static final DoubleType INSTANCE = new DoubleType();
    
    private DoubleType () {}

    @Override
    public Double parse(String s, BackendFormat f) {
        return f.parseDouble(s);
    }
}
