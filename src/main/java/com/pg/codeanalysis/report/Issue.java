package com.pg.codeanalysis.report;

import org.w3c.dom.Node;

import com.pg.codeanalysis.utils.XmlFile;

public class Issue {
	private String xpath;
	private String message;
	private Type type;
	private Severity severity;
	private int effortMinutes;
	private String filename;

	public Issue() {
		super();
	}

	public Issue(String xpath, String message) {
		super();
		this.xpath = xpath;
		this.message = message;
	}

	public Issue(Node node, String message) {
		super();
		this.xpath = XmlFile.getXpath(node);
		this.message = message;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public int getEffortMinutes() {
		return effortMinutes;
	}

	public void setEffortMinutes(int effortMinutes) {
		this.effortMinutes = effortMinutes;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
