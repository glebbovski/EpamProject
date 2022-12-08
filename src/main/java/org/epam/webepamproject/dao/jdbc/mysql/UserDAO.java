package org.epam.webepamproject.dao.jdbc.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.epam.webepamproject.connection.HikariCP;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;
    private PreparedStatement ps = null;

    private static final String FIND_USER_INFO_QUERY = "SELECT * FROM user WHERE username=? AND password=?";
    private static final String IS_USER_EXISTS_QUERY = "SELECT IF (EXISTS (SELECT * FROM user WHERE username=?)," +
            " 1, 0) AS userExists";
    

    public UserDAO() {
        try {
            this.connection = HikariCP.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO check, no need mb
    public boolean isCredentialsValid(String username, String password) {
        try {
//            connection = hikariDataSource.getConnection();
            ps = connection.prepareStatement(FIND_USER_INFO_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            HikariCP.close(connection);
            HikariCP.close(ps);
        }

    }

    public boolean isUserExists(String username) {
        int exists = 0; // 0 - user is not in db; 1 - user is in db
        try {
//            connection = hikariDataSource.getConnection();
            ps = connection.prepareStatement(IS_USER_EXISTS_QUERY);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = rs.getInt("userExists");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            HikariCP.close(connection);
            HikariCP.close(ps);
        }
        return exists == 1;
    }


}
