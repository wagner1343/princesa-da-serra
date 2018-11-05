package princesadaserra.java.service;

import princesadaserra.java.core.user.Role;
import princesadaserra.java.core.user.User;

import java.sql.*;

public class AuthService {
    private static AuthService authService;
    private final String SERVICE_USER = "auth_service";
    private final String SERVICE_PASSWORD = "123456";

    private AuthService(){

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/princesa_da_serra", SERVICE_USER, SERVICE_PASSWORD);
    }

    public static AuthService getInstance(){
           if(authService == null){
               authService = new AuthService();
           }

           return authService;
    }

    public User authenticate(String login, String password){
        System.out.println("AuthService.authenticate");
        System.out.println("login = [" + login + "], password = [" + password + "]");
        System.out.println("Method not implemented, returns true for wagner:1343 ROLE = seller or guest:1343 ROLE = guest or admin:1343 ROLE = admin");
        if(login.equals("wagner") && password.equals("1343")){
            User user = new User();
            user.setName("Wagner W. Martins");
            user.setEmail("wagner1343@outlook.com");
            user.setRole(Role.SELLER);
            return user;
        }else if(login.equals("guest") && password.equals("1343")){
            User user = new User();
            user.setName(login);
            user.setRole(Role.GUEST);
            return user;
        }
        else if(login.equals("admin") && password.equals("1343")){
            User user = new User();
            user.setName(login);
            user.setRole(Role.ADMIN);
            return user;
        }
        else{
            return null;
        }
    }

    public boolean logout(User user) {
        System.out.println("AuthService.logout");
        System.out.println("Method not implemented yet, always returns true");
        return true;
    }

    public boolean register(String userName, String password, String role){
        System.out.println("AuthService.register");

        try (Connection connection = getConnection()) {
            connection.createStatement()
                    .execute(String.format("CREATE USER %s WITH PASSWORD '%s' ROLE %s;", userName, password, role));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
