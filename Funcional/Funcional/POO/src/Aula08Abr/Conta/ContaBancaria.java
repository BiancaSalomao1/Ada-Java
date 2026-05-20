package Aula08Abr.Conta;

public class ContaBancaria {
    protected String nomeTitular;
    protected String numeroAgencia;
    protected String numeroConta;
    protected double saldoConta;

        public ContaBancaria() {
        }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "nomeTitular='" + nomeTitular + '\'' +
                ", numeroAgencia='" + numeroAgencia + '\'' +
                ", numeroConta='" + numeroConta + '\'' +
                ", saldoConta=" + saldoConta +
                '}';
    }
}
