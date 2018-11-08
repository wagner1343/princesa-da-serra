package persistence;

import princesadaserra.java.core.user.Role;
import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.user.UserRepository;

public class TestUserRepository {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository("postgres", "13431343");

        User user = new User();
        user.setRole(Role.NORMAL_USER);
        user.setUsername("wagner1343");
        user.setPassword("13431343");
        user.setFirstName("Wagner Silvestre");
        user.setLastName("Wuchryn Martins");
        user.setEmail("wagner1343@outlook.com");
        user.setCpf("cpf");
        user.setPhone("phone");

        repo.add(user);

        System.out.println("user.getId() = " + user.getId());
    }
}
