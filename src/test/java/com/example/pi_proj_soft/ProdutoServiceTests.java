package com.example.pi_proj_soft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTests {
    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;
    @Test
    public void test_shouldReturnListaWhenCallListar(){
        Produto produto = new Produto();
        produto.setDescricao("Descricao");
        produto.setNome("Produto teste");
        produto.setPreco(10);
        produto.setQuantidade(100);
        produto.setId(0l);

        List<Produto> lista= List.of(produto);

        Mockito.when(produtoRepository.findAll()).thenReturn(lista);

        List<Produto> resultado = produtoService.listar();
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Produto teste", resultado.get(0).getNome());
    }
    @Test
    public void test_shouldReturnProduto() {
        Produto produto = new Produto();
        produto.setDescricao("Descricao");
        produto.setNome("Produto teste");
        produto.setPreco(10);
        produto.setQuantidade(100);
        produto.setId(0l);

        Mockito.when(produtoRepository.findById(0l)).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.get(0l);
        Assertions.assertEquals("Produto teste", resultado.getNome());
        Assertions.assertEquals("Descricao", resultado.getDescricao());
    }

    @Test
    public void test_shouldReturnProdutoSalvoWhenCallCriar() {
        ProdutoDTO dto = new ProdutoDTO();

        dto.setDescricao("Descricao");
        dto.setNome("Produto teste");
        dto.setPreco(10);
        dto.setQuantidade(100);


        Produto salvo = new Produto();

        salvo.setDescricao("Descricao");
        salvo.setNome("Produto teste");
        salvo.setPreco(10);
        salvo.setQuantidade(100);
        salvo.setId(0l);


        Mockito.when(produtoRepository.save(Mockito.any(Produto.class))).thenReturn(salvo);

        Produto resultado = produtoService.criar(dto);
        Assertions.assertEquals(0L, resultado.getId());
        Mockito.verify(produtoRepository).save(Mockito.any(Produto.class));
    }
     @Test
 public void test_shouldDelete() {
     Produto produto = new Produto();

     produto.setDescricao("Descricao");
     produto.setNome("Produto teste");
     produto.setPreco(10);
     produto.setQuantidade(100);
     produto.setId(0l);

     Mockito.when(produtoRepository.findById(0L)).thenReturn(Optional.of(produto));

     produtoService.deletar(0L);

     Mockito.verify(produtoRepository).deleteById(0l);
}
}
