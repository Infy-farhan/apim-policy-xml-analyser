package com.pg.codeanalysis.checks;

import java.util.List;

import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Node;

import com.pg.codeanalysis.report.Severity;
import com.pg.codeanalysis.report.Type;
import com.pg.codeanalysis.utils.XmlFile;

public class BackendCredentialsCheck extends Check {
    
    private final XPathExpression setUrlExpression = getXPathExpression("/policy/inbound//send-request/set-url");
    private final XPathExpression authenticationBasicExpression = getXPathExpression("/policy/inbound//authentication-basic");

    public BackendCredentialsCheck(){
        super();
		type = Type.BUG;
		severity = Severity.BLOCKER;
		effortMinutes = 10;
    }
    @Override
    public void check(XmlFile file) {
        List<Node> setUrlNodes = evaluateAsList(setUrlExpression, file.getDocument());
        if(setUrlNodes==null || setUrlNodes.size()==0) return;
        Node setUrl = setUrlNodes.get(0);
        String setUrlValue = setUrl.getTextContent();
        if(!setUrlValue.contains("vault.azure.net")){
            reportIssue(setUrl, "set-url must contain vault.azure.net");
        }
        List<Node> authenticationBasicNodes = evaluateAsList(authenticationBasicExpression, file.getDocument());
        if(authenticationBasicNodes==null || authenticationBasicNodes.size()==0){
            reportIssue(setUrl, "No authentication-basic found for this");
        }
        Node authenticationBasic = authenticationBasicNodes.get(0);
        String username = authenticationBasic.getAttributes().getNamedItem("username").getNodeValue();
        if(!username.startsWith("{{") && !username.endsWith("}}")){
            reportIssue(authenticationBasic, "username cannot be hardcoded");
        }
        String password = authenticationBasic.getAttributes().getNamedItem("password").getNodeValue();
        if(!password.contains("vault-secret")){
            reportIssue(authenticationBasic, "password must contain vault-secret");
        }
    }   

    
}

