package br.com.unifor.dto;

import br.com.unifor.entity.Aluno;

public class AlunoSimplesDTO {
    public Long id;
    public String nome;

    public AlunoSimplesDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
    }
}
