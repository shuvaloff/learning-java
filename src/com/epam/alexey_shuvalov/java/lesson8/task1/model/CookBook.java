package com.epam.alexey_shuvalov.java.lesson8.task1.model;

import java.util.List;

/**
 * @author Alexey Shuvalov
 *         Created on 14.03.2015.
 */
public interface CookBook {
    double countCalories();

    List<CulinaryVegetable> getIngredients();

    List<CulinaryVegetable> sortVegetablesByCalories();

    List<CulinaryVegetable> findVegetablesByCaloriesRange(double min, double max);
}
