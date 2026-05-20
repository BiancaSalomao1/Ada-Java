package Aula08Maio20026.desafio;

import java.time.LocalDateTime;
import java.util.Optional;

public class Demo{

    public static void main(String[] args) {
        TarefaRepository repository = new TarefaRepositoryInMemory();
        TarefaService service = new TarefaService(repository);

        System.out.println("--- Listagem de Tarefas ---");
        service.listar().forEach(t ->
                System.out.println("[" + t.getCodigo() + "] " + t.getTitulo() + " | Status: " + t.getStatus() + " | Prioridade: " + t.getPrioridade())
        );

        System.out.println("\n--- Buscando Tarefa T002 ---");
        Optional<Tarefa> tarefaT002 = service.buscarPorCodigo("T002");
        tarefaT002.ifPresentOrElse(
                t -> System.out.println("Encontrada: " + t.getTitulo() + " - " + t.getDescricao()),
                () -> System.out.println("Tarefa T002 não encontrada.")
        );

        System.out.println("\n--- Tarefas com Prioridade ALTA ---");
        service.buscarPorPrioridade(Tarefa.Prioridade.ALTA).forEach(t ->
                System.out.println("- " + t.getTitulo())
        );

        System.out.println("\n--- Tarefas com Status PENDENTE ---");
        service.buscarPorStatus(Tarefa.Status.PENDENTE).forEach(t ->
                System.out.println("- " + t.getTitulo())
        );

        System.out.println("\n--- Total de pontos no período ---");
        // Definindo um período de busca (ex: últimos 30 dias até hoje)
        LocalDateTime inicio = LocalDateTime.now().minusDays(30);
        LocalDateTime fim = LocalDateTime.now().plusDays(1);

        int totalPontos = service.calcularPontosNoPeriodo(inicio, fim);
        System.out.println("Pontuação acumulada (Tarefas Concluídas): " + totalPontos + " pontos.");

        // Extra: Tarefas Atrasadas
        System.out.println("\n--- Tarefas Atrasadas (Expiradas) ---");
        service.buscarTarefasAtrasadas().forEach(t ->
                System.out.println("ALERTA: " + t.getTitulo() + " deveria ter sido entregue em " + t.getDataPrevista())
        );


    }
    


}