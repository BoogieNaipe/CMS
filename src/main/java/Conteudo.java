public class Conteudo {
    private int id;
    private String titulo;
    private String texto;
    private String autor;

    public Conteudo(String titulo, String texto, String autor) {
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
    }

    public Conteudo(int id, String titulo, String texto, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getAutor() {
        return autor;
    }
}
