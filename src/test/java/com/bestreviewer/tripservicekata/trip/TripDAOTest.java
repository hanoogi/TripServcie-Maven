package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripDAOTest {
    @Test
    public void testThrowExceptionWhenRetrieveUserTrips(){
        Assertions.assertThrows(Exception.class,()-> TripDAO.findTripsByUser(new User()));
    }

}
