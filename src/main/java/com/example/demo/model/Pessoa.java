package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Pessoa {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geradorPessoa")
	@SequenceGenerator(name = "geradorPessoa", sequenceName = "gerador_pessoa_seq", allocationSize = 1, initialValue = 1)
	private Long id;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String email;

}
