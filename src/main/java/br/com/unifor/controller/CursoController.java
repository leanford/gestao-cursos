package br.com.unifor.controller;

import br.com.unifor.entity.Curso;
import br.com.unifor.service.CursoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CursoController {
    @Inject
    CursoService cursoService;

    @GET
    public List<Curso> listar() { return cursoService.listar(); }

    @GET
    @Path("/{id}")
    public Curso buscar(@PathParam("id") Long id) { return cursoService.buscar(id); }

    @POST
    public Response criar(Curso curso) {
        cursoService.criar(curso);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        cursoService.deletar(id);
        return Response.noContent().build();
    }
}
