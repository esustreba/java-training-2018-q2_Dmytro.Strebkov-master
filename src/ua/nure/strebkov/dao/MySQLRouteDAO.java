package ua.nure.strebkov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.strebkov.entity.Route;

public class MySQLRouteDAO implements RouteDAO {

	final static Logger logger = Logger.getLogger(MySQLRouteDAO.class);

	@Override
	public Iterable<Route> findRoutes(Route route) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		ArrayList<Route> routes = null;

		try {

			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM routes WHERE departureStation = ?"
					+ "AND destinationStation = ? AND departureTime > ?");
			statement.setString(1, route.getDepartureStation());
			statement.setString(2, route.getDestinationStation());
			statement.setTime(3, route.getDepartureTime());

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				if (routes == null) {
					routes = new ArrayList<Route>();
				}

				Route resRoute = new Route();

				// Retrieve information from the result set.
				resRoute.setId(resultSet.getString("id"));
				resRoute.setDepartureStation(resultSet.getString("departureStation"));
				resRoute.setDestinationStation(resultSet.getString("destinationStation"));
				resRoute.setDepartureTime(Time.valueOf(resultSet.getString("departureTime")));
				resRoute.setDestinationTime(Time.valueOf(resultSet.getString("destinationTime")));
				resRoute.setSuitPlaces(resultSet.getInt("suitePlaces"));
				resRoute.setCoupePlaces(resultSet.getInt("coupePlaces"));
				resRoute.setBerthPlaces(resultSet.getInt("berthPlaces"));

				routes.add(resRoute);
				
			}

			return routes;
			
		} catch (SQLException e) {
			logger.error("error", e);
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					logger.error("Cannot close resultSet", e);
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Cannot close statement", e);
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error("Cannot close connection", e);
				}
			}
		}

		return null;
	}
}
