package ua.nure.strebkov.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionPool {

	final static Logger logger = Logger.getLogger(ConnectionPool.class);
	private static DataSource dataSource;

	public static synchronized Connection getConnection() {
		if (dataSource == null) {
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				dataSource = (DataSource) envContext.lookup("jdbc/railway_system");
			} catch (NamingException e) {
				logger.error("Cannot find the data source", e);
			}
		}

		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Cannot establish connection", e);
			return null;
		}
	}

}
