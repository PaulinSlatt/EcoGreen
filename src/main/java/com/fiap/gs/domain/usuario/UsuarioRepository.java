package com.fiap.gs.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    Optional<Usuario> findByEmail(String email);

}
