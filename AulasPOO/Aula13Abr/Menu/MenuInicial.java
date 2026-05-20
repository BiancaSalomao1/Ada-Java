package Aula13Abr.Menu;

import Aula13Abr.enums.EnumMenuInicial;
import utils.Constants;

import java.util.Scanner;

public class MenuInicial {

    public static void exibirMenuInicial() {
        System.out.println(Constants.PRETO + "E-commerce Ser + Tech" + Constants.RESET);

        for (EnumMenuInicial op : EnumMenuInicial.values()) {
            System.out.println(op.getOpcaoId() + " - " + op.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
    }

    public static boolean realizarLogin(Scanner sc) {

        sc.nextLine();

        System.out.print("Digite o login: ");
        String administrador = sc.nextLine();

        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();

        boolean login = administrador.trim().equals("admin")
                && senha.trim().equals("admin");

        System.out.println(login ? "Login bem-sucedido" : "Usuário ou senha inválidos");

        return login;
    }
}