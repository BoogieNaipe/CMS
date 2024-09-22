package CMS.src.main.java;

public class Login implements LoginService {

    @Override
    public boolean authenticate(Usuario usuario, String enteredUsername, String enteredPassword) {
        if (usuario.getUsername().equals(enteredUsername) && usuario.getPassword().equals(enteredPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public void logout(Usuario usuario) {
        usuario.setUsername(null);
        usuario.setPassword(null);
    }
}
