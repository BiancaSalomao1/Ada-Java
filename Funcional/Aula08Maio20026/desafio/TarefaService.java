package Aula08Maio20026.desafio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public void salvar(Tarefa tarefa) {
        tarefaRepository.salvar(tarefa);
    }

    public List<Tarefa> listar() {
        return tarefaRepository.listar();
    }

    public Optional<Tarefa> buscarPorCodigo(String codigo) {
        return tarefaRepository.buscarPorCodigo(codigo);
    }

    public List<Tarefa> buscarPorStatus(Tarefa.Status status) {
        return tarefaRepository.buscarPorStatus(status);
    }

    public List<Tarefa> buscarPorPrioridade(Tarefa.Prioridade prioridade) {
        return tarefaRepository.buscarPorPrioridade(prioridade);
    }


    public List<Tarefa> buscarTarefasAtrasadas() {
        return tarefaRepository.buscarTarefasComDataPrevistaExpirada();
    }


    public int calcularPontosNoPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<Tarefa> tarefasConcluidas = tarefaRepository.buscarTarefasConcluidasNoPeriodo(dataInicial, dataFinal);

        return tarefasConcluidas.stream()
                .mapToInt(this::converterPrioridadeEmPontos)
                .sum();
    }

    private int converterPrioridadeEmPontos(Tarefa tarefa) {
        return switch (tarefa.getPrioridade()) {
            case ALTA -> 10;
            case MEDIA -> 5;
            case BAIXA -> 2;
            default -> 0;
        };
    }
}