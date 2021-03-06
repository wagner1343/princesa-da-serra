package princesadaserra.java.persistence.repository.route;

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

public class SegmentRepository implements Repository<Segment, Long> {

    SegmentMapper mapper;
    private ConnectionPoolDataSource dataSource;

    public SegmentRepository(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        mapper = new SegmentMapper();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getPooledConnection().getConnection();
    }

    @Override
    public Segment find(Long key) {

        Segment segment = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                segment = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return segment;
    }

    public List<Segment> findByCityArrival(Long key){

        List<Segment> segments = new ArrayList<>();
        try(Connection conn = getConnection()){

            ResultSet rs = SQLQueries.findByCityArrival(conn, key).executeQuery();
            while(rs.next())
                segments.add((mapper.map(rs)));

        } catch(SQLException e){

            e.printStackTrace();
        }

        return segments;
    }

    public List<Segment> findByCitySail(Long key){

        List<Segment> segments = new ArrayList<>();
        try(Connection conn = getConnection()){

            ResultSet rs = SQLQueries.findByCitySail(conn, key).executeQuery();
            while(rs.next())
                segments.add((mapper.map(rs)));

        } catch(SQLException e){

            e.printStackTrace();
        }

        return segments;
    }

    @Override
    public void update(Segment segment) {

        try(Connection conn = getConnection()){

            SQLQueries.update(conn, segment).executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Segment> list(){

        List<Segment> segments = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                segments.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return segments;
    }

    @Override
    public Segment add(Segment segment) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, segment).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return segment;
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
    public List<Segment> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<Segment> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_SEGMENT = "INSERT INTO segments (id_citySail, id_cityArrival, estimatedTime, value) VALUES(?, ?, ?, ?)";
        private static final String DELETE_SEGMENT = "DELETE FROM segments WHERE id_segment = ?";
        private static final String UPDATE_SEGMENT = "UPDATE segments SET estimatedTime = ?, value = ? where id_segment = ?";
        private static final String SELECT_SEGMENT = "SELECT *, cs.id_city as id_cityS, cs.name as nameS, ca.id_city as id_cityA, ca.name as nameA from segments s join cities cs on s.id_citySail = cs.id_city join cities ca on s.id_cityArrival = ca.id_city where id_segment = ?";
        private static final String SELECT_ALL_SEGMENT = "SELECT *, cs.id_city as id_cityS, cs.name as nameS, ca.id_city as id_cityA, ca.name as nameA from segments s join cities cs on s.id_citySail = cs.id_city join cities ca on s.id_cityArrival = ca.id_city";
        private static final String SELECT_BY_CITY_ARRIVAL_SEGMENT = "SELECT *, cs.id_city as id_cityS, cs.name as nameS, ca.id_city as id_cityA, ca.name as nameA from segments s join cities cs on s.id_citySail = cs.id_city join cities ca on s.id_cityArrival = ca.id_city where id_cityArrival = ?";
        private static final String SELECT_BY_CITY_SAIL_SEGMENT = "SELECT *, cs.id_city as id_cityS, cs.name as nameS, ca.id_city as id_cityA, ca.name as nameA from segments s join cities cs on s.id_citySail = cs.id_city join cities ca on s.id_cityArrival = ca.id_city where id_citySail = ?";


        public static PreparedStatement findByCityArrival(Connection conn, Long key) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLQueries.SELECT_BY_CITY_ARRIVAL_SEGMENT);
            stmt.setLong(1, key);

            return stmt;
        }

        public static PreparedStatement findByCitySail(Connection conn, Long key) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLQueries.SELECT_BY_CITY_SAIL_SEGMENT);
            stmt.setLong(1, key);

            return stmt;
        }

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            return conn.prepareStatement(SQLQueries.SELECT_ALL_SEGMENT);
        }

        public static PreparedStatement update(Connection conn, Segment segment) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.UPDATE_SEGMENT);
            statement.setTime(1, segment.getTime());
            statement.setDouble(2, segment.getValue());
            statement.setLong(3, segment.getId());

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_SEGMENT);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, Segment segment) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_SEGMENT);
            statement.setLong(1, segment.getCityOrigin().getId());
            statement.setLong(2, segment.getCityDestination().getId());
            statement.setTime(3, segment.getTime());
            statement.setDouble(4, segment.getValue());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_SEGMENT);
            statement.setLong(1, key);

            return statement;
        }
    }
}
