package com.pg.codeanalysis.checks;

import java.util.List;

import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Node;

import com.pg.codeanalysis.report.Severity;
import com.pg.codeanalysis.report.Type;
import com.pg.codeanalysis.utils.XmlFile;


public class NotHardcodedCheck extends Check {
    
    private final XPathExpression rateLimitByKeyExpression = getXPathExpression("//rate-limit-by-key/@calls|//rate-limit-by-key/@renewal-period");
    private final XPathExpression quotaByKeyExpression = getXPathExpression("//quota-by-key/@calls|//quota-by-key/@renewal-period");
    private final XPathExpression setBackendServiceExpression = getXPathExpression("//set-backend-service/@base-url");
    
    public NotHardcodedCheck() {
		super();
		type = Type.BUG;
		severity = Severity.BLOCKER;
		effortMinutes = 10;
	}
    
    @Override
    public void check(XmlFile file) {
        checkAttributeNotHardcoded(file, rateLimitByKeyExpression, "rate-by-limit");
        checkAttributeNotHardcoded(file, quotaByKeyExpression, "quota-by-key");
        checkAttributeNotHardcoded(file, setBackendServiceExpression, "quota-by-key");
    }   

    public void checkAttributeNotHardcoded(XmlFile file, XPathExpression expression, String tag){
        List<Node> attributes = evaluateAsList(expression, file.getDocument());
        if(attributes==null || attributes.size()==0){
        	reportIssue(file.getDocument().getDocumentElement(), tag+" must be present");
        }else{
            for(Node attributeToCheck: attributes){
                String value = attributeToCheck.getTextContent().trim();
                if(!value.startsWith("{{")){
                	reportIssue(attributeToCheck, "This attribute cannot be a hardcoded value");
                }
            }
        }
    }
}
