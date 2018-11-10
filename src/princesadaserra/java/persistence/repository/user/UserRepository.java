package princesadaserra.java.persistence.repository.user;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.connection.AuthenticatedConnectionProvider;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//TODO USER_PERMITION

public class UserRepository extends AuthenticatedConnectionProvider implements Repository<User, Long> {
    private princesadaserra.java.persistence.repository.user.UserMapper mapper;

    public UserRepository(String userName, String password) {
            super("jdbc:postgresql://localhost:5432/princesa_da_serra", userName, password);
        mapper = new princesadaserra.java.persistence.repository.user.UserMapper();
    }

    @Override
    public User find(Long key) {
        User user = null;

        try(Connection conn = getConnection()) {
            ResultSet result = SQLQueries.findByKey(conn, key).executeQuery();

            if(result.next())
                user = mapper.map(result);
        } catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    @Override
    public void update(User user) {
        try(Connection conn = getConnection()){
            System.out.println("UserRepository.update");
            System.out.println("updated rows = " + SQLQueries.update(conn, user).executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User find(String username){
        User user = null;

        try(Connection conn = getConnection()) {
            ResultSet result = SQLQueries.findByUsername(conn, username).executeQuery();

            if(result.next())
                user = mapper.map(result);
        } catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    @Override
    public User add(User user) {
        System.out.println("UserRepository.add");

        try(Connection conn = getConnection()){
            conn.setAutoCommit(false);
            SQLQueries.createDatabaseUser(conn, user).execute();
            ResultSet getUserIdResult = SQLQueries.findDatabaseUserByUsername(conn, user).executeQuery();

            if(getUserIdResult.next())
                user.setId(getUserIdResult.getLong("usesysid"));

            System.out.println("execute = " + SQLQueries.insert(conn, user).execute());
            System.out.println("user.getId() = " + user.getId());
            conn.commit();
        } catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    @Override
    public void remove(Long key) {
        try(Connection conn = getConnection()){
            System.out.println("execute = " + SQLQueries.delete(conn, key).execute());
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<User> find(Specification specification) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> delete(Specification specification) {
        throw new NotImplementedException();
    }

    public List<Role> listRoles(){
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String USER_INSERT = "INSERT INTO users ( first_name, email, phone, cpf, id_user, user_name, last_name) VALUES(?, ?, ?, ?, ?, ?, ?)";
        private static final String USER_DELETE = "DELETE FROM users WHERE id_user = ?";
        private static final String USER_UPDATE = "UPDATE users set first_name = ?, last_name = ?, email = ?, phone = ?, cpf = ? where id_user = ?";
        private static final String USER_SELECT = "Select id_user, first_name, last_name  email, phone, cpf, image_url from users where id_user = ? LIMIT 1";
        private static final String USER_SELECT_BY_USERNAME = "Select usename, id_user, first_name, last_name, email, phone, cpf, image_url from users join pg_user on id_user = usesysid where usename = ? LIMIT 1";

        public static PreparedStatement update(Connection conn, User user) throws SQLException {
            PreparedStatement statement = conn
                    .prepareStatement(SQLQueries.USER_UPDATE);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getCpf());
            statement.setLong(6, user.getId());

            return statement;
        }

        public static PreparedStatement findByUsername(Connection conn, String username) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.USER_SELECT_BY_USERNAME);
            statement.setString(1, username);

            return  statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.USER_SELECT);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement createDatabaseUser(Connection conn, User user) throws SQLException {
            return conn.prepareStatement(
                    String.format("CREATE USER %s WITH PASSWORD '%s' IN ROLE %s",
                            user.getUsername(), user.getPassword(), user.getRole().getName()));
        }

        public static PreparedStatement findDatabaseUserByUsername(Connection conn, User user) throws SQLException {
            PreparedStatement statement = conn.prepareStatement("SELECT usesysid FROM pg_user WHERE usename = ?");
            statement.setString(1, user.getUsername());

            return statement;
        }

        public static PreparedStatement insert(Connection conn, User user) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.USER_INSERT);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCpf());
            statement.setLong(5, user.getId());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getLastName());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.USER_DELETE);
            statement.setLong(1, key);

            return statement;
        }
    }
}
