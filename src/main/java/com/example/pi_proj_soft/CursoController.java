package com.example.pi_proj_soft;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }


    @GetMapping
    public List<Curso> listar(@RequestParam(required = false) String nome) {
        return cursoService.listar(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criar(@RequestBody @Valid Curso curso) {
        return cursoService.criar(curso);
    }
}
// CursoController.java

// @DeleteMapping("/{id}")
// @ResponseStatus(HttpStatus.NO_CONTENT)
// public void deletar(@PathVariable Long id) {
//     cursoService.deletar(id);
// }