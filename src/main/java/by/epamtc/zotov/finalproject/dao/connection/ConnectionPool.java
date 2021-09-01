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
import by.epamtc.zotov.finalproject.exception.ConnectionPoolException;

/**
 * Thread-safe singlton. Manages and distributes connections to the database via
 * JDBC driver.
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static final String DRIVER_PROPERTY_NAME = "driver";
    private static final String CAPACITY_PROPERTY_NAME = "capacity";
    private static final String URL_PROPERTY_NAME = "url";
    private static final String USER_PROPERTY_NAME = "user";
    private static final String PASSWORD_PROPERTY_NAME = "password";

    private static ConnectionPool instance;
    private Properties properties;
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> busyConnections;

    private ConnectionPool() {
        Connection connection = null;
        properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE));
            Class.forName(properties.getProperty(DRIVER_PROPERTY_NAME));

            int poolCapacity = Integer.parseInt(properties.getProperty(CAPACITY_PROPERTY_NAME));
            freeConnections = new ArrayBlockingQueue<Connection>(poolCapacity);
            busyConnections = new ArrayBlockingQueue<Connection>(poolCapacity);

            for (int i = 0; i < poolCapacity; i++) {
                connection = DriverManager.getConnection(properties.getProperty(URL_PROPERTY_NAME),
                        properties.getProperty(USER_PROPERTY_NAME), properties.getProperty(PASSWORD_PROPERTY_NAME));
                freeConnections.offer(connection);
            }
        } catch (IOException e) {
            logger.fatal("Couldn't load properties", e);
            throw new ConnectionPoolException(e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Couldn't load driver class", e);
            throw new ConnectionPoolException(e);
        } catch (SQLException e) {
            logger.fatal("Couldn't create connections", e);
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Initializes ConnectionPool object using properties in db.properties file
     * 
     * @throws ConnectionPoolException Runtime exception occurs if <br>
     *                                 Impossible to load properties <br>
     *                                 Impossible to load JDBC driver <br>
     *                                 Exception occured while creating connections
     */
    public static synchronized void initialize() {
        instance = new ConnectionPool();
    }

    /**
     * Returns reference to the instance of connection pool. If pool is yet to be
     * initialized, will return null
     */
    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Takes connetion object from pool of free connections and returns reference
     * for that connection object.
     * 
     * @throws InterruptedException If interrupted while waiting for connection
     */
    public Connection takeConnection() throws InterruptedException {
        Connection connection = null;
        connection = freeConnections.take();
        busyConnections.offer(connection);

        return connection;
    }

    /**
     * Return connection back to the pool of available connections
     * 
     * @param connection connaction that is to be released
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            if (busyConnections.remove(connection))
                freeConnections.offer(connection);
        }
    }

    /**
     * Destroys connection pool object, releasing memory
     */
    public synchronized void destroy() {
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
        Driver driver = null;

        try {
            driver = DriverManager.getDriver(properties.getProperty(URL_PROPERTY_NAME));
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            logger.error("Exception while deregistering the driver", e);
        }
    }
}