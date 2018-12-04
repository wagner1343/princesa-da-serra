package princesadaserra.java.persistence.repository.trip;

import princesadaserra.java.core.trip.Trip;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.ConnectionPoolDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//FIXME TEM QUE VER ALI QUE EU COMENTEI UMA QUERY POR CONTA DO BANG DO USUARIO
public class TripRepository implements Repository<Trip, Long> {

    TripMapper mapper;
    private ConnectionPoolDataSource dataSource;

    public TripRepository(ConnectionPoolDataSource dataSource) {

        this.dataSource = dataSource;
        mapper = new TripMapper();
    }

    public Connection getConnection() throws SQLException {

        return dataSource.getPooledConnection().getConnection();
    }

    @Override
    public Trip find(Long key) {

        Trip trip = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                trip = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
    }

    public void updateFinish(Trip trip){

        try(Connection connection = getConnection()){

            SQLQueries.updateFinish(connection, trip).executeUpdate();
        } catch (SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public void update(Trip trip) {

        try(Connection conn = getConnection()){

            SQLQueries.update(conn, trip).executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> list(){

        List<Trip> trips = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                trips.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public Trip add(Trip trip) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, trip).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return trip;
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
    public List<Trip> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<Trip> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_TRIP = "INSERT INTO trips (dateSail, estimatedArrivalDate,id_user_driver, id_bus, id_route) VALUES(?, ?, ?, ?, ?)";
        private static final String DELETE_TRIP = "DELETE FROM trips WHERE id_trip = ?";
        private static final String UPDATE_TRIP = "UPDATE trips set dateSail = ?, estimatedArrivalDate = ?, id_user_driver = ?, id_bus = ?, id_route = ? where id_trip = ?";
        private static final String UPDATE_FINISH_DATE_TRIP = "UPDATE trips set dateArrival = ? where id_trip = ?";
        private static final String SELECT_TRIP = "SELECT * from trips t join users u on t.id_user_driver = u.id_user join buses b on t.id_bus = b.id_bus join routes r on t.id_route = r.id_route join models m2 on b.id_model = m2.id_model where id_trip = ?";
        private static final String SELECT_ALL_TRIP = "SELECT * from trips t join users u on t.id_user_driver = u.id_user join buses b on t.id_bus = b.id_bus join routes r on t.id_route = r.id_route join models m2 on b.id_model = m2.id_model";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            return conn.prepareStatement(SQLQueries.SELECT_ALL_TRIP);
        }

        public static PreparedStatement updateFinish(Connection conn, Trip trip) throws SQLException{

            PreparedStatement stmt =  conn.prepareStatement(SQLQueries.UPDATE_FINISH_DATE_TRIP);
            stmt.setTimestamp(1, trip.getTimeFinish());
            stmt.setLong(2, trip.getId());

            return stmt;
        }

        public static PreparedStatement update(Connection conn, Trip trip) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.UPDATE_TRIP);
            statement.setTimestamp(1, trip.getTimeStart());
            statement.setTimestamp(2, trip.getTimeExpected());
            statement.setLong(3, trip.getDriver().getId());
            statement.setLong(4, trip.getBus().getId());
            statement.setLong(5, trip.getRoute().getId());
            statement.setLong(6, trip.getId());

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_TRIP);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, Trip trip) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_TRIP);
            statement.setTimestamp(1, trip.getTimeStart());
            statement.setTimestamp(2, trip.getTimeExpected());
            System.out.println("trip.getDriver() != null = " + trip.getDriver() != null);
            statement.setLong(3, trip.getDriver().getId());
            statement.setLong(4, trip.getBus().getId());
            statement.setLong(5, trip.getRoute().getId());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_TRIP);
            statement.setLong(1, key);

            return statement;
        }
    }
}
