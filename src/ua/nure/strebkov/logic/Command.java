package ua.nure.strebkov.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.strebkov.dao.DAOFactory;

public abstract class Command {
	protected static DAOFactory daoFactory;

	public static void setDAOFactory(DAOFactory factory) {
		daoFactory = factory;
	}

	public abstract void execute(HttpServletRequest request, HttpServletResponse response);

}
