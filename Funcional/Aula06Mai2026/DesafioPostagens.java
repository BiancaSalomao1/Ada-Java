package Aula06Mai2026;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class DesafioPostagens {

    // Análise de Dados de Redes Sociais.
    // Dada uma lista de postagens, identifique quais foram virais
    // (mais de 100 visualizações),
    // extraia apenas o número de curtidas dessas postagens
    // e calcule o total de engajamento somando essas curtidas.

    public static class Postagem {

        String conteudo;
        int visualizacoes;
        int curtidas;

        Postagem(String conteudo, int visualizacoes, int curtidas) {
            this.conteudo = conteudo;
            this.visualizacoes = visualizacoes;
            this.curtidas = curtidas;
        }
    }

    public static void main(String[] args) {

        Random random = new Random();

        List<Postagem> feed = new ArrayList<>();

        // Gerando postagens aleatórias
        for (int i = 1; i <= 10; i++) {

            int views = random.nextInt(2000);
            int likes = random.nextInt(500);

            feed.add(new Postagem(
                    "Post #" + i,
                    views,
                    likes
            ));
        }

        // Exibindo todas as postagens
        System.out.println("=== TODAS AS POSTAGENS ===");

        for (Postagem post : feed) {
            System.out.println(
                    post.conteudo
                            + " | Views: " + post.visualizacoes
                            + " | Curtidas: " + post.curtidas
            );
        }

        // Filtrando postagens virais e pegando apenas curtidas
        List<Integer> postagemFiltrada = feed.stream()
                .filter(post -> post.visualizacoes > 100)
                .map(post -> post.curtidas)
                .toList();

        // Somando curtidas
        int totalEngajamento = postagemFiltrada.stream()
                .reduce(0, Integer::sum);

        // Resultado
        System.out.println("\nCurtidas das postagens virais:");
        System.out.println(postagemFiltrada);

        System.out.println(
                "\nTotal de engajamento (curtidas de postagens virais): "
                        + totalEngajamento
        );
    }
}