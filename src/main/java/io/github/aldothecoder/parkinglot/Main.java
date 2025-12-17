package io.github.aldothecoder.parkinglot;

import java.util.Scanner;

/*
   Entry point for this application.
   This class handles user interaction, input parsing,
   and delegates actions to the ParkingLot class .
*/
public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int totalParkingSlots;

        System.out.print("Enter total number of parking slots: ");

        // Read initial seed data used to configure the parking lot slot amount
        totalParkingSlots = scanner.nextInt();

        scanner.nextLine(); // consume newline
        System.out.println("");

        // Divide total slot amount evenly across all 3 slot types
        int base = totalParkingSlots / 3;
        int remainder = totalParkingSlots % 3;

        int small = base;
        int large = base;
        int oversize = base;

        /*
            If not perfectly/evenly divisible by 3 distribute remainder
            between small and large by adding 1 slot to both or 1 slot to small.
            Priority is given to small.
        */
        if (remainder >= 1) small++;
        if (remainder == 2) large++;

        //Initialize the parking lot with the evenly distributed amounts(or close to even)
        ParkingLot parkingLot = new ParkingLot(small, large, oversize);

        boolean running = true;

        //CLI application loop
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

    /*
      Reads and validates the slot type entered by the user and converts it
      into a SlotType enum for use by the core app logic.
    */
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

    /*
       Handles user interaction for parking a vehicle and delegates
       the action to the ParkingLot class.
    */
    private static void handlePark(Scanner scanner, ParkingLot parkingLot) {
        SlotType type = getSlotType(scanner);
        if (type == null) return;

        boolean success = parkingLot.park(type);
        System.out.println(success ? "Vehicle parked.\n" : "No available slots.\n");
    }

    /*
       Handles user interaction for removing a vehicle and delegates
       the action to the ParkingLot class.
    */
    private static void handleExit(Scanner scanner, ParkingLot parkingLot) {
        SlotType type = getSlotType(scanner);
        if (type == null) return;

        boolean success = parkingLot.exit(type);
        System.out.println(success ? "Vehicle exited.\n" : "No vehicles of this type are currently parked.\n");
    }

}
