package Aula08Abr.Conta;

public class ContaPoupanca extends ContaBancaria {
    protected float taxaRendimento;

    Double calcularNovoSaldo(float taxaRendimento) {
        return saldoConta + (saldoConta * taxaRendimento);
    }

    public ContaPoupanca(float taxaRendimento) {
        super();
        this.taxaRendimento = taxaRendimento;
    }

    public float getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(float taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "taxaRendimento=" + taxaRendimento +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", numeroAgencia='" + numeroAgencia + '\'' +
                ", numeroConta='" + numeroConta + '\'' +
                ", saldoConta=" + saldoConta +
                '}';
    }
}
