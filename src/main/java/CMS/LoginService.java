package CMS;

public interface LoginService {
    Usuario login(String nome, String senha);
    void logout();
    Usuario getUsuarioLogado();
    void atualizarSenha(String novaSenha);
}