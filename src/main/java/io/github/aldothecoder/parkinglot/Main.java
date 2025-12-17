package io.github.aldothecoder.parkinglot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int totalParkingSlots;

        System.out.print("Enter total number of parking slots: ");
        totalParkingSlots = scanner.nextInt();

        scanner.nextLine(); // consume newline

        int base = totalParkingSlots / 3;
        int remainder = totalParkingSlots % 3;

        int small = base;
        int large = base;
        int oversize = base;

        // distribute remainder deterministically
        if (remainder >= 1) small++;
        if (remainder == 2) large++;

        ParkingLot parkingLot = new ParkingLot(small, large, oversize);

        boolean running = true;

        while(running){

            System.out.println("Select one of the following: ");
            System.out.println("1 - Park vehicle");
            System.out.println("2 - Exit vehicle");
            System.out.println("3 - Quit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> System.out.println("Handling Park...");
                case "2" -> System.out.println("Handling Exit...");
                case "3" -> running = false;
                default -> System.out.println("Invalid option.");
            }

        }

        scanner.close();
        System.out.println("Goodbye.");

    }



}
