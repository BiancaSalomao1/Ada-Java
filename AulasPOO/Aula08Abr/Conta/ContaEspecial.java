package Aula08Abr.Conta;

public class ContaEspecial extends ContaBancaria {
    protected float limiteCredito;


    Double calcularValorLimite(float limiteCredito) {
        return (saldoConta + limiteCredito);
    }

    public ContaEspecial(float limiteCredito) {
        super();
        this.limiteCredito = limiteCredito;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
