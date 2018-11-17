package princesadaserra.java.tests;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.trip.TripRepository;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;

public class TestandoErrosDoBanco {

    public static void main(String[] args){

        PDSDatabaseConnectionPool connectionPool = new PDSDatabaseConnectionPool("postgres", "123456");
        Model m = new Model();
        ModelRepository modelRepository = new ModelRepository(connectionPool);
        Long x = new Long(3);
        m = modelRepository.find(x);
        TripRepository tripRepository = new TripRepository(connectionPool);
        Trip t = tripRepository.find(x);

    }
}
