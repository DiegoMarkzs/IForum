package com.projeto.IForum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.IForum.model.Relato;
import com.projeto.IForum.model.RelatoAnonimo;
import com.projeto.IForum.model.RelatoPublico;
import com.projeto.IForum.dto.RelatoDTO;
import com.projeto.IForum.model.CategoriaRelato;
import com.projeto.IForum.model.TipoRelato;
import com.projeto.IForum.service.RelatoService;

@CrossOrigin
@RestController
@RequestMapping("/relatos")
public class RelatoController {

    @Autowired
    private RelatoService relatoService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RelatoDTO dto) {
        Relato relato;

        if (dto.isAnonimo()) {
            relato = new RelatoAnonimo();
        } else {
            relato = new RelatoPublico();
           
        }

        relato.setAssunto(dto.getAssunto());
        relato.setCategoria(CategoriaRelato.valueOf(dto.getCategoria()));
        relato.setTipo(TipoRelato.valueOf(dto.getTipoRelato()));

        Relato salvo = relatoService.salvar(relato);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relato> getRelatoById(@PathVariable Long id) {
        Optional<Relato> relato = relatoService.buscarPorId(id);
        return relato.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
   public ResponseEntity<Relato> atualizarRelato(@PathVariable Long id, @RequestBody RelatoDTO dto) {
    try {
        Relato novoRelato = dto.isAnonimo() ? new RelatoAnonimo() : new RelatoPublico();
        novoRelato.setAssunto(dto.getAssunto());
        novoRelato.setCategoria(CategoriaRelato.valueOf(dto.getCategoria()));
        novoRelato.setTipo(TipoRelato.valueOf(dto.getTipoRelato()));
        
        Relato atualizado = relatoService.atualizarRelato(id, novoRelato);
        return ResponseEntity.ok(atualizado);
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelato(@PathVariable Long id) {
        if (relatoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        relatoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Relato>> listarTodos() {
        List<Relato> relatos = relatoService.buscarTodos();
        return ResponseEntity.ok(relatos);
    }

    

    
}
