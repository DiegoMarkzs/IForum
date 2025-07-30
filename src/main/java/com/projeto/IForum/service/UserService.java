package com.projeto.IForum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.IForum.model.User;
import com.projeto.IForum.repositorios.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User salvar(User user) {
        return userRepository.save(user);
    }

   
    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    public boolean fazerLogin(String email, String senha){
        return userRepository.findByEmailAndSenha(email, senha).isPresent();
    }

   
    public User buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

   
    public List<User> listarTodos() {
        Iterable<User> iterable = userRepository.findAll();
        List<User> lista = new ArrayList<>();
        iterable.forEach(lista::add);
        return lista;
    }

  
    public void deletarPorId(Long id) {
        userRepository.deleteById(id);
    }

    public User atualizar(Long id, User userAtualizado) {
        User existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existente.setNome(userAtualizado.getNome());
        existente.setSobrenome(userAtualizado.getSobrenome());
        existente.setEmail(userAtualizado.getEmail());
        existente.setSenha(userAtualizado.getSenha());
        existente.setNascimento(userAtualizado.getNascimento());

        return userRepository.save(existente);
    }
}

