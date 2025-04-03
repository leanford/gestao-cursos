package br.com.unifor.service;


import br.com.unifor.entity.Curso;
import br.com.unifor.repository.CursoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CursoService {
    @Inject
    CursoRepository cursoRepository;

    public List<Curso> listar() { return cursoRepository.listAll(); }
    public Curso buscar(Long id) { return cursoRepository.findById(id); }


    public void criar(Curso curso) { cursoRepository.persist(curso); }

    @Transactional
    public void deletar(Long id) { cursoRepository.deleteById(id); }
}