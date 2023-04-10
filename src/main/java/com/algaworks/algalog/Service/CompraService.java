package com.algaworks.algalog.Service;


import java.util.List;
import java.util.Optional;

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
		
		public Compra save(Compra compra) {
			return repo.save(compra);
		}
		
		public void delete(Compra compra) {
			repo.delete(compra);
		}
		
		public List<Compra> listAll(){
			return repo.findAll();
		}
		
		public long count() {
			return repo.count();
		}
}


