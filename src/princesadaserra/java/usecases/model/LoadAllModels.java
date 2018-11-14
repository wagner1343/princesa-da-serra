package princesadaserra.java.usecases.model;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadAllModels extends Task<String, List<Model>, Integer> {

    private String user;
    private String password;

    public LoadAllModels(String user, String password){

        this.user = user;
        this.password = password;
    }

    @Override
    protected List<Model> execute(String x){

        PDSDatabaseConnectionPool connectionPool = new PDSDatabaseConnectionPool(user, password);
        ModelRepository modelRepository = null;
        List<Model> models = new ArrayList<>();
        try{

            modelRepository = new ModelRepository(connectionPool);
            models = modelRepository.list();
            System.out.println("List all models success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return models;
    }
}
