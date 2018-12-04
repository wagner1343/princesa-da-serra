package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.util.threading.Task;

public class GetTripByTripId extends Task<String, Trip, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetTripByTripId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Trip execute(String useless){

        TripRepository tripRepository = null;
        Trip trip = null;

        try{

            tripRepository = new TripRepository(connectionPool);
            trip = new Trip();
            trip = tripRepository.find(key);
            System.out.println("Find trip by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return trip;
    }
}
