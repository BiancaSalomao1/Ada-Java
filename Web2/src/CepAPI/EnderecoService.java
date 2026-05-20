package CepAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnderecoService {

    private static final String API_URL =
            "https://viacep.com.br/ws/%s/json/";

    public static Endereco consultarCep(String cep) {

        String url = String.format(API_URL, cep);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            String json = response.body();

            String cepJson = extrairValor(json, "cep");
            String logradouro = extrairValor(json, "logradouro");
            String bairro = extrairValor(json, "bairro");
            String localidade = extrairValor(json, "localidade");
            String uf = extrairValor(json, "uf");

            return new Endereco(
                    cepJson,
                    logradouro,
                    bairro,
                    localidade,
                    uf
            );

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();
            return null;
        }
    }

    private static String extrairValor(String json, String chave) {

        String busca = "\"" + chave + "\":";

        int inicio = json.indexOf(busca);

        if (inicio == -1) {
            return "";
        }

        inicio += busca.length();

        while (json.charAt(inicio) == ' ' ||
                json.charAt(inicio) == '\"') {

            inicio++;
        }

        int fim = json.indexOf("\"", inicio);

        return json.substring(inicio, fim);
    }
}