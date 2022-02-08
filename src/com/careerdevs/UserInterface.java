package com.careerdevs;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    private static Scanner scanner = new Scanner(System.in);  // only used in this class

    public static String readString(String question) {
        while (true) {
            System.out.println(question + "\nInput: ");
            String inputString = scanner.nextLine();

            if (!inputString.trim().equals("")) { // if it is not an empty string return it
                return inputString.trim();
            }
            System.out.println("You must enter something");
        }
    }
//    public static int readInt(String question) {
//        System.out.println(question + "\nNumber: ");
//        while (!scanner.hasNextInt()) {
//            System.out.println("Please enter a valid number!");
//            scanner.nextLine();
//        }
//        return scanner.nextInt();
//    }

    public static int readInt(String question, int min, int max) {
        //recursion
        try {
            System.out.print(question + "\nSelection:  ");
            System.out.println("Number (" + min + " - " + max + "): ");
            int userInput = scanner.nextInt(); // exception risk
            if (userInput >= min && userInput <= max) {
                scanner.nextLine();
                return userInput;
            }
            System.out.println("Number outside of the valid range, try again");
            return readInt(question, min, max);

        } catch (InputMismatchException exception) {  // wasn't an int
            System.out.println("Invalid data type");
            scanner.nextLine();
            return readInt(question, min, max);

        } catch (Exception exception) {  // general error
            System.out.println(exception.getClass());
            scanner.nextLine();
            return readInt(question, min, max);
        }
    }

    public static boolean yerOrNo(String question) {  // uses => while loop break
        while (true) {
            System.out.println("\n" + question + "\ny/n");
            String rawInputString = scanner.nextLine();
            char cleanInput = rawInputString.toLowerCase(Locale.ROOT).trim().charAt(0);

            if (cleanInput == 'y') {
                return true;
            } else if (cleanInput == 'n') {
                return false;
            }
            System.out.println("Input must be Y or N");
        }
    }
}