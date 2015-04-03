package com.epam.alexey_shuvalov.java.lesson5.task1;

import com.epam.alexey_shuvalov.java.lesson5.task1.model.*;
import com.epam.alexey_shuvalov.java.lesson5.task1.model.impl.*;
import java.util.Arrays;
import java.util.List;

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
        Cucumber cucumber = new Cucumber();
        Onion onion = new Onion();
        Potato potato = new Potato();
        Tomato tomato = new Tomato();

        List<CulinaryVegetable> ingredients = Arrays.asList(cucumber, onion, potato, tomato);
        
        CookBook salad = new Salad(ingredients);

        SaladUtils.printResults(salad, salad.getIngredients(), 30, 75);
    }
}
