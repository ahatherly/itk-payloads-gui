package uk.nhs.interoperability.payloads.gui.model;

import java.util.ArrayList;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.metadata.Field;

public class PayloadObject {
	private ArrayList<Field> fields = null;
	private Payload payload = null;
	private String name = null;
	private String packg = null;
	private String versionedName = null;
	
	public PayloadObject(Payload payload) {
		setPayload(payload);
	}

	public ArrayList<Field> getFields() {
		return fields;
	}
	
	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
		
		this.name = payload.getClassName();
		this.packg = payload.getPackage();
		this.versionedName = payload.getVersionedName();
		
		if (this.fields == null) {
			this.fields = new ArrayList<Field>();
		} else {
			this.fields.clear();
		}
		
		for (String name : payload.getFieldDefinitions().keySet()) {
			Field f = payload.getFieldDefinitions().get(name);
			
			// Don't include the fixed fields
			switch(f.getTypeEnum()) {
			case Fixed:
				break;
			default:
				this.fields.add(f);
				break;
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getPackg() {
		return packg;
	}

	public String getVersionedName() {
		return versionedName;
	}
}
