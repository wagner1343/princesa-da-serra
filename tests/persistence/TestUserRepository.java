package persistence;

import princesadaserra.java.core.user.User;
import princesadaserra.java.persistence.user.UserRepository;

public class TestUserRepository {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository("postgres", "123456");
        User user = new User();
        user.setName("Volmir");
        user.setEmail("volmir@outlook.com");
        user.setCpf("cpf");
        user.setPhone("phone");

        repo.add(user);

        System.out.println("user.getId() = " + user.getId());
    }
}
