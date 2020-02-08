package com.avi.ubi.carpark.exceptions;

import org.springframework.http.HttpStatus;

public class MissingChargingPointException extends ServiceException {
    public MissingChargingPointException(int id) {
        super("Charging point " + id + " missing", HttpStatus.NOT_FOUND);
    }
}
