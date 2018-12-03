package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.util.threading.Task;

public class GetRouteByRouteId extends Task<String, Route, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private Long key = null;

    public GetRouteByRouteId(PDSDatabaseConnectionPool connectionPool, Long key){

        this.connectionPool = connectionPool;
        this.key = key;
    }

    @Override
    protected Route execute(String useless){

        RouteRepository routeRepository = null;
        Route route = null;

        try{

            routeRepository = new RouteRepository(connectionPool);
            route = new Route();
            route = routeRepository.find(key);
            System.out.println("Find route by key success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return route;
    }
}
