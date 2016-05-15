package uk.nhs.interoperability.payloads.gui.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.nhs.interoperability.payloads.CodedValue;
import uk.nhs.interoperability.payloads.DomainObjectFactory;
import uk.nhs.interoperability.payloads.FieldType;
import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.metadata.Field;

/**
 * A simple wrapper object to allow us to keep track of the payload being
 * edited, but also the field name in the parent payload that it should be
 * inserted into when saved (or null if this is the top level payload)
 * @author Adam Hatherly
 */
public class SerialisablePayloadAndFieldName implements Serializable {

	private static final long serialVersionUID = 5146322260516427404L;
	private String fieldName;
	private String payloadJson;
	
	public SerialisablePayloadAndFieldName(Payload payload, String parentFieldName) {
		setPayload(payload);
		this.fieldName = parentFieldName;
	}
	
	public Payload getPayload() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectDeserialiser());
		Gson gson = gsonBuilder.create();
		System.out.println("Deserialising JSON: " + this.payloadJson);
		return gson.fromJson(this.payloadJson, Payload.class);
	}
	
	public void setPayload(Payload payload) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Payload.class, new PayloadObjectSerialiser());
		gsonBuilder.registerTypeAdapter(Vocabulary.class, new VocabularySerialiser());
		gsonBuilder.setExclusionStrategies(new PayloadObjectSerialiseExclusions());
		Gson gson = gsonBuilder.create();
		System.out.println("Serialising to JSON: " + payload.toString());
		this.payloadJson = gson.toJson(payload, Payload.class);
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getPayloadJson() {
		return payloadJson;
	}

	public void setPayloadJson(String payloadJson) {
		this.payloadJson = payloadJson;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
