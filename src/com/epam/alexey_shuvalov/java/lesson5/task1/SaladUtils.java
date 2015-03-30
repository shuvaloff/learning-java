package com.epam.alexey_shuvalov.java.lesson5.task1;

import com.epam.alexey_shuvalov.java.lesson5.task1.model.CulinaryVegetable;
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

}
