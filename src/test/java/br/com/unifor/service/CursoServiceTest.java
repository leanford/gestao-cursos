package br.com.unifor.service;

import br.com.unifor.dto.CursoDTO;
import br.com.unifor.entity.Aluno;
import br.com.unifor.entity.Curso;
import br.com.unifor.repository.CursoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.LockModeType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    @InjectMocks
    CursoService cursoService;

    @Mock
    CursoRepository cursoRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarCursosSemAlunos() {
        Curso curso1 = new Curso();
        curso1.setNome("Engenharia");

        Curso curso2 = new Curso();
        curso2.setNome("Direito");

        List<Curso> cursos = List.of(curso1, curso2);

        // mock Curso.findAll().list()
        try (MockedStatic<Curso> cursoStaticMock = mockStatic(Curso.class)) {
            PanacheQueryMock queryMock = new PanacheQueryMock(cursos);
            cursoStaticMock.when(Curso::findAll).thenReturn(queryMock);

            List<CursoDTO> result = cursoService.listarCursosSemAlunos();

            assertEquals(2, result.size());
            assertEquals("Engenharia", result.get(0).getNome());
        }
    }

    @Test
    void testBuscarAlunosPorCurso_ComCursoExistente() {
        Curso curso = new Curso();
        Aluno aluno = new Aluno();
        aluno.setNome("Carlos");
        curso.setAlunos((List<Aluno>) Set.of(aluno));

        try (MockedStatic<Curso> cursoStaticMock = mockStatic(Curso.class)) {
            cursoStaticMock.when(() -> Curso.findById(1L)).thenReturn(curso);

            Response response = cursoService.buscarAlunosPorCurso(1L);

            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            List<?> body = (List<?>) response.getEntity();
            assertEquals(1, body.size());
        }
    }

    @Test
    void testBuscarAlunosPorCurso_CursoNaoEncontrado() {
        try (MockedStatic<Curso> cursoStaticMock = mockStatic(Curso.class)) {
            cursoStaticMock.when(() -> Curso.findById(999L)).thenReturn(null);

            Response response = cursoService.buscarAlunosPorCurso(999L);

            assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        }
    }

    @Test
    void testCriar() {
        Curso curso = new Curso();
        curso.setNome("Medicina");

        cursoService.criar(curso);

        verify(cursoRepository).persist(curso);
    }

    @Test
    void testDeletar_Sucesso() {
        Curso curso = new Curso();
        curso.setAlunos(List.of());

        try (MockedStatic<Curso> cursoStaticMock = mockStatic(Curso.class)) {
            cursoStaticMock.when(() -> Curso.findById(10L)).thenReturn(curso);

            Response response = cursoService.deletar(10L);

            assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        }
    }

    @Test
    void testDeletar_CursoNaoEncontrado() {
        try (MockedStatic<Curso> cursoStaticMock = mockStatic(Curso.class)) {
            cursoStaticMock.when(() -> Curso.findById(999L)).thenReturn(null);

            Response response = cursoService.deletar(999L);

            assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        }
    }

    @Test
    void testDeletar_CursoComAlunos() {
        Curso curso = new Curso();
        Aluno aluno = new Aluno();
        curso.setAlunos(List.of(aluno));

        try (MockedStatic<Curso> cursoStaticMock = mockStatic(Curso.class)) {
            cursoStaticMock.when(() -> Curso.findById(5L)).thenReturn(curso);

            Response response = cursoService.deletar(5L);

            assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
            assertEquals("Não é possível remover o curso porque ele possui alunos vinculados.",
                    response.getEntity());
        }
    }

    static class PanacheQueryMock implements PanacheQuery<Curso> {
        private final List<Curso> cursos;

        PanacheQueryMock(List<Curso> cursos) {
            this.cursos = cursos;
        }

        @Override
        public <T> PanacheQuery<T> project(Class<T> type) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> page(Page page) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> page(int pageIndex, int pageSize) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> nextPage() {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> previousPage() {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> firstPage() {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> lastPage() {
            return null;
        }

        @Override
        public boolean hasNextPage() {
            return false;
        }

        @Override
        public boolean hasPreviousPage() {
            return false;
        }

        @Override
        public int pageCount() {
            return 0;
        }

        @Override
        public Page page() {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> range(int startIndex, int lastIndex) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> withLock(LockModeType lockModeType) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> withHint(String hintName, Object value) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> filter(String filterName, Parameters parameters) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> filter(String filterName, Map<String, Object> parameters) {
            return null;
        }

        @Override
        public <T extends Curso> PanacheQuery<T> filter(String filterName) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public <T extends Curso> List<T> list() {
            return List.of();
        }

        @Override
        public <T extends Curso> Stream<T> stream() {
            return Stream.empty();
        }

        @Override
        public <T extends Curso> T firstResult() {
            return null;
        }

        @Override
        public <T extends Curso> Optional<T> firstResultOptional() {
            return Optional.empty();
        }

        @Override
        public <T extends Curso> T singleResult() {
            return null;
        }

        @Override
        public <T extends Curso> Optional<T> singleResultOptional() {
            return Optional.empty();
        }
    }
}