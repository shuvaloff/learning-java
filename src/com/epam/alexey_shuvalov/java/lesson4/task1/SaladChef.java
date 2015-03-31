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

        try {
            SaladUtils.printResults(salad, salad.getIngredients(), 30, -75);    
        } catch (CookBookException ex) {
            System.out.println(ex.getMessage());
        }
        
        Salad newSalad = new Salad();
        /* showing an example with null-checks implemented */
        newSalad.addIngredient(tomato);
        newSalad.addIngredient(onion);
        newSalad.addIngredient(potato);
        newSalad.addIngredient(cucumber);
        
        SaladUtils.printResults(newSalad, newSalad.getIngredients(), -30, 75);
    }   
}
