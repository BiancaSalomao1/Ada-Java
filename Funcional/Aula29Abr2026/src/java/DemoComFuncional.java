package Aula29Abr2026.src.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DemoComFuncional {

    public static void main(String[] args) {

        LoadHistorico.load();
        Scanner scanner = new Scanner(System.in);

        Modalidade filtroModalidade = null;
        Double filtroTempoMaximo = null;
        Double filtroDistanciaMinima = null;

        List<Integer> criteriosOrdenacao = new ArrayList<>();

        while (true) {

            System.out.println("\n--- MENU ---");
            System.out.println("1. Filtro Modalidade");
            System.out.println("2. Filtro Tempo Máximo");
            System.out.println("3. Filtro Distância Mínima");
            System.out.println("4. Ordenação");
            System.out.println("5. Limpar");
            System.out.println("6. Mostrar Resultados");
            System.out.println("0. Sair");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida.");
                scanner.next();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Modalidade: ");
                    try {
                        filtroModalidade = Modalidade.valueOf(scanner.nextLine().toUpperCase());
                        System.out.println("OK");
                    } catch (Exception e) {
                        System.out.println("Inválido");
                    }
                    break;

                case 2:
                    System.out.print("Tempo máximo: ");
                    if (scanner.hasNextDouble()) {
                        filtroTempoMaximo = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("OK");
                    } else {
                        System.out.println("Inválido");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.print("Distância mínima: ");
                    if (scanner.hasNextDouble()) {
                        filtroDistanciaMinima = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("OK");
                    } else {
                        System.out.println("Inválido");
                        scanner.next();
                    }
                    break;

                case 4:
                    System.out.println("1 Tempo ↑");
                    System.out.println("2 Tempo ↓");
                    System.out.println("3 Distância ↑");
                    System.out.println("4 Distância ↓");
                    System.out.println("5 Modalidade");

                    if (scanner.hasNextInt()) {
                        int ord = scanner.nextInt();
                        scanner.nextLine();

                        if (ord >= 1 && ord <= 5) {
                            criteriosOrdenacao.add(ord);
                        }
                    } else {
                        scanner.next();
                    }
                    break;

                case 5:
                    filtroModalidade = null;
                    filtroTempoMaximo = null;
                    filtroDistanciaMinima = null;
                    criteriosOrdenacao.clear();
                    System.out.println("Resetado.");
                    break;

                case 6:
                    List<HistoricoTreino> resultado =
                            new ArrayList<>(RepositorioHistoricoTreino.listarTodos());


                    if (filtroModalidade != null) {
                        Modalidade finalFiltroModalidade = filtroModalidade;
                        resultado.removeIf(h -> !h.getModalidade().equals(finalFiltroModalidade));
                    }

                    if (filtroTempoMaximo != null) {
                        Double finalFiltroTempoMaximo = filtroTempoMaximo;
                        resultado.removeIf(h -> h.getTempo() > finalFiltroTempoMaximo);
                    }

                    if (filtroDistanciaMinima != null) {
                        Double finalFiltroDistanciaMinima = filtroDistanciaMinima;
                        resultado.removeIf(h -> h.getDistancia() < finalFiltroDistanciaMinima);
                    }

                    Comparator<HistoricoTreino> comparator = null;

                    for (int c : criteriosOrdenacao) {
                        Comparator<HistoricoTreino> atual = null;

                        switch (c) {
                            case 1 -> atual = Comparator.comparing(HistoricoTreino::getTempo);
                            case 2 -> atual = Comparator.comparing(HistoricoTreino::getTempo).reversed();
                            case 3 -> atual = Comparator.comparing(HistoricoTreino::getDistancia);
                            case 4 -> atual = Comparator.comparing(HistoricoTreino::getDistancia).reversed();
                            case 5 -> atual = Comparator.comparing(HistoricoTreino::getModalidade);
                        }

                        if (atual != null) {
                            comparator = (comparator == null)
                                    ? atual
                                    : comparator.thenComparing(atual);
                        }
                    }

                    if (comparator != null) {
                        resultado.sort(comparator);
                    }

                    if (resultado.isEmpty()) {
                        System.out.println("Nenhum resultado.");
                    } else {
                        resultado.forEach(h ->
                                System.out.println(
                                        h.getAtleta().getNome() + " | " +
                                                h.getModalidade() + " | " +
                                                h.getTempo() + " | " +
                                                h.getDistancia()
                                )
                        );
                    }

                    break;

                case 0:
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}