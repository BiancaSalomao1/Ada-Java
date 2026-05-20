package Aula13Abr.Menu;

import Aula13Abr.enums.EnumMenuCliente;

import java.util.Scanner;

public class MenuCliente {

    public static void MenuCliente(Scanner sc) {
        int opcao;

        do {
            System.out.println("Welcome to E-commerce Ser + Tech");

            for (EnumMenuCliente op : EnumMenuCliente.values()) {
                System.out.println(op.getOpcaoId() + " - " + op.getDescricao());
            }

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            EnumMenuCliente opcaoEnum = EnumMenuCliente.getOpcao(opcao);

            if (opcaoEnum == null) {
                System.out.println("Opção inválida");
                continue;
            }

            switch (opcaoEnum) {
                case LISTAR_PRODUTOS:
                    System.out.println("Listando produtos...");
                    break;

                case PESQUISAR_PRODUTO:
                    System.out.println("Pesquisando produto...");
                    break;

                case FAZER_PEDIDO:
                    System.out.println("Fazendo pedido...");
                    break;

                case LISTAR_PEDIDOS:
                    System.out.println("Listando meus pedidos...");
                    break;

                case SAIR:
                    System.out.println("Encerrando...");
                    break;
            }

        } while (opcao != EnumMenuCliente.SAIR.getOpcaoId());
    }
}