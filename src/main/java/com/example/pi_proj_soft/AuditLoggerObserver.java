package com.example.pi_proj_soft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditLoggerObserver implements ProdutoObserver{
    private static final Logger logger = LoggerFactory.getLogger(AuditLoggerObserver.class);

    @Override
    public void atualizar(Produto produto, String status) {
        String mensagem = String.format(
                "AUDITORIA - Produto ID: %d | Status: %s | Preco: %s",
                produto.getId(),
                status,
                produto.getPreco()
        );
        logger.info(mensagem);
    }
}
