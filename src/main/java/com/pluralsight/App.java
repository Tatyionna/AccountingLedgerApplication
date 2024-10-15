package com.pluralsight;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput;

        System.out.println("Welcome to your Account Ledger! \n" + "Choose an option:");
        while (true) {
            // Display options to the user
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment");
            System.out.println("3. Ledger");
            System.out.print("4. Exit ");
            userInput = scan.nextLine();
        }

    }
}
