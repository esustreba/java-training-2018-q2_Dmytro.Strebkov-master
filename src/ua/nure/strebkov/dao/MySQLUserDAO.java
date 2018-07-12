package ua.nure.strebkov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import ua.nure.strebkov.entity.User;

public class MySQLUserDAO implements UserDAO {

	final static Logger logger = Logger.getLogger(MySQLUserDAO.class);
	
	@Override
	public Integer insertUser(User user) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = ConnectionPool.getConnection();
			statement = connection.prepareStatement("INSERT INTO users "
					+ "(email, password, firstName, surname,phone) " + "values " + "(?, ?, ?, ?, ?)");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getSurname());
			statement.setString(5, user.getPhone());

			return statement.executeUpdate();

		} catch (Exception e) {
			return -1;

		} finally {

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

	}

	@Override
	public User findUser(User user) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;

		try {

			connection = ConnectionPool.getConnection();
			statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());

			resultSet = statement.executeQuery();

			if (resultSet.first()) {

				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("firstname"));
				user.setSurname(resultSet.getString("surname"));
				user.setPhone(resultSet.getString("phone"));
				int isActivated = resultSet.getInt("isActivated");

				user.setActivated((isActivated == 0) ? false : true);

				return user;
			}

		} catch (SQLException e) {
			logger.error("Error", e);
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

	@Override
	public boolean updateUser(User user) {
		return false;
	}
	
	public Iterable<User> countUsers1(){
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		ArrayList<User> users1 = null;
	
		try {
			connection = ConnectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM users WHERE isActivated = 1");
			
			while(resultSet.next()) {
				if(users1 == null) {
					users1 = new ArrayList<User>();
				}
				
				User user = new User();
				user.setName(resultSet.getString("firstName"));
				users1.add(user);
		}
		return users1;
	} catch (SQLException e) {
		logger.error("Error", e);
	}finally {
		
		if (resultSet != null) {
			try {
				resultSet.close();
			}catch (SQLException e) {
				logger.error("Cannot close resultSet", e);
			}
		}
		
		if (statement != null) {
			try {
				statement.close();
			}catch (SQLException e) {
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
	
	public Iterable<User> countUsers0(){
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		ArrayList<User> users0 = null;
		
		try {
			connection = ConnectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM users WHERE isActivated = 0");
			
			while(resultSet.next()) {
				if(users0 == null) {
					users0 = new ArrayList<User>();
				}
				
				User user = new User();
				user.setName(resultSet.getString("firstName"));
				users0.add(user);
		}
		return users0;
	} catch (SQLException e) {
		logger.error("Error", e);
	}finally {
		
		if (resultSet != null) {
			try {
				resultSet.close();
			}catch (SQLException e) {
				logger.error("Cannot close resultSet", e);
			}
		}
		
		if (statement != null) {
			try {
				statement.close();
			}catch (SQLException e) {
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

	
	@Override
	public Iterable<User> findUnconfirmed() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		ArrayList<User> users = null;
		
		try {
			connection = ConnectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users WHERE isActivated = 0");
			
			while(resultSet.next()) {
				if(users == null) {
					users = new ArrayList<User>();
				}
				User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setEmail(resultSet.getString("email"));
					user.setName(resultSet.getString("firstName"));
					user.setSurname(resultSet.getString("surname"));
					user.setPhone(resultSet.getString("phone"));
					users.add(user);
					
			}
			
			return users;
		}catch (SQLException e) {
			logger.error("Error", e);
		}finally {
			
			if (resultSet != null) {
				try {
					resultSet.close();
				}catch (SQLException e) {
					logger.error("Cannot close resultSet", e);
				}
			}
			
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException e) {
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

	@Override
	public int activateBatch(Iterable<User> users) {
		Connection connection = null;
		PreparedStatement statement = null;

		if (users != null) {
			try {

				connection = ConnectionPool.getConnection();

				statement = connection.prepareStatement("UPDATE users SET isActivated = 1 WHERE id = ?");

				Iterator<User> iterator = users.iterator();

				while (iterator.hasNext()) {
					statement.setInt(1, iterator.next().getId());
					statement.addBatch();
				}

				int[] result = statement.executeBatch();
				int count = 0;

				System.out.println("Update result:");
				for (int i = 0; i < result.length; ++i) {
					if (result[i] == 1) {
						++count;
					} else {
						// TODO: Add exception here.
						System.out.println("Update command #" + i + "failed: " + result[i]);
					}
				}

				return count;

			} catch (SQLException e) {
				logger.error("error", e);
			} finally {
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
		}

		return 0;
	}

	@Override
	public int deleteBatch(Iterable<User> users) {
		Connection connection = null;
		PreparedStatement statement = null;

		if (users != null) {
			try {

				// Get Connection and create PreparedStatement.
				connection = ConnectionPool.getConnection();

				statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");

				Iterator<User> iterator = users.iterator();

				while (iterator.hasNext()) {
					statement.setInt(1, iterator.next().getId());
					statement.addBatch();
				}

				int[] result = statement.executeBatch();
				int count = 0;

				System.out.println("Delete result:");
				for (int i = 0; i < result.length; ++i) {
					if (result[i] == 1) {
						++count;
					} else {
						// TODO: Add exception here.
						System.out.println("Delete command #" + i + "failed: " + result[i]);
					}
				}

				return count;

			} catch (SQLException e) {
				logger.error("Error", e);
			} finally {
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
		}

		return 0;
	}
}


