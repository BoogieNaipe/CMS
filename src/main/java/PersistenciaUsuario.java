import java.util.List;

public interface PersistenciaUsuario {
    boolean save(Usuario usuario);
    boolean atualizar(Usuario usuario);
    List<Usuario> listar();
    boolean remover(String username);
}
