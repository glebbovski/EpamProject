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

public class HikariCP {

    private static HikariDataSource dataSource = null;

    static {
        try (FileInputStream f = new FileInputStream("src/main/resources/db.properties")) {

            Properties properties = new Properties();
            properties.load(f);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.username"));
            config.setPassword(properties.getProperty("db.password"));
            config.addDataSourceProperty("minimumIdle", properties.getProperty("db.minimumIdle"));
            config.addDataSourceProperty("maximumPoolSize", properties.getProperty("db.maximumPoolSize"));

            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public static HikariDataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        } else {
            throw new RuntimeException("Datasource is null");
        }
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
