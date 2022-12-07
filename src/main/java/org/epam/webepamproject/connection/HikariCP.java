package org.epam.webepamproject.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariCP {

    private final static HikariDataSource dataSource;
    private final static HikariConfig config = new HikariConfig();

    static {
        try(FileInputStream f = new FileInputStream("D:\\Epam\\Project\\EpamProject\\src\\main\\resources\\db.properties")) {
            Properties properties = new Properties();
            properties.load(f);

            config.setJdbcUrl(properties.getProperty("jdbcUrl"));
            config.setUsername(properties.getProperty("user"));
            config.setPassword(properties.getProperty("password"));
            config.setDriverClassName(properties.getProperty("driverClassName"));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            dataSource = new HikariDataSource(config);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HikariCP() {
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public static void close(ResultSet resultSet) {
//        if (resultSet != null) {
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
