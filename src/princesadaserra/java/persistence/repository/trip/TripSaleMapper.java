package princesadaserra.java.persistence.repository.trip;

import princesadaserra.java.core.route.City;
import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.core.user.User;
import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//FIXME ESSE MAPPER

public class TripSaleMapper implements Mapper<ResultSet, TripSale>{

    @Override
    public TripSale map(ResultSet result){

        TripSale tripSale = null;
        User employee = null;
        User user = null;
        User driver = null;
        City start = null;
        City finish = null;
        Trip trip = null;
        Bus bus = null;
        Route route = null;

        try{

            employee = new User();
            user = new User();
            start = new City();
            finish = new City();
            trip = new Trip();
            bus = new Bus();
            tripSale = new TripSale();
            driver = new User();
            route = new Route();

            tripSale.setId(result.getLong("id_sell"));
            tripSale.setTotalValue(result.getDouble("totalValue"));
            tripSale.setSeatNumber(result.getInt("numberSeat"));
            employee.setId(result.getLong("id_employee"));
            user.setId(result.getLong("id_user"));
            start.setId(result.getLong("id_citySail"));
            finish.setId(result.getLong("id_citySail"));
            trip.setId(result.getLong("id_trip"));
            employee.setId(result.getLong("id_usere"));
            employee.setFirstName(result.getString("first_namee"));
            user.setId(result.getLong("id_useru"));
            user.setFirstName(result.getString("first_nameu"));
            start.setId(result.getLong("id_citysail"));
            start.setName(result.getString("names"));
            finish.setId(result.getLong("id_cityarrival"));
            finish.setName(result.getString("namea"));
            bus.setId(result.getLong("id_bus"));
            driver.setId(result.getLong("id_user_driver"));
            trip.setId(result.getLong("id_trip"));
            trip.setBus(bus);
            trip.setDriver(driver);
            trip.setTimeExpected(result.getTime("estimatedarrivaltime"));
            trip.setTimeFinish(result.getTime("datearrival"));
            trip.setTimeStart(result.getTime("datesail"));
            route.setId(result.getLong("id_route"));
            route.setName(result.getString("namer"));
            trip.setRoute(route);
            tripSale.setCityDestination(finish);
            tripSale.setCityOrigin(start);
            tripSale.setEmployee(employee);
            tripSale.setPassenger(user);
            tripSale.setTrip(trip);

        } catch(SQLException e){

            e.printStackTrace();
        }
        return tripSale;
    }
}
