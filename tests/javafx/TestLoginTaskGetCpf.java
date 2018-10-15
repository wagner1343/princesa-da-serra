package javafx;

import princesadaserra.java.core.user.User;
import princesadaserra.java.util.threading.Task;

public class TestLoginTaskGetCpf extends Task<Object, User, Integer>{
    private String user;
    private String password;

    public TestLoginTaskGetCpf(String user, String password){
        this.user = user;
        this.password = password;
    }


    @Override
    public User execute(Object argument) {
        // acesso a db
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User test = new User();
        test.setName(user + " : " + password);

        test.setCpf("cpfloco1234");

        return test;
    }
}
