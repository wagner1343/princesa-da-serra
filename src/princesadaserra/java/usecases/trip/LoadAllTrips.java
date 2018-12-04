package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadAllTrips extends Task<Boolean, List<Trip>, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;

    public LoadAllTrips(PDSDatabaseConnectionPool connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<Trip> execute(Boolean useless){

        TripRepository tripRepository = null;
        List<Trip> trips = new ArrayList<>();
        try{

            tripRepository = new TripRepository(connectionPool);
            trips = tripRepository.list();
            System.out.println("List all trips success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return trips;
    }
}
