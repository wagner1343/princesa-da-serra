package princesadaserra.java.persistence.repository.trip;

import princesadaserra.java.core.trip.TripSale;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.ConnectionPoolDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripSaleRepository implements Repository<TripSale, Long> {

    TripSaleMapper mapper;
    private ConnectionPoolDataSource dataSource;

    public TripSaleRepository(ConnectionPoolDataSource dataSource) {

        this.dataSource = dataSource;
        mapper = new TripSaleMapper();
    }

    public Connection getConnection() throws SQLException {

        return dataSource.getPooledConnection().getConnection();
    }

    @Override
    public TripSale find(Long key) {

        TripSale tripSale = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                tripSale = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return tripSale;
    }

    @Override
    public void update(TripSale tripSale) { throw new NotImplementedException(); }

    @Override
    public List<TripSale> list(){

        List<TripSale> sells = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                sells.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return sells;
    }

    @Override
    public TripSale add(TripSale tripSale) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, tripSale).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return tripSale;
    }

    public List<TripSale> findByUser(Long key){

        List<TripSale> tripSales = new ArrayList<>();

        try(Connection conn = getConnection()){

            ResultSet resultSet = SQLQueries.findByUser(conn, key).executeQuery();
            while (resultSet.next())
                tripSales.add(mapper.map(resultSet));

        } catch(SQLException e){

            e.printStackTrace();
        }
        return tripSales;
    }

    public List<TripSale> findByTrip(Long key){

        List<TripSale> tripSales = new ArrayList<>();

        try(Connection conn = getConnection()){

            ResultSet resultSet = SQLQueries.findByTrip(conn, key).executeQuery();
            while (resultSet.next())
                tripSales.add(mapper.map(resultSet));

        } catch(SQLException e){

            e.printStackTrace();
        }
        return tripSales;
    }

    @Override
    public void remove(Long key) { throw new NotImplementedException(); }

    @Override
    public List<TripSale> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<TripSale> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_TRIPSALE = "INSERT INTO sells (id_user, id_employee, id_trip, id_cityArrival, id_citySail, totalValue, numberSeat) VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_TRIPSALE = "SELECT *, uu.id_user as id_useru from sells s join users ue on s.id_user = ue.id_user join users uu on s.id_employee = uu.id_user join cities ca on s.id_cityArrival = ca.id_city join cities cs on s.id_citySail = cs.id_city join trips t on s.id_trip = t.id_trip where id_sell = ?";
        private static final String SELECT_ALL_TRIPSALE = "SELECT *, uu.id_user as id_useru from sells s join users ue on s.id_user = ue.id_user join users uu on s.id_employee = uu.id_user join cities ca on s.id_cityArrival = ca.id_city join cities cs on s.id_citySail = cs.id_city join trips t on s.id_trip = t.id_trip";
        private static final String SELECT_BY_USER_TRIPSALE = "SELECT *, uu.id_user as id_useru from sells s join users ue on s.id_user = ue.id_user join users uu on s.id_employee = uu.id_user join cities ca on s.id_cityArrival = ca.id_city join cities cs on s.id_citySail = cs.id_city join trips t on s.id_trip = t.id_trip where id_user = ?";
        private static final String SELECT_BY_TRIP_TRIPSALE = "SELECT *, uu.id_user as id_useru from sells s join users ue on s.id_user = ue.id_user join users uu on s.id_employee = uu.id_user join cities ca on s.id_cityArrival = ca.id_city join cities cs on s.id_citySail = cs.id_city join trips t on s.id_trip = t.id_trip where id_trip = ?";

        public static PreparedStatement findAll(Connection conn) throws SQLException {

            return conn.prepareStatement(SQLQueries.SELECT_ALL_TRIPSALE);
        }

        public static PreparedStatement findByUser(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_BY_USER_TRIPSALE);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement findByTrip(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_BY_TRIP_TRIPSALE);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_TRIPSALE);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, TripSale tripSale) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_TRIPSALE);
            statement.setLong(1, tripSale.getPassenger().getId());
            statement.setLong(2, tripSale.getEmployee().getId());
            statement.setLong(3, tripSale.getTrip().getId());
            statement.setLong(4, tripSale.getCityDestination().getId());
            statement.setLong(5, tripSale.getCityOrigin().getId());
            statement.setDouble(6, tripSale.getTotalValue());
            statement.setInt(7, tripSale.getSeatNumber());

            return statement;
        }

    }
}
