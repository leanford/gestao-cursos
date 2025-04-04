package br.com.unifor.dto;

import br.com.unifor.entity.Curso;

public class CursoDTO {

    private Long id;
    private String nome;

    // Construtor padrão
    public CursoDTO() {}

    // Construtor baseado na entidade
    public CursoDTO(Curso curso) {
        this.id = curso.id;
        this.nome = curso.nome;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
