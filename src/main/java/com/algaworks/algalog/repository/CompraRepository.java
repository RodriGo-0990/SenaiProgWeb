package com.algaworks.algalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.entity.Compra;

@Repository
public interface CompraRepository  extends JpaRepository<Compra, Long>{

}
