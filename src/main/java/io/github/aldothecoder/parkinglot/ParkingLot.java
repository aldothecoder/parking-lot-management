package io.github.aldothecoder.parkinglot;

import java.util.EnumMap;
import java.util.Map;

public class ParkingLot {

    private final Map<SlotType, Integer> totalSlots;
    private final Map<SlotType, Integer> availableSlots;

    public ParkingLot(int small, int large, int oversize){

        totalSlots = new EnumMap<>(SlotType.class);
        availableSlots = new EnumMap<>(SlotType.class);

        totalSlots.put(SlotType.SMALL, small);
        totalSlots.put(SlotType.LARGE, large);
        totalSlots.put(SlotType.OVERSIZE, oversize);

        availableSlots.putAll(totalSlots);

    }

    public boolean park(SlotType type){

        int available = availableSlots.get(type);

        if(available == 0){
            return false;
        }

        availableSlots.put(type, available - 1);
        return true;

    }

    public boolean exit(SlotType type){

        int available = availableSlots.get(type);
        int total = totalSlots.get(type);

        if(total == available){
            return false;
        }
        availableSlots.put(type, available + 1);
        return true;

    }

    public void displayCurrentStatus(){

        System.out.println("\nCurrent Parking Lot Status:");
        for(SlotType type: SlotType.values()){
            System.out.println(
                    type + ": " + availableSlots.get(type) + "/" + totalSlots.get(type) + " available slots."
            );
        }

    }

}
