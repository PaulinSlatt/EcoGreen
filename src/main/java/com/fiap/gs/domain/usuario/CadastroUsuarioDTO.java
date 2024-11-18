package com.fiap.gs.domain.usuario;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record CadastroUsuarioDTO(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String senha)  {



}
