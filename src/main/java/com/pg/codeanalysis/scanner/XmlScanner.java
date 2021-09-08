package com.pg.codeanalysis.scanner;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.codeanalysis.checks.Check;
import com.pg.codeanalysis.checks.CheckList;
import com.pg.codeanalysis.report.Issue;
import com.pg.codeanalysis.utils.XmlFile;

public class XmlScanner {
    public void scan(String pathToScan) throws IOException, ParserConfigurationException, SAXException {
        List<Issue> issues = new ArrayList<>();
        File baseFile = new File(pathToScan);
        if (baseFile.isDirectory()) {
            File[] files = baseFile.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".xml");
                }
            });
            for (File file : files) {
                issues.addAll(analyse(file));
            }
        } else {
            issues.addAll(analyse(baseFile));
        }
        exportIssues(issues, pathToScan);
    }

    public List<Issue> analyse(File file) throws IOException, ParserConfigurationException, SAXException {
        List<Issue> issues = new ArrayList<>();
        XmlFile xml = new XmlFile(file.toPath());
        for (Check check : CheckList.getAllChecks()) {
            check.check(xml);
            issues.addAll(check.getIssues());
        }
        issues.forEach(x -> x.setFilename(file.getName()));
        return issues;
    }

    public void exportIssues(List<Issue> list, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String report = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        File givenFile = new File(path);
        if (givenFile.isDirectory()) {
            Files.writeString(Paths.get(path + "/report.json"), report);
        } else {
            String writeToPath = givenFile.getParent() + "/report.json";
            Files.writeString(Paths.get(writeToPath), report);
        }
    }
}
