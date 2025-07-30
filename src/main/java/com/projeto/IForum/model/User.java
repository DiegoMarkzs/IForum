package com.projeto.IForum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class User implements Serializable {
	private static final long serialVersionUID = -6518853480190451215L;
	

	

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Relato> relatos = new ArrayList<>();
		

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "tb_user_seq", allocationSize = 1)
	private long id;

	@Column(unique = true)
	@Nullable()
	private String email;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "Sobrenome")
	private String sobrenome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Nascimento")
	private Date nascimento;
	
	@Column(name = "Senha")
	private String senha;
	
	public User() {
		
	}	


}
