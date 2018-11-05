package auth;

import princesadaserra.java.core.user.User;
import princesadaserra.java.service.AuthService;

public class TestAuthService {
    public static void main(String[] args) {
        AuthService authService = AuthService.getInstance();
        authService.register("test_user6", "13431343", "test_rolse");
    }
}
