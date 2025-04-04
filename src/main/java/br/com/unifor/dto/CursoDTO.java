package br.com.unifor.dto;

import br.com.unifor.entity.Curso;

public class CursoDTO {

    public Long id;
    public String nome;

    public CursoDTO(Curso curso) {
        this.id = curso.id;
        this.nome = curso.nome;
    }
}
