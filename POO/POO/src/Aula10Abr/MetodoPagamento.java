package Aula10Abr;

public abstract class MetodoPagamento {

    protected String metodoPagamento;
    protected String codigo;

    public MetodoPagamento(String metodoPagamento, String codigo) {
        this.metodoPagamento = metodoPagamento;
        this.codigo = codigo;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public abstract void processarPagamento();
}