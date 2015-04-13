package com.epam.alexey_shuvalov.java.lesson8.task1.parsers;

import com.epam.alexey_shuvalov.java.lesson8.task1.model.CulinaryVegetable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMvParser {
    private String filePath;

    public DOMvParser(String filePath) {
        this.filePath = filePath;
    }

    public List<CulinaryVegetable> getVegetables() throws Exception {
        List<CulinaryVegetable> culinaryVegetables = new ArrayList<>();
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("vegetable");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            CulinaryVegetable culinaryVegetable = new CulinaryVegetable();
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                culinaryVegetable.setVegetableName(eElement.getElementsByTagName("name").item(0).getTextContent());
                culinaryVegetable.setProtein(Double.parseDouble(eElement.getElementsByTagName("protein").item(0).getTextContent()));
                culinaryVegetable.setFat(Double.parseDouble(eElement.getElementsByTagName("fat").item(0).getTextContent()));
                culinaryVegetable.setCarbohydrate(Double.parseDouble(eElement.getElementsByTagName("carbohydrate").item(0).getTextContent()));
                culinaryVegetable.setCalories(Double.parseDouble(eElement.getElementsByTagName("calories").item(0).getTextContent()));
            culinaryVegetables.add(culinaryVegetable);
            }
        }
        return culinaryVegetables;
    }
}