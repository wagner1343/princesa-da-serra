package princesadaserra.java.persistence.repository.user;

import javafx.scene.control.ListCell;
import princesadaserra.java.core.role.Role;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.Repository;
import princesadaserra.java.persistence.repository.Specification;
import princesadaserra.java.usecases.user.RoleMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO USER_PERMITION

public  class UserRepository implements Repository<User, Long> {
    UserMapper mapper;
    RoleMapper roleMapper;
    private ConnectionPoolDataSource dataSource;

    public UserRepository(ConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
        mapper = new UserMapper();
        roleMapper = new RoleMapper();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getPooledConnection().getConnection();
    }

    public List<User> list(){

        List<User> users = new ArrayList<>();

        try(Connection conn = getConnection()) {
            ResultSet result = SQLQueries.findAll(conn).executeQuery();

            while(result.next())
                users.add(mapper.map(result));
        } catch (SQLException e) { e.printStackTrace(); }

        return users;
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
            else throw new SQLException();
            System.out.println("UserRepository.find ok");
            System.out.println("user = " + user);
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
            else throw new SQLException();

            if(user.getRole().getName().equals("admin")){
                conn.createStatement().execute("ALTER USER " + user.getUsername() + " SUPERUSER ");
            }
            System.out.println("execute = " + SQLQueries.insert(conn, user).execute());
            System.out.println("SQLQueries.insertUserRole(conn, user).execute() = " + SQLQueries.insertUserRole(conn, user).execute());

            System.out.println("user.getId() = " + user.getId());
            conn.commit();
        } catch (SQLException e) {
            user = null;
            e.printStackTrace();
        }

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
        return null;
    }

    @Override
    public List<User> delete(Specification specification) {
        return null;
    }

    public List<Role> findAllRoles(){
        List<Role> list = null;

        try(Connection conn = getConnection()){
            list = new ArrayList<>();
            ResultSet resultSet =  SQLQueries.findAllRoles(conn).executeQuery();

            while(resultSet.next())
                list.add(roleMapper.map(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> find(String... specification) {
        List<User> list = null;
        try(Connection conn = getConnection()){
            list = new ArrayList<>();
            ResultSet resultSet = SQLQueries.findBySpecification(conn, specification).executeQuery();

            while (resultSet.next()){
                list.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<User> delete(String... specification) {
        throw new NotImplementedException();
    }

    private static class SQLQueries {
        private static final String INSERT_USER = "INSERT INTO users ( first_name, email, phone, cpf, id_user, user_name, last_name, image_url) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        private static final String INSERT_USER_ROLE = "INSERT INTO users_roles (id_user, id_role) VALUES (?, ?)";
        private static final String DELETE_USER = "DELETE FROM users WHERE id_user = ?";
        private static final String UPDATE_USER = "UPDATE users set first_name = ?, last_name = ?, email = ?, phone = ?, cpf = ? where id_user = ?";
        private static final String SELECT_USER = "SELECT user_name, users.id_user, first_name, last_name, email, phone, cpf, image_url, role, r.id_role from users join pg_user on users.user_name = pg_user.usename join users_roles ur on users.id_user = ur.id_user join roles r on r.id_role = ur.id_role where users.id_user = ? LIMIT 1";
        private static final String SELECT_BY_NAME_USER = "SELECT user_name, users.id_user, first_name, last_name, email, phone, cpf, image_url, role, r.id_role from users join pg_user on users.user_name = pg_user.usename join users_roles ur on users.id_user = ur.id_user join roles r on r.id_role = ur.id_role where usename = ? LIMIT 1";
        private static final String SELECT_BY_SPECIFICATION = "SELECT user_name, users.id_user, first_name, last_name, email, phone, cpf, image_url, role, r.id_role from users join pg_user on users.user_name = pg_user.usename join users_roles ur on users.id_user = ur.id_user join roles r on r.id_role = ur.id_role";
        private static final String SELECT_ALL_USER = "SELECT user_name, users.id_user, first_name, last_name, email, phone, cpf, image_url, role, r.id_role from users join pg_user on users.user_name = pg_user.usename join users_roles ur on users.id_user = ur.id_user join roles r on r.id_role = ur.id_role";
        private static final String SELECT_ALL_ROLES = "SELECT * FROM roles";

        public static PreparedStatement findAll(Connection conn) throws SQLException{

            return conn.prepareStatement(SQLQueries.SELECT_ALL_USER);
        }

        public static PreparedStatement findAllRoles(Connection conn) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_ALL_ROLES);
            return statement;
        }

        public static PreparedStatement findBySpecification(Connection conn, String... specifications) throws SQLException {
            String query = SELECT_BY_SPECIFICATION;
            if(specifications.length > 0){
                query.concat(" where ");
            }
            for(int x = 0; x < specifications.length - 1; x++){
                query.concat(" " + specifications[x] + " AND ");
            }
            if(specifications.length-1 >= 0){
                query.concat(specifications[specifications.length-1]);
            }
            PreparedStatement statement = conn.prepareStatement(query);

            return statement;
        }

        public static PreparedStatement update(Connection conn, User user) throws SQLException {
            PreparedStatement statement = conn
                    .prepareStatement(SQLQueries.UPDATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getCpf());
            statement.setLong(6, user.getId());

            return statement;
        }

        public static PreparedStatement findByUsername(Connection conn, String username) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_BY_NAME_USER);
            statement.setString(1, username);

            return  statement;
        }

        public static PreparedStatement findByKey(Connection conn, Long key) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.SELECT_USER);
            statement.setLong(1, key);

            return statement;
        }

        public static PreparedStatement createDatabaseUser(Connection conn, User user) throws SQLException {
            String q = String.format("CREATE USER %s WITH PASSWORD '%s' IN ROLE %s",
                    user.getUsername(), user.getPassword(), user.getRole().getName());
            System.out.println("SQLQueries.createDatabaseUser");
            System.out.println("q = " + q);
            return conn.prepareStatement(q);
        }

        public static PreparedStatement findDatabaseUserByUsername(Connection conn, User user) throws SQLException {
            PreparedStatement statement = conn.prepareStatement("SELECT usesysid FROM pg_user WHERE usename = ?");
            statement.setString(1, user.getUsername());

            return statement;
        }

        public static PreparedStatement insert(Connection conn, User user) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCpf());
            statement.setLong(5, user.getId());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getLastName());
            if(user.getImageUrl() != null ) statement.setString(8, user.getImageUrl());

            return statement;
        }

        public static PreparedStatement insertUserRole(Connection conn, User user)throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.INSERT_USER_ROLE);
            statement.setLong(1, user.getId());
            statement.setLong(2, user.getRole().getId());

            return statement;
        }

        public static PreparedStatement delete(Connection conn, Long key) throws SQLException {
            PreparedStatement statement = conn.prepareStatement(SQLQueries.DELETE_USER);
            statement.setLong(1, key);

            return statement;
        }
    }
}
