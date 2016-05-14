package uk.nhs.interoperability.payloads.gui.model;

import javax.xml.xpath.XPathExpression;

import uk.nhs.interoperability.payloads.metadata.Field;

public class FieldDecorator extends Field {
	
	/*private String name;
	private String xpath;
	private String description;
	private boolean mandatory;
	private boolean addAtStart = false;
	private String vocabulary;
	private String type;
	private String typePackage;
	private int maxOccurs = 1;
	private String format;
	private String fixed;
	private String ifExists;
	private String ifNotExists;
	private String ifValueField;
	private String ifValue;
	private XPathExpression compiledXpath;
	private boolean isDuplicateXPath = false;
	private String dependentFixedField;
	private String localCodeOID;
	private String deriveOIDFrom;
	private String deriveValueFromTemplateNameUsedInField;
	private String versionedIdentifierXPath;
	private String versionedIdentifierField;
	private boolean suppressCodeSystem = false;
	private boolean suppressDisplayName = false;
	private String expectedExpressionCode = null;
	private String expectedExpressionDisplayName = null;
	*/
	private Vocabulary vocabValues = null;
	
	public FieldDecorator(Field f) {
		/*this.setType(f.getTypeEnum().name());
		this.setTypePackage(f.getTypePackage());
		this.setName(f.getName());
		this.setXpath(f.getXpath());
		this.setDescription(f.getDescription());
		this.setMandatory(f.isMandatory());
		this.setAddAtStart(f.isAddAtStart());
		this.setVocabulary(f.getVocabulary());
		this.setTypePackage(f.getTypePackage());
		this.setMaxOccurs(f.getMaxOccurs());
		this.setFormat(f.getFormat());
		this.setFixed(f.getFixed());
		this.setIfExists(f.getIfExists());
		this.setIfNotExists(f.getIfNotExists());
		this.setIfValueField(f.getIfValueField());
		this.setIfValue(f.getIfValue());
		this.setLocalCodeOID(f.getLocalCodeOID());
		this.setDeriveOIDFrom(f.getDeriveOIDFrom());
		this.setDeriveValueFromTemplateNameUsedInField(f.getDeriveValueFromTemplateNameUsedInField());
		this.setVersionedIdentifierXPath(f.getVersionedIdentifierXPath());
		this.setVersionedIdentifierField(f.getVersionedIdentifierField());
		this.setSuppressCodeSystem(f.isSuppressCodeSystem());
		this.setSuppressDisplayName(f.isSuppressDisplayName());
		this.setExpectedExpressionCode(f.getExpectedExpressionCode());
		this.setExpectedExpressionDisplayName(f.getExpectedExpressionDisplayName());*/
		super(f.getName(),
				makeNullEmpty(f.getXpath()),
				makeNullEmpty(f.getDescription()),
				f.isMandatory()?"true":"false",
				f.isAddAtStart()?"true":"false",
				makeNullEmpty(f.getVocabulary()),
				makeNullEmpty(f.getTypeName()),
				makeNullEmpty(f.getTypePackage()),
				String.valueOf(f.getMaxOccurs()),
				makeNullEmpty(f.getLocalCodeOID()),
				makeNullEmpty(f.getDeriveOIDFrom()),
				makeNullEmpty(f.getVersionedIdentifierXPath()),
				makeNullEmpty(f.getVersionedIdentifierField()),
				f.isSuppressCodeSystem()?"true":"false",
				f.isSuppressDisplayName()?"true":"false",
				makeNullEmpty(f.getExpectedExpressionCode()),
				makeNullEmpty(f.getExpectedExpressionDisplayName()));
	}
	
	private static String makeNullEmpty(String val) {
		if (val == null) return "";
		return val;
	}

	public Vocabulary getVocabValues() {
		return vocabValues;
	}

	public void setVocabValues(Vocabulary vocabValues) {
		this.vocabValues = vocabValues;
	}
}
