package Aula29Abr2026.src.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemoSemFuncional {

    public static void main(String[] args) {

        LoadHistorico.load();
        Scanner scanner = new Scanner(System.in);

        Filtro filtro = new Filtro();
        List<Integer> ordenacoes = new ArrayList<>();

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
                        filtro.modalidade = Modalidade.valueOf(scanner.nextLine().toUpperCase());
                    } catch (Exception e) {
                        System.out.println("Inválido");
                    }
                    break;

                case 2:
                    System.out.print("Tempo máximo: ");
                    if (scanner.hasNextDouble()) {
                        filtro.tempoMaximo = scanner.nextDouble();
                        scanner.nextLine();
                    } else {
                        System.out.println("Inválido");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.print("Distância mínima: ");
                    if (scanner.hasNextDouble()) {
                        filtro.distanciaMinima = scanner.nextDouble();
                        scanner.nextLine();
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

                    if (scanner.hasNextInt()) {
                        int ord = scanner.nextInt();
                        scanner.nextLine();
                        ordenacoes.add(ord);
                    } else {
                        scanner.next();
                    }
                    break;

                case 5:
                    filtro = new Filtro();
                    ordenacoes.clear();
                    System.out.println("Resetado.");
                    break;

                case 6:

                    List<HistoricoTreino> base =
                            RepositorioHistoricoTreino.listarTodos();

                    List<HistoricoTreino> resultado = aplicarFiltros(base, filtro);

                    aplicarOrdenacao(resultado, ordenacoes);

                    imprimir(resultado);

                    break;

                case 0:
                    scanner.close();
                    return;
            }
        }
    }

    public static List<HistoricoTreino> aplicarFiltros(
            List<HistoricoTreino> lista, Filtro filtro) {

        List<HistoricoTreino> resultado = new ArrayList<>();

        for (HistoricoTreino h : lista) {

            boolean valido = true;

            if (filtro.modalidade != null) {
                if (!h.getModalidade().equals(filtro.modalidade)) {
                    valido = false;
                }
            }

            if (filtro.tempoMaximo != null) {
                if (h.getTempo() > filtro.tempoMaximo) {
                    valido = false;
                }
            }

            if (filtro.distanciaMinima != null) {
                if (h.getDistancia() < filtro.distanciaMinima) {
                    valido = false;
                }
            }

            if (valido) {
                resultado.add(h);
            }
        }

        return resultado;
    }

    public static void aplicarOrdenacao(
            List<HistoricoTreino> lista, List<Integer> criterios) {

        for (int criterio : criterios) {

            for (int i = 0; i < lista.size() - 1; i++) {

                for (int j = 0; j < lista.size() - i - 1; j++) {

                    HistoricoTreino atual = lista.get(j);
                    HistoricoTreino prox = lista.get(j + 1);

                    boolean trocar = false;

                    if (criterio == 1) { // tempo crescente
                        if (atual.getTempo() > prox.getTempo()) trocar = true;
                    }

                    if (criterio == 2) { // tempo decrescente
                        if (atual.getTempo() < prox.getTempo()) trocar = true;
                    }

                    if (criterio == 3) { // distancia crescente
                        if (atual.getDistancia() > prox.getDistancia()) trocar = true;
                    }

                    if (criterio == 4) { // distancia decrescente
                        if (atual.getDistancia() < prox.getDistancia()) trocar = true;
                    }

                    if (trocar) {
                        lista.set(j, prox);
                        lista.set(j + 1, atual);
                    }
                }
            }
        }
    }


    public static void imprimir(List<HistoricoTreino> lista) {

        if (lista.isEmpty()) {
            System.out.println("Nenhum resultado.");
            return;
        }

        for (HistoricoTreino h : lista) {
            System.out.println(
                    h.getAtleta().getNome() + " | " +
                            h.getModalidade() + " | " +
                            h.getTempo() + " | " +
                            h.getDistancia()
            );
        }
    }
}