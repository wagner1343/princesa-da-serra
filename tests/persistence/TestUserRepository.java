package persistence;

import princesadaserra.java.core.role.Role;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.repository.user.UserRepository;

public class TestUserRepository {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository("postgres", "13431343");

        User user = new User();
        user.setRole(new Role("normal_user"));
        user.setUsername("wagner");
        user.setPassword("13431343");
        user.setFirstName("Wagner");
        user.setLastName("");
        user.setEmail("wagner1343@outlook.com");
        user.setCpf("cpf");
        user.setPhone("phone");

        repo.add(user);

        System.out.println("user.getId() = " + user.getId());
    }
}
