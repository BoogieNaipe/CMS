package CMS;

public class Conteudo {
    private int id;
    private String titulo;
    private String descricao;
    private Usuario autor;

    // Construtor existente
    public Conteudo(int id, String titulo, String descricao, Usuario autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
    }

    // Novo construtor para corresponder aos parâmetros passados
    public Conteudo(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}