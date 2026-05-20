package DesafioFilePath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ExibirMenu {
    private final Scanner scanner;
    private static final String BASE_PATH = "historico_cotacoes";

    private final Map<Integer, String> moedas = Map.of(
            1, "USD", 2, "EUR", 3, "ARS",
            4, "CLP", 5, "INR", 6, "JPY", 7, "GBP"
    );

    public ExibirMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public Optional<String> obterMoedaSelecionada() {
        System.out.println("\n========== MENU DE COTAÇÕES ==========");
        moedas.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + " - Consultar/Atualizar " + entry.getValue()));

        System.out.println("8 - Listar cotações já baixadas (Local)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        if (!scanner.hasNextInt()) {
            scanner.next();
            return Optional.empty();
        }

        int escolha = scanner.nextInt();
        if (escolha == 0) return Optional.of("SAIR");
        if (escolha == 8) return Optional.of("LISTAR");

        return Optional.ofNullable(moedas.get(escolha));
    }

    public void listarArquivosLocais() {
        System.out.println("\n--- Buscando Histórico Local ---");
        Path root = Paths.get(BASE_PATH);

        if (!Files.exists(root)) {
            System.out.println(" Nenhuma pasta de histórico encontrada. Faça uma consulta primeiro.");
            return;
        }

        try (var stream = Files.walk(root)) {
            List<Path> arquivos = stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".csv"))
                    .toList();

            if (arquivos.isEmpty()) {
                System.out.println("Nenhuma cotação salva encontrada.");
            } else {
                System.out.println("Arquivos encontrados:");
                for (Path p : arquivos) {
                    String moeda = p.getParent().getFileName().toString();
                    Main.carregarEExibirLocal(p.toString(), moeda);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar arquivos: " + e.getMessage());
        }
    }
}