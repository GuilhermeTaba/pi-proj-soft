package com.example.pi_proj_soft;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;
    private String instrutor;
    private Integer cargaHoraria;
    private Boolean deletado = false;
    private LocalDateTime dataCriacao;

    public static Curso fromDto(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        curso.setInstrutor(dto.getInstrutor());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setDeletado(false);
        curso.setDataCriacao(LocalDateTime.now());
        return curso;
    }
}