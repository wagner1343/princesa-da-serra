package princesadaserra.java.persistence.repository.route;

import princesadaserra.java.core.route.City;
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

public class CityRepository implements Repository<City, Long> {

    CityMapper mapper;

    private ConnectionPoolDataSource dataSource;

    public CityRepository(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        mapper = new CityMapper();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getPooledConnection().getConnection();
    }
    @Override
    public City find(Long key) {

        City city = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                city = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return city;
    }

    @Override
    public void update(City city) {

        try(Connection conn = getConnection()){

            SQLQueries.update(conn, city).executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<City> list(){

        List<City> cities = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                cities.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City add(City city) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, city).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return city;
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
    public List<City> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<City> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_CITY = "INSERT INTO cities (name) VALUES(?)";
        private static final String DELETE_CITY = "DELETE FROM cities WHERE id_city = ?";
        private static final String UPDATE_CITY = "UPDATE cities set name = ? where id_city = ?";
        private static final String SELECT_CITY = "SELECT * from cities where id_city = ?";
        private static final String SELECT_ALL_CITY = "SELECT * from cities";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            return conn.prepareStatement(SQLQueries.SELECT_ALL_CITY);
        }

        public static PreparedStatement update(Connection conn, City city) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.UPDATE_CITY);
            statement.setString(1, city.getName());
            statement.setLong(2, city.getId());

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_CITY);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, City city) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_CITY);
            statement.setString(1, city.getName());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_CITY);
            statement.setLong(1, key);

            return statement;
        }
    }
}
