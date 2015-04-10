package com.epam.alexey_shuvalov.java.lesson8.task1;

import com.epam.alexey_shuvalov.java.lesson8.task1.model.*;
import com.epam.alexey_shuvalov.java.lesson8.task1.parsers.DOMvParser;
import com.epam.alexey_shuvalov.java.lesson8.task1.parsers.SAXvParser;

/**
 * @author Alexey Shuvalov
 *         Created on 11.04.2015.
 */
public class SaladChef {
    public static void main(String[] args) {
        SaladChef app = new SaladChef();
        try {
            app.startApplication();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startApplication() throws Exception {
        String filePath = ".\\src\\com\\epam\\alexey_shuvalov\\java\\lesson8\\task1\\vegetables.xml";

        SAXvParser saxParser = new SAXvParser(filePath);
        CookBook salad = new Salad(saxParser.getVegetables());
        SaladUtils.printResults(salad, salad.getIngredients(), 30, 75);

        DOMvParser domParser = new DOMvParser(filePath);
        CookBook salado = new Salad(domParser.getVegetables());
        SaladUtils.printResults(salado, salado.getIngredients(), 30, 75);

    }
}

