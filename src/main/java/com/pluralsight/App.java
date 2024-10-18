package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

                case "2":
                    paymentMethod();
                    break;

                case "3":
                    System.out.println("Ledger Menu \n"+"Choose an option: ");
                    displayLedgerScreen();
                    int ledgerSelection = scan.nextInt();
                        if (ledgerSelection == 1) {
                            displayAllEntries();
                        }
                        if (ledgerSelection == 2) {
                            readTransactionsFile();
                        }
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
    Transaction payment = new Transaction(payDescription,payVendor,-paymentAmount);
    payment.writeToTransaction(filePath);
}
public static void readTransactionsFile () {
    try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String input;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        int count = 0;

        while ((input = bufferedReader.readLine()) != null) {
            if (input.startsWith("date")) {
                continue;
            }
            if (input.trim().isEmpty()) {
                continue;
            }
            String[] tokens = input.split("\\|");
            String date = tokens[0];
            String time = tokens[1];
            String description = tokens[2];
            String vendor = tokens[3];
            double amount = Double.parseDouble(tokens[4]);

            if (amount > 0)
                transactions.add(new Transaction(tokens[2], tokens[3], Double.parseDouble(tokens[4])));
            }
             bufferedReader.close();
        System.out.println("Deposits: ");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

    } catch (IOException e) {
        // display stack trace if there was an error
        e.printStackTrace();

    }
}
public static void displayAllEntries () {
    try {
        FileReader fileReader = new FileReader(filePath);

        BufferedReader bufReader = new BufferedReader(fileReader);
        String input;

        while ((input = bufReader.readLine()) != null) {

            System.out.println(input);
        }

        // close the stream and release the resources
        bufReader.close();
    } catch (IOException e) {
        // display stack trace if there was an error
        e.printStackTrace();
    }
}
public static void displayAllDeposits () {

}
}


