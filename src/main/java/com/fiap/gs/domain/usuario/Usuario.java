package com.fiap.gs.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "usuario_energia")
@Entity(name = "Usuario_energia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = " id_usuario_energia")
    private Long id;

    private String nome;

    @Email
    private String email;

    @NotBlank
    private String senha;

    private Boolean ativo;

    public Usuario(CadastroUsuarioDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public Usuario(Long aLong) {
    }

    public void atualizarInformacoes(AttUsuarioDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }


    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void excluir() {
        this.ativo = false;
    }
}
