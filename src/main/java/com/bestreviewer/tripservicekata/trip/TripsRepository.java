package com.bestreviewer.tripservicekata.trip;

import com.bestreviewer.tripservicekata.user.User;

import java.util.List;

public class TripsRepository {
    public TripsRepository() {
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }
}