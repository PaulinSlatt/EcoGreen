package com.fiap.gs.domain.usuario;


import com.fiap.gs.infra.security.DTOTokenJWT;
import com.fiap.gs.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(CadastroUsuarioDTO dados) {
        var usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        return repository.save(usuario);
    }

    public Page<Usuario> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    @Transactional
    public Usuario atualizar(AttUsuarioDTO dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        return usuario;
    }

    @Transactional
    public void excluir(Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
    }

    public Usuario buscarUsuarioPorId(Long usuarioId) {
        return repository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Método de autenticação para gerar token JWT
    public LoginResponseDTO autenticarUsuario(String email, String senha) {
        Authentication auth = new UsernamePasswordAuthenticationToken(email, senha);
        Authentication authentication = authenticationManager.authenticate(auth);

        // Casting para UsuarioUserDetails para extrair o objeto Usuario interno
        UsuarioUserDetails usuarioDetails = (UsuarioUserDetails) authentication.getPrincipal();
        Usuario usuario = usuarioDetails.getUsuario();
        String token = tokenService.gerarToken(usuario);

        return new LoginResponseDTO(token, new ListaUsuarioDTO(usuario));
    }
}