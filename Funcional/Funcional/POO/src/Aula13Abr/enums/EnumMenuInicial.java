package Aula13Abr.enums;

import java.util.Optional;

public enum EnumMenuInicial {

    SOU_ADMINISTRADOR(1, "Sou Administrador"),
    SOU_CLIENTE(2, "Sou Cliente"),
    SAIR(0, "Sair");


   // SOU_CLIENTE(2, "Sou Cliente");


    private int opcaoId;
    private String descricao;

    EnumMenuInicial(int opcaoId, String descricao) {
        this.opcaoId = opcaoId;
        this.descricao = descricao;
    }

    public int getOpcaoId() {
        return opcaoId;
    }

    public String getDescricao() {
        return descricao;
    }
    public static Optional<EnumMenuInicial> getOpcao(int codigo) {
        for (EnumMenuInicial op : EnumMenuInicial.values()) {
            if (op.getOpcaoId() == codigo) {
                return Optional.of(op);
            }
        }
        return Optional.empty();
    }


}
