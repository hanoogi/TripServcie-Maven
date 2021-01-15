package com.bestreviewer.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.user.UserSession;

public class TripService {
	private TripDAO tripDAO;

	public List<Trip> getFriendTrips(User friends, User loggedInUser) throws UserNotLoggedInException {
		validate(loggedInUser);

		return friends.isFriendWith(loggedInUser)
				? tripsBy(friends)
				: noTrips();

	}

	private void validate(User loggedInUser) {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	private ArrayList<Trip> noTrips(){
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user) {
		return tripDAO.tripsBy(user);
	}

}
