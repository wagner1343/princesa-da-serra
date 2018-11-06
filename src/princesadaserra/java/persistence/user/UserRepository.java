package princesadaserra.java.persistence.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.AuthenticatedConnectionProvider;
import princesadaserra.java.persistence.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository extends AuthenticatedConnectionProvider implements Repository<User, Long> {
    public UserRepository(String userName, String password) {
        super("jdbc:postgresql://localhost:5432/princesa_da_serra", userName, password);
    }

    @Override
    public User find(Long key) {
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement(SQLQueries.USER_SELECT);

            statement.setLong(1, key);

            ResultSet result = statement.executeQuery();
            User user = new User();
            while(result.next()){
                user.setId(result.getLong("id_user"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
                user.setCpf(result.getString("cpf"));
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {
        try{
            PreparedStatement statement = getConnection()
                    .prepareStatement(SQLQueries.USER_UPDATE);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCpf());
            statement.setLong(5, user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User find(String username){
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement(SQLQueries.USER_SELECT_BY_NAME);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();
            User user = new User();
            while(result.next()){
                user.setId(result.getLong("id_user"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
                user.setCpf(result.getString("cpf"));
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User add(User user) {
        System.out.println("UserRepository.add");
        try{
            PreparedStatement statement = getConnection()
                    .prepareStatement(SQLQueries.USER_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCpf());

            System.out.println("execute = " + statement.execute());

            ResultSet keys = statement.getGeneratedKeys();

            if(keys.next()){
                user.setId(keys.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void remove(Long key) {
        try{
            PreparedStatement statement = getConnection().prepareStatement(SQLQueries.USER_DELETE);
            statement.setLong(1, key);

            System.out.println("execute = " + statement.execute());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private class SQLQueries {
        public static final String USER_INSERT = "INSERT INTO users (name, email, phone, cpf) VALUES(?, ?, ?, ?)";
        public static final String USER_DELETE = "DELETE FROM users WHERE id_user = ?";
        public static final String USER_UPDATE = "UPDATE users set name = ?, email = ?, phone = ?, cpf = ? where user_id = ?";
        public static final String USER_SELECT = "Select id_user, name, email, phone, cpf from users where id_user = ? LIMIT 1";
        public static final String USER_SELECT_BY_NAME = "Select id_user, name, email, phone, cpf from users where name = ? LIMIT 1";
    }
}
