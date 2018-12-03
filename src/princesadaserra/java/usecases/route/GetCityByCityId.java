package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.City;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.CityRepository;
import princesadaserra.java.util.threading.Task;

public class GetCityByCityId extends Task<String, City, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetCityByCityId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected City execute(String useless){

        CityRepository cityRepository = null;
        City city = null;

        try{

            cityRepository = new CityRepository(connectionPool);
            city = new City();
            city = cityRepository.find(key);
            System.out.println("Find city by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return city;
    }
}
