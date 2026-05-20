package Aula13Abr.enums;

public enum EnumMenuCliente {

    LISTAR_PRODUTOS(1, "Listar Produtos"),
    PESQUISAR_PRODUTO(2, "Pesquisar Produto"),
    FAZER_PEDIDO(3, "Fazer Pedido"),
    LISTAR_PEDIDOS(4, "Listar meus pedidos"),
    SAIR(0, "Sair");

    private int opcaoId;
    private String descricao;

    EnumMenuCliente(int opcaoId, String descricao) {
        this.opcaoId = opcaoId;
        this.descricao = descricao;
    }

    public int getOpcaoId() {
        return opcaoId;
    }

    public String getDescricao() {
        return descricao;
    }


    public static EnumMenuCliente getOpcao(int codigo) {
        for (EnumMenuCliente op : EnumMenuCliente.values()) {
            if (op.getOpcaoId() == codigo) {
                return op;
            }
        }
        return null;
    }
}