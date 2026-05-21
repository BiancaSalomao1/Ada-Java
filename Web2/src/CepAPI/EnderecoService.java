package CepAPI;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnderecoService {

    private static final String API_URL = "https://viacep.com.br/ws/%s/json/";

    // Instância única do Jackson para converter JSON em Objeto
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Endereco consultarCep(String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        String url = String.format(API_URL, cepLimpo);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            String json = response.body();

            // ViaCEP retorna HTTP 200 mesmo quando o CEP não existe, mas envia um JSON com erro=true
            if (json.contains("\"erro\":")) {
                throw new IllegalArgumentException("CEP não encontrado na base de dados do ViaCEP.");
            }

            return objectMapper.readValue(json, Endereco.class);

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao consultar o CEP: " + e.getMessage());
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
    }
}
