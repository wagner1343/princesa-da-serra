package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

public class CreateNewModel extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Model model = null;

    public CreateNewModel(PDSDatabaseConnectionPool connectionPool, Model model){

        this.connectionPool = connectionPool;
        this.model = model;
    }

    @Override
    protected Boolean execute(String useless) {

        ModelRepository modelRepository = null;
        try{

            modelRepository = new ModelRepository(connectionPool);
            modelRepository.add(model);
            System.out.println("Add model success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
