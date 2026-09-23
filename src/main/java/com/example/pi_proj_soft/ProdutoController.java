package com.example.pi_proj_soft;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }
    @GetMapping("/{id}")
    public Produto get(@PathVariable Long id) {
        return produtoService.get(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody @Valid ProdutoDTO dto) {
        return produtoService.criar(dto);
    }
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deletar(@PathVariable Long id) {
         produtoService.deletar(id);
     }

}
