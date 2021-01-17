package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.user.DelegatedUserSession;
import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {

    private static final User NOT_LOGGED_USER = null;
    private static final User LOGGED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_JEJU = new Trip();
    private static final Trip TO_BUSAN = new Trip();

    TripService tripService;

    @BeforeEach
    public void setup(){
        DelegatedUserSession userSessions = new UserSessionsThatReturnsUser(LOGGED_USER);
        tripService = new TestableTripService(userSessions);
    }

    @Test
    void test_fail() {
        //fail();
    }

    @Test
    @DisplayName("Should throw on exception when user is not logged.")
    public void throwsExceptionWhenNotLoggedIn(){
        DelegatedUserSession userSessions = new UserSessionsThatReturnsUser(NOT_LOGGED_USER);
        assertThrows(Exception.class,()->new TripService(userSessions).getTripsByUser(NOT_LOGGED_USER));
    }

    @Test
    @DisplayName("친구가 아닌 경우 trip 을 반환하지 않는다.")
    public void returnsNoTripForUserWithNoFriend(){
        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_JEJU);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertTrue(friendTrips.isEmpty());
    }

    @Test
    @DisplayName("친구인 경우 trip 을 반환한다.")
    public void returnsTripsWhenUserAreFriend(){
        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addFriend(LOGGED_USER);
        friend.addTrip(TO_JEJU);
        friend.addTrip(TO_BUSAN);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);
        assertTrue(friendTrips.contains(TO_JEJU) && friendTrips.contains(TO_BUSAN));
    }

    private class TestableTripService extends TripService{
        public TestableTripService(DelegatedUserSession userSessions) {
            super(userSessions);
        }
    }

    private class UserSessionsThatReturnsUser extends DelegatedUserSession {
        private User loggedUser;

        UserSessionsThatReturnsUser(User loggedUser) {
            this.loggedUser = loggedUser;
        }

        @Override
        public User getLoggedUser() {
            return loggedUser;
        }
    }

}
