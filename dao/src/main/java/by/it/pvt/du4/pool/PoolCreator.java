package by.it.pvt.du4.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolCreator {
    private static final Logger LOG = LoggerFactory.getLogger(PoolCreator.class);
    private static HikariConfig config = null;

    static {
        String propPath = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+ System.getProperty("file.separator")+
                "main" +System.getProperty("file.separator")+"resources"+ System.getProperty("file.separator") + "database.properties";
        LOG.trace("USER.PROPERTY=" + propPath);
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

    public static Connection getConnectionFromPool() throws SQLException {
        return DS.getConnection();
    }
}
