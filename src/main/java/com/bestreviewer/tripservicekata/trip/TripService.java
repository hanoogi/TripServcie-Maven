package com.bestreviewer.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.User;
import com.bestreviewer.tripservicekata.user.UserSession;

public class TripService {

	public static final ArrayList<Trip> NO_TRIPS = new ArrayList<Trip>();

	private final UserSession userSessions = UserSession.getInstance();

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		return getTripsBy(user, userSessions.getLoggedUser());
	}

	private List<Trip> getTripsBy(User user, User loggedUser) {
		checkIfTheUserIsLogged(loggedUser);

		return user.friendsOf(loggedUser)
				? tripsBy(user)
				: NO_TRIPS;
	}

	private void checkIfTheUserIsLogged(User loggedUser) {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

}
