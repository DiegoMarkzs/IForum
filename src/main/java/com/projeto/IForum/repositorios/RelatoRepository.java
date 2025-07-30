package com.projeto.IForum.repositorios;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.IForum.model.Relato;

@Repository
public interface RelatoRepository extends CrudRepository<Relato, Long>{

    List<Relato> findByCategoria(String categoria);

}
