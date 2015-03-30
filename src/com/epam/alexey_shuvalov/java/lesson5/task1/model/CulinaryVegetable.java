package com.epam.alexey_shuvalov.java.lesson5.task1.model;

/**
 * @author Alexey Shuvalov
 *         Created on 14.03.2015.
 */
public class CulinaryVegetable {
    private final static int HUNDRED_OF_GRAMS = 100;

    private String vegetableName;
    private double fat;
    private double protein;
    private double carbohydrate;
    private double calories;

    public CulinaryVegetable() {

    }

    public CulinaryVegetable(String vegetableName, double calories) {
        this.vegetableName = vegetableName;
        this.calories = calories;
    }

    public CulinaryVegetable(String vegetableName, double fat, double protein, double carbohydrate, double calories) {
        this.vegetableName = vegetableName;
        this.fat = fat;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.calories = calories;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return vegetableName +
                ", fat = " + fat +
                ", protein = " + protein +
                ", carbohydrate = " + carbohydrate +
                ", calories = " + calories;
    }
}
