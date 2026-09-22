package com.example.pi_proj_soft;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class CursoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_shouldCreateCurso() throws Exception {
        Curso curso = new Curso();
        curso.setDescricao("teste");
        curso.setInstrutor("Osvaldo");
        curso.setCargaHoraria(10);
        curso.setNome("Curso");

        MvcResult result = mockMvc.perform(
                        post("/cursos")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isCreated())
                .andReturn();

        Curso cursoCriado = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                Curso.class);

        Assertions.assertNotNull(cursoCriado.getId());
        Assertions.assertEquals("Curso", cursoCriado.getNome());
        Assertions.assertFalse(cursoCriado.getDeletado());
        Assertions.assertNotNull(cursoCriado.getDataCriacao());
    }
    @Test
    void test_shouldListarCursos() throws Exception {
        Curso curso = new Curso();
        curso.setDescricao("teste");
        curso.setInstrutor("Osvaldo");
        curso.setCargaHoraria(10);
        curso.setNome("Curso");

        mockMvc.perform(
                        post("/cursos")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isCreated());

        Curso outro = new Curso();
        outro.setDescricao("teste");
        outro.setInstrutor("Osvaldo");
        outro.setCargaHoraria(10);
        outro.setNome("Python");

        mockMvc.perform(
                        post("/cursos")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(outro)))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(
                        get("/cursos")
                                .param("nome", "Cur"))
                .andExpect(status().isOk())
                .andReturn();

        Curso[] cursos = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                Curso[].class);

        Assertions.assertEquals(1, cursos.length);
        Assertions.assertEquals("Curso", cursos[0].getNome());
    }
}
// CursoControllerTests.java

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

// @Test
// void test_shouldDeletarCurso() throws Exception {
//     Curso curso = new Curso();
//     curso.setDescricao("teste");
//     curso.setInstrutor("Osvaldo");
//     curso.setCargaHoraria(10);
//     curso.setNome("Curso");
//
//     MvcResult result = mockMvc.perform(
//                     post("/cursos")
//                             .contentType("application/json")
//                             .content(objectMapper.writeValueAsString(curso)))
//             .andExpect(status().isCreated())
//             .andReturn();
//
//     Curso cursoCriado = objectMapper.readValue(
//             result.getResponse().getContentAsString(),
//             Curso.class);
//
//     mockMvc.perform(delete("/cursos/" + cursoCriado.getId()))
//             .andExpect(status().isNoContent());
//
//     MvcResult resultLista = mockMvc.perform(get("/cursos"))
//             .andExpect(status().isOk())
//             .andReturn();
//
//     Curso[] cursos = objectMapper.readValue(
//             resultLista.getResponse().getContentAsString(),
//             Curso[].class);
//
//     Assertions.assertEquals(0, cursos.length);
//     Assertions.assertTrue(cursoRepository.findById(cursoCriado.getId()).get().getDeletado());
// }