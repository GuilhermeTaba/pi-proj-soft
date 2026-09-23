package com.example.pi_proj_soft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Integer preco;
}
