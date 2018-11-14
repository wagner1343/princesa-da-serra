package princesadaserra.java.usecases.model;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

//FIXME NAO SEI OQ POR NO TIPO DE RETORNO, PQ ESSA FUNÇAO NAO RETORNA NADA

public class CreateNewModel extends Task<Model, String, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;

    public CreateNewModel(PDSDatabaseConnectionPool connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected String execute(Model model) {

        ModelRepository modelRepository = null;
        try{

            modelRepository = new ModelRepository(connectionPool);
            modelRepository.add(model);
            System.out.println("Add model sucess");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return "";
    }
}
