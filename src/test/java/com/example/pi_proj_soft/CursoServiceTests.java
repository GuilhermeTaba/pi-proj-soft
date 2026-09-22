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

@ExtendWith(MockitoExtension.class)
public class CursoServiceTests {
    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    public void test_shouldReturnListaWhenCallListar(){
        Curso curso = new Curso();
        curso.setDataCriacao(LocalDateTime.of( 2000,1, 2,0,0));
        curso.setId((long) 1) ;
        curso.setDeletado(Boolean.FALSE);
        curso.setDescricao("teste");
        curso.setInstrutor("Osvaldo");
        curso.setCargaHoraria(10);
        curso.setNome("Curso");
        List<Curso> lista= List.of(curso);

        Mockito.when(cursoRepository.findByNomeStartingWithIgnoreCaseAndDeletadoFalse("Cur")).thenReturn(lista);

        List<Curso> resultado = cursoService.listar("Cur");
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Curso", resultado.get(0).getNome());
    }
    @Test
    public void test_shouldReturnTodosWhenCallListarSemFiltro() {
        Curso curso = new Curso();
        curso.setNome("Curso");

        Mockito.when(cursoRepository.findByDeletadoFalse()).thenReturn(List.of(curso));

        List<Curso> resultado = cursoService.listar(null);
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    public void test_shouldSetDeletadoFalseAndDataCriacaoWhenCallCriar() {
        Curso curso = new Curso();
        curso.setNome("Curso");
        curso.setDeletado(true);

        Mockito.when(cursoRepository.save(curso)).thenReturn(curso);

        Curso resultado = cursoService.criar(curso);
        Assertions.assertFalse(resultado.getDeletado());
        Assertions.assertNotNull(resultado.getDataCriacao());
    }

    @Test
    public void test_shouldReturnCursoSalvoWhenCallCriar() {
        Curso curso = new Curso();
        curso.setNome("Curso");

        Curso salvo = new Curso();
        salvo.setId(1L);
        salvo.setNome("Curso");

        Mockito.when(cursoRepository.save(curso)).thenReturn(salvo);

        Curso resultado = cursoService.criar(curso);
        Assertions.assertEquals(1L, resultado.getId());
        Mockito.verify(cursoRepository).save(curso);
    }
}

// CursoServiceTests.java

// import java.util.Optional;
// import org.springframework.web.server.ResponseStatusException;

// @Test
// public void test_shouldMarcarDeletadoWhenCallDeletar() {
//     Curso curso = new Curso();
//     curso.setId(1L);
//     curso.setNome("Curso");
//     curso.setDeletado(false);
//
//     Mockito.when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
//
//     cursoService.deletar(1L);
//
//     Assertions.assertTrue(curso.getDeletado());
//     Mockito.verify(cursoRepository).save(curso);
// }

// @Test
// public void test_shouldThrowNotFoundWhenCallDeletarCursoInexistente() {
//     Mockito.when(cursoRepository.findById(1L)).thenReturn(Optional.empty());
//
//     Assertions.assertThrows(ResponseStatusException.class, () -> cursoService.deletar(1L));
//     Mockito.verify(cursoRepository, Mockito.never()).save(Mockito.any());
// }