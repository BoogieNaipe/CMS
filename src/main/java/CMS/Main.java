package CMS;

public class Main {
    public static void main(String[] args) {
        // Criar tabelas
        CreateTable.createUsuarioTable();
        System.out.println("Tabela de Usuários criada com sucesso!");
        CreateTable.createConteudoTable();
        System.out.println("Tabela de Conteúdos criada com sucesso!");

        // Iniciar serviços e TUI
        UsuarioDB usuarioDB = new UsuarioDB();
        UsuarioService usuarioService = new UsuarioService(usuarioDB);
        ConteudoService conteudoService = new ConteudoService();
        LoginService loginService = new LoginServiceImpl(usuarioDB);
        TUI tui = new TUI(usuarioService, conteudoService, loginService);
        tui.iniciar();
    }
}