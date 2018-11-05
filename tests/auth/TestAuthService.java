package auth;

import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;

public class TestAuthService {
    public static void main(String[] args) {
        AuthService authService = AuthService.getInstance();
        User user = new User();
        user.setLogin("wagner1343");

        authService.register("test_user4", "13431343", "test_role");
    }
}
