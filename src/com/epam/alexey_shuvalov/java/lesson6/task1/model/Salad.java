package com.epam.alexey_shuvalov.java.lesson6.task1.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

/**
 * @author Alexey Shuvalov Created on 14.03.2015.
 */
public class Salad { //implements CookBook {
    private List<CulinaryVegetable> culinaryVegetables = new ArrayList<>();

    public Salad() {
    }

    public Salad(List<CulinaryVegetable> culinaryVegetables) {
        this.culinaryVegetables = culinaryVegetables;
    }

//    @Override
    public List<CulinaryVegetable> sortVegetablesByCalories() {
        List<CulinaryVegetable> culinaryVegetables = this.culinaryVegetables;
        Collections.sort(culinaryVegetables, new Comparator<CulinaryVegetable>() {
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
        return culinaryVegetables;
    }

//    @Override
    public double countCalories() {
        double totalCalories = 0;
        for (CulinaryVegetable culinaryVegetable : culinaryVegetables) {
            if (culinaryVegetable != null) {
                totalCalories += culinaryVegetable.getCalories();
            }
        }
        return totalCalories;
    }

//    @Override
    public List<CulinaryVegetable> findVegetablesByCaloriesRange(double min, double max) {
        double calories;
        List<CulinaryVegetable> foundVegetables = new ArrayList<>();
        for (CulinaryVegetable culinaryVegetable : culinaryVegetables) {
            calories = culinaryVegetable.getCalories();
            if ((calories >= min) && (calories <= max)) {
                foundVegetables.add(culinaryVegetable);
            }
        }
        return foundVegetables;        
    }

//    @Override
    public List<CulinaryVegetable> getIngredients() {
        return culinaryVegetables;
    }

}
