package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TripServiceTest {
    private static final User NOT_LOGGED_USER = null;
    private static final User LOGGED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_JEJU = new Trip();

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

    @Test
    @DisplayName("친구가 아닌 경우 trip 을 반환하지 않는다.")
    public void testReturnNoTripWhenUserNotFriend(){
        TripService tripService = new TestableTripService();
        loggedInUser = LOGGED_USER;
        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_JEJU);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertEquals(0,friendTrips.size());
    }

    private class TestableTripService extends TripService{
        @Override
        protected User getLoggedInUser(){
            return loggedInUser;
        }
    }
}

