package com.projeto.IForum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_COORDENADOR")
@Data
@Getter
@Setter
public class Coordenador{

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coordenador_seq")
	@SequenceGenerator(name = "coordenador_seq", sequenceName = "tb_coordenador_seq", allocationSize = 1)
	private long id;

	@Column(unique = true)
	private String email;
	@Column(name = "Nome")
	private String nome;
	@Column(name = "Senha")
	private String senha;
	
}
