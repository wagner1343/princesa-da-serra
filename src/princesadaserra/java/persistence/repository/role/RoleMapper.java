package princesadaserra.java.persistence.repository.role;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<ResultSet, Role>{

    @Override
    public Role map(ResultSet result){

        Role role = null;
        try{

            role = new Role();
            role.setId(result.getLong("id_role"));
            role.setName(result.getString("role"));
        } catch(SQLException e){

            e.printStackTrace();
        }
        return role;
    }
}
