import java.util.List;

public interface Persistencia<T> {
    boolean save(T entidade);
    boolean atualizar(T entidade);
    List<T> listar();
    boolean remover(int id);
}
