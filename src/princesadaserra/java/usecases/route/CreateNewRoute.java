package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;

public class CreateNewRoute extends Task<String, Boolean, Integer> {

    private ConnectionPoolDataSource connectionPool = null;
    private Route route = null;

    public CreateNewRoute(ConnectionPoolDataSource connectionPool, Route route){

        this.connectionPool = connectionPool;
        this.route = route;
    }

    @Override
    protected Boolean execute(String useless) {

        RouteRepository routeRepository = null;
        try{

            routeRepository = new RouteRepository(connectionPool);
            routeRepository.add(route);
            System.out.println("Add route success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
