package com.algaworks.algalog.Service;

import com.algaworks.algalog.entity.Cliente;
import com.algaworks.algalog.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo ;

    public Optional<Cliente> findById(Long id) {
        return repo.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return repo.save(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = repo.findById(id).get();
        repo.delete(cliente);
    }

    public List<Cliente> listAll(){
        return repo.findAll();
    }
    public long count() {
        return repo.count();
    }

    public void alterarNome(String nome , Long id) {
        Optional<Cliente> cliente = repo.findById(id);
        if (Optional.ofNullable(cliente).isPresent()) {
            cliente.get().setNome(nome);
            repo.save(cliente.get());
        }
    }

}
