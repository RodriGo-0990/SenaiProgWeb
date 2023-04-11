package com.algaworks.algalog.controller;

import com.algaworks.algalog.DTOs.CompraDTO;
import com.algaworks.algalog.Service.CompraService;
import com.algaworks.algalog.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/compra")
public class CompraControler {

    @Autowired
    CompraService service;
    @PostMapping( "/inserirCompra")
    public ResponseEntity<String> inserirCompra(@RequestBody CompraDTO dto) {
        try {
            service.inserirCompra(dto);

        } catch (Exception e) {
            return new ResponseEntity<>("compra NÃO inserida:"+e.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(("compra inserida!"), HttpStatus.OK);
    }

    @GetMapping("/consultarCompra/{id}")
    public ResponseEntity<Compra>consultarCompra(@PathVariable("id")Long id){
        try {
            return ResponseEntity.ok().body(service.findById(id).get().getCompra());
        } catch (Exception e) {
            return new ResponseEntity<>(new Compra(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/listar")
    public ResponseEntity<List<Compra>> ListarCliente() {
        try {
            List<Compra> lista = service.listAll();
            if (!lista.isEmpty())
                return ResponseEntity.ok().body(service.listAll());
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/contar")
    public Long contarQuantidadeRegistros(){
        return service.count();
    }

}
