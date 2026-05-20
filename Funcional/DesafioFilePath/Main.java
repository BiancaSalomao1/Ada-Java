package DesafioFilePath;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/json/daily/";
    private static final String BASE_PATH = "historico_cotacoes";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExibirMenu menu = new ExibirMenu(scanner);

        boolean continuar = true;
        while (continuar) {
            Optional<String> opcao = menu.obterMoedaSelecionada();

            if (opcao.isPresent()) {
                String valor = opcao.get();
                switch (valor) {
                    case "SAIR" -> {
                        continuar = false;
                        System.out.println("Encerrando programa...");
                    }
                    case "LISTAR" -> menu.listarArquivosLocais();
                    default -> consultarCotacao(valor);
                }
            } else {
                System.out.println(" Opção inválida!");
            }
        }
        scanner.close();
    }

    static void consultarCotacao(String moeda) {
        try {
            Path pastaDaMoeda = Paths.get(BASE_PATH, moeda.toUpperCase());
            Files.createDirectories(pastaDaMoeda);

            String nomeArquivo = pastaDaMoeda.resolve("cotacao_" + moeda.toLowerCase() + ".csv").toString();
            String json = buscarDadosDaApi(BASE_URL + moeda + "-BRL/5");

            List<Cotacao> cotacoes = extrairCotacoesDoJson(json);
            if (cotacoes.isEmpty()) {
                System.out.println(" Nenhuma cotação encontrada para " + moeda);
                return;
            }

            salvarEmCsv(cotacoes, nomeArquivo);
            exibirEstatisticas(lerCsv(nomeArquivo), moeda);

        } catch (Exception e) {
            System.err.println(" Erro ao processar " + moeda + ": " + e.getMessage());
        }
    }

    private static String buscarDadosDaApi(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) throw new RuntimeException("HTTP " + response.statusCode());
        return response.body();
    }

    private static List<Cotacao> extrairCotacoesDoJson(String json) {
        String[] objetos = json.replace("[", "").replace("]", "").split("\\},\\{");
        return Arrays.stream(objetos)
                .map(obj -> obj.replace("{", "").replace("}", ""))
                .map(Main::mapToCotacao)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private static Optional<Cotacao> mapToCotacao(String objeto) {
        try {
            long ts = Long.parseLong(extrairValor(objeto, "timestamp"));
            BigDecimal bid = new BigDecimal(extrairValor(objeto, "bid"));
            BigDecimal ask = new BigDecimal(extrairValor(objeto, "ask"));
            LocalDate data = Instant.ofEpochSecond(ts).atZone(ZoneId.systemDefault()).toLocalDate();
            return Optional.of(new Cotacao(data, bid, ask));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static String extrairValor(String objeto, String chave) {
        return Arrays.stream(objeto.split(","))
                .filter(p -> p.contains("\"" + chave + "\""))
                .findFirst()
                .map(p -> p.split(":")[1].replace("\"", "").trim())
                .orElse("");
    }

    private static void salvarEmCsv(List<Cotacao> cotacoes, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("data,valor_compra,valor_venda");
            cotacoes.forEach(c -> writer.printf("%s,%s,%s%n",
                    c.getData(), c.getValorCompra(), c.getValorVenda()));
        }
        System.out.println("Arquivo atualizado: " + nomeArquivo);
    }

    private static List<Cotacao> lerCsv(String nomeArquivo) throws IOException {
        Path path = Paths.get(nomeArquivo);
        if (!Files.exists(path)) return Collections.emptyList();

        try (var lines = Files.lines(path)) {
            return lines.skip(1)
                    .filter(linha -> !linha.isBlank())
                    .map(linha -> linha.split(","))
                    .filter(partes -> partes.length >= 3)
                    .map(p -> new Cotacao(
                            LocalDate.parse(p[0].trim()),
                            new BigDecimal(p[1].trim()),
                            new BigDecimal(p[2].trim())
                    ))
                    .collect(Collectors.toList());
        }
    }

    private static void exibirEstatisticas(List<Cotacao> cotacoes, String moeda) {
        System.out.println("\n--- Dados da Moeda: " + moeda + " ---");
        cotacoes.forEach(c -> System.out.printf("Data: %s | Compra: R$ %-8s | Venda: R$ %-8s%n",
                c.getData(), c.getValorCompra(), c.getValorVenda()));

        BigDecimal mediaCompra = cotacoes.stream()
                .map(Cotacao::getValorCompra)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(cotacoes.size()), 2, RoundingMode.HALF_UP);

        System.out.println("---------------------------------------------------------");
        System.out.println("Média de Compra: R$ " + mediaCompra);
        System.out.println("---------------------------------------------------------\n");
    }


    public static void carregarEExibirLocal(String caminhoArquivo, String moeda) {
        try {
            List<Cotacao> lidas = lerCsv(caminhoArquivo);
            if (lidas.isEmpty()) {
                System.out.println("Arquivo vazio: " + caminhoArquivo);
            } else {
                exibirEstatisticas(lidas, moeda);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler local: " + e.getMessage());
        }
    }
}