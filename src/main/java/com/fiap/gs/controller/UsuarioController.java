package com.fiap.gs.controller;

import com.fiap.gs.domain.usuario.*;
import com.fiap.gs.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint para cadastro de usuários
    @PostMapping
    public ResponseEntity<ListaUsuarioDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = usuarioService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new ListaUsuarioDTO(usuario));
    }

    // Endpoint para login de usuários, retorna um token JWT
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AutenticacaoDTO autenticacao) {
        var loginResponse = usuarioService.autenticarUsuario(autenticacao.email(), autenticacao.senha());
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ListaUsuarioDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = usuarioService.listar(paginacao).map(ListaUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }


    @PutMapping("/editar")
    public ResponseEntity<ListaUsuarioDTO> atualizar(@RequestBody @Valid AttUsuarioDTO dados) {
        var usuarioAtualizado = usuarioService.atualizar(dados);
        return ResponseEntity.ok(new ListaUsuarioDTO(usuarioAtualizado));
    }

    // Endpoint para excluir o próprio usuário autenticado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para detalhar as informações do usuário autenticado
    @GetMapping("/{id}")
    public ResponseEntity<ListaUsuarioDTO> detalhar(@PathVariable Long id) {
        var usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(new ListaUsuarioDTO(usuario));
    }
}