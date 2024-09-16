import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConteudoHSQL implements PersistenciaConteudo {
    private Connection conexao;

    public ConteudoHSQL() {
        try {
            conexao = DriverManager.getConnection("jdbc:hsqldb:file:CMS", "admin", "admin");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    @Override
    public boolean save(Conteudo conteudo) {
        String sql = "INSERT INTO Conteudo (titulo, texto, autor) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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

    @Override
    public boolean atualizar(Conteudo conteudo) {
        String sql = "UPDATE Conteudo SET titulo = ?, texto = ?, autor = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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

    @Override
    public List<Conteudo> listar() {
        List<Conteudo> conteudos = new ArrayList<>();
        String sql = "SELECT * FROM Conteudo";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                conteudos.add(new Conteudo(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("texto"),
                        rs.getString("autor")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os conteúdos: " + e.getMessage());
        }
        return conteudos;
    }

    @Override
    public boolean remover(int id) {
        String sql = "DELETE FROM Conteudo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover o conteúdo: " + e.getMessage());
            return false;
        }
    }
}
