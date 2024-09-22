package CMS.src.main.java;
import java.util.Scanner;

public class MenusTUI {
    public void iniciar(){
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new Login();
        Usuario usuario = new Usuario("admin", "password123");

        while (true) {
            System.out.print("##--------------Menu Principal--------------##\n\n");
            System.out.print("|------------------------------------------|\n");
            System.out.print("| 1. Login: Fazer login no sistema.        |\n");
            System.out.print("| 2. Listar Conteúdos: Listar os conteúdos.|\n");
            System.out.print("| 3. Primeiro Acesso? Criar usuário!       |\n");
            System.out.print("| 4. Sair: Sair do sistema.                |\n");
            System.out.print("|------------------------------------------|\n");
            System.out.print("Digite uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Digite seu nome de usuário: ");
                    String enteredUsername = scanner.nextLine();
                    System.out.print("Digite sua senha: ");
                    String enteredPassword = scanner.nextLine();

                    if (loginService.authenticate(usuario, enteredUsername, enteredPassword)) {
                        System.out.println("Login bem-sucedido! Bem-vindo, " + enteredUsername + "!");
                        menuAdministracao(scanner, loginService, usuario);
                    } else {
                        System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
                    }
                    break;

                case 2:
                    System.out.println("Listando conteúdos...");
                    // Adicione aqui a lógica para listar os conteúdos
                    break;

                case 3:
                    System.out.print("Digite o novo nome de usuário: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Digite a nova senha: ");
                    String newPassword = scanner.nextLine();
                    usuario = new Usuario(newUsername, newPassword);
                    System.out.println("Usuário criado com sucesso!");
                    break;

                case 4:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    ArtPrinter.printArt();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
    private static void menuAdministracao(Scanner scanner, LoginService loginService, Usuario usuario) {
        while (true) {
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

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Criando conteúdo... ");
                    break;
                case 2:
                    System.out.println("Listando conteúdos... ");
                    break;
                case 3:
                    System.out.println("Atualizando conteúdo... ");
                    break;
                case 4:
                    System.out.println("Excluindo conteúdo... ");
                    break;
                case 5:
                    System.out.println("Criando usuário... ");
                    break;
                case 6:
                    System.out.println("Listando usuários... ");
                    break;
                case 7:
                    System.out.println("Editando usuário... ");
                    break;
                case 8:
                    System.out.println("Excluindo usuário... ");
                    break;
                case 9:
                    System.out.println("Alterando sua senha... ");
                    break;
                case 10:
                    loginService.logout(usuario);
                    System.out.println("Logout bem-sucedido!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente...");
                    break;

            }
        }
    }
}