package Aula10Abr;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<MetodoPagamento> pagamentos = new ArrayList<>( List.of(
                new CartaoCredito("123"),
                new Boleto("456"),
                new Pix("789"),
                new CriptoMoeda("BTC-001")
        ));

        ProcessadorPagamento.processarPagamentos(pagamentos);
    }
}