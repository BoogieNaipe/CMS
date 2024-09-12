

public class UsuarioService {
    public boolean login(String username, String password) {

        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }else{
            return false;
        }
    }

}
