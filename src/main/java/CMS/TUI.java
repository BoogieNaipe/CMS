package CMS;

import java.util.List;
import java.util.Scanner;

public class TUI {
    private final Scanner scanner;
    private final UsuarioService usuarioService;
    private final ConteudoService conteudoService;
    private final LoginService loginService;
    private boolean isLoggedIn = false;

    public TUI(UsuarioService usuarioService, ConteudoService conteudoService, LoginService loginService) {
        this.scanner = new Scanner(System.in);
        this.usuarioService = usuarioService;
        this.conteudoService = conteudoService;
        this.loginService = loginService;
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
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    login();
                    if (isLoggedIn) {
                        menuAdministracao();
                    }
                    break;
                case 2:
                    listarConteudos();
                    break;
                case 3:
                    criarUsuario();
                    break;
                case 4:
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void login() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario usuario = loginService.login(nome, senha);
        if (usuario != null) {
            isLoggedIn = true;
            System.out.println("Login bem-sucedido.");
        } else {
            System.out.println("Nome ou senha incorretos.");
        }
    }

    private void menuAdministracao() {
        boolean administrando = true;
        while (administrando) {
            exibirMenuAdministracao();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarConteudo();
                    break;
                case 2:
                    listarConteudos();
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
                    listarUsuarios();
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
                    administrando = false;
                    isLoggedIn = false;
                    System.out.println("Saindo do menu de administração...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void criarConteudo() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("ID do Autor: ");
        int autorId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Usuario autor = usuarioService.buscarUsuarioPorId(autorId);
        if (autor != null) {
            Conteudo conteudo = new Conteudo(0, titulo, descricao, autor);
            conteudoService.adicionarConteudo(conteudo);
            System.out.println("Conteúdo adicionado com sucesso.");
        } else {
            System.out.println("Autor não encontrado.");
        }
    }

    private void listarConteudos() {
        List<Conteudo> conteudos = conteudoService.listarConteudos();
        System.out.println("Lista de Conteúdos:");
        System.out.println("-------------------");
        System.out.printf("%-5s %-20s %-30s%n", "ID", "Título", "Descrição");
        System.out.println("-------------------");
        for (Conteudo conteudo : conteudos) {
            System.out.printf("%-5d %-20s %-30s%n", conteudo.getId(), conteudo.getTitulo(), conteudo.getDescricao());
        }
        System.out.println("-------------------");
    }

    private void atualizarConteudo() {
        System.out.print("ID do Conteúdo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Conteudo conteudo = conteudoService.listarConteudos().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (conteudo != null) {
            System.out.print("Novo Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Nova Descrição: ");
            String descricao = scanner.nextLine();
            conteudo.setTitulo(titulo);
            conteudo.setDescricao(descricao);
            conteudoService.atualizarConteudo(conteudo);
            System.out.println("Conteúdo atualizado com sucesso.");
        } else {
            System.out.println("Conteúdo não encontrado.");
        }
    }

    private void excluirConteudo() {
        System.out.print("ID do Conteúdo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        if (conteudoService.removerConteudo(id)) {
            System.out.println("Conteúdo removido com sucesso.");
        } else {
            System.out.println("Falha ao remover conteúdo.");
        }
    }

    private void criarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario usuario = new Usuario(0, nome, senha);
        usuarioService.adicionarUsuario(usuario);
        System.out.println("Usuário adicionado com sucesso.");
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        System.out.println("Lista de Usuários:");
        System.out.println("------------------");
        System.out.printf("%-5s %-20s%n", "ID", "Nome");
        System.out.println("------------------");
        for (Usuario usuario : usuarios) {
            System.out.printf("%-5d %-20s%n", usuario.getId(), usuario.getNome());
        }
        System.out.println("------------------");
    }

    private void alterarUsuario() {
        System.out.print("ID do Usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nova Senha: ");
            String senha = scanner.nextLine();
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuarioService.atualizarUsuario(usuario);
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void excluirUsuario() {
        System.out.print("ID do Usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        if (usuarioService.removerUsuario(id)) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Falha ao remover usuário.");
        }
    }

    private void alterarSenha() {
        if (isLoggedIn) {
            System.out.print("Nova Senha: ");
            String novaSenha = scanner.nextLine();
            loginService.atualizarSenha(novaSenha);
        } else {
            System.out.println("Nenhum usuário está logado.");
        }
    }
}