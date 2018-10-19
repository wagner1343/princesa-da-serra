package princesadaserra.java.service;

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

    public boolean authenticate(String login, String password){
        System.out.println("Method not implemented, returns true for wagner:1343");
        return login.equals("wagner") && password.equals("1343");
    }
}
