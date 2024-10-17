package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String date, time, description, vendor;
    private double amount;

    public Transaction(String description, String vendor, double amount) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.date = now.format(dateFormatter);
        this.time = now.format(timeFormatter);
    }

    @Override
    public String toString() {
        return
                this.date + '|' + this.time + '|' + this.description + '|' + this.vendor + '|' + this.amount ;
    }
    public void writeToTransaction(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(toString() + "\n");
            System.out.println("Successfully recorded transaction of " + amount);
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential IO exceptions
        }
    }
}
