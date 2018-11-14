package princesadaserra.java.persistence.repository.role;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements Repository<Role, Long>{
    private ConnectionPoolDataSource dataSource;

    public RoleRepository(ConnectionPoolDataSource dataSource) {

        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getPooledConnection().getConnection();
    }

    RoleMapper mapper;

    public void remove(Long key){

        try(Connection conn = getConnection()){

            SQLqueries.delete(conn, key).execute();
        } catch (SQLException e){

            e.printStackTrace();
        }
    }

    public void update(Role role){

        try(Connection conn = getConnection()){

            SQLqueries.update(conn, role).executeUpdate();
        } catch(SQLException e){

            e.printStackTrace();
        }
    }

    public Role add(Role role){

        try(Connection conn = getConnection()){

            conn.setAutoCommit(false);
            SQLqueries.insert(conn, role).execute();
            conn.commit();
        } catch (SQLException e){

            e.printStackTrace();
        }
        return role;
    }

    public List<Role> list(){

        List<Role> roles = new ArrayList<>();

        try(Connection conn = getConnection()){

            ResultSet rs = SQLqueries.findAll(conn).executeQuery();
            while(rs.next())
                roles.add(mapper.map(rs));

        } catch (SQLException e){

            e.printStackTrace();
        }
        return roles;
    }

    public Role find(Long key){

        Role role = null;

        try(Connection conn = getConnection()){

            ResultSet rs = SQLqueries.findByKey(conn, key).executeQuery();
            if(rs.next())
                role = mapper.map(rs);

        } catch (SQLException e){

            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> find(Specification specification) {
        return null;
    }

    @Override
    public List<Role> delete(Specification specification) {
        return null;
    }

    private static class SQLqueries {

        private static final String INSERT_ROLE = "INSERT INTO roles (role) values (?)";
        private static final String DELETE_ROLE = "DELETE FROM roles where id_role = ?";
        private static final String UPDATE_ROLE = "UPDATE roles set role = ? where id_role = ?";
        private static final String SELECT_BY_KEY_ROLE = "SELECT * from roles where id_role = ?";
        private static final String SELECT_ALL_ROLE = "SELECT * from roles";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            return conn.prepareStatement(SQLqueries.SELECT_ALL_ROLE);
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.SELECT_BY_KEY_ROLE);
            stmt.setLong(1, key);

            return stmt;
        }

        public static PreparedStatement insert(Connection conn, Role role) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.INSERT_ROLE);
            stmt.setString(1, role.getName());

            return stmt;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.DELETE_ROLE);
            stmt.setLong(1, key);

            return stmt;
        }

        public static PreparedStatement update(Connection conn, Role role) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.UPDATE_ROLE);
            stmt.setString(1, role.getName());
            stmt.setLong(2, role.getId());

            return stmt;
        }
    }
}
