package br.com.unifor.dto;

import br.com.unifor.entity.Aluno;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoDTO {
    private Long id;
    private String nome;
    private List<CursoDTO> cursos;

    // Construtor padrão (necessário para serialização)
    public AlunoDTO() {}

    // Construtor com entidade
    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.cursos = aluno.getCursos().stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<CursoDTO> getCursos() {
        return cursos;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCursos(List<CursoDTO> cursos) {
        this.cursos = cursos;
    }
}
