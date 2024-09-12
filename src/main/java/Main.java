import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        ConteudoService conteudoService = new ConteudoService();

        boolean logado = false;

        while (true) {
            if (!logado) {

                System.out.print("##--------------Menu Principal--------------##\n\n");
                System.out.print("|------------------------------------------|\n");
                System.out.print("| 1.Login: Fazer login no sistema.         |\n");
                System.out.print("| 2.Listar Conteúdos: Listar os conteúdos. |\n");
                System.out.print("| 4. Sair: Sair do sistema.                |\n");
                System.out.print("|------------------------------------------|\n");
                System.out.print("Digite uma opção: ");

                int op = sc.nextInt();
                sc.nextLine();
                if (op == 4) {
                    ArtPrinter.printArt();
                    System.exit(0);
                }
                switch (op) {
                    case 1:
                        System.out.print("Digite o nome de usuário: ");
                        String username = sc.nextLine();
                        System.out.print("Digite sua senha: ");
                        String password = sc.nextLine();

                        boolean loginSucesso = usuarioService.login(username, password);

                        if (loginSucesso) {
                            System.out.println("Login efetuado com sucesso!");
                        } else {
                            System.out.println("Credenciais inválidas! Tente novamente...");
                        }
                        break;

                    case 2:
                        conteudoService.listarConteudos();
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente...");

                }

            }else {
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

                int op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1:


                }

            }
        }

    }
}
