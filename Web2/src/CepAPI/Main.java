package CepAPI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o CEP: ");

        String cep = scanner.nextLine();

        try {

            Endereco endereco =
                    EnderecoService.consultarCep(cep);

            System.out.println("Endereço encontrado:");
            System.out.println(endereco);

        } catch (Exception e) {

            System.out.println(
                    "Erro ao buscar o endereço: "
                            + e.getMessage()
            );

        } finally {

            scanner.close();
        }
    }
}