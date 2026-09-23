package com.example.pi_proj_soft;

public interface ProdutoObservable {
    void notificarObservadores(Produto produto, String status);
}
