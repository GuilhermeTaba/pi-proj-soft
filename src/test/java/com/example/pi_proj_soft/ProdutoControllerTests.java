package com.example.pi_proj_soft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
public class ProdutoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void test_shouldCreateCurso() throws Exception {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setDescricao("Descricao");
        produto.setNome("Produto teste");
        produto.setPreco(10);
        produto.setQuantidade(100);


        MvcResult result = mockMvc.perform(
                        post("/cursos")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isCreated())
                .andReturn();

        Produto produtoCriado = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                Produto.class);

        Assertions.assertNotNull(produtoCriado.getId());
        Assertions.assertEquals("Produto teste", produtoCriado.getNome());

    }
}
