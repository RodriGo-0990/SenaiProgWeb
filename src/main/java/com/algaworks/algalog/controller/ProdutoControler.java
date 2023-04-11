package com.algaworks.algalog.controller;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.algalog.Service.ProdutoService;
import com.algaworks.algalog.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoControler {

	@Autowired
	ProdutoService service;


	//cria registro
	@PostMapping(value = "/save") //salva inserindo os valores
	public ResponseEntity<Produto> SalvarCompra(@RequestParam String produto, @RequestParam Double preco, @RequestBody Produto Produto) {
		try {
			return ResponseEntity.ok().body(service.save(Produto, produto, preco));
		}catch (Exception e){
			return  ResponseEntity.badRequest().body(null);
		}
	}

	//consulta todos
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Produto>> ListarCompra() {
		try {
			List<Produto> lista = service.listAll();
			if (!lista.isEmpty())
				return ResponseEntity.ok().body(service.listAll());
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	//consulta por id
	@GetMapping(value = "/consulta/{id}")
	public ResponseEntity<Produto> consultaCompra(@PathVariable("id") long id) {
		try {
			return ResponseEntity.ok().body(service.findById(id).get());
		} catch (Exception e) {
			return new ResponseEntity<>(new Produto(), HttpStatus.BAD_REQUEST);
		}
	}

	//exclusão
	@DeleteMapping(value = "/deletar/{codigo}")
	public ResponseEntity<Produto> deletarCompra(@PathVariable("codigo") Long codigo) {
		try {
			service.delete(codigo);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//alterar campo nome
	@PatchMapping(value = "/alterarProduto/{codigo}")
	public ResponseEntity<String> alterarNomeProduto(@RequestParam String produto, @PathVariable("codigo") long codigo) {
		try {
			service.alterarNomeProduto(produto, codigo);
		} catch (Exception e) {
			return new ResponseEntity<>("produto não alterado", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("produto alterado", HttpStatus.OK);
	}

	//alterar preço
	@PatchMapping(value = "/alterarPreco/{codigo}")
	public ResponseEntity<String> alterarPreco(@RequestParam Double preco, @PathVariable("codigo") Long codigo) {
		try {
			service.alterarPreco(preco, codigo);
		} catch (Exception e) {
			return new ResponseEntity<>("preço não alterado", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(" preço alterado", HttpStatus.OK);
	}

	//contar quantidade de Registros
	@GetMapping(value = "/contar")
	public Long contarQuantidadeRegistros(){
		return service.count();
	}

}
