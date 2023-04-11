package com.algaworks.algalog.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String produto;

	public Double preco;

	@OneToOne
	@JoinColumn(name = "cliente")
	public Cliente cliente;

	
	
}
