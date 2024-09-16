import java.util.List;

public class ConteudoService {
    private final PersistenciaConteudo persistenciaConteudo;

    public ConteudoService(PersistenciaConteudo persistenciaConteudo) {
        this.persistenciaConteudo = persistenciaConteudo;
    }

    public boolean salvarConteudo(Conteudo conteudo) {
        return persistenciaConteudo.save(conteudo);
    }

    public boolean atualizarConteudo(Conteudo conteudo) {
        return persistenciaConteudo.atualizar(conteudo);
    }

    public List<Conteudo> listarConteudos() {
        return persistenciaConteudo.listar();
    }

    public boolean excluirConteudo(int id) {
        return persistenciaConteudo.remover(id);
    }
}
