package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String date, time, description, vendor;
    private double amount;

    public Transaction () {

    }
    public Transaction(String description, String vendor, double amount) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.date = date;
        this.time = time;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.date = now.format(dateFormatter);
        this.time = now.format(timeFormatter);
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String toString() {
            return date + '|' + time + '|' + description + '|' + vendor + '|' + amount;
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
