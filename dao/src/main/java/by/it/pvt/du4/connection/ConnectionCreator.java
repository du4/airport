package by.it.pvt.du4.connection;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionCreator {

    private static volatile Connection connection = null;
    private static final Lock lock = new ReentrantLock();

    private static  String path = "/home/du4/IdeaProjects/airport/dao/src/main/resources/database.properties";
    private static Properties prop = new Properties();

    static {
        Driver driver;
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static {
        try(InputStream input = new FileInputStream(path)){
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, FileNotFoundException {

        if (connection == null || connection.isClosed()) {
            try{
                lock.lock();
                if (connection == null || connection.isClosed()) {
                    Class.forName("com.mysql.jdbc.Driver");
                    if (connection == null || connection.isClosed())
                        connection = DriverManager.getConnection(prop.getProperty("dburl"), prop.getProperty("dbusername"), prop.getProperty("dbpassword"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return connection;
    }

}
