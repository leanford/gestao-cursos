package br.com.unifor.dto;
import br.com.unifor.entity.Aluno;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoDTO {
    public Long id;
    public String nome;
    public List<CursoDTO> cursos;

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.id;
        this.nome = aluno.nome;
        this.cursos = aluno.getCursos().stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
    }

}
