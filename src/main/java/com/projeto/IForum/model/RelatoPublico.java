package com.projeto.IForum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "TB_RELATO_PUBLICO")
@Data
public class RelatoPublico extends Relato{

    


    public RelatoPublico(User usuario){
        this.setUsuario(usuario);
    }
    public RelatoPublico() {
        
    }

     public void setUsuario(User usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo em RelatoPublico");
        }
        super.setUsuario(usuario);
    }

}
