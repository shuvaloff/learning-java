package com.epam.alexey_shuvalov.java.lesson1.task3;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author Alexey Shuvalov
 * Task #3
 * Написать калькулятор – программа, которая на входе получает два целых числа (число А и число В),
 * выполняет арифметическую операцию («+» - сложение, «-» - вычитание, «*» - умножение, «/» - деление)
 * и выводит результат в консоль. Для каждой операции использовать отдельный метод (нельзя использовать
 * методы библиотечного класса Math). Предусмотреть пользовательское меню для выбора необходимой операции.
 * Ввод входных данных осуществлять с клавиатуры.
 */

class CalculatorV {
    public static void main(String[] args){
        CalculatorV app = new CalculatorV();
        app.startApplication();
    }

    void startApplication(){
        boolean startOver = true;
        double result;
        double[] readNumbers;
        System.out.println("Calculator.");
        while (startOver) {
            System.out.println("Please choose an operation by typing the first letter of its name.");
            System.out.println("[A]ddition, [S]ubtraction, [M]ultiplication, [D]ivision");
            System.out.println("You can also type [Q]uit for closing the application.");
            Scanner calcScanner = new Scanner(System.in).useLocale(Locale.getDefault()).useDelimiter("\\n"); // "\\r\\n" 
            /* for the line above: Windows systems use \r\n to terminate lines, 
            while UNIX (and, for some reason, NetBeans IDE output) ones use \n 
            Should keep that in mind if your code doesn't run properly in Eclipse 
            or any other IDE just change pattern to "\\r\\n" and you should be fine
            */
            String input = calcScanner.next();
            if (input.length() != 0) {
                switch (Character.toUpperCase(input.toCharArray()[0])){
                    case 'A':
                        System.out.println("You've selected Addition. Please now enter two numbers.");
                        readNumbers = inputNumbers(calcScanner);
                        result = ElementaryArithmetic.addition(readNumbers[0], readNumbers[1]);
                        System.out.println(readNumbers[0] + " + " + readNumbers[1] + " = " + result);
                        break;
                    case 'S':
                        System.out.println("You've selected Subtraction. Please now enter two numbers.");
                        readNumbers = inputNumbers(calcScanner);
                        result = ElementaryArithmetic.subtraction(readNumbers[0], readNumbers[1]);
                        System.out.println(readNumbers[0] + " - " + readNumbers[1] + " = " + result);
                        break;
                    case 'M':
                        System.out.println("You've selected Multiplication. Please now enter two numbers.");
                        readNumbers = inputNumbers(calcScanner);
                        result = ElementaryArithmetic.multiplication(readNumbers[0], readNumbers[1]);
                        System.out.println(readNumbers[0] + " * " + readNumbers[1] + " = " + result);
                        break;
                    case 'D':
                        System.out.println("You've selected Division. Please now enter two numbers.");
                        System.out.println("Please also note, that it is not possible to divide by zero.");
                        readNumbers = inputNumbers(calcScanner);
                        if (readNumbers[1] == 0) {
                            System.out.println("Cannot divide by zero.");
                        } else {
                            result = ElementaryArithmetic.division(readNumbers[0], readNumbers[1]);
                            System.out.println(readNumbers[0] + " / " + readNumbers[1] + " = " + result);
                        }
                        break;
                    case 'Q':
                        System.out.println("Good Bye!");
                        startOver = false;
                        calcScanner.close();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static double[] inputNumbers(Scanner calcScanner) {
        /**
         * @param calcScanner 
         * @return double[2] 
         */
        double[] readNumbers = new double[2];
        while (!calcScanner.hasNextDouble()) {
            System.out.println("Please input the first number.");
            calcScanner.next();
        }
        readNumbers[0] = calcScanner.nextDouble();
        while (!calcScanner.hasNextDouble()) {
            System.out.println("Please input the second number.");
            calcScanner.next();
        }
        readNumbers[1] = calcScanner.nextDouble();
        return readNumbers;
    }

}
