package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadAllBuses extends Task<Boolean, List<Bus>, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;

    public LoadAllBuses(PDSDatabaseConnectionPool connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<Bus> execute(Boolean useless){

        BusRepository busRepository = null;
        List<Bus> buses = new ArrayList<>();
        try{

            busRepository = new BusRepository(connectionPool);
            buses = busRepository.list();
            System.out.println("List all buses success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return buses;
    }
}
