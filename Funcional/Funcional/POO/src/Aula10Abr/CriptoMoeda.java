package Aula10Abr;

public class CriptoMoeda extends MetodoPagamento {

    public CriptoMoeda(String codigo) {
        super("Criptomoeda", codigo);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento realizado com Criptomoeda");
    }
}