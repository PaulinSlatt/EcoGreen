package com.fiap.gs.infra.security;

import com.fiap.gs.domain.usuario.Usuario;
import com.fiap.gs.domain.usuario.UsuarioRepository;
import com.fiap.gs.domain.usuario.UsuarioUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;


    @Component
    public class SecurityFilter extends OncePerRequestFilter {

        private final TokenService tokenService;
        private final UsuarioRepository usuarioRepository;

        @Autowired
        public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
            this.tokenService = tokenService;
            this.usuarioRepository = usuarioRepository;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            String tokenJWT = recuperarToken(request);
            if (tokenJWT != null) {
                String subject = tokenService.getSubject(tokenJWT);
                Optional<Usuario> usuario = usuarioRepository.findByEmail(subject);
                if (usuario.isPresent()) {
                    UsuarioUserDetails usuarioDetails = new UsuarioUserDetails(usuario.get());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            usuarioDetails, null, usuarioDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        }

        private String recuperarToken(HttpServletRequest request) {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                return authorizationHeader.replace("Bearer ", "");
            }
            return null;
        }
    }
