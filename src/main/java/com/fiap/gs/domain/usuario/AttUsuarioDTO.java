package com.fiap.gs.domain.usuario;


import jakarta.validation.constraints.NotNull;

public record AttUsuarioDTO(@NotNull Long id, String nome, String email, String senha) {


}
