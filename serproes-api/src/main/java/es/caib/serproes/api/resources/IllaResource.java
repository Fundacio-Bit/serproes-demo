package es.caib.serproes.api.resources;

import es.caib.serproes.entity.IllaEntity;
import es.caib.serproes.service.IllaServiceInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/illa")
@Api(value="/illa")
public class IllaResource {

    @EJB
    private IllaServiceInterface illaService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna llistat d'illes", notes="Retorna un JSON amb un llistat d'illes.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response list() {
        try {
            return Response.status(200).entity(illaService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @POST
    @Path("/add/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Afegeix una illa")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response addIllaEntity(@PathParam("value")
                                 @ApiParam(value="Nom de l'illa")
                                 String value) {
        try {
            IllaEntity illaEntity = new IllaEntity();
            illaEntity.setIlaNom(value);
            return Response.status(200).entity(illaService.addIllaEntity(illaEntity)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/findIllaById/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna les dades d'una illa", notes="Retorna un JSON amb les dades d'una illa donat l'identificador.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findIllaById(
            @PathParam("value")
            @ApiParam(value="Identificador de l'illa")
                    Long value
    ) {
        try {
            IllaEntity illa = illaService.findIllaById(value);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                    .header("Access-Control-Max-Age", "1209600")
                    .entity(illa)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
