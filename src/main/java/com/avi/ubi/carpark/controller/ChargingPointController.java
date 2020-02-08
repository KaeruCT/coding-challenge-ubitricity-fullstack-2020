package com.avi.ubi.carpark.controller;
import com.avi.ubi.carpark.model.ChargingPoint;
import com.avi.ubi.carpark.request.StatusRequest;
import com.avi.ubi.carpark.response.ChargingPointResponse;
import com.avi.ubi.carpark.service.ChargingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(("/chargingPoints"))

public class ChargingPointController {
    @Autowired private ChargingPointService chargingPointService;

    /**
     * Change the connection status of a charging point
     * @param id The id of the charging point
     * @param statusRequest The connection status for the charging point.
     *                      The "occupied" field will determine if this charging
     *                      point becomes connected or disconnected.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = PATCH, path = "/{id}")
    public void setConnectionStatus(@PathVariable int id, @RequestBody StatusRequest statusRequest) {
        if (statusRequest.isOccupied()) {
            chargingPointService.connect(id);
        } else {
            chargingPointService.disconnect(id);
        }
    }

    /**
     * Obtain a list of all the charging points, occupied or not
     *
     * @return List of all the charging points
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = GET)
    public List<ChargingPointResponse> getChargingPoints() {
        return chargingPointService.getChargingPoints()
                .values()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ChargingPointResponse toResponse(ChargingPoint chargingPoint) {
        ChargingPointResponse response = new ChargingPointResponse();
        response.setId(chargingPoint.getId());
        response.setAmps(chargingPoint.getAmps());
        response.setOccupied(chargingPoint.isOccupied());
        response.setOccupiedSince(chargingPoint.getOccupiedSince());
        response.setFastCharging(chargingPointService.isFastCharging(chargingPoint.getAmps()));
        return response;
    }
}
