package princesadaserra.java.persistence.repository.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements Mapper<ResultSet, Route>{

    @Override
    public Route map(ResultSet result){

        Route route = null;
        try{

            route = new Route();
            route.setId(result.getLong("id_route"));
            route.setName(result.getString("name"));
        } catch(SQLException e){

            e.printStackTrace();
        }
        return route;
    }
}
