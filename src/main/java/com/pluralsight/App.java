package com.pluralsight;

import java.util.Scanner;

public class App {
    public static Scanner scan = new Scanner(System.in);
    public static String filePath = "src/main/resources/transactions.csv"; // root path of the file your writing to
    public static void main(String[] args) {
        String userInput;
        System.out.println("Welcome to your Account Ledger! \n" + "Choose an option:");


        //loop to keep app running
        while (true) {
            displayHomeScreen();
            userInput = scan.nextLine();

            switch (userInput) {
                case "1":
                    depositMethod();
                    break;
                        /* Validate the deposit amount
                        if (depositAmount <= 0.00) {
                            System.out.println("Deposit amount must be positive.");
                        } */
                case "2":
                    paymentMethod();
                    break;



                case "3":
                    System.out.println("Ledger Menu \n"+"Choose an option: ");
                    displayLedgerScreen();
                    userInput = scan.nextLine();
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
    public static void displayLedgerScreen() {
        System.out.println("1. Display All Entries");
        System.out.println("2. View Deposits");
        System.out.println("3. View Payments");
        System.out.println("4. View Reports ");
        System.out.println("5. Return to Main Menu");;
    }
    public static void depositMethod () {
        System.out.println("Enter deposit amount: ");
        double depositAmount = scan.nextDouble();
        scan.nextLine();

        System.out.println("Enter Description of Deposit: ");
        String depositDescription = scan.nextLine();

        System.out.println("Enter Vendor of Deposit: ");
        String depositVendor = scan.nextLine();

        Transaction deposit = new Transaction(depositDescription,depositVendor,depositAmount);
        deposit.writeToTransaction(filePath);
    }
public static void paymentMethod () {
    System.out.println("Enter Payment Amount: ");
    double paymentAmount = scan.nextDouble();
    scan.nextLine();
    System.out.println("Enter Description of Deposit: ");
    String payDescription = scan.nextLine();
    //make payment method, put it here
    System.out.println("Enter Vendor of Deposit: ");
    String payVendor = scan.nextLine();
    Transaction payment = new Transaction(payDescription,payVendor,paymentAmount);
    payment.writeToTransaction(filePath);
}
}


