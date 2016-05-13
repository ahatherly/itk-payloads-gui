package uk.nhs.interoperability.payloads.gui.model;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import uk.nhs.interoperability.payloads.Payload;
import uk.nhs.interoperability.payloads.toc_edischarge_draftB.ClinicalDocument;

public class PayloadInstanceCreator implements InstanceCreator<Payload> {
	public Payload createInstance(Type type) {
	    return new ClinicalDocument();
	}
}
