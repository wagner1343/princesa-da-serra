package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LoadTripsBySpecification extends Task<Boolean, List<Trip>, Integer> {

    private ConnectionPoolDataSource connectionPool = null;
    private Predicate<Trip>[] predicates;

    public LoadTripsBySpecification(ConnectionPoolDataSource connectionPool, Predicate<Trip>[] predicates){
        this.predicates = predicates;
        this.connectionPool = connectionPool;
    }

    @Override
    protected List<Trip> execute(Boolean useless){

        TripRepository tripRepository = null;
        List<Trip> trips = new ArrayList<>();
        try{

            tripRepository = new TripRepository(connectionPool);
            trips = tripRepository.list();
            for(Predicate<Trip> p : predicates){
                trips.removeIf(p);
            }
            System.out.println("List all trips success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return trips;
    }
}
