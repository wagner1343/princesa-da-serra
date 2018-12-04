package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.City;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.CityRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import java.util.ArrayList;
import java.util.List;

public class LoadAllCities extends Task<Boolean, List<City>, Integer> {

    private ConnectionPoolDataSource connectionPool = null;

    public LoadAllCities(ConnectionPoolDataSource connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<City> execute(Boolean useless){

        CityRepository cityRepository = null;
        List<City> cityes = new ArrayList<>();
        try{

            cityRepository = new CityRepository(connectionPool);
            cityes = cityRepository.list();
            System.out.println("List all cityes success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return cityes;
    }
}
