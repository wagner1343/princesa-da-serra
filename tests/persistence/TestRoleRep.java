package persistence;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.persistence.repository.role.RoleRepository;

public class TestRoleRep {

    public static void main(String[] args){

        RoleRepository rr = new RoleRepository("postgres", "123456");
        Role role = new Role("giche");
        rr.add(role);
    }
}
