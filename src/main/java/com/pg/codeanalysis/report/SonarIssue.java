package com.pg.codeanalysis.report;

public class SonarIssue{
	private String engineId;
	private String ruleId;
	private Location primaryLocation;
	private Type type;
	private Severity severity;
	private int effortMinutes;
	//private Location[] secondaryLocations;

	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Location getPrimaryLocation() {
		return primaryLocation;
	}

	public void setPrimaryLocation(Location primaryLocation) {
		this.primaryLocation = primaryLocation;
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

//	public Location[] getSecondaryLocations() {
//		return secondaryLocations;
//	}
//
//	public void setSecondaryLocations(Location[] secondaryLocations) {
//		this.secondaryLocations = secondaryLocations;
//	}

}
