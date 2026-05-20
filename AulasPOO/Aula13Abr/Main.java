package Aula13Abr;

import Aula13Abr.Menu.MenuAdmin;
import Aula13Abr.Menu.MenuCliente;
import Aula13Abr.Menu.MenuInicial;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            MenuInicial.exibirMenuInicial();
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    if (MenuInicial.realizarLogin(sc)) {
                        MenuAdmin.MenuAdmin(sc);
                    } else {
                        System.out.println("Acesso negado. Retornando ao menu inicial.");
                    }
                    break;

                case 2:
                    MenuCliente.MenuCliente(sc);
                    break;

                case 0:
                    utils.EfeitoTexto.escrever("Encerrando o programa...", 50);
                    break;

                default:
                    System.out.println("Opção inválida");
            }

        } while (opcao != 0);
    }
}