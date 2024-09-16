import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioHSQL implements PersistenciaUsuario {
    private Connection conexao;

    public UsuarioHSQL() {
        try {
            conexao = DriverManager.getConnection("jdbc:hsqldb:file:CMS", "admin", "admin");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
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
                usuarios.add(new Usuario(rs.getString("username"), rs.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
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
}
