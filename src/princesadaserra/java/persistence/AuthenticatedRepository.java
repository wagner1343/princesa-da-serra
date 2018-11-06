package princesadaserra.java.persistence;

public abstract class AuthenticatedRepository<K, I> implements Repository<K, I>{
    private String userName;
    private String password;

    protected AuthenticatedRepository(String userName, String password){
        setUserAndPassword(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    void setUserAndPassword(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
