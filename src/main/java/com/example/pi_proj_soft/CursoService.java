package com.example.pi_proj_soft;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;


    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listar(String nome) {
        if (nome == null || nome.isBlank()) {
            return cursoRepository.findByDeletadoFalse();
        }
        return cursoRepository.findByNomeStartingWithIgnoreCaseAndDeletadoFalse(nome);
    }


    public Curso criar(CursoDTO dto) {
        Curso curso = Curso.fromDto(dto);
        return cursoRepository.save(curso);
    }
}
// CursoService.java

// import org.springframework.http.HttpStatus;
// import org.springframework.web.server.ResponseStatusException;

// public void deletar(Long id) {
//     Curso curso = cursoRepository.findById(id)
//             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
//     curso.setDeletado(true);
//     cursoRepository.save(curso);
// }