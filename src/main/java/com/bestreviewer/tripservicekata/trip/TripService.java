package com.bestreviewer.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import com.bestreviewer.tripservicekata.exception.UserNotLoggedInException;
import com.bestreviewer.tripservicekata.user.DelegatedUserSession;
import com.bestreviewer.tripservicekata.user.User;

public class TripService {

	public static final ArrayList<Trip> NO_TRIPS = new ArrayList<Trip>();

	private final DelegatedUserSession userSessions;
	private final TripsRepository tripsRepository = new TripsRepository();

	public TripService() {
		this( new DelegatedUserSession());
	}

	public TripService(DelegatedUserSession userSessions) {
		this.userSessions = userSessions;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		return getTripsBy(user, userSessions.getLoggedUser());
	}

	private List<Trip> getTripsBy(User user, User loggedUser) {
		checkIfTheUserIsLogged(loggedUser);

		return user.friendsOf(loggedUser)
				? tripsRepository.tripsBy(user)
				: NO_TRIPS;
	}

	private void checkIfTheUserIsLogged(User loggedUser) {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> tripsBy(User user) {
		return tripsRepository.tripsBy(user);
	}

}
