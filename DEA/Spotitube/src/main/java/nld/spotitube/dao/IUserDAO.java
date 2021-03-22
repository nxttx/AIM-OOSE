package nld.spotitube.dao;

import nld.spotitube.exceptions.NoRowsAreEffectedException;

import java.sql.SQLException;

public interface IUserDAO {

    public boolean CheckUserExists(String user, String password) throws SQLException;

    public void SetToken(String user, String token) throws NoRowsAreEffectedException, SQLException;

    public boolean CheckToken(String user, String token) throws SQLException;

    public boolean CheckTokenExists(String token) throws SQLException;

}
