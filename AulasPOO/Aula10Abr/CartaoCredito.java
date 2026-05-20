package Aula10Abr;

public class CartaoCredito extends MetodoPagamento {

    public CartaoCredito(String codigo) {
        super("Cartão de Crédito", codigo);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento realizado com Cartão de Crédito");
    }
}