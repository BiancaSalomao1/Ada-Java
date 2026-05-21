package CepAPI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();

        try {
            Endereco endereco = EnderecoService.consultarCep(cep);

            if (endereco != null) {
                System.out.println("\nEndereço encontrado:");
                System.out.println(endereco);
            } else {
                System.out.println("\nNão foi possível obter o endereço. Verifique sua conexão com a internet.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro de validação: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("\nErro inesperado ao buscar o endereço: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}
