package princesadaserra.java.persistence.repository.user;

import princesadaserra.java.core.user.User;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<ResultSet, User> {
    @Override
    public User map(ResultSet result) {
        User user = null;

        try {
            user = new User();
            user.setId(result.getLong("id_user"));

            user.setFirstName(result.getString("first_name"));
            user.setLastName(result.getString("last_name"));
            user.setEmail(result.getString("email"));
            user.setPhone(result.getString("phone"));
            user.setCpf(result.getString("cpf"));
            user.setImageUrl(result.getString("image_url"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
