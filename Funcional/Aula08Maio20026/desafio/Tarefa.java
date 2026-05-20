package Aula08Maio20026.desafio;

import java.time.LocalDateTime;

public class Tarefa {

    private String codigo;
    private String titulo;
    private String descricao;
    private LocalDateTime dataPrevista;
    private LocalDateTime dataConclusao;
    private Prioridade prioridade = Prioridade.BAIXA;
    private Status status = Status.PENDENTE;
    private int pontos = 0;

    private Tarefa(String codigo, String titulo, String descricao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        validarCodigo();
        validarTitulo();
        validarDescricao();
    }



    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        validarCodigo();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        validarTitulo();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        validarDescricao();
    }

    public LocalDateTime getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDateTime dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Status getStatus() {
        return status;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
        validarPontos();
    }



    public static Tarefa novaTarefaPendentePrioridadeAlta(String codigo, String titulo, String descricao) {
        Tarefa tarefa = new Tarefa(codigo, titulo, descricao);
        tarefa.atualizarPrioridade(Prioridade.ALTA);
        return tarefa;
    }

    public static Tarefa novaTarefaPendentePrioridadeMedia(String codigo, String titulo, String descricao) {
        Tarefa tarefa = new Tarefa(codigo, titulo, descricao);
        tarefa.atualizarPrioridade(Prioridade.MEDIA);
        return tarefa;
    }

    public static Tarefa novaTarefaPendentePrioridadeBaixa(String codigo, String titulo, String descricao) {
        Tarefa tarefa = new Tarefa(codigo, titulo, descricao);
        tarefa.atualizarPrioridade(Prioridade.BAIXA);
        return tarefa;
    }



    private void validarCodigo() {
        if (this.codigo == null || this.codigo.length() < 4) {
            throw new RuntimeException("Codigo inválido");
        }
    }

    private void validarTitulo() {
        if (this.titulo == null || this.titulo.length() < 5) {
            throw new RuntimeException("Titulo inválido");
        }
    }

    private void validarDescricao() {
        if (this.descricao == null || this.descricao.length() < 10) {
            throw new RuntimeException("Descricao inválida");
        }
    }

    private void validarPontos() {
        if (this.pontos < 0 || this.pontos > 10) {
            throw new RuntimeException("Pontos inválidos");
        }
    }



    public void atualizarStatus(Status novoStatus) {
        // Regra: Não pode pular de PENDENTE direto para CONCLUIDA (deve passar por EM_ANDAMENTO)
        if (this.status == Status.PENDENTE && novoStatus == Status.CONCLUIDA) {
            throw new RuntimeException("Atualização de status inválida: passe de PENDENTE para EM_ANDAMENTO primeiro.");
        }

        if (novoStatus == Status.CONCLUIDA) {
            this.dataConclusao = LocalDateTime.now();
        }
        this.status = novoStatus;
    }

    public void atualizarPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }


    public boolean isPrioridadeAlta() {
        return this.prioridade == Prioridade.ALTA;
    }



    public enum Prioridade {
        BAIXA, MEDIA, ALTA
    }

    public enum Status {
        PENDENTE, EM_ANDAMENTO, CONCLUIDA
    }
}