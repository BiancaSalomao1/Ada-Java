package ProgFuncional;


//Função Anônima é uma função sem um nome específico, que pode ser definida e usada diretamente no local onde é necessária. Em Java, as funções anônimas são frequentemente representadas por expressões lambda, que permitem criar implementações concisas de interfaces funcionais. As expressões lambda são úteis para simplificar o código e evitar a necessidade de criar classes anônimas para implementar interfaces funcionais, como Runnable, Callable, Comparator, entre outras.
//Variáveis lâmbdas são variáveis que podem ser usadas dentro de expressões lambda para capturar e acessar valores do contexto externo. Elas permitem que as expressões lambda acessem e utilizem variáveis definidas fora do escopo da função, desde que essas variáveis sejam efetivamente finais ou final. Isso significa que as variáveis capturadas não podem ser modificadas após serem capturadas pela expressão lambda, garantindo a segurança e a consistência do código.
//Predicate é uma interface funcional que representa uma função que recebe um argumento e retorna um valor booleano. Ela é comumente usada para expressar condições ou critérios de filtragem em coleções de dados, como listas ou conjuntos. A interface Predicate possui um método abstrato chamado test(), que é implementado para definir a lógica de avaliação da condição.

//Consumer é uma interface funcional que representa uma função que recebe um argumento e não retorna nenhum valor. Ela é comumente usada para expressar ações ou operações que devem ser realizadas em um objeto, como imprimir, modificar ou processar dados. A interface Consumer possui um método abstrato chamado accept(), que é implementado para definir a lógica da ação a ser executada.

// Supplier é uma interface funcional que representa uma função que não recebe argumentos e retorna um valor. Ele é comumente usado para expressar a geração ou fornecimento de dados, como criar objetos, gerar números aleatórios ou fornecer valores padrão. A interface Supplier possui um método abstrato chamado get(), que é implementado para definir a lógica de geração do valor a ser retornado.

//O Comparator é utilizado para criar regras de ordenação entre elementos de uma classe. As seguintes regras devem ser obedecidas na implementação do método de um Comparator:
//retornar um número negativo se o primeiro valor é menor que o segundo;
//retornar zero se os valores são iguais;
//retornar positivo se o primeiro valor é maior que o segundo.
//Comparators são frequentemente usados para ordenar coleções de objetos, como listas ou conjuntos, com base em critérios específicos, como ordem alfabética, ordem numérica ou qualquer outra lógica personalizada definida pelo programador.

//Collections é uma classe utilitária em Java que fornece métodos estáticos para manipular e operar em coleções de dados, como listas, conjuntos e mapas. Ela oferece uma variedade de métodos para realizar operações comuns, como ordenação, busca, filtragem e transformação de coleções. A classe Collections é parte do pacote java.util e é amplamente utilizada para facilitar o trabalho com coleções em Java.
//Tipos de Collections -
//List: Representa uma coleção ordenada de elementos, onde cada elemento pode ser acessado por sua posição (índice) na lista. Exemplo: ArrayList, LinkedList, Vector.
//Set: Representa uma coleção que não permite elementos duplicados e não mantém uma ordem específica. Exemplo: HashSet, TreeSet, LinkedHashSet.
//Map: Representa uma coleção de pares chave-valor, onde cada chave é única e mapeia para um valor correspondente. Exemplo: HashMap, TreeMap, LinkedHashMap.

//List é uma interface em Java que representa uma coleção ordenada de elementos, onde cada elemento pode ser acessado por sua posição (índice) na lista. Ela é parte do pacote java.util e é amplamente utilizada para armazenar e manipular conjuntos de dados. A interface List define métodos para adicionar, remover, acessar e modificar elementos na lista, bem como para realizar operações como ordenação, busca e iteração sobre os elementos. Existem várias implementações da interface List, como ArrayList, LinkedList e Vector, cada uma com suas próprias características e desempenho.
//Metodos List  : .add(E e): Adiciona um elemento à lista.
//.get(int index): Retorna o elemento na posição especificada.
//.remove(int index): Remove o elemento na posição especificada.
//contains(Object o): Retorna true se a lista contiver o elemento especificado.
//.size(): Retorna o número de elementos na lista.
//.forEach(Consumer<? super E> action): Executa a ação fornecida para cada elemento da lista.
//.removeIf(Predicate<? super E> filter): Remove todos os elementos da lista que satisfazem a condição definida pelo Predicate fornecido.
//.sort(Comparator<? super E> c): Ordena os elementos da lista de acordo com a ordem definida pelo Comparator fornecido.
//...


import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class ProgFunTests {
    static void main() {

        List<String> nomes = List.of("Alice", "Bob", "Charlie", "David", "Eve");

        // Usando Predicate para filtrar nomes que começam com 'A'
        List<String> nomesComA = nomes.stream()
                .filter(nome -> nome.startsWith("A"))
                .toList();
        System.out.println("Nomes que começam com 'A': " + nomesComA);

        // Usando Consumer para imprimir cada nome
        System.out.println("Todos os nomes:");
        nomes.forEach(nome -> System.out.println(nome));

        // Usando Supplier para fornecer um nome aleatório
        Supplier<String> nomeAleatorio = () -> nomes.get((int) (Math.random() * nomes.size()));
        System.out.println("Nome aleatório: " + nomeAleatorio.get());

        // Usando Comparator para ordenar os nomes em ordem alfabética
        List<String> nomesOrdenados = nomes.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("Nomes ordenados: " + nomesOrdenados);

        // Usando Collections para ordenar os nomes em ordem alfabética
        List<String> nomesOrdenadosCollections = new java.util.ArrayList<>(List.copyOf(nomes));
        java.util.Collections.sort(nomesOrdenadosCollections);
        System.out.println("Nomes ordenados com Collections: " + nomesOrdenadosCollections);

        // Usando List para acessar e modificar elementos
        List<String> nomesModificaveis = new java.util.ArrayList<>(nomes);
        nomesModificaveis.add("Frank");
        System.out.println("Nomes modificáveis: " + nomesModificaveis);


    }
}
