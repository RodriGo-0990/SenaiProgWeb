package com.algaworks.algalog.controller;

import com.algaworks.algalog.Service.ClienteService;
import com.algaworks.algalog.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/Cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    //cria registro
    @PostMapping(value = "/save")
    public ResponseEntity<Cliente> SalvarCliente(@RequestBody Cliente cliente) {
       try {
           return ResponseEntity.ok().body(service.save(cliente));
       }catch (Exception e){
           return  ResponseEntity.badRequest().body(null);
       }
    }

    //consulta todos
    @GetMapping(value = "/listar")
    public ResponseEntity<List<Cliente>> ListarCliente() {
        try {
            List<Cliente> lista = service.listAll();
            if (!lista.isEmpty())
                return ResponseEntity.ok().body(service.listAll());
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    //consulta por id
    @GetMapping(value = "/consulta/{id}")
    public ResponseEntity<Cliente> consultaCliente(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok().body(service.findById(id).get());
        } catch (Exception e) {
            return new ResponseEntity<>(new Cliente(), HttpStatus.BAD_REQUEST);
        }
    }

    //exclusão
    @DeleteMapping(value = "/deletar/{codigo}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable("codigo") Long codigo) {
        try {
            service.delete(codigo);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //alterar campo nome
    @PatchMapping(value = "/alterar/{codigo}")
    public ResponseEntity<String> alterarNomeCliente(@RequestParam String nome, @PathVariable("codigo") long codigo) {
        try {
            service.alterarNome(nome, codigo);
        } catch (Exception e) {
            return new ResponseEntity<>("cliente não encontrado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("nome alterado", HttpStatus.OK);
    }

    //contar quantidade de Registros
    @GetMapping(value = "contar")
    public Long contarQuantidadeRegistros(){
        return service.count();
    }



}
