package com.projeto.IForum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_RELATO_ANONIMO")
@Data
public class RelatoAnonimo extends Relato{

    

    public RelatoAnonimo(){
    }

}
