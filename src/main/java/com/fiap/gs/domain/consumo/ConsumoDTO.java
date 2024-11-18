package com.fiap.gs.domain.consumo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsumoDTO(@NotNull Long usuarioId, @NotNull LocalDate data, @NotNull @PositiveOrZero BigDecimal consumoKwh, ConsumoTendencia tendencia) {
    public ConsumoDTO(Consumo consumo) {
        this(
                consumo.getUsuario().getId(),
                consumo.getData(),
                consumo.getConsumoKwh(),
                consumo.getTendencia()
        );
    }

    @JsonProperty("tendenciaMensagem")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTendenciaMensagem() {
        return tendencia != null ? tendencia.getMensagem() : null;
    }
}