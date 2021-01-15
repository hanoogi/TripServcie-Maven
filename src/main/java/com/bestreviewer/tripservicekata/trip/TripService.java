package com.bestreviewer.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (getLoggedInUser() == null) {
			throw new UserNotLoggedInException();

		}

		return user.isFriendWith(getLoggedInUser())
				? tripsBy(user)
				: noTrips();

	}

	private ArrayList<Trip> noTrips(){
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
