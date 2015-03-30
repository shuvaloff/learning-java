package com.epam.alexey_shuvalov.java.lesson4.task1.model;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Alexey Shuvalov Created on 14.03.2015.
 */
public class Salad implements CookBook {

    private final CulinaryVegetable[] culinaryVegetables;

    public Salad() {
        this.culinaryVegetables = new CulinaryVegetable[100];
    }

    public Salad(CulinaryVegetable[] culinaryVegetables) {
        this.culinaryVegetables = culinaryVegetables;
    }

    @Override
    public CulinaryVegetable[] sortVegetablesByCalories() {
        CulinaryVegetable[] culinaryVegetables = this.culinaryVegetables.clone();
        Arrays.sort(culinaryVegetables, new Comparator<CulinaryVegetable>() {
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

    @Override
    public double countCalories() {
        double totalCalories = 0;
        for (CulinaryVegetable culinaryVegetable : culinaryVegetables) {
            if (culinaryVegetable != null) {
                totalCalories += culinaryVegetable.getCalories();
            }
        }
        return totalCalories;
    }

    @Override
    public CulinaryVegetable[] findVegetablesByCaloriesRange(double min, double max) throws AnInvalidValueException {
        if (min < 0) {
            throw new AnInvalidValueException("Min < 0");
        }
        if (max < 0) {
            throw new AnInvalidValueException("Max < 0");
        }
        if (min > max) {
            throw new AnInvalidValueException("Min > Max");
        }
        CulinaryVegetable[] foundVegetables = new CulinaryVegetable[culinaryVegetables.length];
        for (int i = 0; i < culinaryVegetables.length; i++) {
            if (culinaryVegetables[i] != null) {
                if ((culinaryVegetables[i].getCalories() >= min) && (culinaryVegetables[i].getCalories() <= max)) {
                    foundVegetables[i] = culinaryVegetables[i];
                }
            }
        }
        return foundVegetables;
    }

    @Override
    public CulinaryVegetable[] getIngredients() {
        return culinaryVegetables;
    }

    public void addIngredient(CulinaryVegetable culinaryVegetable) {
        /**
         * @param culinaryVegetable vegetable to be added
         *
         */
        culinaryVegetables[getCurrentPosition()] = culinaryVegetable;
    }

    public int getCurrentPosition() {
        for (int i = 0; i < culinaryVegetables.length; i++) {
            if (culinaryVegetables[i] == null) {
                return i;
            }
        }
        return 0;
    }
    
    public class AnInvalidValueException extends Exception {
        public AnInvalidValueException(String exceptionMessage) {
            super(exceptionMessage);
        }
    }
}
