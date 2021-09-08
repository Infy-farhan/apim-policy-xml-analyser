package com.pg.codeanalysis.report;

public class TextRange {
	private int startLine;
	private int endLine;
	private int startColumn;
	private int endColumn;

	public TextRange() {
		super();
	}

	public TextRange(int startLine, int endLine) {
		super();
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public TextRange(int startLine, int endLine, int startColumn, int endColumn) {
		super();
		this.startLine = startLine;
		this.endLine = endLine;
		this.startColumn = startColumn;
		this.endColumn = endColumn;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public int getStartColumn() {
		return startColumn;
	}

	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
	}

}
