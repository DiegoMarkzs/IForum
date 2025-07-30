package com.projeto.IForum.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "TB_ALUNO")
@Data
@Getter
@Setter
public class Aluno extends User{

    @Column(name = "curso")
    private String curso;

    public Aluno() {
        super();
    }
}
