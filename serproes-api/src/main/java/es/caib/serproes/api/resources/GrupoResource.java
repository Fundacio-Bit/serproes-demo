package es.caib.serproes.api.resources;

import es.caib.serproes.entity.GrupoEntity;
import es.caib.serproes.service.GrupoServiceInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/grupo")
@Api(value="/grupo")
public class GrupoResource {

    @EJB
    private GrupoServiceInterface grupoService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna llistat de grups d’espècies", notes="Retorna un JSON amb el llistat de grups d’espècies.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response list() {
        try {
            return Response.status(200).entity(grupoService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @GET
    @Path("/findGrupoById/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna dades d'un grup", notes="Retorna un JSON amb els dades d'un grup donat un identificador de grup.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findGrupoById(
        @PathParam("value")
        @ApiParam(value="Identificador de grup")
        Integer value
    ) {
        try {
            GrupoEntity Grupo = grupoService.findGrupoById(value);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                    .header("Access-Control-Max-Age", "1209600")
                    .entity(Grupo)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/listNoms")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna llistat amb els noms dels grups", notes="Retorna un JSON amb el llistat amb els noms dels grups.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response listNoms() {
        try {
            List my_result = new ArrayList();
            for (GrupoEntity grupo: grupoService.list() ) {
                String gruNombre = "";
                if (grupo.getGruNombre() != null){
                    gruNombre = grupo.getGruNombre();
                }
                List<String> my_row =  Arrays.asList(gruNombre);
                my_result.add(gruNombre);
            }
            return Response.status(200)
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                    .header("Access-Control-Max-Age", "1209600")
                    .entity(my_result)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

//    @POST
//    @Path("/add/{value}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value="Permite dar de alta un elemento")
//    @ApiResponses(value= {
//            @ApiResponse(code=200, message="OK"),
//            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
//    })
//    public Response addGrupoEntity(@PathParam("value")
//                                 @ApiParam(value="Valor de la entidad")
//                                 String value) {
//        try {
//            GrupoEntity GrupoEntity = new GrupoEntity();
//            GrupoEntity.setGruNombre(value);
//            return Response.status(200).entity(grupoService.addGrupoEntity(GrupoEntity)).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }

}
