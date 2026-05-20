package Aula29Abr2026.src.java;

import java.util.ArrayList;
import java.util.List;


public class RepositorioHistoricoTreino{

    public static Object listarPorModalidade;
    static List<HistoricoTreino> historicos = new ArrayList<>();

    public static void adicionar(HistoricoTreino historico) {
        historicos.add(historico);
    }

    public static List<HistoricoTreino> listarTodos() {
        return historicos;
    }


    public static List<HistoricoTreino> listarPorModalidade(String modalidadeStr) {

        List<HistoricoTreino> resultado = new ArrayList<>();

        try {
            // converte String → Enum
            Modalidade modalidade = Modalidade.valueOf(modalidadeStr.toUpperCase());

            for (HistoricoTreino ht : historicos) {
                if (ht.modalidade == modalidade) {
                    resultado.add(ht);
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Modalidade inválida: " + modalidadeStr);
        }

        return resultado;
    }
}