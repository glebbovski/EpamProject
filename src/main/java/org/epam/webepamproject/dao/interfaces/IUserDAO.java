package org.epam.webepamproject.dao.interfaces;

public interface IUserDAO {

    boolean isCredentialsValid(String username, String password);
    boolean isUserExists(String username);
    boolean isUserAdmin(String username);
}
