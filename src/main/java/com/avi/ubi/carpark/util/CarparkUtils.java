package com.avi.ubi.carpark.util;

public class CarparkUtils {
    /**
     * @param occupiedChargingPoints how many charging points are being used
     * @param totalAmp the total amps we can distribute
     * @param fastChargingAmp how many amps needed for fast charging
     * @param slowChargingAmp how many amps needed for slow charging
     * @return How many charging slots can support fast charging
     */
    public static int getFastChargingSlots(
        int occupiedChargingPoints,
        int totalAmp,
        int fastChargingAmp,
        int slowChargingAmp
    ) {
        int fastChargingSlots = occupiedChargingPoints;
        int ampUsed = fastChargingSlots * fastChargingAmp;

        while (ampUsed > totalAmp) {
            fastChargingSlots -= 1;
            int slowChargingSlots = occupiedChargingPoints - fastChargingSlots;
            ampUsed = fastChargingSlots * fastChargingAmp + slowChargingSlots * slowChargingAmp;
        }

        return fastChargingSlots;
    }
}
