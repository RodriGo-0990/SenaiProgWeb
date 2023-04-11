package com.algaworks.algalog.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String nome;

	public Double preco;

	@ManyToOne
	@JoinColumn(name = "compra")
	public Compra compra;

	
	
}
