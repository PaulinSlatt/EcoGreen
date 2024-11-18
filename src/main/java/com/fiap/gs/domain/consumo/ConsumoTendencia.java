package com.fiap.gs.domain.consumo;

public enum ConsumoTendencia {
    MELHOROU("Seu consumo diminuiu em relação ao mês anterior!"),
    PIOROU("Seu consumo aumentou em relação ao mês anterior."),
    IGUAL("Seu consumo permaneceu igual ao mês anterior."),
    PRIMEIRO_REGISTRO("Primeiro registro de consumo, sem histórico para comparação.");

    private final String mensagem;

    ConsumoTendencia(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}