package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

public class GetModelByModelId extends Task<String, Model, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetModelByModelId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Model execute(String useless){

        ModelRepository modelRepository = null;
        Model model = null;

        try{

            modelRepository = new ModelRepository(connectionPool);
            model = new Model();
            model = modelRepository.find(key);
            System.out.println("Find model by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return model;
    }
}
