package com.epam.alexey_shuvalov.java.lesson8.task1;

import com.epam.alexey_shuvalov.java.lesson8.task1.model.CookBook;
import com.epam.alexey_shuvalov.java.lesson8.task1.model.CulinaryVegetable;
import java.util.List;

/**
 * @author Alexey Shuvalov
 */
public class SaladUtils {

    public static void showIngredients(List<CulinaryVegetable> culinaryVegetables) {
        for (CulinaryVegetable aCulinaryVegetable : culinaryVegetables) {
            if (aCulinaryVegetable != null) System.out.println(aCulinaryVegetable);
        }
    }
    public static void printResults(CookBook salad, List<CulinaryVegetable> culinaryVegetables, double min, double max) {
        System.out.println("\nThere are following vegetables in this salad: \n");
        showIngredients(culinaryVegetables);
        System.out.println("\nTotal Calories in this salad: " + salad.countCalories() + " kcal.");
        System.out.println("\nList of the vegetables sorted by Calories (ascending order): \n");
        showIngredients(salad.sortVegetablesByCalories());
        System.out.println("\nThis salad contains the following vegetables (within " + min + ".." + max + " Calories range):\n");
        showIngredients(salad.findVegetablesByCaloriesRange(min, max));
    }
}
