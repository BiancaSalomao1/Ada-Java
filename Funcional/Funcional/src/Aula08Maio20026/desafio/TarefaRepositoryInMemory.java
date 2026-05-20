package Aula08Maio20026.desafio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TarefaRepositoryInMemory implements TarefaRepository {

    private final List<Tarefa> tarefas = new ArrayList<>();

    public TarefaRepositoryInMemory() {
        Tarefa t1 = Tarefa.novaTarefaPendentePrioridadeBaixa("T001", "Estudar Java", "Revisar conceitos de OO e interfaces");
        Tarefa t2 = Tarefa.novaTarefaPendentePrioridadeMedia("T002", "Fazer exercícios", "Resolver os desafios da aula 6");
        Tarefa t3 = Tarefa.novaTarefaPendentePrioridadeAlta("T003", "Revisar código", "Revisar as pull requests pendentes");
        Tarefa t4 = Tarefa.novaTarefaPendentePrioridadeAlta("T004", "Deploy em PRD", "Realizar o deploy da aplicação em produção");
        Tarefa t5 = Tarefa.novaTarefaPendentePrioridadeBaixa("T005", "Ler documentação", "Ler a documentação do novo framework");

        // Ajustando status iniciais para o Mock
        t2.atualizarStatus(Tarefa.Status.EM_ANDAMENTO);

        t3.atualizarStatus(Tarefa.Status.EM_ANDAMENTO);
        t3.atualizarStatus(Tarefa.Status.CONCLUIDA);

        t4.atualizarStatus(Tarefa.Status.EM_ANDAMENTO);
        t4.atualizarStatus(Tarefa.Status.CONCLUIDA);

        // Usando reflection para simular datas retroativas ou futuras (cenários de teste)
        setField(t1, "dataPrevista", LocalDateTime.now().minusDays(2));
        setField(t2, "dataPrevista", LocalDateTime.now().plusDays(5));
        setField(t3, "dataPrevista", LocalDateTime.now().minusDays(1));
        setField(t3, "dataConclusao", LocalDateTime.now().minusDays(10));
        setField(t4, "dataPrevista", LocalDateTime.now().plusDays(2));
        setField(t4, "dataConclusao", LocalDateTime.now().minusDays(2));

        tarefas.add(t1);
        tarefas.add(t2);
        tarefas.add(t3);
        tarefas.add(t4);
        tarefas.add(t5);
    }

    private void setField(Object obj, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            System.err.println("Erro ao setar campo " + fieldName + " via reflection: " + e.getMessage());
        }
    }

    @Override
    public void salvar(Tarefa tarefa) {
        // Lógica de Upsert: se o código existe, remove o antigo e insere o novo/atualizado
        buscarPorCodigo(tarefa.getCodigo()).ifPresent(tarefas::remove);
        tarefas.add(tarefa);
    }

    @Override
    public List<Tarefa> listar() {
        // Retorna uma cópia da lista para proteger a integridade dos dados internos
        return new ArrayList<>(tarefas);
    }

    @Override
    public Optional<Tarefa> buscarPorCodigo(String codigo) {
        return tarefas.stream()
                .filter(t -> t.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    @Override
    public List<Tarefa> buscarPorStatus(Tarefa.Status status) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tarefa> buscarPorPrioridade(Tarefa.Prioridade prioridade) {
        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tarefa> buscarTarefasComDataPrevistaExpirada() {
        LocalDateTime agora = LocalDateTime.now();
        return tarefas.stream()
                .filter(t -> t.getStatus() != Tarefa.Status.CONCLUIDA)
                .filter(t -> t.getDataPrevista() != null && t.getDataPrevista().isBefore(agora))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tarefa> buscarTarefasConcluidasNoPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Tarefa.Status.CONCLUIDA)
                .filter(t -> t.getDataConclusao() != null)
                .filter(t -> !t.getDataConclusao().isBefore(dataInicial) &&
                        !t.getDataConclusao().isAfter(dataFinal))
                .collect(Collectors.toList());
    }
}