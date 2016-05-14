package uk.nhs.interoperability.payloads.gui.model;

import java.util.Map;

import uk.nhs.interoperability.payloads.AbstractPayload;
import uk.nhs.interoperability.payloads.metadata.Field;
import uk.nhs.interoperability.payloads.util.xml.XMLNamespaceContext;

public class DummyPayload extends AbstractPayload {

	private Map<String, Field> fieldDefinitions;
	private String className;
	private String packg;
	private String versionedName;
	private XMLNamespaceContext namespaceContext;
	private String rootNode;
	
	public String serialise() {
		return super.serialise(this);
	}

	public Map<String, Field> getFieldDefinitions() {
		return this.fieldDefinitions;
	}

	public String getClassName() {
		return this.className;
	}

	public String getPackage() {
		return this.packg;
	}

	public String getVersionedName() {
		return this.versionedName;
	}

	public XMLNamespaceContext getNamespaceContext() {
		return this.namespaceContext;
	}

	public String getRootNode() {
		return this.rootNode;
	}

}
