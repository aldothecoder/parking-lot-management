package io.github.aldothecoder.parkinglot;

import java.util.EnumMap;
import java.util.Map;

/**
    Represents a parking lot and encapsulates all parking-related data and business logic.
    It manages slot availability per type and total slot capacity.
 */
public class ParkingLot {

    //Stores total number of slots per for each type of slot(cant be re-assigned, only manipulated)
    private final Map<SlotType, Integer> totalSlots;

    //Stores available number of slots for each type of slot(cant be re-assigned, only manipulated)
    private final Map<SlotType, Integer> availableSlots;

    /*
        Constructor takes in pre-defined amounts of slots for each type of slot and initializes the class data.
        It comes from user input(seed-data).
    */
    public ParkingLot(int small, int large, int oversize){

        totalSlots = new EnumMap<>(SlotType.class);
        availableSlots = new EnumMap<>(SlotType.class);

        totalSlots.put(SlotType.SMALL, small);
        totalSlots.put(SlotType.LARGE, large);
        totalSlots.put(SlotType.OVERSIZE, oversize);

        //Available slots is equal to total slots because initially all slots are available
        availableSlots.putAll(totalSlots);

    }

    //If successful, parks a vehicle of a given car/slot type. Otherwise, fails and returns.
    public boolean park(SlotType type){

        int available = availableSlots.get(type);

        if(available == 0){
            return false;
        }

        availableSlots.put(type, available - 1);
        return true;

    }

    //If successful, removes a vehicle of a given car/slot type. Otherwise, fails and returns.
    public boolean exit(SlotType type){

        int available = availableSlots.get(type);
        int total = totalSlots.get(type);

        if(total == available){
            return false;
        }
        availableSlots.put(type, available + 1);
        return true;

    }

    /*
        Displays the current state of the parking lot in terms
        of how many available spaces there are for each type and also how many slots there are total
    */
    public void displayCurrentStatus(){

        System.out.println("\nCurrent Parking Lot Status: ");
        for(SlotType type: SlotType.values()){
            System.out.println(
                    type + ": " + availableSlots.get(type) + "/" + totalSlots.get(type) + " available slots."
            );
        }
        System.out.println("");
    }

}
