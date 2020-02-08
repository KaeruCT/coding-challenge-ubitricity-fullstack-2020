package com.avi.ubi.carpark.response;

import java.util.Date;

public class ChargingPointResponse {
    private int id;
    private int amps;
    private Date occupiedSince;
    private boolean occupied;
    private boolean isFastCharging;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmps() {
        return amps;
    }

    public void setAmps(int amps) {
        this.amps = amps;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Date getOccupiedSince() {
        return occupiedSince;
    }

    public void setOccupiedSince(Date occupiedSince) {
        this.occupiedSince = occupiedSince;
    }

    public boolean isFastCharging() {
        return isFastCharging;
    }

    public void setFastCharging(boolean fastCharging) {
        isFastCharging = fastCharging;
    }
}
