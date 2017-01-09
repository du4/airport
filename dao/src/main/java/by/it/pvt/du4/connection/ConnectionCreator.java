package by.it.pvt.du4.connection;

//import by.it.pvt.du4.controller.FrontController;
import com.mysql.fabric.jdbc.FabricMySQLDriver;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionCreator {

    private static volatile Connection connection = null;
    public static final String URL_DB = "jdbc:mysql://localhost:3306/airportdb";
    public static final String USER_DB = "root";
    public static final String PASSWORD_DB = "root";
    private static final Lock lock = new ReentrantLock();

    static {
        Driver driver;
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static Connection getConnection() throws SQLException, FileNotFoundException {

        if (connection == null || connection.isClosed()) {
            try{
                lock.lock();
                if (connection == null || connection.isClosed()) {
                    Class.forName("com.mysql.jdbc.Driver");
                    if (connection == null || connection.isClosed())
                        connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);

//                    connection = DriverManager.getConnection(FrontController.connectionSettings.getURL_DB(), FrontController.connectionSettings.getUSER_DB(), FrontController.connectionSettings.getPASSWORD_DB());
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
