package br.com.unifor.service;

import br.com.unifor.dto.AlunoSimplesDTO;
import br.com.unifor.dto.CursoDTO;
import br.com.unifor.entity.Curso;
import br.com.unifor.repository.CursoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class CursoService {

    @Inject
    CursoRepository cursoRepository;

    public List<CursoDTO> listarCursosSemAlunos() {
        List<Curso> cursos = Curso.findAll().list();
        return cursos.stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
    }

    public Response buscarAlunosPorCurso(Long id) {
        Curso curso = Curso.findById(id);
        if (curso == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        curso.getAlunos().size();

        List<AlunoSimplesDTO> alunosDTO = curso.getAlunos().stream()
                .map(AlunoSimplesDTO::new)
                .collect(Collectors.toList());

        return Response.ok(alunosDTO).build();
    }

    public void criar(Curso curso) {
        cursoRepository.persist(curso);
    }

    public Response deletar(Long id) {
        Curso curso = Curso.findById(id);

        if (curso == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (curso.alunos != null && !curso.alunos.isEmpty()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Não é possível remover o curso porque ele possui alunos vinculados.").build();
        }

        curso.delete();
        return Response.noContent().build();
    }
}