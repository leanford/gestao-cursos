package br.com.unifor.controller;

import br.com.unifor.dto.AlunoDTO;
import br.com.unifor.entity.Aluno;
import br.com.unifor.service.AlunoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import org.jboss.logging.Logger;

@Path("/api/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoController {
    @Inject
    AlunoService alunoService;

    private static final Logger LOG = Logger.getLogger(AlunoController.class);

    @GET
    @Path("/")
    public List<AlunoDTO> listar() {
        return alunoService.listar();
    }

    @POST
    public Response criar(Aluno aluno) {
        alunoService.criar(aluno);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        alunoService.deletar(id);
        return Response.noContent().build();
    }
}