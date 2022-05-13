package br.com.diegopatricio.freela.pagamento.domain;

public enum StatusPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    private StatusPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }

    public static StatusPagamento toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (StatusPagamento pagamento : StatusPagamento.values()){
            if (cod.equals(pagamento.getCod())){
                return pagamento;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
