import java.util.List;

public interface PersistenciaConteudo {
    boolean save(Conteudo conteudo);
    boolean atualizar(Conteudo conteudo);
    List<Conteudo> listar();
    boolean remover(int id);
}
