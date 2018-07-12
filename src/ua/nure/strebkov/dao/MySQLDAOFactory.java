package ua.nure.strebkov.dao;



public class MySQLDAOFactory extends DAOFactory {
	
	@Override
	public UserDAO getUserDAO() {
	return new MySQLUserDAO();
	}

	@Override
	public TrainDAO getTrainDAO() {
		// TODO Auto-generated method stub
		return new MySQLTrainDAO();
	}

	@Override
	public RouteDAO getRouteDAO() {
		// TODO Auto-generated method stub
		return new MySQLRouteDAO();
	}

}
