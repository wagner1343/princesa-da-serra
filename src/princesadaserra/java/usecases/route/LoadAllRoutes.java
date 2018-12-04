package princesadaserra.java.usecases.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.persistence.repository.connection.PDSDatabaseConnectionPool;
import princesadaserra.java.persistence.repository.route.RouteRepository;
import princesadaserra.java.util.threading.Task;

import javax.sql.ConnectionPoolDataSource;
import java.util.ArrayList;
import java.util.List;

public class LoadAllRoutes extends Task<Boolean, List<Route>, Integer> {

    private ConnectionPoolDataSource connectionPool = null;

    public LoadAllRoutes(ConnectionPoolDataSource connectionPool){

        this.connectionPool = connectionPool;
    }

    @Override
    protected List<Route> execute(Boolean useless){

        RouteRepository routeRepository = null;
        List<Route> routees = new ArrayList<>();
        try{

            routeRepository = new RouteRepository(connectionPool);
            routees = routeRepository.list();
            System.out.println("List all routees success");
            setSuccess();
        } catch(Exception e){

            e.printStackTrace();
        }
        return routees;
    }
}
