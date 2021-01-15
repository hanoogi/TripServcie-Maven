package com.bestreviewer.tripservicekata.trip;

import java.util.List;

import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.exception.CollaboratorCallException;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}

	public List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}
}