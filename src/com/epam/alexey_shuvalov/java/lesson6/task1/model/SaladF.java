package com.epam.alexey_shuvalov.java.lesson6.task1.model;

import com.epam.alexey_shuvalov.java.lesson6.task1.FileUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

/**
 * @author Alexey Shuvalov Created on 14.03.2015.
 */
public class SaladF implements CookBook {
    private final String filePath;
    
    public SaladF(String filePath) {
        this.filePath = filePath;
    }
 
    @Override
    public List<CulinaryVegetable> sortVegetablesByCalories() {
        List<CulinaryVegetable> ingredients = FileUtils.deserializeObjectsFromFile(filePath);
        Collections.sort(ingredients, new Comparator<CulinaryVegetable>() {
            @Override
            public int compare(CulinaryVegetable o1, CulinaryVegetable o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (o1 == null) {
                    return 1;
                } else if (o2 == null) {
                    return -1;
                }
                return (int) (o1.getCalories() - o2.getCalories());
            }
        });
        return ingredients;
    }

    @Override
    public double countCalories() {
        List<CulinaryVegetable> ingredients = FileUtils.deserializeObjectsFromFile(filePath);
        double totalCalories = 0;
        for (CulinaryVegetable ingredient : ingredients) {
            if (ingredient != null) {
                totalCalories += ingredient.getCalories();
            }
        }
        return totalCalories;
    }

    @Override
    public List<CulinaryVegetable> findVegetablesByCaloriesRange(double min, double max) {
        double calories;
        List<CulinaryVegetable> ingredients = FileUtils.deserializeObjectsFromFile(filePath);
        List<CulinaryVegetable> foundVegetables = new ArrayList<>();
        for (CulinaryVegetable ingredient : ingredients) {
            calories = ingredient.getCalories();
            if ((calories >= min) && (calories <= max)) {
                foundVegetables.add(ingredient);
            }
        }
        return foundVegetables;
    }

    @Override
    public List<CulinaryVegetable> getIngredients() {
        return FileUtils.deserializeObjectsFromFile(filePath);
    }

}
