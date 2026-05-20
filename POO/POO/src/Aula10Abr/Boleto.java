package Aula10Abr;

public class Boleto extends MetodoPagamento {

    public Boleto(String codigo) {
        super("Boleto", codigo);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento realizado via Boleto");
    }
}