package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.util.threading.Task;

import java.util.ArrayList;
import java.util.List;

public class LoadFullRoute extends Task<Boolean, Route, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public LoadFullRoute(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Route execute(Boolean useless){

        Route route = null;
        RouteRepository routeRepository = null;
        try{

            routeRepository = new RouteRepository(connectionPool);
            route = routeRepository.fullRoute(key);
            System.out.println("List all routees success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return route;
    }
}
