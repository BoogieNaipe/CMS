package CMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDB implements Persistencia<Usuario> {

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, senha) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
            System.out.println("Usuário criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, senha = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                usuarios.add(new Usuario(id, nome, senha));
            }
            if (usuarios.isEmpty()) {
                System.out.println("Não há usuários para serem listados!");
            } else {
                System.out.println("Lista de usuários obtida com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean remover(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário removido com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Falha ao remover usuário.");
        return false;
    }

    public Usuario findById(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String senha = rs.getString("senha");
                    System.out.println("Usuário encontrado com sucesso!");
                    return new Usuario(id, nome, senha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Usuário não encontrado.");
        return null;
    }

    public Usuario findByNomeESenha(String nome, String senha) {
        String sql = "SELECT * FROM Usuario WHERE nome = ? AND senha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return new Usuario(id, nome, senha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}