package com.epam.alexey_shuvalov.java.lesson1.task1;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Alexey Shuvalov
 * Task #1.1
 * In an integer array swap negative maximum element with positive minimum (there is no info about duplicates).
 **/

class ArrayNegativeMaxVsPositiveMinV {
    public static void main(String[] args) {
        ArrayNegativeMaxVsPositiveMinV app = new ArrayNegativeMaxVsPositiveMinV();
        app.startApplication();
    }

    private final static int ARRAY_SIZE = 20;

    void startApplication(){
        int[] intArray = new int[ARRAY_SIZE];
        Random random = new Random();
        int min = -10, max = 10;
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(max - min + 1) + min;
        }
        System.out.println("intArray contains the following elements:");
        System.out.print(Arrays.toString(intArray));
        int negativeMax = -1, positiveMin = -1;
        for (int index = 0; index < intArray.length; index++) {
            if (intArray[index] < 0) {
                if (negativeMax < 0 || intArray[negativeMax] < intArray[index]) {
                    negativeMax = index;
                }
            } else {
                if (positiveMin < 0 || intArray[positiveMin] > intArray[index]) {
                    positiveMin = index;
                }
            }
        }
        if (negativeMax == -1) {
            System.out.print("\nintArray doesn't contain negative elements.");
        }
        if (positiveMin == -1) {
            System.out.print("\nintArray doesn't contain positive elements.");
        }
        if (negativeMax == -1 || positiveMin == -1) {
            System.out.print("\nThere are no either negative or positive elements in this array, thus it won't be modified.");
        } else {
            System.out.print("\nPositive minimum is " + intArray[positiveMin] + ".");
            System.out.print("\nNegative maximum is " + intArray[negativeMax] + ".");
            System.out.print("\nSwapping...");
            int swap = intArray[positiveMin];
            intArray[positiveMin] = intArray[negativeMax];
            intArray[negativeMax] = swap;
        }
        System.out.println("\nintArray (after swapping negative min with positive max) contains the following elements:");
        System.out.println(Arrays.toString(intArray));
    }
}

