package princesadaserra.java.persistence.repository.trip;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripMapper implements Mapper<ResultSet, Trip> {

    @Override
    public Trip map(ResultSet result) {

        Trip trip = null;
        User user = null;
        Bus bus = null;
        Route route = null;
        try {

            trip = new Trip();
            user = new User();
            bus = new Bus();
            route = new Route();
            trip.setId(result.getLong("id_trip"));
            trip.setDateStart(result.getDate("dateSail"));
            trip.setDateFinish(result.getDate("dateArrival"));
            trip.setDateExpected(result.getDate("estimatedArrivalDate"));
            user.setId(result.getLong("id_user_driver"));
            user.setCpf(result.getString("cpf"));
            user.setEmail(result.getString("email"));
            user.setFirstName(result.getString("first_name"));
            user.setLastName(result.getString("last_name"));
            user.setPhone(result.getString("phone"));
            bus.setId(result.getLong("id_bus"));
            bus.setLastMaintenance(result.getDate("dateLastmaintenance"));
            route.setId(result.getLong("id_route"));
            //route.setSegments();
            route.setName(result.getString("name"));
            trip.setBus(bus);
            trip.setDriver(user);
            trip.setRoute(route);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }
}
