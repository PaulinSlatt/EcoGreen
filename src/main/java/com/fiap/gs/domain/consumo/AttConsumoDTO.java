package com.fiap.gs.domain.consumo;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record AttConsumoDTO(@NotNull Long id, BigDecimal consumoKwh, LocalDate data, ConsumoTendencia tendencia) {


    public AttConsumoDTO(Consumo consumo) {
        this(
                consumo.getId(),
                consumo.getConsumoKwh(),
                consumo.getData(),
                consumo.getTendencia()

        );
    }
}