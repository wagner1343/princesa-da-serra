package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.City;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.CityRepository;
import princesadaserra.java.util.threading.Task;

public class UpdateCity extends Task<String, Boolean, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private City city = null;

    public UpdateCity(PDSDatabaseConnectionPool connectionPool, City city){

        this.connectionPool = connectionPool;
        this.city = city;
    }

    @Override
    protected Boolean execute(String useless){

        CityRepository cityRepository = null;

        try{

            cityRepository = new CityRepository(connectionPool);
            cityRepository.update(city);
            System.out.println("Update city success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
