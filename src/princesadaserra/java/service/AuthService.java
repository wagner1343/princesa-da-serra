package princesadaserra.java.service;

import princesadaserra.java.core.user.Role;
import princesadaserra.java.core.user.User;

public class AuthService {
    private static AuthService authService;

    private AuthService(){
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
            user.setName(login);
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
}
