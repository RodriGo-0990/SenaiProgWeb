package com.algaworks.algalog.Service;


import java.util.List;
import java.util.Optional;

import com.algaworks.algalog.entity.Produto;
import com.algaworks.algalog.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo ;

	public Optional<Produto> findById(Long id) {
		return repo.findById(id);
	}

	public Produto save(Produto produto, String nomeproduto, Double preco) {
		produto.setNome(nomeproduto);
		produto.setPreco(preco);
		return repo.save(produto);
	}

	public void delete(Long id) {
		Produto produto = repo.findById(id).get();
		repo.delete(produto);
	}

	public List<Produto> listAll(){
		return repo.findAll();
	}
	public long count() {
		return repo.count();
	}

	public void alterarNomeProduto(String produto , Long id) {
		Optional<Produto> compra = repo.findById(id);
		if (Optional.ofNullable(compra).isPresent()) {
			compra.get().setNome(produto);
			repo.save(compra.get());
		}
	}
	public void alterarPreco(Double preco , Long id) {
		Optional<Produto> compra = repo.findById(id);
		if (Optional.ofNullable(compra).isPresent()) {
			compra.get().setPreco(preco);
			repo.save(compra.get());
		}
	}

}