package nld.spotitube.dao;

public interface IUserDAO {

    public boolean CheckUserExists(String user, String password);

    public void SetToken(String user, String token);

    public boolean CheckToken(String user, String token);

    public boolean CheckTokenExists(String token);

}
