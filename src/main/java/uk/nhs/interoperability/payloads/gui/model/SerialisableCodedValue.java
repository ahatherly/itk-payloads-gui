package uk.nhs.interoperability.payloads.gui.model;

import uk.nhs.interoperability.payloads.CodedValue;

public class SerialisableCodedValue {
	private String code;
	private String displayName;
	private String oid;
	
	public SerialisableCodedValue(CodedValue codedValue) {
		this.code = codedValue.getCode();
		this.displayName = codedValue.getDisplayName();
		this.oid = codedValue.getOID();
	}
	
	public CodedValue getCodedValue() {
		return new CodedValue(this.code, this.displayName, this.oid);
	}
}
