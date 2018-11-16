package princesadaserra.java.persistence.repository.trip;

import princesadaserra.java.core.route.City;
import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.core.user.User;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//TODO FAZER O JOIN MAIOR QUE QUE O AP DO VEIGAR NO LATE GAME PRA TERMINAR ESSE MAPPER

public class TripSaleMapper implements Mapper<ResultSet, TripSale>{

    @Override
    public TripSale map(ResultSet result){

        TripSale tripSale = null;
        User employee = null;
        User user = null;
        City start = null;
        City finish = null;
        Trip trip = null;
        try{

            employee = new User();
            user = new User();
            start = new City();
            finish = new City();
            trip = new Trip();
            tripSale = new TripSale();
            tripSale.setId(result.getLong("id_sell"));
            tripSale.setTotalValue(result.getDouble("totalValue"));
            tripSale.setSeatNumber(result.getInt("numberSeat"));
            employee.setId(result.getLong("id_employee"));
            user.setId(result.getLong("id_user"));
            start.setId(result.getLong("id_citySail"));
            finish.setId(result.getLong("id_citySail"));
            trip.setId(result.getLong("id_trip"));
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
