package br.com.unifor.service;

import br.com.unifor.entity.Aluno;
import br.com.unifor.repository.AlunoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
@Transactional
public class AlunoService {
    @Inject
    AlunoRepository alunoRepository;

    public List<Aluno> listar() { return alunoRepository.listAll(); }
    public Aluno buscar(Long id) { return alunoRepository.findById(id); }
    public void criar(Aluno aluno) { alunoRepository.persist(aluno); }

    @Transactional
    public void deletar(Long id) {
        System.out.println("Aluno ID: " + id);

        Aluno aluno = alunoRepository.findById(id);
        if (aluno == null) {
            throw new NotFoundException("Aluno não encontrado com ID: " + id);
        }
        System.out.println("Aluno ID: " + id);

        alunoRepository.delete(aluno);
    }


}