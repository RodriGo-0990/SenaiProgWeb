package com.algaworks.algalog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.Service.CompraService;
import com.algaworks.algalog.entity.Compra;

@RestController
@RequestMapping(value = "/compras")
public class CompraControler {

	@Autowired
	CompraService service;
	
	//cria
	@PostMapping(value = "/save")
	public ResponseEntity<Compra> SalvarCliente(@RequestBody Compra compra) {
		return ResponseEntity.ok().body(service.save(compra));
	}
	
	//consulta todos
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Compra>> ListarCliente() {
		try {
			List<Compra> lista = service.listAll();
			if(!lista.isEmpty())
				return ResponseEntity.ok().body(service.listAll());
				return new ResponseEntity<>(lista , HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
			
	}
	//consulta por id
	@GetMapping(value = "/consulta/id")
	public ResponseEntity<Compra> consultaCompra(@PathVariable("id") long id){
		try {
			Optional<Compra> compra = service.findById(id);
			if(Optional.ofNullable(compra).isPresent())
				return new ResponseEntity<Compra>(new Compra(), HttpStatus.OK);
				return new ResponseEntity<Compra>(new Compra(), HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<Compra>(new Compra(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//exclusão
	@DeleteMapping(value = "/deletar")
	public ResponseEntity<Compra> deletarCliente(Compra compra){
		service.delete(compra);
		return ResponseEntity.ok().body(compra);
	}
	
	//alterar
	@PutMapping(value = "/alterar/{codigo}")
	public void alterarCompra(@RequestBody Compra compra , @PathVariable("codigo") int codigo) {}

}
