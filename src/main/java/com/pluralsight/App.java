package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
                    System.out.println("""

                            Ledger Menu\s
                            Choose an option:\s""");
                    displayLedgerScreen();
                    int ledgerInput = scan.nextInt();
                    scan.nextLine(); // Clear the newline character from the buffer
                    switch (ledgerInput) {

                        case 1:
                            readTransactionsFile("All");
                            break;
                        case 2:
                            readTransactionsFile("Deposit");
                            break;
                        case 3:
                            readTransactionsFile("Payment");
                            break;
                        case 4:
                            //reports
                            break;
                        case 5:
                            //return Home
                            break;
                    }
                            break;

                case "4":
                    System.out.println("Exiting the application.");
                    scan.close(); // Close the scanner before exiting
                    return; // Exit the loop
                default:
                    System.out.println("Invalid option. Please try again.");


            }

        }

    }

    public static void displayHomeScreen() {
        System.out.println("\n1.) Add Deposit");
        System.out.println("2.) Make Payment");
        System.out.println("3.) Ledger");
        System.out.println("4.) Exit ");
        ;
    }

    public static void displayLedgerScreen() {
        System.out.println("1. Display All Entries");
        System.out.println("2. View Deposits");
        System.out.println("3. View Payments");
        System.out.println("4. View Reports ");
        System.out.println("5. Return to Main Menu");
        ;
    }
    private static void readTransactionsFile(String type) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String input;

            ArrayList<Transaction> transactions = new ArrayList<>();
            while ((input = bufferedReader.readLine()) != null) {
                if (input.trim().isEmpty() || input.startsWith("date")) continue;


                String[] tokens = input.split("\\|");
                if (tokens.length != 5) {
                    System.out.println("Invalid entry, skipping: " + input);
                    continue; // Skip malformed lines
                }
                String date = tokens[0];
                String time = tokens[1];
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]);

                Transaction transaction = new Transaction(description, vendor, amount);
                if (type.equals("All") || (type.equals("Deposit") && amount > 0) || (type.equals("Payment") && amount < 0)) {
                    transactions.add(transaction);
                }
            }

            System.out.println(type + " Transactions:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void depositMethod() {

        System.out.println("Enter deposit amount: ");
        double depositAmount = scan.nextDouble();
        scan.nextLine();

        System.out.println("Enter Description of Deposit: ");
        String depositDescription = scan.nextLine();

        System.out.println("Enter Vendor of Deposit: ");
        String depositVendor = scan.nextLine();

        Transaction deposit = new Transaction(depositDescription, depositVendor, depositAmount);
        deposit.writeToTransaction(filePath);
    }

    public static void paymentMethod() {
        System.out.println("Enter Payment Amount: ");
        double paymentAmount = scan.nextDouble();
        scan.nextLine();
        System.out.println("Enter Description of Payment: ");
        String payDescription = scan.nextLine();
        //make payment method, put it here
        System.out.println("Enter Vendor of Payment: ");
        String payVendor = scan.nextLine();
        Transaction payment = new Transaction(payDescription, payVendor, -paymentAmount);
        payment.writeToTransaction(filePath);
    }

    public static ArrayList<String> readTransactions() {
        ArrayList<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
       /* public static void displayLedgerInfo () {
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
            } */
    }


