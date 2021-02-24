package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.user.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TripServiceTest {
    private static final User NOT_LOGGED_USER = null;
    private static final User LOGGED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_JEJU = new Trip();
    private static final Trip TO_BUSAN = new Trip();

    UserSession userSessionMock;
    MockedStatic<TripDAO> tripDaoMock;

    @BeforeEach
    public void beforeEach()
    {
        userSessionMock = mock(UserSession.class);
        tripDaoMock = mockStatic(TripDAO.class);
    }

    @AfterEach
    public void afterEach()
    {
        tripDaoMock.close();
    }

    @Test
    public void testThrowxceptionWhenNotLogged() {
        assertThrows(UserNotLoggedInException.class, () ->doGetTripsByUser(NOT_LOGGED_USER, ANOTHER_USER));
    }

    @Test
    public void testReturnsNoTripWhenUserNotFriend() {
        ANOTHER_USER.addTrip(TO_JEJU);
        ANOTHER_USER.addTrip(TO_BUSAN);

        assertEquals(0, doGetTripsByUser(LOGGED_USER, ANOTHER_USER).size());
    }

    @Test
    public void testReturnTripsOfFriend() {
        ANOTHER_USER.addFriend(LOGGED_USER);
        ANOTHER_USER.addTrip(TO_JEJU);
        ANOTHER_USER.addTrip(TO_BUSAN);

        int anotherUsersTripCount = ANOTHER_USER.trips().size();
        assertEquals(anotherUsersTripCount, doGetTripsByUser(LOGGED_USER, ANOTHER_USER).size());
    }

    private List<Trip> doGetTripsByUser(User loginUser, User targetUser)
    {
        // LoginUser 설정
        Mockito.when(userSessionMock.getLoggedUser()).thenReturn(loginUser);

        try (MockedStatic<UserSession> userSessionSingleton = mockStatic(UserSession.class)) {

            // Dependency가 존재하는 가상 함수들에 대한 기능 설정
            userSessionSingleton.when(UserSession::getInstance).thenReturn(userSessionMock);
            Mockito.when(TripDAO.findTripsByUser(targetUser)).thenReturn(targetUser.trips());

            // TripService를 이용하여 getTripsByUser 함수 실행
            TripService tripService = new TripService();
            List<Trip> tripList = tripService.getTripsByUser(targetUser);
            return tripList;
        }
    }
}
