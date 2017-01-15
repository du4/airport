package by.it.pvt.du4.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionCreator {

    private static HikariConfig config = null;

    static {
        config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/airportdb");
        config.setUsername("root");
        config.setPassword("root");
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(30000);
        config.addDataSourceProperty("databaseName","airportdb");
        config.addDataSourceProperty("serverName","127.0.0.1");
    }
    private static final HikariDataSource DS = new HikariDataSource(config);

    public static Connection getDataSource() throws SQLException {
        return DS.getConnection();
    }
}
