package com.projeto.IForum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.IForum.model.Coordenador;
import com.projeto.IForum.repositorios.CoordenadorRepository;

@Service
public class CoordenadorService {
     @Autowired
    private CoordenadorRepository coordenadorRepository;

   
    public Coordenador salvar(Coordenador coordenador) {
        return coordenadorRepository.save(coordenador);
    }

   
    public Optional<Coordenador> buscarPorId(Long id) {
        return coordenadorRepository.findById(id);
    }

  
    public List<Coordenador> listarTodos() {
        Iterable<Coordenador> iterable = coordenadorRepository.findAll();
        List<Coordenador> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

   
    public void deletarPorId(Long id) {
        coordenadorRepository.deleteById(id);
    }


    public Coordenador atualizar(Long id, Coordenador coordenadorAtualizado) {
        Coordenador existente = coordenadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));

        existente.setNome(coordenadorAtualizado.getNome());
        existente.setEmail(coordenadorAtualizado.getEmail());
        existente.setSenha(coordenadorAtualizado.getSenha());
    

        return coordenadorRepository.save(existente);
    }

    public boolean fazerLogin(String email, String senha){
        return coordenadorRepository.findByEmailAndSenha(email, senha).isPresent();
    }

}
