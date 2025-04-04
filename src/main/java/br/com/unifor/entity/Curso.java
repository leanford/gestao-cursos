package br.com.unifor.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Curso extends PanacheEntity {

    public String nome;

    @ManyToMany(mappedBy = "cursos")
    public List<Aluno> alunos;

    public Long getId() {
        return this.id;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
