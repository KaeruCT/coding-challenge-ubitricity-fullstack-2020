package com.avi.ubi.carpark.test;

import com.avi.ubi.carpark.util.CarparkUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CarparkUtilsTest {
    private final int totalAmp = 100;
    private final int fastChargingAmp = 20;
    private final int slowChargingAmp = 10;

    @Test
    public void test_getFastChargingSlots_oneOccupied() {
        int fastChargingSlots = CarparkUtils.getFastChargingSlots(1, totalAmp, fastChargingAmp, slowChargingAmp);
        assertThat(fastChargingSlots, is(1));
    }

    @Test
    public void test_getFastChargingSlots_fiveOccupied() {
        int fastChargingSlots = CarparkUtils.getFastChargingSlots(5, totalAmp, fastChargingAmp, slowChargingAmp);
        assertThat(fastChargingSlots, is(5));
    }

    @Test
    public void test_getFastChargingSlots_sixOccupied() {
        int fastChargingSlots = CarparkUtils.getFastChargingSlots(6, totalAmp, fastChargingAmp, slowChargingAmp);
        assertThat(fastChargingSlots, is(4));
    }

    @Test
    public void test_getFastChargingSlots_tenOccupied() {
        int fastChargingSlots = CarparkUtils.getFastChargingSlots(10, totalAmp, fastChargingAmp, slowChargingAmp);
        assertThat(fastChargingSlots, is(0));
    }
}
