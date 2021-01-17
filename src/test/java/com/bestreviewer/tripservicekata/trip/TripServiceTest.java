package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TripServiceTest {
    @Test
    void test_fail() {
        //fail();
    }

    @Test
    @DisplayName("Should throw on exception when user is not logged.")
    public void testThrowExceptionWhenNotLogged(){
        TripService tripService = new TestableTripService();

        assertThrows(Exception.class,()->tripService.getTripsByUser(null));
    }

    private class TestableTripService extends TripService{
        @Override
        protected User getLoggedUser(){
            return null;
        }
    }
}
