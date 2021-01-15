package com.bestreviewer.tripservicekata.trip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TripServiceTest {
    @Test
    void test_fail() {
        //fail();
    }

    @Test
    @DisplayName("Should throw on exception when user is not logged.")
    public void testThrowExceptionWhenNotLogged(){
        TripService tripService = new TripService();

        tripService.getTripsByUser(null);
    }
}
