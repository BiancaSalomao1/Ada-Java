package Aula13Abr.enums;

public enum EnumMenuAdmin {

    CADASTRAR_PRODUTO(1, "Cadastrar Produto"),
    LISTAR_PRODUTOS(2, "Listar Produtos"),
    ATUALIZAR_PRODUTO(3, "Atualizar Produto"),
    EXCLUIR_PRODUTO(4, "Excluir Produto"),
    SAIR(0, "Sair");

    private int opcaoId;
    private String descricao;

    EnumMenuAdmin(int opcaoId, String descricao) {
        this.opcaoId = opcaoId;
        this.descricao = descricao;
    }

    public int getOpcaoId() {
        return opcaoId;
    }

    public String getDescricao() {
        return descricao;
    }

    //metodo para obter a opção com base no código
    public static EnumMenuAdmin getOpcao(int codigo) {
        for (EnumMenuAdmin op : values()) {
            if (op.getOpcaoId() == codigo) {
                return op;
            }
        }
        return null; //ignorar erro de código inválido
    }
}