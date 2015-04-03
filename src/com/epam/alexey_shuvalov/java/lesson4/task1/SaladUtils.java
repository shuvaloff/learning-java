package com.epam.alexey_shuvalov.java.lesson4.task1;

import com.epam.alexey_shuvalov.java.lesson4.task1.model.CookBook;
import com.epam.alexey_shuvalov.java.lesson4.task1.model.CookBookException;
import com.epam.alexey_shuvalov.java.lesson4.task1.model.CulinaryVegetable;

/**
 * @author Alexey Shuvalov
 */
public class SaladUtils {

    public static void showIngredients(CulinaryVegetable[] culinaryVegetables) {
        for (CulinaryVegetable aCulinaryVegetable : culinaryVegetables) {
            if (aCulinaryVegetable != null) System.out.println(aCulinaryVegetable);
        }
    }
    
    public static void printResults(CookBook salad, CulinaryVegetable[] culinaryVegetables, double min, double max) throws CookBookException {
        System.out.println("\nThere are following vegetables in this salad: \n");
        showIngredients(culinaryVegetables);
        System.out.println("\nTotal Calories in this salad: " + salad.countCalories() + " kcal.");
        System.out.println("\nList of the vegetables sorted by Calories (ascending order): \n");
        showIngredients(salad.sortVegetablesByCalories());
        System.out.println("\nThis salad contains the following vegetables (within " + min + ".." + max + " Calories range):\n");
        showIngredients(salad.findVegetablesByCaloriesRange(min, max));
    }
}
