package com.bestreviewer.tripservicekata.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bestreviewer.tripservicekata.trip.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private static final User CHULSOO = new User();
    private static final User SOONHEE= new User();

    @Test
    @DisplayName("친구가 아닌 경우")
    public void testInformWhenUsersAreNotFrields(){
        User user = aUser()
                .friendsWith(CHULSOO)
                .build();
        assertFalse(user.isFriendWith(SOONHEE));
    }

    @Test
    @DisplayName("친구인 경우")
    public void testInformWhenUsersAreFrields(){
        User user = aUser()
                .friendsWith(CHULSOO,SOONHEE)
                .build();
        assertTrue(user.isFriendWith(SOONHEE));
    }
}
