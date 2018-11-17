package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.util.threading.Task;

public class UpdateBus extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Bus bus = null;

    public UpdateBus(PDSDatabaseConnectionPool connectionPool, Bus bus){

        this.connectionPool = connectionPool;
        this.bus = bus;
    }

    @Override
    protected Boolean execute(String useless){

        BusRepository busRepository = null;

        try{

            busRepository = new BusRepository(connectionPool);
            busRepository.update(bus);
            System.out.println("Update bus success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
