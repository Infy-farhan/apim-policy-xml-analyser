package com.pg.codeanalysis.report;

public class Location {
	private String message;
	private String filePath;
	private TextRange textRange;

	public Location() {
		super();
	}

	public Location(String message) {
		super();
		this.message = message;
	}

	public Location(String message, String filePath) {
		super();
		this.message = message;
		this.filePath = filePath;
	}

	public Location(String message, String filePath, TextRange textRange) {
		super();
		this.message = message;
		this.filePath = filePath;
		this.textRange = textRange;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public TextRange getTextRange() {
		return textRange;
	}

	public void setTextRange(TextRange textRange) {
		this.textRange = textRange;
	}

}
