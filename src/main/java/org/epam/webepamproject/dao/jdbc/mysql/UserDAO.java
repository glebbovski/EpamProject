package org.epam.webepamproject.dao.jdbc.mysql;

import org.epam.webepamproject.connection.HikariCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.epam.webepamproject.dao.interfaces.IUserDAO;
import org.epam.webepamproject.model.User;

public class UserDAO implements IUserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    private static final String FIND_USER_INFO_QUERY = "SELECT * FROM user WHERE username=? AND password=?";
    private static final String IS_USER_EXISTS_QUERY = "SELECT IF (EXISTS (SELECT * FROM user WHERE username=?)," +
            " 1, 0) AS userExists";
    private static final String GET_ADMIN_VALUE_QUERY = "SELECT isAdmin FROM user WHERE username=?";
    private static final String CREATE_USER = "INSERT INTO user (username, password, isAdmin) " +
            "VALUES (?, ?, 0)";
    private static final String GET_ALL_USERS = "SELECT * FROM user";
    

    public UserDAO() {

    }

    @Override
    public boolean isCredentialsValid(String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = HikariCP.getConnection();
            ps = connection.prepareStatement(FIND_USER_INFO_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            logger.info("returned value of isCredentialsValid-function and ResultSet variable closed");
            return rs.next();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            HikariCP.close(connection);
            HikariCP.close(ps);
            logger.info("Connection and PreparedStatement closed");

        }
        return false; // because of logger
    }

    @Override
    public boolean isUserExists(String username) {
        Connection connection = null;
        PreparedStatement ps = null;
        int exists = 0; // 0 - user is not in db; 1 - user is in db
        try {
            connection = HikariCP.getConnection();
            ps = connection.prepareStatement(IS_USER_EXISTS_QUERY);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = rs.getInt("userExists");
            }
            logger.info("isUserExists-function and ResultSet variable closed");

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            HikariCP.close(connection);
            HikariCP.close(ps);
            logger.info("Connection and PreparedStatement closed");
        }
        return exists == 1;
    }

    @Override
    public boolean isUserAdmin(String username) {
        Connection connection = null;
        PreparedStatement ps = null;
        int adminValue = 0;
        try {
            connection = HikariCP.getConnection();
            ps = connection.prepareStatement(GET_ADMIN_VALUE_QUERY);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                adminValue = rs.getInt("isAdmin");
            }
            logger.info("isUserAdmin-function and ResultSet variable closed");

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            HikariCP.close(connection);
            HikariCP.close(ps);
            logger.info("Connection and PreparedStatement closed");
        }
        return adminValue == 1;
    }

    @Override
    public void create(String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = HikariCP.getConnection();
            ps = connection.prepareStatement(CREATE_USER);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            HikariCP.close(connection);
            HikariCP.close(ps);
            logger.info("Connection and PreparedStatement closed");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = HikariCP.getConnection();
            ps = connection.prepareStatement(GET_ALL_USERS);
            List<User> users = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                if(rs.getBoolean("isAdmin")) { // true = admin
                    user.setRole(User.Role.ADMIN);
                } else {
                    user.setRole(User.Role.USER);
                }
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            HikariCP.close(connection);
            HikariCP.close(ps);
            logger.info("Connection and PreparedStatement closed");
        }
        return null;
    }
}
