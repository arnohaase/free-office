package de.arnohaase.freeoffice.calc.backend.type;

import de.arnohaase.freeoffice.calc.backend.format.BackendFormat;


public class IntegerType implements CellValueType<Integer> {
    public static final IntegerType INSTANCE = new IntegerType(); 
   
    private IntegerType() {}

    @Override
    public Integer parse(String s, BackendFormat f) {
        return f.parseInteger(s);
    }
}
