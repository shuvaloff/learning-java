package com.epam.alexey_shuvalov.java.lesson2.task1;

import com.epam.alexey_shuvalov.java.lesson2.task1.model.CulinaryVegetable;

/**
 * @author Alexey Shuvalov
 */
public class SaladUtils {

    public static void showIngredients(CulinaryVegetable[] culinaryVegetables) {
        for (CulinaryVegetable aCulinaryVegetable : culinaryVegetables) {
            if (aCulinaryVegetable != null) System.out.println(aCulinaryVegetable);
        }
    }

}
