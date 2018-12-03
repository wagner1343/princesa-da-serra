package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.util.threading.Task;

public class GetRouteByRouteName extends Task<String, Route, Integer> {

    private PDSDatabaseConnectionPool connectionPool = null;
    private String name = null;

    public GetRouteByRouteName(PDSDatabaseConnectionPool connectionPool, String name){

        this.connectionPool = connectionPool;
        this.name = name;
    }

    @Override
    protected Route execute(String useless){

        RouteRepository routeRepository = null;
        Route route = null;

        try{

            routeRepository = new RouteRepository(connectionPool);
            route = new Route();
            route = routeRepository.findByName(name);
            System.out.println("Find route by name success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return route;
    }
}
