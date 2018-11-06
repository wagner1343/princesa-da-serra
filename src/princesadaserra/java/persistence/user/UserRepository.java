package princesadaserra.java.persistence.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.AuthenticatedDbRepository;
import princesadaserra.java.persistence.AuthenticatedRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends AuthenticatedDbRepository<User, Long> {
    protected UserRepository(String userName, String password) {
        super("jdbc:postgresql://localhost:5432/princesa_da_serra", userName, password);
    }

    @Override
    public User find(Long key) {
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("Select id_user, name, email, phone, cpf from users where id_user = ? LIMIT 1;");

            statement.setLong(1, key);

            ResultSet result = statement.executeQuery();
            User user = new User();
            while(result.next()){
                user.setId(result.getInt("id_user"));
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
                    .prepareStatement("UPDATE users set name = ?, email = ?, phone = ?, cpf = ? where user_id = ?;");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCpf());
            statement.setLong(5, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User add(User user) {
        try{
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO users VALUES(");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCpf());
            statement.setLong(5, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long key) {

    }
}
