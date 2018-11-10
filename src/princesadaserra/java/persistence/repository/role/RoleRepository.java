package princesadaserra.java.persistence.repository.role;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.persistence.connection.AuthenticatedConnectionProvider;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends AuthenticatedConnectionProvider implements Repository<Role, Long>{

    RoleMapper mapper;

    public RoleRepository(String userName, String password){

        super("jdbc:postgresql://localhost:5432/princesa_da_serra", userName, password);
        mapper = new RoleMapper();
    }

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

        List<Role> roles = new ArrayList<Role>();

        try(Connection conn = getConnection()){

            ResultSet rs = SQLqueries.findAll(conn).executeQuery();
            while(rs.next()){

                roles.add(mapper.map(rs));
            }
        } catch (SQLException e){

            e.printStackTrace();
        }
        return roles;
    }

    public Role find(Long key){

        Role role = null;

        try(Connection conn = getConnection()){

            ResultSet rs = SQLqueries.findByKey(conn, key).executeQuery();
            if(rs.next()){

                role = mapper.map(rs);
            }
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

        private static final String ROLE_INSERT = "INSERT INTO permitions (permition) values (?)";
        private static final String ROLE_DELETE = "DELETE FROM permitions where id_permition = ?";
        private static final String ROLE_UPDATE = "UPADATE roles set permition = ? where id_permition = ?";
        private static final String ROLE_SELECT_BY_KEY = "SELECT * from roles where id_permition = ?";
        private static final String ROLE_SELECT_ALL = "SELECT * from roles";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.ROLE_SELECT_ALL);

            return stmt;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.ROLE_SELECT_BY_KEY);
            stmt.setLong(1, key);

            return stmt;
        }

        public static PreparedStatement insert(Connection conn, Role role) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.ROLE_INSERT);
            stmt.setString(1, role.getName());

            return stmt;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.ROLE_DELETE);
            stmt.setLong(1, key);

            return stmt;
        }

        public static PreparedStatement update(Connection conn, Role role) throws SQLException{

            PreparedStatement stmt = conn.prepareStatement(SQLqueries.ROLE_UPDATE);
            stmt.setString(1, role.getName());
            stmt.setLong(2, role.getId());

            return stmt;
        }
    }
}
