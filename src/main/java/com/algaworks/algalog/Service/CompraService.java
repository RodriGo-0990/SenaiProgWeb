package com.algaworks.algalog.Service;


import java.util.List;
import java.util.Optional;

import com.algaworks.algalog.entity.Compra;
import com.algaworks.algalog.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algalog.entity.Compra;
import com.algaworks.algalog.repository.CompraRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository repo ;

	public Optional<Compra> findById(Long id) {
		return repo.findById(id);
	}

	public Compra save(Compra compra, String produto, Double preco) {
		compra.setProduto(produto);
		compra.setPreco(preco);
		return repo.save(compra);
	}

	public void delete(Long id) {
		Compra compra = repo.findById(id).get();
		repo.delete(compra);
	}

	public List<Compra> listAll(){
		return repo.findAll();
	}
	public long count() {
		return repo.count();
	}

	public void alterarNomeProduto(String produto , Long id) {
		Optional<Compra> compra = repo.findById(id);
		if (Optional.ofNullable(compra).isPresent()) {
			compra.get().setProduto(produto);
			repo.save(compra.get());
		}
	}
	public void alterarPreco(Double preco , Long id) {
		Optional<Compra> compra = repo.findById(id);
		if (Optional.ofNullable(compra).isPresent()) {
			compra.get().setPreco(preco);
			repo.save(compra.get());
		}
	}

}