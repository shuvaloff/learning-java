package com.epam.alexey_shuvalov.java.lesson8.task1.parsers;

import com.epam.alexey_shuvalov.java.lesson8.task1.model.CulinaryVegetable;
import java.io.FileInputStream;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXvParser {
    private String filePath;

    public SAXvParser(String filePath) {
        this.filePath = filePath;
    }

    public List<CulinaryVegetable> getVegetables() throws Exception {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(new FileInputStream(filePath), handler);
        return handler.culinaryVegetables;
    }
}