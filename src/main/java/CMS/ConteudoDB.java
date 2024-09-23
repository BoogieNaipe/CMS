package CMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConteudoDB implements Persistencia<Conteudo> {

    @Override
    public void save(Conteudo conteudo) {
        String sql = "INSERT INTO Conteudo (titulo, descricao, autor_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conteudo.getTitulo());
            stmt.setString(2, conteudo.getDescricao());
            stmt.setInt(3, conteudo.getAutor().getId());
            stmt.executeUpdate();
            System.out.println("Conteúdo criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Conteudo conteudo) {
        String sql = "UPDATE Conteudo SET titulo = ?, descricao = ?, autor_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conteudo.getTitulo());
            stmt.setString(2, conteudo.getDescricao());
            stmt.setInt(3, conteudo.getAutor().getId());
            stmt.setInt(4, conteudo.getId());
            stmt.executeUpdate();
            System.out.println("Conteúdo atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Conteudo> listar() {
        List<Conteudo> conteudos = new ArrayList<>();
        String sql = "SELECT * FROM Conteudo";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int autorId = rs.getInt("autor_id");
                Usuario autor = new UsuarioDB().findById(autorId);
                conteudos.add(new Conteudo(id, titulo, descricao, autor));
            }
            if (conteudos.isEmpty()) {
                System.out.println("Não há conteúdos para serem listados!");
            } else {
                System.out.println("Lista de conteúdos obtida com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conteudos;
    }

    @Override
    public boolean remover(int id) {
        String sql = "DELETE FROM Conteudo WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Conteúdo removido com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Falha ao remover conteúdo.");
        return false;
    }

    public Conteudo findById(int id) {
        String sql = "SELECT * FROM Conteudo WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String titulo = rs.getString("titulo");
                    String descricao = rs.getString("descricao");
                    int autorId = rs.getInt("autor_id");
                    Usuario autor = new UsuarioDB().findById(autorId);
                    System.out.println("Conteúdo encontrado com sucesso!");
                    return new Conteudo(id, titulo, descricao, autor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Conteúdo não encontrado.");
        return null;
    }
}