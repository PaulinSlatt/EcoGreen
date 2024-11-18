package com.fiap.gs.controller;

import com.fiap.gs.domain.consumo.ConsumoService;
import com.fiap.gs.domain.consumo.ConsumoDTO;
import com.fiap.gs.domain.consumo.ListaConsumoDTO;
import com.fiap.gs.domain.consumo.AttConsumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    // Endpoint para cadastrar um novo consumo
    @PostMapping
    public ResponseEntity<ConsumoDTO> cadastrar(@RequestBody @Valid ConsumoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var consumo = consumoService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/consumo/{id}").buildAndExpand(consumo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ConsumoDTO(consumo));
    }

    // Endpoint para listar todos os consumos, com paginação
    @GetMapping
    public ResponseEntity<Page<ListaConsumoDTO>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        var page = consumoService.listar(paginacao).map(ListaConsumoDTO::new);
        return ResponseEntity.ok(page);
    }

    // Endpoint para atualizar as informações de um consumo específico
    @PutMapping
    public ResponseEntity<ListaConsumoDTO> atualizar(@RequestBody @Valid AttConsumoDTO dados) {
        var consumoAtualizado = consumoService.atualizar(dados);
        return ResponseEntity.ok(new ListaConsumoDTO(consumoAtualizado));
    }

    // Endpoint para excluir um consumo pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        consumoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para detalhar um consumo específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ListaConsumoDTO> detalhar(@PathVariable Long id) {
        var consumo = consumoService.detalhar(id);
        return ResponseEntity.ok(new ListaConsumoDTO(consumo));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ConsumoDTO>> listarEvolucaoConsumo(@PathVariable Long usuarioId) {
        List<ConsumoDTO> evolucaoConsumo = consumoService.listarConsumosPorUsuario(usuarioId);
        return ResponseEntity.ok(evolucaoConsumo);
    }
}