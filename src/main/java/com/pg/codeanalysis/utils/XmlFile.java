package com.pg.codeanalysis.utils;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlFile {
	String content;
	Document document;

	public XmlFile(Path filePath) throws IOException, ParserConfigurationException, SAXException {
		content = Files.readString(filePath);
		init();
	}

	public XmlFile(String content) throws ParserConfigurationException, SAXException, IOException {
		this.content = content;
		init();
	}

	private void init() throws ParserConfigurationException, SAXException, IOException {
		document = createXmlDocument(content);
	}

	private Document createXmlDocument(String xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		return doc;
	}

	public static String readFile(String path) throws IOException {
		Path filePath = Path.of(path);
		return Files.readString(filePath);
	}
	
	public static String getXpath(Node node){
		if(node==null) return "";
		if(node.getNodeType()==Node.ELEMENT_NODE) {
			Node prev = node.getPreviousSibling();
			int ctr=0;
			while(prev!=null) {
				if(prev.getNodeName().equals(node.getNodeName())) ctr++;
				else break;
			}
			return getXpath(node.getParentNode())+"/"+node.getNodeName()+(ctr>0? "["+ctr+"]":"");
		}else if(node.getNodeType()==Node.ATTRIBUTE_NODE) {
			return getXpath(((Attr) node).getOwnerElement())+"/@"+node.getNodeName();
		}
		return "";
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
}
