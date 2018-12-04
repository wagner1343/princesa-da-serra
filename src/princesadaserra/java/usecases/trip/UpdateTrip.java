package princesadaserra.java.usecases.trip;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.util.threading.Task;

public class UpdateTrip extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Trip trip = null;

    public UpdateTrip(PDSDatabaseConnectionPool connectionPool, Trip trip){

        this.connectionPool = connectionPool;
        this.trip = trip;
    }

    @Override
    protected Boolean execute(String useless){

        TripRepository tripRepository = null;

        try{

            tripRepository = new TripRepository(connectionPool);
            tripRepository.update(trip);
            System.out.println("Update trip success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
