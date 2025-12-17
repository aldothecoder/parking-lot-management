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


}
