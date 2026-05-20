package Aula10Abr;

import java.util.List;

public class ProcessadorPagamento {

    public static void processarPagamentos(List<MetodoPagamento> pagamentos) {

        for (MetodoPagamento pagamento : pagamentos) {

            pagamento.processarPagamento();

            System.out.printf(
                    "Tipo de pagamento: %s\nCódigo do pagamento: %s\n\n",
                    pagamento.getMetodoPagamento(),
                    pagamento.getCodigo()
            );
        }
    }
}