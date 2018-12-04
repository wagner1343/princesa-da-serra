package princesadaserra.java.usecases.vehicle;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import java.util.ArrayList;
import java.util.List;

public class LoadAllModels extends Task<Boolean, List<Model>, Integer> {

    private ConnectionPoolDataSource connectionPool = null;

    public LoadAllModels(ConnectionPoolDataSource connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<Model> execute(Boolean useless){

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
