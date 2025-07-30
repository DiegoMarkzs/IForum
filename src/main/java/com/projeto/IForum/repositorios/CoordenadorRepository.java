package com.projeto.IForum.repositorios;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.IForum.model.Coordenador;

@Repository
public interface CoordenadorRepository extends CrudRepository<Coordenador, Long>{
    Optional<Coordenador> findByEmailAndSenha(String email, String senha);

}
