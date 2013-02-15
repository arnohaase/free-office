package de.arnohaase.freeoffice.calc.backend.naive;

import de.arnohaase.freeoffice.calc.backend.format.BackendFormat;
import de.arnohaase.freeoffice.calc.backend.type.CellValueType;
import de.arnohaase.freeoffice.calc.backend.type.StringType;


public class CellImpl {
	/**
	 * for optimization - represents an as yet untouched cell
	 */
	public static final CellImpl INITIAL = new CellImpl();
	
	private BackendFormat format;
	private CellValueType<?> type;
	private String raw;
	private Object value;
	
	public CellImpl() {
	    type = StringType.INSTANCE;
	    //TODO initialize format with a default
	}
	
	public BackendFormat getFormat() {
        return format;
    }
	public void setFormat(BackendFormat format) {
        this.format = format;
    }
	public CellValueType<?> getType() {
		return type;
	}
	public void setType(CellValueType<?> type) {
		this.type = type;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
