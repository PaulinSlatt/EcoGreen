package com.fiap.gs.domain.consumo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ConsumoRepository extends JpaRepository<Consumo, Long> {

    Page<Consumo> findAllByAtivoTrue(Pageable paginacao);


    List<Consumo> findAllByUsuarioIdAndAtivoTrue(Long usuarioId);

}
