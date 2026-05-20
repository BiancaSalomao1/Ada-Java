package Aula08Abr.Produto;

public class CD extends Produto {
    String artistaCD;
    String gravadoraCD;
    int qntFaixasCD;

    public CD(String nomeProduto, double valorProduto, String codigoBarrasProduto, String artistaCD, String gravadoraCD, int numeroFaixasCD) {
        super();
        this.artistaCD = artistaCD;
        this.gravadoraCD = gravadoraCD;
        this.qntFaixasCD = numeroFaixasCD;
    }

    public String getArtistaCD() {
        return artistaCD;
    }

    public void setArtistaCD(String artistaCD) {
        this.artistaCD = artistaCD;
    }

    public String getGravadoraCD() {
        return gravadoraCD;
    }

    public void setGravadoraCD(String gravadoraCD) {
        this.gravadoraCD = gravadoraCD;
    }

    public int getQntFaixasCD() {
        return qntFaixasCD;
    }

    public void setQntFaixasCD(int qntFaixasCD) {
        this.qntFaixasCD = qntFaixasCD;
    }

    @Override
    public String toString() {
        return "CD{" +
                "nomeProduto='" + getNomeProduto() + '\'' +
                ", valorProduto=" + getValorProduto() +
                ", codigoBarrasProduto='" + getCodigoBarrasProduto() + '\'' +
                "artistaCD='" + artistaCD + '\'' +
                ", gravadoraCD='" + gravadoraCD + '\'' +
                ", qntFaixasCD=" + qntFaixasCD +
                '}';
    }
}
