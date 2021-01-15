package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private static final Trip TO_BUSAN = new Trip();

    private User loggedInUser;
    TripService tripService;

    @Test
    void test_fail() {
        //fail();
    }

    @BeforeEach
    public void setup(){
        tripService = new TestableTripService();
        loggedInUser = LOGGED_USER;
    }

    @Test
    @DisplayName("Should throw on exception when user is not logged.")
    public void testThrowExceptionWhenNotLogged(){
        Assertions.assertThrows(Exception.class,()-> tripService.getTripsByUser(NOT_LOGGED_USER));
    }

    @Test
    @DisplayName("친구가 아닌 경우 trip 을 반환하지 않는다.")
    public void testReturnNoTripWhenUserNotFriend(){
        User friend = UserBuilder.aUser()
                .friendsWith(ANOTHER_USER)
                .withTrips(TO_JEJU)
                .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertEquals(0,friendTrips.size());
    }

    @Test
    @DisplayName("친구인 경우 trip 을 반환한다.")
    public void testReturnTripWhenUserAreFriend(){
        User friend = UserBuilder.aUser()
                .friendsWith(ANOTHER_USER, loggedInUser)
                .withTrips(TO_JEJU,TO_BUSAN)
                .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertEquals(2,friendTrips.size());
    }

    private class TestableTripService extends TripService{
        @Override
        protected List<Trip> tripsBy(User user) throws UserNotLoggedInException {
            return user.trips();
        }

        @Override
        protected User getLoggedInUser(){
            return loggedInUser;
        }

    }
}

