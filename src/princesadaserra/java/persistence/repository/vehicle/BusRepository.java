package princesadaserra.java.persistence.repository.vehicle;

import princesadaserra.java.core.vehicle.Bus;
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

//TODO PROBLEMA COM DATA DO JAVA/SQL

public class BusRepository implements Repository<Bus, Long> {

    BusMapper mapper;

    private ConnectionPoolDataSource dataSource;

    public BusRepository(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        mapper = new BusMapper();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getPooledConnection().getConnection();
    }

    @Override
    public Bus find(Long key) {

        Bus bus = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                bus = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bus;
    }

    @Override
    public void update(Bus bus) {

        try(Connection conn = getConnection()){

            SQLQueries.update(conn, bus).executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Bus> list(){

        List<Bus> buses = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                buses.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return buses;
    }

    @Override
    public Bus add(Bus bus) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, bus).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bus;
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
    public List<Bus> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<Bus> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_BUS = "INSERT INTO buses (id_model, datelastmaintenance) VALUES(?, ?)";
        private static final String DELETE_BUS = "DELETE FROM buses WHERE id_bus = ?";
        private static final String UPDATE_BUS = "UPDATE buses datelastmaintenance = ? where id_bus = ?";
        private static final String SELECT_BUS = "SELECT * from buses b join models m on b.id_model = m.id_model where id_bus = ?";
        private static final String SELECT_ALL_BUS = "SELECT * from buses b join models m on b.id_model = m.id_model";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_BUS);

            return stmt;
        }

        public static PreparedStatement update(Connection conn, Bus bus) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.UPDATE_BUS);
            //statement.setDate(1, bus.getLastMaintenance());
            statement.setLong(2, bus.getId());

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_BUS);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, Bus bus) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_BUS);
            statement.setLong(1, bus.getModel().getId());
            //statement.setDate(2, bus.getLastMaintenance());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_BUS);
            statement.setLong(1, key);

            return statement;
        }
    }
}
