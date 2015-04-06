package com.epam.alexey_shuvalov.java.lesson6.task1;

import com.epam.alexey_shuvalov.java.lesson6.task1.model.*;
import com.epam.alexey_shuvalov.java.lesson6.task1.model.impl.*;
import java.util.Arrays;

/**
 * @author Alexey Shuvalov
 *         Created on 14.03.2015.
 */
public class SaladChef {
    public static void main(String[] args) {
        SaladChef app = new SaladChef();
        app.startApplication();
    }

    public void startApplication() {        
        String fullPath = "D:\\ingredients.ser";
        if (!FileUtils.isFileExist(fullPath)) {
            Cucumber cucumber = new Cucumber();
            Onion onion = new Onion();
            Potato potato = new Potato();
            Tomato tomato = new Tomato();
            FileUtils.serializeObjectsToFile(Arrays.asList(tomato, cucumber, potato, onion), fullPath);            
        }
        CookBook salad = new Salad(FileUtils.deserializeObjectsFromFile(fullPath));
        SaladUtils.printResults(salad, salad.getIngredients(), 30, 75);
    }
}

