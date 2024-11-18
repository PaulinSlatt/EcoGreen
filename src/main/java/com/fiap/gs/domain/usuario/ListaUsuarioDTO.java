package com.fiap.gs.domain.usuario;

public record ListaUsuarioDTO(Long id, String nome, String email) {

    public ListaUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }


}
