package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

public class UpdateModel extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Model model = null;

    public UpdateModel(PDSDatabaseConnectionPool connectionPool, Model model){

        this.connectionPool = connectionPool;
        this.model = model;
    }

    @Override
    protected Boolean execute(String useless){

        ModelRepository modelRepository = null;

        try{

            modelRepository = new ModelRepository(connectionPool);
            modelRepository.update(model);
            System.out.println("Update model success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
