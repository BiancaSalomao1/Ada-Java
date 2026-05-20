package Aula08Abr;

import Aula08Abr.Conta.ContaEspecial;
import Aula08Abr.Conta.ContaPoupanca;
import Aula08Abr.Produto.CD;
import Aula08Abr.Produto.Livro;


public class Aula08AbrMain {
    static void main() {
        Livro livro1 = new Livro("O Senhor dos Anéis", 12.23, "1010001111", "J.R.R. Tolkien", "HarperCollins", 1178);
        CD cd1 = new CD("Thriller", 15.80, "1001011", "Michael Jackson", "Epic Records", 9);

            System.out.println(livro1);
            System.out.println(cd1);

//        ContaEspecial contaEspecial1 = new ContaEspecial("João Silva", "123456789", 1000.00, 500.00);
//        ContaPoupanca contaPoupanca1 = new ContaPoupanca("Maria Souza", "987654321", 2000.00, 0.05);
//
//            System.out.println(contaEspecial1);
//            System.out.println(contaPoupanca1);
    }
}