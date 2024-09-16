import java.util.List;

public class UsuarioService {
    private final PersistenciaUsuario persistenciaUsuario;

    public UsuarioService(PersistenciaUsuario persistenciaUsuario) {
        this.persistenciaUsuario = persistenciaUsuario;
    }

    public boolean salvarUsuario(Usuario usuario) {
        return persistenciaUsuario.save(usuario);
    }

    public boolean atualizarUsuario(Usuario usuario) {
        return persistenciaUsuario.atualizar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return persistenciaUsuario.listar();
    }

    public boolean excluirUsuario(String username) {
        return persistenciaUsuario.remover(username);
    }

    public boolean login(String username, String password) {
        List<Usuario> usuarios = listarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(username) && usuario.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}
