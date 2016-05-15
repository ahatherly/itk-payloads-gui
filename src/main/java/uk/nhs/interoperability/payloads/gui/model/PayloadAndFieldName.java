package uk.nhs.interoperability.payloads.gui.model;

import uk.nhs.interoperability.payloads.Payload;

/**
 * A simple wrapper object to allow us to keep track of the payload being
 * edited, but also the field name in the parent payload that it should be
 * inserted into when saved (or null if this is the top level payload)
 * @author Adam Hatherly
 */
public class PayloadAndFieldName {
	private Payload payload;
	private String fieldName;
	
	public PayloadAndFieldName(Payload payload, String fieldName) {
		super();
		this.payload = payload;
		this.fieldName = fieldName;
	}
	
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
