package com.pg.codeanalysis.apimpolicyxmlanalyser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pg.codeanalysis.scanner.XmlScanner;


@SpringBootApplication
public class ApimPolicyXmlAnalyserApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ApimPolicyXmlAnalyserApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		String pathToScan;
		if(args==null || args.length==0){
			pathToScan = "G:\\Farhan\\vscode\\apim\\apim-policy-xml-analyser\\src\\main\\resources\\policy.xml";
			//throw new Exception("No path provided to scan.");
		}else{
			pathToScan = args[0];
		}
		XmlScanner scanner = new XmlScanner();
		scanner.scan(pathToScan);
	}

}
