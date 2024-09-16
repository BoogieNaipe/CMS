import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioHSQL implements Persistencia<Usuario> {
    private Connection conexao;

    public UsuarioHSQL() {
        try {
            conexao = DriverManager.getConnection("jdbc:hsqldb:file:CMS", "admin", "admin");
            System.out.println("Conexão criada: " + conexao);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void criarTabelas() {
        String sql =
                "CREATE TABLE IF NOT EXISTS Usuario (" +
                        "id INTEGER IDENTITY PRIMARY KEY, " +
                        "username VARCHAR(50) NOT NULL, " +
                        "password VARCHAR(50) NOT NULL);";

        try (Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela Usuario: " + e.getMessage());
        }
    }

    @Override
    public boolean save(Usuario usuario) {
        String sql = "INSERT INTO Usuario (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET password = ? WHERE username = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getPassword());
            stmt.setString(2, usuario.getUsuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o usuário: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                usuarios.add(new Usuario(username, password));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public boolean remover(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover o usuário: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(String username) {
        String sql = "DELETE FROM Usuario WHERE username = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover o usuário: " + e.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
