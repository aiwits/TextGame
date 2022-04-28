package org.xl.framework.build.factory;

public class PropertyDefinitionValue {
	private String attrRef;
	private String textRef;

	public PropertyDefinitionValue(String attrRef, String textRef) {
		this.attrRef = attrRef;
		this.textRef = textRef;
	}

	public String getAttrRef() {
		return attrRef;
	}

	public void setAttrRef(String attrRef) {
		this.attrRef = attrRef;
	}

	public String getTextRef() {
		return textRef;
	}

	public void setTextRef(String textRef) {
		this.textRef = textRef;
	}
}
