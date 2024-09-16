import java.util.Scanner;

public class TUI {
    private Scanner scanner;
    private UsuarioService usuarioService;
    private ConteudoService conteudoService;
    private boolean logado;

    public TUI(UsuarioService usuarioService, ConteudoService conteudoService) {
        this.scanner = new Scanner(System.in);
        this.usuarioService = usuarioService;
        this.conteudoService = conteudoService;
        this.logado = false;
    }

    public void iniciar() {
        while (true) {
            if (!logado) {
                exibirMenuPrincipal();
            } else {
                exibirMenuAdministracao();
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.print("##--------------Menu Principal--------------##\n\n");
        System.out.print("|------------------------------------------|\n");
        System.out.print("| 1.Login: Fazer login no sistema.         |\n");
        System.out.print("| 2.Listar Conteúdos: Listar os conteúdos. |\n");
        System.out.print("| 3. Primeiro Acesso? Criar usuário!       |\n");
        System.out.print("| 4. Sair: Sair do sistema.                |\n");
        System.out.print("|------------------------------------------|\n");
        System.out.print("Digite uma opção: ");

        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                realizarLogin();
                break;
            case 2:
                conteudoService.listarConteudos();
                break;
            case 3:
                criarUsuario();
                break;
            case 4:
                ArtPrinter.printArt();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente...");
                break;
        }
    }

    private void realizarLogin() {
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String password = scanner.nextLine();

        boolean loginSucesso = usuarioService.login(username, password);

        if (loginSucesso) {
            System.out.println("Login efetuado com sucesso!");
            logado = true;
        } else {
            System.out.println("Credenciais inválidas! Tente novamente...");
        }
    }

    private void exibirMenuAdministracao() {
        System.out.print("##--------------Menu de Administração--------------##\n\n");
        System.out.print("|--------------------------------------------------|\n");
        System.out.print("| 1. Criar Conteúdo: Cria novo conteúdo.           |\n");
        System.out.print("| 2. Listar Conteúdos: Listar os conteúdos.        |\n");
        System.out.print("| 3. Atualizar Conteúdo: Editar conteúdo.          |\n");
        System.out.print("| 4. Excluir Conteúdo: Deletar conteúdo.           |\n");
        System.out.print("| 5. Criar Usuário: Cria novo usuário.             |\n");
        System.out.print("| 6. Listar Usuários: Listar os usuários.          |\n");
        System.out.print("| 7. Alterar Usuário: Editar usuário.              |\n");
        System.out.print("| 8. Excluir Usuário: Deletar usuário.             |\n");
        System.out.print("| 9. Alterar Senha: Alterar sua própria senha.     |\n");
        System.out.print("| 10. Logout: Sair do login.                       |\n");
        System.out.print("|--------------------------------------------------|\n");
        System.out.print("Digite uma opção: ");

        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                criarConteudo();
                break;
            case 2:
                conteudoService.listarConteudos();
                break;
            case 3:
                atualizarConteudo();
                break;
            case 4:
                excluirConteudo();
                break;
            case 5:
                criarUsuario();
                break;
            case 6:
                usuarioService.listarUsuarios(); // Assumes listarUsuarios is implemented to display users
                break;
            case 7:
                alterarUsuario();
                break;
            case 8:
                excluirUsuario();
                break;
            case 9:
                alterarSenha();
                break;
            case 10:
                logado = false;
                System.out.println("Logout realizado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente...");
                break;
        }
    }

    private void criarConteudo() {
        System.out.println("Digite o título do conteúdo:");
        String titulo = scanner.nextLine();
        System.out.println("Digite o texto do conteúdo:");
        String texto = scanner.nextLine();
        System.out.println("Digite o nome do autor (usuário):");
        String autor = scanner.nextLine();

        Conteudo novoConteudo = new Conteudo(titulo, texto, autor);
        boolean sucesso = conteudoService.salvarConteudo(novoConteudo);

        if (sucesso) {
            System.out.println("Conteúdo criado com sucesso!");
        } else {
            System.out.println("Erro ao criar conteúdo.");
        }
    }

    private void atualizarConteudo() {
        // Implementação necessária
        System.out.println("Funcionalidade ainda não implementada.");
    }

    private void excluirConteudo() {
        // Implementação necessária
        System.out.println("Funcionalidade ainda não implementada.");
    }

    private void criarUsuario() {
        System.out.println("Criação de novo usuário");
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        Usuario novoUsuario = new Usuario(username, password);
        boolean sucesso = usuarioService.salvarUsuario(novoUsuario);

        if (sucesso) {
            System.out.println("Usuário criado com sucesso!");
        } else {
            System.out.println("Erro ao criar usuário.");
        }
    }

    private void alterarUsuario() {
        // Implementação necessária
        System.out.println("Funcionalidade ainda não implementada.");
    }

    private void excluirUsuario() {
        // Implementação necessária
        System.out.println("Funcionalidade ainda não implementada.");
    }

    private void alterarSenha() {
        // Implementação necessária
        System.out.println("Funcionalidade ainda não implementada.");
    }
}
