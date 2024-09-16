public class Main {
    public static void main(String[] args) {
        UsuarioHSQL usuarioHSQL = new UsuarioHSQL();
        ConteudoHSQL conteudoHSQL = new ConteudoHSQL();

        UsuarioService usuarioService = new UsuarioService(usuarioHSQL);
        ConteudoService conteudoService = new ConteudoService(conteudoHSQL);

        TUI tui = new TUI(usuarioService, conteudoService);

        tui.iniciar();
    }
}
