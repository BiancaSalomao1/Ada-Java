package Aula08Abr.Produto;

public class Livro extends Produto {
    private String autorLivro;
    private String editoraLivro;
    private int numeroPaginasLivro;

    public Livro(String nomeProduto, double valorProduto, String codigoBarrasProduto, String autorLivro, String editoraLivro, int numeroPaginasLivro) {
        super();
        this.autorLivro = autorLivro;
        this.editoraLivro = editoraLivro;
        this.numeroPaginasLivro = numeroPaginasLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public String getEditoraLivro() {
        return editoraLivro;
    }

    public void setEditoraLivro(String editoraLivro) {
        this.editoraLivro = editoraLivro;
    }

    public int getNumeroPaginasLivro() {
        return numeroPaginasLivro;
    }

    public void setNumeroPaginasLivro(int numeroPaginasLivro) {
        this.numeroPaginasLivro = numeroPaginasLivro;
    }

    @Override
    public String toString() {
        return "Livro{" + "nomeProduto='" + getNomeProduto() + '\'' +
                ", valorProduto=" + getValorProduto() +
                ", codigoBarrasProduto='" + getCodigoBarrasProduto() + '\'' +
                "autorLivro='" + autorLivro + '\'' +
                ", editoraLivro='" + editoraLivro + '\'' +
                ", numeroPaginasLivro=" + numeroPaginasLivro +
                '}';
    }
}

