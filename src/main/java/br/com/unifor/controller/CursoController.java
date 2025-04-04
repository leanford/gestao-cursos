package br.com.unifor.controller;

import br.com.unifor.dto.CursoDTO;
import br.com.unifor.entity.Curso;
import br.com.unifor.service.CursoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CursoController {
    @Inject
    CursoService cursoService;

    @GET
    public List<CursoDTO> listarCursosSemAlunos() {
        return cursoService.listarCursosSemAlunos();
    }

    @GET
    @Path("/{id}/alunos")
    public Response buscarAlunosPorCurso(@PathParam("id") Long id) {
        return cursoService.buscarAlunosPorCurso(id);
    }

    @POST
    public Response criar(Curso curso) {
        cursoService.criar(curso);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        return cursoService.deletar(id);
    }

}
