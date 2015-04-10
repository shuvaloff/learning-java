package com.epam.alexey_shuvalov.java.lesson8.task1.parsers;

import com.epam.alexey_shuvalov.java.lesson8.task1.model.CulinaryVegetable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Shuvalov
 *         Created on 10.04.2015.
 */
public class SAXHandler extends DefaultHandler {
    public List<CulinaryVegetable> culinaryVegetables = new ArrayList<>();
    private CulinaryVegetable culinaryVegetable = null;
    private String value = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "vegetable":
                culinaryVegetable = new CulinaryVegetable();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "vegetable":
                culinaryVegetables.add(culinaryVegetable);
                break;
            case "name":
                culinaryVegetable.setVegetableName(value);
                break;
            case "protein":
                culinaryVegetable.setProtein(Double.parseDouble(value));
                break;
            case "fat":
                culinaryVegetable.setFat(Double.parseDouble(value));
                break;
            case "carbohydrate":
                culinaryVegetable.setCarbohydrate(Double.parseDouble(value));
                break;
            case "calories":
                culinaryVegetable.setCalories(Double.parseDouble(value));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = String.copyValueOf(ch, start, length).trim();
    }

}
