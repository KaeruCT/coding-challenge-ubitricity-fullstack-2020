package com.avi.ubi.carpark.service;

import com.avi.ubi.carpark.exceptions.MissingChargingPointException;
import com.avi.ubi.carpark.exceptions.OccupiedChargingPointException;
import com.avi.ubi.carpark.model.ChargingPoint;
import com.avi.ubi.carpark.util.CarparkUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

import static java.util.stream.Collectors.toMap;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@PropertySource("classpath:application.properties")
public class ChargingPointService {
    private Map<Integer, ChargingPoint> chargingPoints;

    @Value("${carpark.totalAmp}")
    private int totalAmp;
    @Value("${carpark.fastChargingAmp}")
    private int fastChargingAmp;
    @Value("${carpark.slowChargingAmp}")
    private int slowChargingAmp;
    @Value("${carpark.chargingPointsAmount}")
    private int chargingPointsAmount;

    @PostConstruct
    public void init() {
        chargingPoints = IntStream
            .rangeClosed(1, chargingPointsAmount)
            .boxed()
            .map(ChargingPoint::new)
            .collect(toMap(ChargingPoint::getId, cp -> cp));
    }

    private void recalculateDistribution() {
        List<ChargingPoint> sortedByOccupied = chargingPoints
            .values()
            .stream()
            .filter(ChargingPoint::isOccupied)
            .sorted(Comparator.comparing(ChargingPoint::getOccupiedSince))
            .collect(Collectors.toList());
        Collections.reverse(sortedByOccupied);

        int position = 0;
        int fastChargingSlots = CarparkUtils.getFastChargingSlots(sortedByOccupied.size(), totalAmp, fastChargingAmp, slowChargingAmp);
        System.out.println(fastChargingSlots);
        for (ChargingPoint chargingPoint : sortedByOccupied) {
            System.out.println("  " + chargingPoint.getId());
            chargingPoint.setAmps(position < fastChargingSlots ? fastChargingAmp : slowChargingAmp);
            position++;
        }
    }

    public synchronized void connect(int chargingPointId) {
        ChargingPoint cp = chargingPoints.get(chargingPointId);
        if (cp == null) {
            throw new MissingChargingPointException(chargingPointId);
        }
        if (cp.isOccupied()) {
            throw new OccupiedChargingPointException(chargingPointId);
        }
        cp.setOccupiedSince(new Date());
        cp.setOccupied(true);
        recalculateDistribution();
    }

    public synchronized void disconnect(int chargingPointId) {
        ChargingPoint cp = chargingPoints.get(chargingPointId);
        if (cp == null) {
            throw new MissingChargingPointException(chargingPointId);
        }
        cp.setOccupied(false);
        cp.setOccupiedSince(null);
        cp.setAmps(0);
        recalculateDistribution();
    }

    public Map<Integer, ChargingPoint> getChargingPoints() {
        return chargingPoints;
    }

    public boolean isFastCharging(int amps) {
        return amps == fastChargingAmp;
    }
}
