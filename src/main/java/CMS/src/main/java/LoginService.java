package CMS.src.main.java;

public interface LoginService {

    boolean authenticate(Usuario usuario, String enteredUsername, String enteredPassword);
    void logout(Usuario usuario);
}
