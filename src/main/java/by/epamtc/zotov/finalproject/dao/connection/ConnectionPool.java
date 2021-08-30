package by.epamtc.zotov.finalproject.dao.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

//TODO FIX SOME MAGIC VALUES
public class ConnectionPool {
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private Properties properties;
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> busyConnections;
    private static ConnectionPool instance;
    private static final Logger logger = LogManager.getLogger();

    private ConnectionPool() {
        Connection connection = null;
        properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE));
            Class.forName(properties.getProperty("driver"));

            int poolCapacity = Integer.parseInt(properties.getProperty("capacity"));
            freeConnections = new ArrayBlockingQueue<Connection>(poolCapacity);
            busyConnections = new ArrayBlockingQueue<Connection>(poolCapacity);

            for (int i = 0; i < poolCapacity; i++) {
                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
                        properties.getProperty("password"));
                freeConnections.offer(connection);
            }
        } catch (IOException e) {
            logger.fatal("Couldn't load properties", e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Couldn't load driver class", e);
        } catch (SQLException e) {
            logger.fatal("Couldn't create connections", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                instance = new ConnectionPool();
            }
        }

        return instance;
    }

    public Connection takeConnection() {
        Connection connection = null;

        //TODO IF NO FREE CONNECTIONS FATAL ERROR
        try {
            connection = freeConnections.take();
            busyConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Interruption while waiting for connection", e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            if (busyConnections.remove(connection))
                freeConnections.offer(connection);
        }
    }

    public void destroy() {
        closeConnections(freeConnections);
        closeConnections(busyConnections);
        deregisterDriver();
    }

    private void closeConnections(BlockingQueue<Connection> queue) {
        for (Connection connection : queue) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Exception while closing a connection", e);
            }
        }
    }

    private void deregisterDriver() {
        Driver driver=null;
        
        try {
            driver = DriverManager.getDriver(properties.getProperty("url"));
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            logger.error("Exception while deregistering the driver", e);
        }
    }
}