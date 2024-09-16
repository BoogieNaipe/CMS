import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConteudoService {
    private final ConteudoHSQL conteudoHSQL;

    public ConteudoService(ConteudoHSQL conteudoHSQL) {
        this.conteudoHSQL = conteudoHSQL;
    }

    public boolean salvarConteudo(Conteudo conteudo) {
        String sql = "INSERT INTO Conteudo (titulo, texto, autor) VALUES (?, ?, ?)";

        try (Connection conn = conteudoHSQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conteudo.getTitulo());
            stmt.setString(2, conteudo.getTexto());
            stmt.setString(3, conteudo.getAutor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o conteúdo: " + e.getMessage());
            return false;
        }
    }

    public void listarConteudos() {
        String sql = "SELECT id, titulo, texto, autor FROM Conteudo";

        try (Connection conn = conteudoHSQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("Listagem de Conteúdos:\n");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String texto = rs.getString("texto");
                String autor = rs.getString("autor");

                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Texto: " + texto);
                System.out.println("Autor: " + autor);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os conteúdos: " + e.getMessage());
        }
    }

    public boolean atualizarConteudo(Conteudo conteudo) {
        String sql = "UPDATE Conteudo SET titulo = ?, texto = ?, autor = ? WHERE id = ?";
        try (Connection conn = conteudoHSQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conteudo.getTitulo());
            stmt.setString(2, conteudo.getTexto());
            stmt.setString(3, conteudo.getAutor());
            stmt.setInt(4, conteudo.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o conteúdo: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirConteudo(int id) {
        String sql = "DELETE FROM Conteudo WHERE id = ?";
        try (Connection conn = conteudoHSQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o conteúdo: " + e.getMessage());
            return false;
        }
    }
}
