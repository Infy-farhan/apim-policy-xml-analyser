package com.pg.codeanalysis.checks;



import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pg.codeanalysis.report.Issue;
import com.pg.codeanalysis.report.Severity;
import com.pg.codeanalysis.report.Type;
import com.pg.codeanalysis.utils.XmlFile;

public abstract class Check {
	private static final XPathFactory xpathfactory = XPathFactory.newInstance();
	private static final XPath xpath = xpathfactory.newXPath();
	protected Type type;
	protected Severity severity;
	protected int effortMinutes;
	
	private static List<Issue> issues = new ArrayList<>();
	public abstract void check(XmlFile xmlFile);
	
	public XPathExpression getXPathExpression(String expression) {
		try {
			return xpath.compile(expression);
		} catch (XPathExpressionException e) {
			return null;
		}
	}
	public List<Node> evaluateAsList(XPathExpression xpression, Document doc){
		List<Node> list = new ArrayList<>();
		try {
			NodeList nodes = (NodeList)xpression.evaluate(doc, XPathConstants.NODESET);
			for(int i=0; i<nodes.getLength(); i++) {
				list.add(nodes.item(i));
			}
			return list;
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return list;
		}
	}
	
	public void reportIssue(Node node, String message) {
		Issue issue = new Issue(node, message);
		issue.setType(type);
		issue.setSeverity(severity);
		issue.setEffortMinutes(effortMinutes);
		issues.add(issue);
	}

	public List<Issue> getIssues() {
		return issues;
	}
	
}
