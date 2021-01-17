package com.bestreviewer.tripservicekata.user;

public class DelegatedUserSession {
    public DelegatedUserSession() {
    }

    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
