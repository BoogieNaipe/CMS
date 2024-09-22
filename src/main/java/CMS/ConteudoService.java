package CMS;

import java.util.List;

public class ConteudoService {
    private final ConteudoDB conteudoDB;

    public ConteudoService() {
        this.conteudoDB = new ConteudoDB();
    }

    public void adicionarConteudo(Conteudo conteudo) {
        conteudoDB.save(conteudo);
    }

    public void atualizarConteudo(Conteudo conteudo) {
        conteudoDB.atualizar(conteudo);
    }

    public List<Conteudo> listarConteudos() {
        return conteudoDB.listar();
    }

    public boolean removerConteudo(int id) {
        return conteudoDB.remover(id);
    }
}