package utils;

public class EfeitoTexto {
    public static void escrever(String texto, int velocidade) {
        try {
            for (char c : texto.toCharArray()) {
                System.out.print(utils.Constants.PRETO + c + utils.Constants.RESET);
                Thread.sleep(velocidade);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
