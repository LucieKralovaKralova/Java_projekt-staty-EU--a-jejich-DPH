package com.engeto.ifloop;

import java.util.Scanner;

public class Support {
    public static int INVALID_INPUT = 20;

    private static Scanner scanner = null;

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }


    public static int safeReadInt() {

            int result = INVALID_INPUT;
            String inputText = getScanner().nextLine();
            try {
                result = Integer.parseInt(inputText);
            } catch (NumberFormatException ex) {
                System.err.println("Zadal jsi text, který nelze převést na celé číslo nebo jsi nezadal žádné číslo: " + inputText);
            }return result;
        }

}