import java.util.List;

public class UsuarioService {
    private final Persistencia<Usuario> persistencia;

    public UsuarioService(Persistencia<Usuario> persistencia) {
        this.persistencia = persistencia;
    }

    public boolean salvarUsuario(Usuario usuario) {
        return persistencia.save(usuario);
    }

    public boolean atualizarUsuario(Usuario usuario) {
        return persistencia.atualizar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return persistencia.listar();
    }

    public boolean excluirUsuario(String username) {
        return ((UsuarioHSQL) persistencia).remover(username);
    }

    public boolean alterarSenha(Usuario usuario) {
        return persistencia.atualizar(usuario);
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
