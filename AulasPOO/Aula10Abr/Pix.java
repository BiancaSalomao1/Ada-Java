package Aula10Abr;

public class Pix extends MetodoPagamento {

    public Pix(String codigo) {
        super("PIX", codigo);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento realizado via PIX");
    }
}