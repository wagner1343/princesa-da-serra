package princesadaserra.java.persistence.repository.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//FIXME TEM Q VER COMO VAI FAZER A PARTE DA LISTA DE SEGMENTOS POR ROTA, E DE PROCURAR POR SEGMENTO DE PARTIDA E SAIDA

public class RouteRepository implements Repository<Route, Long> {

    RouteMapper mapper;
    private ConnectionPoolDataSource dataSource;

    public RouteRepository(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        mapper = new RouteMapper();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getPooledConnection().getConnection();
    }

    @Override
    public Route find(Long key) {

        Route route = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                route = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }

    @Override
    public void update(Route route) {

        try(Connection conn = getConnection()){

            SQLQueries.update(conn, route).executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Route> list(){

        List<Route> routes = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                routes.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return routes;
    }

    @Override
    public Route add(Route route) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, route).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return route;
    }

    @Override
    public void remove(Long key) {

        try(Connection conn = getConnection()){

            SQLQueries.delete(conn, key).execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Route> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<Route> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {

        private static final String INSERT_ROUTE = "INSERT INTO routes (name) VALUES(?)";
        private static final String DELETE_ROUTE = "DELETE FROM routes WHERE id_route = ?";
        private static final String UPDATE_ROUTE = "UPDATE routes set name = ? where id_route = ?";
        private static final String SELECT_ROUTE = "SELECT * from routes where id_route = ?";
        private static final String SELECT_ALL_ROUTE = "SELECT * from routes";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_ROUTE);

            return stmt;
        }

        public static PreparedStatement update(Connection conn, Route route) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.UPDATE_ROUTE);
            statement.setString(1, route.getName());
            statement.setLong(2, route.getId());

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_ROUTE);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, Route route) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_ROUTE);
            statement.setString(1, route.getName());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_ROUTE);
            statement.setLong(1, key);

            return statement;
        }
    }
}
