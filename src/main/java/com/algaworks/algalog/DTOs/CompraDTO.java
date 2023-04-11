package com.algaworks.algalog.DTOs;

import lombok.Data;

@Data
public class CompraDTO {

    private Double valor;

    private Long cliente; //codigo da Entity Cliente

    private Long produto;//codigo da Entity Produto
}
