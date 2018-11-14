package princesadaserra.java.usecases.model;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.vehicle.ModelRepository;
import princesadaserra.java.util.threading.Task;

//FIXME NAO SEI OQ POR NO TIPO DE RETORNO, PQ ESSA FUNÇAO NAO RETORNA NADA

public class CreateNewModel extends Task<Model, String, Integer> {

    private String user;
    private String password;

    public CreateNewModel(String user, String password){

        this.user = user;
        this.password = password;
    }

    @Override
    protected String execute(Model model) {

        PDSDatabaseConnectionPool connectionPool = new PDSDatabaseConnectionPool(user, password);
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
