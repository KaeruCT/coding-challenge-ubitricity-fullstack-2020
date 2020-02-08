package com.avi.ubi.carpark.exceptions;

import org.springframework.http.HttpStatus;

public class OccupiedChargingPointException extends ServiceException {
    public OccupiedChargingPointException(int id) {
        super("Charging point " + id + " already occupied", HttpStatus.CONFLICT);
    }
}
