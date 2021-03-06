package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;

public class CreateNewTrip extends Task<String, Boolean, Integer> {

    private ConnectionPoolDataSource connectionPool = null;
    private Trip trip = null;

    public CreateNewTrip(ConnectionPoolDataSource connectionPool, Trip trip){

        this.connectionPool = connectionPool;
        this.trip = trip;
    }

    @Override
    protected Boolean execute(String useless) {

        TripRepository tripRepository = null;
        try{

            tripRepository = new TripRepository(connectionPool);
            tripRepository.add(trip);
            System.out.println("Add trip success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
