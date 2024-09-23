package CMS;

public class LoginServiceImpl implements LoginService {
    private UsuarioDB usuarioDB;
    private Usuario usuarioLogado;

    public LoginServiceImpl(UsuarioDB usuarioDB) {
        this.usuarioDB = usuarioDB;
    }

    @Override
    public Usuario login(String nome, String senha) {
        Usuario usuario = usuarioDB.findByNomeESenha(nome, senha);
        if (usuario != null) {
            this.usuarioLogado = usuario;
        }
        return usuario;
    }

    @Override
    public void logout() {
        this.usuarioLogado = null;
    }

    @Override
    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }

    @Override
    public void atualizarSenha(String novaSenha) {
        if (usuarioLogado != null) {
            usuarioLogado.setSenha(novaSenha);
            usuarioDB.atualizar(usuarioLogado);
            System.out.println("Senha atualizada com sucesso!");
        } else {
            System.out.println("Nenhum usuário está logado.");
        }
    }
}