package princesadaserra.java.persistence.repository.vehicle;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.persistence.connection.AuthenticatedConnectionProvider;
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

//TODO ARRUMAR A CONEXAO DE TODAS AS OUTRAS REP

public class ModelRepository implements Repository<Model, Long> {

    ModelMapper mapper;
    private ConnectionPoolDataSource dataSource;

    public ModelRepository(ConnectionPoolDataSource dataSource) {

        this.dataSource = dataSource;
        mapper = new ModelMapper();
    }

    public Connection getConnection() throws SQLException {

        return dataSource.getPooledConnection().getConnection();
    }

    @Override
    public Model find(Long key) {

        Model model = null;
        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();
            if(result.next())
                model = mapper.map(result);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return model;
    }

    @Override
    public void update(Model model) {

        try(Connection conn = getConnection()){

             SQLQueries.update(conn, model).executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Model> list(){

        List<Model> models = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet result = SQLQueries.findAll(conn).executeQuery();
            while(result.next())
                models.add(mapper.map(result));

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return models;
    }

    @Override
    public Model add(Model model) {

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLQueries.insert(conn, model).execute();
            conn.commit();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return model;
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
    public List<Model> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<Model> delete(Specification specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_MODEL = "INSERT INTO models (amntAxis, amntSeats) VALUES(?, ?)";
        private static final String DELETE_MODEL = "DELETE FROM models WHERE id_model = ?";
        private static final String UPDATE_MODEL = "UPDATE models set amntAxis = ?, amntSeats = ? where id_model = ?";
        private static final String SELECT_MODEL = "SELECT * from models where id_model = ?";
        private static final String SELECT_ALL_MODEL = "SELECT * from models";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLQueries.SELECT_ALL_MODEL);

            return stmt;
        }

        public static PreparedStatement update(Connection conn, Model model) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.UPDATE_MODEL);
            statement.setInt(1, model.getAmntAxis());
            statement.setInt(2, model.getAmntSeats());
            statement.setLong(3, model.getId());

            return statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_MODEL);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement insert(Connection conn, Model model) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_MODEL);
            statement.setInt(1, model.getAmntAxis());
            statement.setInt(2, model.getAmntSeats());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_MODEL);
            statement.setLong(1, key);

            return statement;
        }
    }
}
