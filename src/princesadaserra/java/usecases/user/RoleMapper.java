package princesadaserra.java.usecases.user;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<ResultSet, Role> {
    @Override
    public Role map(ResultSet resultSet) {
        Role role = null;
        try {
            role = new Role();
            role.setId(resultSet.getLong("id_role"));
            role.setName(resultSet.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
