package org.epam.webepamproject.dao.interfaces;

import org.epam.webepamproject.model.User;

import java.util.List;

public interface IUserDAO {

    boolean isCredentialsValid(String username, String password);
    boolean isUserExists(String username);
    boolean isUserAdmin(String username);
    void create(String username, String password);
    List<User> getAllUsers();
}
