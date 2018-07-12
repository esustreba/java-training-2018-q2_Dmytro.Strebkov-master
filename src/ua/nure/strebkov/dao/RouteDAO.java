package ua.nure.strebkov.dao;

import ua.nure.strebkov.entity.Route;

public interface RouteDAO {
	Iterable<Route> findRoutes(Route route);    /*
												 * Find the route by departure and
												 * destination stations, departure time.
												 */

}
