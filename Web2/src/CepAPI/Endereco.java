package CepAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor // Construtor vazio obrigatório para o Jackson
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String rua;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("localidade")
    private String localidade;

    @JsonProperty("uf")
    private String uf;

    // Construtor customizado que o Lombok @Builder usará internamente
    @Builder
    public Endereco(String cep, String rua, String complemento, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        validacaoCEP();
    }




    private void validacaoCEP() {
        if (cep == null || !cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }
    }
}
