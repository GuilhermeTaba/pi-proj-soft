package com.example.pi_proj_soft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private String nome;
    private String descricao;
    private String instrutor;
    private Integer cargaHoraria;
}