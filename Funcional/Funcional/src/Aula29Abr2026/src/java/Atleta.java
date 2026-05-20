package Aula29Abr2026.src.java;

class Atleta {

    String nome;
    String nacionalidade;
    double altura, peso;

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +'}';
    }
}