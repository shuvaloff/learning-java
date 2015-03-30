package com.epam.alexey_shuvalov.java.lesson4.task1.model;

/**
 * @author Alexey Shuvalov
 *         Created on 14.03.2015.
 */
public interface CookBook {
    double countCalories();

    CulinaryVegetable[] getIngredients();

    CulinaryVegetable[] sortVegetablesByCalories();

    CulinaryVegetable[] findVegetablesByCaloriesRange(double min, double max) 
            throws Salad.AnInvalidValueException;
}
