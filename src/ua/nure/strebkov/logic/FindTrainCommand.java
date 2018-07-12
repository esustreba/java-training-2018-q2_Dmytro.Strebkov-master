package ua.nure.strebkov.logic;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.strebkov.dao.RouteDAO;
import ua.nure.strebkov.entity.Route;

public class FindTrainCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Route route = new Route();
		route.setDepartureStation(request.getParameter("departureStation"));
		route.setDestinationStation(request.getParameter("destinationStation"));

		String strDepartureTime = request.getParameter("departureTime") + ":00", strDepartureDate = request
				.getParameter("departureDate");

		Date departureDate = Date.valueOf(strDepartureDate);
		Time departureTime = Time.valueOf(strDepartureTime);

		route.setDepartureTime(departureTime);

		Calendar currentDateTime = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
		Calendar departureDateTime = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));

	//	For some reason we get extra 12 hours every time so we subtract them.
		departureDateTime.setTimeInMillis(departureDate.getTime() + departureTime.getTime() - 12 * 60 * 60 * 1000);

		// Check if it is not too late.
		if (departureDateTime.after(currentDateTime)) {
			RouteDAO routeDAO = daoFactory.getRouteDAO();

			//Find the routes by departure and destination stations, dep. time.
			Iterable<Route> routes = routeDAO.findRoutes(route);

			if (route != null) {
				request.setAttribute("routes", routes);
				request.setAttribute("deparureDate", departureDate);
			}
		}

	}

}
