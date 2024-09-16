import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConteudoHSQL {
    private Connection conexao = null;

    public Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/CMS", "admin", "admin");
        }
        return conexao;
    }
    public void criarTabelas(){
        String sql = "CREATE TABLE IF NOT EXISTS Conteudo (" +
                "id INTEGER IDENTITY PRIMARY KEY," +
                "titulo VARCHAR(250)," +
                "texto VARCHAR(10000)," +
                "autor VARCHAR(250)," +
                "FOREIGN KEY (autor) REFERENCES Usuario(username));";

        try (Connection conexao = getConexao(); Statement stmt = conexao.createStatement()){
            stmt.executeUpdate(sql);
        }catch (SQLException e) {
            System.out.println("Erro ao criar tabela Conteudo: " + e.getMessage());
        }
    }
}