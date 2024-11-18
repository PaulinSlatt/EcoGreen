package com.fiap.gs.domain.consumo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.gs.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


// Classe para representar o BD e fazer a persistência JPA

@Table(name = "consumo_energia")
@Entity(name = "Consumo_energia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consumo {

    @Id
    @Column(name = "id_consumo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate data;

    @Column(name = "consumo_kwh", precision = 10, scale = 2, nullable = false)
    private BigDecimal consumoKwh;


    @Column(name = "tendencia", length = 255)
    @Enumerated(EnumType.STRING)
    private ConsumoTendencia tendencia;

    private boolean ativo;



    public Consumo(ConsumoDTO dados) {
        this.usuario = new Usuario(dados.usuarioId());
        this.data = dados.data();
        this.consumoKwh = dados.consumoKwh();
        this.tendencia = dados.tendencia();
        this.ativo = true;
    }


    public void atualizarInformacoes(AttConsumoDTO dados) {
        if (dados.data() != null) {
            this.data = dados.data();
        }
        if (dados.consumoKwh() != null) {
            this.consumoKwh = dados.consumoKwh();
        }

    }

    public void excluir() {
        this.ativo = false;
    }


}