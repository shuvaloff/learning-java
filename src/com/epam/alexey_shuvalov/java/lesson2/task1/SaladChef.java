package com.epam.alexey_shuvalov.java.lesson2.task1;

import com.epam.alexey_shuvalov.java.lesson2.task1.model.*;
import com.epam.alexey_shuvalov.java.lesson2.task1.model.impl.*;

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

        CulinaryVegetable[] ingredients = new CulinaryVegetable[4];        
        ingredients[0] = cucumber;
        ingredients[1] = onion;
        ingredients[2] = potato;
        ingredients[3] = tomato;

        CookBook salad = new Salad(ingredients);

        System.out.println("There are following vegetables in this salad: \n");
        SaladUtils.showIngredients(salad.getIngredients());
        System.out.println("\nTotal Calories in this salad: " + salad.countCalories() + " kcal.");
        System.out.println("\nList of the vegetables sorted by Calories (ascending order): \n");
        SaladUtils.showIngredients(salad.sortVegetablesByCalories());
        System.out.println("\nThis salad contains the following vegetables (within 30..75 Calories range):\n");
        SaladUtils.showIngredients(salad.findVegetablesByCaloriesRange(30, 75));

        Salad newSalad = new Salad();
        /* showing an example with null-checks implemented */
        CulinaryVegetable[] ingredientsForNewSalad = newSalad.getIngredients();
        newSalad.addIngredient(tomato);
        newSalad.addIngredient(onion);
        newSalad.addIngredient(potato);
        newSalad.addIngredient(cucumber);
        
        System.out.println("\nThere are following vegetables in this salad: \n");
        SaladUtils.showIngredients(ingredientsForNewSalad);
        System.out.println("\nTotal Calories in this salad: " + newSalad.countCalories() + " kcal.");
        System.out.println("\nList of the vegetables sorted by Calories (ascending order): \n");
        SaladUtils.showIngredients(newSalad.sortVegetablesByCalories());
        System.out.println("\nThis salad contains the following vegetables (within 30..75 Calories range):\n");
        SaladUtils.showIngredients(newSalad.findVegetablesByCaloriesRange(30, 75));
    }
}
