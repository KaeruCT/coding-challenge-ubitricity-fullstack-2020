package com.avi.ubi.carpark.service;

import com.avi.ubi.carpark.model.ChargingPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ChargingPointService.class})
public class ChargingPointServiceTest {
    @Autowired
    private ChargingPointService chargingPointService;

    @Test
    public void test_chargingPoints_oneConnection() {
        chargingPointService.init();
        chargingPointService.connect(1);
        Map<Integer, ChargingPoint> chargingPoints = chargingPointService.getChargingPoints();
        ChargingPoint chargingPoint = chargingPoints.get(1);
        assertThat(chargingPoint.getId(), is(1));
        assertThat(chargingPoint.getAmps(), is(20));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(2);
        assertThat(chargingPoint.isOccupied(), is(false));
    }

    @Test
    public void test_chargingPoints_sixConnections() {
        chargingPointService.init();
        for (int i = 1; i <= 6; i++) {
            chargingPointService.connect(i);
        }

        Map<Integer, ChargingPoint> chargingPoints = chargingPointService.getChargingPoints();

        ChargingPoint chargingPoint = chargingPoints.get(1);
        assertThat(chargingPoint.getAmps(), is(10));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(2);
        assertThat(chargingPoint.getAmps(), is(10));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(3);
        assertThat(chargingPoint.getAmps(), is(20));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(4);
        assertThat(chargingPoint.getAmps(), is(20));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(5);
        assertThat(chargingPoint.getAmps(), is(20));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(6);
        assertThat(chargingPoint.getAmps(), is(20));
        assertThat(chargingPoint.isOccupied(), is(true));

        chargingPoint = chargingPoints.get(7);
        assertThat(chargingPoint.getAmps(), is(0));
        assertThat(chargingPoint.isOccupied(), is(false));

        chargingPoint = chargingPoints.get(8);
        assertThat(chargingPoint.getAmps(), is(0));
        assertThat(chargingPoint.isOccupied(), is(false));

        chargingPoint = chargingPoints.get(9);
        assertThat(chargingPoint.getAmps(), is(0));
        assertThat(chargingPoint.isOccupied(), is(false));

        chargingPoint = chargingPoints.get(10);
        assertThat(chargingPoint.getAmps(), is(0));
        assertThat(chargingPoint.isOccupied(), is(false));
    }
}
