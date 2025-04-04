package br.com.unifor.service;

import br.com.unifor.dto.AlunoDTO;
import br.com.unifor.entity.Aluno;
import br.com.unifor.entity.Curso;
import br.com.unifor.repository.AlunoRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @InjectMocks
    AlunoService alunoService;

    @Mock
    AlunoRepository alunoRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");

        try (MockedStatic<Aluno> alunoMockedStatic = mockStatic(Aluno.class)) {
            alunoMockedStatic.when(Aluno::listAll).thenReturn(List.of(aluno));

            List<AlunoDTO> result = alunoService.listar();

            assertEquals(1, result.size());
            assertEquals("João", result.get(0).getNome());
        }
    }

    @Test
    void testCriar() {
        Curso cursoMock = mock(Curso.class);
        when(cursoMock.getId()).thenReturn(1L);
        when(Curso.findById(1L)).thenReturn(cursoMock);

        Aluno aluno = new Aluno();
        aluno.setNome("Maria");

        List<Curso> cursos = List.of(cursoMock);
        aluno.setCursos(cursos);

        try (MockedStatic<Curso> cursoMockedStatic = mockStatic(Curso.class)) {
            cursoMockedStatic.when(() -> Curso.findById(1L)).thenReturn(cursoMock);

            alunoService.criar(aluno);
            assertEquals(1, aluno.getCursos().size());
            assertEquals(cursoMock, aluno.getCursos().get(0));
        }
    }

    @Test
    void testDeletar_ComAlunoExistente() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);

        when(alunoRepository.findById(1L)).thenReturn(aluno);

        alunoService.deletar(1L);

        verify(alunoRepository).delete(aluno);
    }

    @Test
    void testDeletar_ComAlunoInexistente() {
        when(alunoRepository.findById(99L)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> alunoService.deletar(99L));

        assertEquals("Aluno não encontrado com ID: 99", exception.getMessage());
    }
}
