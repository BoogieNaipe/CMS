package CMS;

import java.util.List;

public class UsuarioService {
    private final UsuarioDB usuarioDB;

    public UsuarioService(UsuarioDB usuarioDB) {
        this.usuarioDB = usuarioDB;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarioDB.save(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDB.atualizar(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDB.findById(id);
    }

    public boolean removerUsuario(int id) {
        return usuarioDB.remover(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDB.listar();
    }
}