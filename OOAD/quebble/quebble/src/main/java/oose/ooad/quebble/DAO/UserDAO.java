package src.main.java.oose.ooad.quebble.DAO;

import src.main.java.oose.ooad.quebble.DAO.MockData.MockUserData;

public class UserDAO {

    public void checkUsernameExists(String username) {

    }

    public void AddUser(String username, String password) {

    }

    public boolean checkCredentials(String username, String password)
    {
        return username.equals(MockUserData.player1.getUsername()) && password.equals(MockUserData.player1.getPassword());
    }

}
