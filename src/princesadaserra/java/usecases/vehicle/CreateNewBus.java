package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;

public class CreateNewBus extends Task<String, Bus, Integer> {

    private ConnectionPoolDataSource connectionPool = null;
    private Bus bus = null;

    public CreateNewBus(ConnectionPoolDataSource connectionPool, Bus bus){

        this.connectionPool = connectionPool;
        this.bus = bus;
    }

    @Override
    protected Bus execute(String useless) {

        BusRepository busRepository = null;
        try{

            busRepository = new BusRepository(connectionPool);
            busRepository.add(bus);
            System.out.println("Add vehicle success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return null;
        }
        return bus;
    }
}
