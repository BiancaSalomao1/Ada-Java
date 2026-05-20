package Aula13Abr.Menu;

import Aula13Abr.enums.EnumMenuAdmin;

import java.util.Scanner;

public class MenuAdmin {

    public static void MenuAdmin(Scanner sc) {
        int opcao;

        do {
            System.out.println(utils.Constants.PRETO + "E-commerce Ser + Tech" + utils.Constants.RESET);


            for (EnumMenuAdmin op : EnumMenuAdmin.values()) {
                System.out.println(op.getOpcaoId() + " - " + op.getDescricao());
            }

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            EnumMenuAdmin opcaoEnum = EnumMenuAdmin.getOpcao(opcao);

            if (opcaoEnum == null) {
                System.out.println("Opção inválida");
                continue;
            }

            switch (opcaoEnum) {
                case CADASTRAR_PRODUTO:
                    System.out.println("Cadastrando...");
                    break;

                case LISTAR_PRODUTOS:
                    System.out.println("Listando...");
                    break;

                case ATUALIZAR_PRODUTO:
                    System.out.println("Atualizando...");
                    break;

                case EXCLUIR_PRODUTO:
                    System.out.println("Excluindo...");
                    break;

                case SAIR:
                    System.out.println("Encerrando...");
                    break;
            }

        } while (opcao != EnumMenuAdmin.SAIR.getOpcaoId());
    }
}