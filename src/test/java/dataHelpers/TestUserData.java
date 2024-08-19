package dataHelpers;

import java.util.Properties;

public class TestUserData {

    public TestUserData(Properties props) {
        validUser = new User(props.getProperty("validUsername"), props.getProperty("validPassword"));
        invalidUser = new User(props.getProperty("invalidUsername"), props.getProperty("invalidPassword"));
    }
    private final User validUser;
    private final User invalidUser;

    public User getValidUser() {
        return validUser;
    }

    public User getInvalidUser() {
        return invalidUser;
    }
}
