package com.projeto.IForum.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.IForum.model.Relato;
import com.projeto.IForum.model.RelatoAnonimo;
import com.projeto.IForum.model.RelatoPublico;
import com.projeto.IForum.model.User;
import com.projeto.IForum.repositorios.RelatoRepository;

@Service
@Transactional
public class RelatoService {

    @Autowired
    private RelatoRepository relatoRepository;

    public Relato salvar(Relato relato) {
        relato.setData(LocalDate.now());
        return relatoRepository.save(relato);
    }

   
    public Optional<Relato> buscarPorId(Long id) {
        return relatoRepository.findById(id);
    }

      @Transactional
public Relato atualizarRelato(Long id, Relato novo) {
    Relato relatoExistente = relatoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Relato não encontrado"));

    relatoExistente.setAssunto(novo.getAssunto());
    relatoExistente.setCategoria(novo.getCategoria());
    relatoExistente.setTipo(novo.getTipo());
    relatoExistente.setData(LocalDate.now());

    return relatoRepository.save(relatoExistente);
}

   public void deletarPorId(Long id) {
        relatoRepository.deleteById(id);
    }

    
    public List<Relato> listarTodas() {
        Iterable<Relato> iterable = relatoRepository.findAll();
        List<Relato> lista = new ArrayList<>();
        iterable.forEach(lista::add);
        return lista;
    }

    public List<Relato> buscarTodos() {
    Iterable<Relato> iterable = relatoRepository.findAll();
    return StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());
}

   
 

    
    public List<Relato> buscarPorCategoria(String categoria) {
        return relatoRepository.findByCategoria(categoria);
    }

     public void fazerRelatoAnonimo(RelatoAnonimo denuncia) {
        relatoRepository.save(denuncia);
    }

    public void fazerRelatoPublico(RelatoPublico relato, User usuario) {
        relato.setUsuario(usuario);
        relatoRepository.save(relato);
    }

  

    

    

}
