package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

public class DeleteModel extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key;

    public DeleteModel(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Boolean execute(String useless){

        ModelRepository modelRepository = null;

        try{

            modelRepository = new ModelRepository(connectionPool);
            modelRepository.remove(key);
            System.out.println("Delete model success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
