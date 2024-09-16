import java.util.List;

public class UsuarioService {
    private final Persistencia<Usuario> persistencia;

    public UsuarioService(Persistencia<Usuario> persistencia) {
        this.persistencia = persistencia;
    }

    public boolean salvarUsuario(Usuario usuario) {
        try {
            persistencia.save(usuario);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> listarUsuarios() {
        return persistencia.listar();
    }

    public boolean login(String username, String password) {
        List<Usuario> usuarios = listarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
