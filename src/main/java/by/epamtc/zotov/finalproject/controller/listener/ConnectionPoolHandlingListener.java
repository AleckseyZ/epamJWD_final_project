package by.epamtc.zotov.finalproject.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;

public class ConnectionPoolHandlingListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}