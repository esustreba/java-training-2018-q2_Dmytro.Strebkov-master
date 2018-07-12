package ua.nure.strebkov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.strebkov.entity.Train;

public class MySQLTrainDAO implements TrainDAO {
	
	final static Logger logger = Logger.getLogger(MySQLTrainDAO.class);

	@Override
	public Train findTrain(Train train) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM trains WHERE routeID = ? AND date = ?");

			statement.setString(1, train.getRouteID());
			statement.setDate(2, train.getDate());

			resultSet = statement.executeQuery();

			if (resultSet.first()) {
				Train resTrain = new Train();

				resTrain.setId(resultSet.getInt("id"));
				resTrain.setRouteID(resultSet.getString("routeID"));
				resTrain.setDate(resultSet.getDate("date"));
				resTrain.setSuiteReserved(resultSet.getInt("suiteReserved"));
				resTrain.setCoupeReserved(resultSet.getInt("coupeReserved"));
				resTrain.setBerthReserved(resultSet.getInt("berthReserved"));

				return resTrain;
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("error", e);
		} finally {

			if (resultSet != null) {
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
