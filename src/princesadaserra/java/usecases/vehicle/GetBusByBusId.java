package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.util.threading.Task;

public class GetBusByBusId extends Task<String, Bus, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetBusByBusId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Bus execute(String useless){

        BusRepository busRepository = null;
        Bus bus = null;

        try{

            busRepository = new BusRepository(connectionPool);
            bus = new Bus();
            bus = busRepository.find(key);
            System.out.println("Find bus by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return bus;
    }
}
