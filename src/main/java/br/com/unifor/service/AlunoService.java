package br.com.unifor.service;

import br.com.unifor.dto.AlunoDTO;
import br.com.unifor.entity.Aluno;
import br.com.unifor.entity.Curso;
import br.com.unifor.repository.AlunoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class AlunoService {
    @Inject
    AlunoRepository alunoRepository;

    public List<AlunoDTO> listar() { return Aluno.listAll().stream()
            .map(aluno -> new AlunoDTO((Aluno) aluno))
            .collect(Collectors.toList()); }

    public void criar(Aluno aluno) {
        List<Curso> cursosReais = aluno.getCursos().stream()
                .map(c -> Curso.findById(c.getId()))
                .filter(c -> c != null)
                .map(c -> (Curso) c)
                .toList();

        aluno.setCursos(cursosReais);
        aluno.persist();
    }

    public void deletar(Long id) {

        Aluno aluno = alunoRepository.findById(id);
        if (aluno == null) {
            throw new NotFoundException("Aluno não encontrado com ID: " + id);
        }

        alunoRepository.delete(aluno);
    }

}