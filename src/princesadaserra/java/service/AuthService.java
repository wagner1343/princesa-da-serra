package princesadaserra.java.service;

import princesadaserra.java.core.user.Role;
import princesadaserra.java.core.user.User;

import java.sql.*;

public class AuthService {
    private static String userName;
    private static String password;
    private static String connectionUrl = "jdbc:postgresql://localhost:5432/princesa_da_serra";

    public static Connection getAuthConnection(String userName, String password) throws SQLException {
        return DriverManager.getConnection(connectionUrl ,userName, password);
    }

    public static Connection getAuthConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl ,userName, password);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    public static void logout(){
        userName = null;
        password = null;
    }

    public static boolean authenticate(String userName, String password){
        System.out.println("AuthService.authenticate");
        System.out.println("login = [" + userName + "], password = [" + password + "]");

        try {
            if(getAuthConnection() != null){
                AuthService.userName = userName;
                AuthService.password = password;
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String userName){
        System.out.println("AuthService.delete");
        System.out.println("userName = [" + userName + "]");

        try (Connection connection = getAuthConnection()) {
            connection.createStatement()
                    .execute(String.format("DROP USER %s;", userName));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String userName, String password, String role){
        System.out.println("AuthService.register");
        System.out.println("userName = [" + userName + "], password = [" + password + "], role = [" + role + "]");

        try (Connection connection = getAuthConnection()) {
            connection.createStatement()
                    .execute(String.format("CREATE USER %s WITH PASSWORD '%s' ROLE %s;", userName, password, role));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
