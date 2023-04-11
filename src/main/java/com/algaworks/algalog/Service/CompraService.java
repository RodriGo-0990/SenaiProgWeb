package com.algaworks.algalog.Service;

import com.algaworks.algalog.DTOs.CompraDTO;
import com.algaworks.algalog.entity.Cliente;
import com.algaworks.algalog.entity.Compra;
import com.algaworks.algalog.entity.Produto;
import com.algaworks.algalog.repository.CompraRepository;
import com.algaworks.algalog.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

   private final ProdutoService produtoService;
   private final ClienteService clienteService;
   private final CompraRepository compraRepository;

   public CompraService(ProdutoService produtoRepo,ClienteService clienteRepo, CompraRepository compraRepo ){
        this.produtoService = produtoRepo;
        this.clienteService = clienteRepo;
        this.compraRepository = compraRepo;
   }

    public void inserirCompra(CompraDTO compraDTO) throws Exception {
       Compra compra = new Compra();
       Optional<Produto> produto = produtoService.findById(compraDTO.getProduto());
       Optional<Cliente> cliente = clienteService.findById(compraDTO.getCliente());;
        if(produto.isPresent() && cliente.isPresent()){
            compra.setProduto(produto.get());
            compra.setCliente(cliente.get());
            compra.setValor(produto.get().getPreco());
            compraRepository.save(compra);
        }else {
            throw new Exception("dados inválidos");
        }
    }

    public Optional<Cliente> findById(Long id) {
        return clienteService.findById(id);
    }
    public void delete(Long id) {
        Compra compra = compraRepository.findById(id).get();
        compraRepository.delete(compra);
    }

    public List<Compra> listAll(){
        return compraRepository.findAll();
    }
    public long count() {
        return compraRepository.count();
    }

}
