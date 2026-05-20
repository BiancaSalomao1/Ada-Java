
package Aula29Abr2026.src.java;


public class HistoricoTreino{

    Atleta atleta;
    Modalidade modalidade;
    double tempo, distancia;

    public HistoricoTreino(Atleta atleta, Modalidade modalidade, double tempo, double distancia){
        this.atleta = atleta;
        this.modalidade = modalidade;
        this.tempo = tempo;
        this.distancia = distancia;
    }


    public Atleta getAtleta() {
        return atleta;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public double getTempo() {
        return tempo;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return  "atleta=" + atleta +
                ", modalidade=" + modalidade +
                ", tempo=" + tempo +
                ", distancia=" + distancia +
                '}';
    }
}