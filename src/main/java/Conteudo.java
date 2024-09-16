public class Conteudo {
    private int id;
    private String titulo;
    private String texto;
    private String autor;  // Nome do autor (relacionado ao nome de usuário)

    public Conteudo(String titulo, String texto, String autor) {
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}