package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TripServiceTest {
    private static final User NOT_LOGGED_USER = null;
    private static final User LOGGED_USER = new User();

    private User loggedInUser;

    @Test
    void test_fail() {
        //fail();
    }

    @Test
    @DisplayName("Should throw on exception when user is not logged.")
    public void testThrowExceptionWhenNotLogged(){
        TripService tripService = new TestableTripService();
        Assertions.assertThrows(Exception.class,()-> tripService.getTripsByUser(NOT_LOGGED_USER));
    }

    private class TestableTripService extends TripService{
        @Override
        protected User getLoggedInUser(){
            return loggedInUser;
        }
    }
}

