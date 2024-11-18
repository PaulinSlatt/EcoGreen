package com.fiap.gs.domain.consumo;

import com.fiap.gs.domain.usuario.Usuario;
import com.fiap.gs.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PreAuthorize("isAuthenticated()")
    public Consumo cadastrar(ConsumoDTO dados) {
        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Consumo consumo = new Consumo();
        consumo.setUsuario(usuario);
        consumo.setData(dados.data());
        consumo.setConsumoKwh(dados.consumoKwh());
        consumo.setAtivo(true);

        // Busca o consumo do mês anterior
        Optional<Consumo> consumoAnteriorOpt = buscarConsumoMesAnterior(usuario.getId(), dados.data());

        // Calcula a tendência com base no consumo anterior e armazena a mensagem completa
        ConsumoTendencia tendencia = calcularTendencia(dados.consumoKwh(), consumoAnteriorOpt);
        consumo.setTendencia(tendencia);

        return repository.save(consumo);
    }

    private ConsumoTendencia calcularTendencia(BigDecimal consumoAtual, Optional<Consumo> consumoAnteriorOpt) {
        if (consumoAnteriorOpt.isPresent()) {
            BigDecimal consumoAnterior = consumoAnteriorOpt.get().getConsumoKwh();

            if (consumoAtual.compareTo(consumoAnterior) < 0) {
                return ConsumoTendencia.MELHOROU;
            } else if (consumoAtual.compareTo(consumoAnterior) > 0) {
                return ConsumoTendencia.PIOROU;
            } else {
                return ConsumoTendencia.IGUAL;
            }
        } else {
            // Retorna PRIMEIRO_REGISTRO ou uma outra tendência padrão se não houver consumo anterior
            return ConsumoTendencia.PRIMEIRO_REGISTRO;
        }
    }

    @PreAuthorize("isAuthenticated()")
    public Optional<Consumo> buscarConsumoMesAnterior(Long usuarioId, LocalDate dataAtual) {
        List<Consumo> consumosUsuario = repository.findAllByUsuarioIdAndAtivoTrue(usuarioId);
        LocalDate mesAnterior = dataAtual.minusMonths(1);

        return consumosUsuario.stream()
                .filter(consumo -> consumo.getData().getMonthValue() == mesAnterior.getMonthValue() &&
                        consumo.getData().getYear() == mesAnterior.getYear())
                .findFirst();
    }

    @PreAuthorize("isAuthenticated()")
    public Page<Consumo> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    public Consumo atualizar(AttConsumoDTO dados) {
        var consumo = repository.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado"));
        consumo.atualizarInformacoes(dados);
        return consumo;
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    public void excluir(Long id) {
        var tratamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado"));
        tratamento.excluir();
    }

    @PreAuthorize("isAuthenticated()")
    public Consumo detalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Consumo não encontrado com o ID: " + id));
    }

    @PreAuthorize("isAuthenticated()")
    public List<ConsumoDTO> listarConsumosPorUsuario(Long usuarioId) {
        // Busca todos os consumos ativos do usuário, ordenados por data (ascendente)
        List<Consumo> consumos = repository.findAllByUsuarioIdAndAtivoTrue(usuarioId).stream()
                .sorted((c1, c2) -> c1.getData().compareTo(c2.getData()))
                .collect(Collectors.toList());

        List<ConsumoDTO> consumoDTOs = new ArrayList<>();
        Consumo consumoAnterior = null;

        for (Consumo consumoAtual : consumos) {
            // Calcula a tendência com relação ao consumo anterior
            ConsumoTendencia tendencia = calcularTendencia(consumoAtual.getConsumoKwh(), Optional.ofNullable(consumoAnterior));
            consumoAtual.setTendencia(tendencia);

            // Adiciona o consumo atual ao DTO
            consumoDTOs.add(new ConsumoDTO(consumoAtual));

            // Atualiza o consumoAnterior para o próximo cálculo de tendência
            consumoAnterior = consumoAtual;
        }

        return consumoDTOs;
    }
}