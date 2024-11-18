package com.fiap.gs.domain.consumo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListaConsumoDTO(Long id, Long usuarioId, LocalDate data, BigDecimal consumoKwh, ConsumoTendencia tendencia) {

    public ListaConsumoDTO(Consumo consumo) {
        this(
                consumo.getId(),
                consumo.getUsuario().getId(),
                consumo.getData(),
                consumo.getConsumoKwh(),
                consumo.getTendencia()
        );
    }
}