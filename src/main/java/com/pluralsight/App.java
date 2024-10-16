package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput;
        String filePath = "src/main/resources/transactions.csv"; // Specify the file to write to
        System.out.println("Welcome to your Account Ledger! \n" + "Choose an option:");


        //loop to keep app running
        while (true) {
            displayHomeScreen();
            userInput = scan.nextLine();

            switch (userInput) {
                case "1":
                        System.out.println("Enter deposit amount: ");
                        double depositAmount = scan.nextDouble();
                        scan.nextLine();

                        System.out.println("Enter Description of Deposit: ");
                        String depdescription = scan.nextLine();

                        System.out.println("Enter Vendor of Deposit: ");
                        String depvendor = scan.nextLine();

                        Deposit deposit = new Deposit(depdescription,depvendor,depositAmount);
                        deposit.writeToFile(filePath);
                        break;



                        /* Validate the deposit amount
                        if (depositAmount <= 0.00) {
                            System.out.println("Deposit amount must be positive.");
                        } */
                case "2":
                    System.out.println("Enter Payment Amount: ");
                    double paymentAmount = scan.nextDouble();
                    scan.nextLine();


                    System.out.println("Enter Description of Deposit: ");
                    String paydescription = scan.nextLine();
                        //make payment method, put it here
                    System.out.println("Enter Vendor of Deposit: ");
                    String payvendor = scan.nextLine();

                    break;



                case "3":
                        System.out.println("Navigate to Ledger");
                        //make Ledger and go here
                        break;


                case "4":
                        System.out.println("Exiting the application.");


                        }

        }

    }
    public static void displayHomeScreen() {
        System.out.println("1. Add Deposit");
        System.out.println("2. Make Payment");
        System.out.println("3. Ledger");
        System.out.println("4. Exit ");;
    }
}


