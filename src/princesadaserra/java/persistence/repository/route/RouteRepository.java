package princesadaserra.java.persistence.repository.route;

import princesadaserra.java.core.route.Route;
import princesadaserra.java.core.route.Segment;
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

//FIXME TEM Q VER COMO VAI FAZER A PARTE DE PROCURAR POR SEGMENTO DE PARTIDA E SAIDA. VER EM OUTRAS CLASSES TAMBEM

public class RouteRepository implements Repository<Route, Long> {

    RouteMapper mapper;
    SegmentMapper mapperS;
    private ConnectionPoolDataSource dataSource;

    public RouteRepository(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        mapper = new RouteMapper();
        mapperS = new SegmentMapper();
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

    public void addRouteSegment(Route route){

        try(Connection connection = getConnection()){

            connection.setAutoCommit(false);
            for(int x = 0; x < route.getSegments().size(); x++){

                SQLQueries.insertRouteSegment(connection, route.getId(), route.getSegments().get(x).getId(), x).execute();
            }
            connection.commit();
        } catch (SQLException e){

            e.printStackTrace();
        }
    }

    public Route findByName(String nome){

        Route route = null;

        try(Connection connection = getConnection()) {

            ResultSet resultSet = SQLQueries.findByName(connection, nome).executeQuery();
            if(resultSet.next())
                route = mapper.map(resultSet);
        } catch (SQLException e){

            e.printStackTrace();
        }
        return route;
    }

    public Route fullRoute(Long key){

        Route route = null;
        List<Segment> segments = new ArrayList<>();

        try(Connection connection = getConnection()){

            route = new Route();
            ResultSet resultSet = SQLQueries.findFullRoute(connection, key).executeQuery();
            System.out.println(key);
            while (resultSet.next()){
                segments.add(mapperS.map(resultSet));

            }
        } catch (Exception e){

            e.printStackTrace();
        }
        route.setSegments(segments);
        return route;
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
        private static final String INSERT_ROUTE_SEGMENT = "INSERT INTO routes_segments values (?, ?, ?)";
        private static final String SELECT_BY_NAME_ROUTE = "SELECT * FROM routes where name = ?";
        private static final String SELECT_ALL_ROUTE_SEGMENT = "SELECT *, r.id_route as id_routeR, r.name as nameR, cs.id_city as id_cityS, cs.name as nameS, ca.id_city as id_cityA, ca.name as nameA from routes_segments rs join routes r on rs.id_route = r.id_route join segments s on s.id_segment = rs.id_segment join cities cs on s.id_citySail = cs.id_city join cities ca on s.id_cityArrival = ca.id_city where rs.id_route = ? order by 3 asc";

        public static PreparedStatement findFullRoute(Connection connection, Long key) throws SQLException{

            PreparedStatement statement = connection.prepareStatement(SQLQueries.SELECT_ALL_ROUTE_SEGMENT);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement findByName(Connection connection, String nome) throws  SQLException{

            PreparedStatement statement = connection.prepareStatement(SQLQueries.SELECT_BY_NAME_ROUTE);
            statement.setString(1, nome);

            return statement;
        }

        public static PreparedStatement insertRouteSegment(Connection connection, Long route, Long segment, int pos) throws SQLException{

            PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_ROUTE_SEGMENT);
            statement.setLong(1, route);
            statement.setLong(2, segment);
            statement.setInt(3, pos);

            return  statement;
        }

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            return conn.prepareStatement(SQLQueries.SELECT_ALL_ROUTE);
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
