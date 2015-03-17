package com.epam.alexey_shuvalov.java.lesson1.task2;

import java.util.Scanner;

/**
 * @author Alexey Shuvalov
 * Task #2.1
 * Input N strings from the console. Find shortest and longest strings; output them and their length to the console.
 */
class StringShortestAndLongestV {
    public static void main(String[] args){
        StringShortestAndLongestV app = new StringShortestAndLongestV();
        app.startApplication();
    }

    void startApplication(){
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n"); // "\\r\\n" 
        /* for the line above: Windows systems use \r\n to terminate lines, 
        while UNIX (and, for some reason, NetBeans IDE output) ones use \n 
        Should keep that in mind if your code doesn't run properly in Eclipse 
        or any other IDE just change pattern to "\\r\\n" and you should be fine
        */
        System.out.println("Hello! How many strings do you want to type in?");
        while (!scanner.hasNextInt()) { 
            System.out.println("Please input some positive integer in order to proceed.");
            scanner.nextLine();
        }
        int size = scanner.nextInt();
        if (size < 0) {
            System.out.println("You've input a negative value, so we converted it to a positive one for you!");
            size = Math.abs(size);
        }
        String[] strArray = new String[size];
        for(int i = 0; i < strArray.length; i++) {
            System.out.println("Please enter a string: ");
            strArray[i] = scanner.next();
        }
        scanner.close();
        switch (strArray.length) {
            case 0:
                System.out.println("\nLOL, BYE!");
                break;
            case 1:
                System.out.println("The only string present in strArray is " + "\n" + strArray[0] +
                        "\n" + "It has length of " + strArray[0].length() + ".");
                break;
            default:
                for (String aStrArray : strArray) {
                    System.out.println(aStrArray.trim());
                }
                String shortest = strArray[0], longest = strArray[0];
                for (String aStrArray : strArray) {
                    if (shortest.length() > aStrArray.length()) {
                        shortest = aStrArray;
                    } else if (longest.length() < aStrArray.length()) {
                        longest = aStrArray;
                    }
                }
                if (shortest.length() == longest.length()) {
                    System.out.println("Length of the shortest and longest strings is the same: " + shortest.length() + ".");
                } else {
                    System.out.println("Length of the shortest string is " + shortest.length());
                    System.out.println("The shortest string: " + shortest.trim());
                    System.out.println("Length of the longest string is " + longest.length());
                    System.out.println("The longest string: " + longest.trim());
                }
                break;
        }
    }   
}
