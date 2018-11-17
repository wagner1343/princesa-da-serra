package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.BusRepository;
import princesadaserra.java.util.threading.Task;

public class DeleteBus extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public DeleteBus(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Boolean execute(String useless){

        BusRepository busRepository = null;

        try{

            busRepository = new BusRepository(connectionPool);
            busRepository.remove(key);
            System.out.println("Delete bus success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
