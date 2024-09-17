package CMS.src.main.java;

public class Usuario {
    private String username;
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsuario() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}