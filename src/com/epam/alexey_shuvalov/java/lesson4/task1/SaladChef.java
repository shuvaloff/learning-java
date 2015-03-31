package com.epam.alexey_shuvalov.java.lesson4.task1;

import com.epam.alexey_shuvalov.java.lesson4.task1.model.*;
import com.epam.alexey_shuvalov.java.lesson4.task1.model.impl.*;

/**
 * @author Alexey Shuvalov
 *         Created on 14.03.2015.
 */
public class SaladChef {
    public static void main(String[] args) throws CookBookException {
        SaladChef app = new SaladChef();
        app.startApplication();
    }

    public void startApplication() throws CookBookException {
        Cucumber cucumber = new Cucumber();
        Onion onion = new Onion();
        Potato potato = new Potato();
        Tomato tomato = new Tomato();     
        
        CookBook salad = new Salad(new CulinaryVegetable[]{cucumber, onion, potato, tomato});
        
        System.out.println("There are following vegetables in this salad: \n");
        SaladUtils.showIngredients(salad.getIngredients());
        System.out.println("\nTotal Calories in this salad: " + salad.countCalories() + " kcal.");
        System.out.println("\nList of the vegetables sorted by Calories (ascending order): \n");
        SaladUtils.showIngredients(salad.sortVegetablesByCalories());
        System.out.println("\nThis salad contains the following vegetables (within 30..-75 Calories range):\n");
        try {
            SaladUtils.showIngredients(salad.findVegetablesByCaloriesRange(30, -75));
        } catch (CookBookException ex) {
            System.out.println(ex.getMessage());
        }

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
        System.out.println("\nThis salad contains the following vegetables (within -30..75 Calories range):\n");
        SaladUtils.showIngredients(newSalad.findVegetablesByCaloriesRange(-30, 75));
    } 
    
}
