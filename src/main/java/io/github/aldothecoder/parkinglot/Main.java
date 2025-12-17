package io.github.aldothecoder.parkinglot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int totalParkingSlots;

        System.out.print("Enter total number of parking slots: ");
        totalParkingSlots = scanner.nextInt();

        scanner.nextLine(); // consume newline
        System.out.println("");

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
            System.out.println("3 - Display Current Status");
            System.out.println("4 - Quit");

            String choice = scanner.nextLine();

            System.out.println("");

            switch (choice) {
                case "1" -> handlePark(scanner, parkingLot);
                case "2" -> handleExit(scanner, parkingLot);
                case "3" -> parkingLot.displayCurrentStatus();
                case "4" -> running = false;
                default -> System.out.println("Invalid option.");
            }

        }

        scanner.close();
        System.out.println("Goodbye.");

    }

    private static SlotType getSlotType(Scanner scanner) {
        System.out.print("Enter car type (SMALL: Small and compact car, LARGE: Full-size car, OVERSIZE: SUV or Truck): ");
        String input = scanner.nextLine();

        try {
            return SlotType.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid slot type.\n");
            return null;
        }
    }

    private static void handlePark(Scanner scanner, ParkingLot parkingLot) {
        SlotType type = getSlotType(scanner);
        if (type == null) return;

        boolean success = parkingLot.park(type);
        System.out.println(success ? "Vehicle parked.\n" : "No available slots.\n");
    }

    private static void handleExit(Scanner scanner, ParkingLot parkingLot) {
        SlotType type = getSlotType(scanner);
        if (type == null) return;

        boolean success = parkingLot.exit(type);
        System.out.println(success ? "Vehicle exited.\n" : "No vehicles of this type are currently parked.\n");
    }

}
