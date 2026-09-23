package com.example.pi_proj_soft;

import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService implements ProdutoObservable {
    private final ProdutoRepository produtoRepository;
    @Autowired(required = false)
    private List<ProdutoObserver> observers;
    @Override
    public void notificarObservadores(Produto produto, String status) {
        if (observers != null) {
            for (ProdutoObserver observer : observers) {
                observer.atualizar(produto, status);
            }
        }
    }


    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto get(long id){

        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuário não encontrado"
        ));
    }
    public Produto criar(ProdutoDTO dto) {
        Produto produto = Produto.fromDto(dto);
        notificarObservadores(produto, "Criacao de produto");
        if(produto.getQuantidade() < 10){
            notificarObservadores(produto, "Baixa quantidade");
        }
        return produtoRepository.save(produto);
    }

    public void deletar(long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));;
        notificarObservadores(produto, "Delecao");
        produtoRepository.deleteById(id);
    }

}
