package es.caib.serproes.api.resources;

import es.caib.serproes.entity.EspeciEntity;
import es.caib.serproes.entity.FamiliEntity;
import es.caib.serproes.service.FamiliServiceInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/famili")
@Api(value="/famili")
public class FamiliResource {

    @EJB
    private FamiliServiceInterface familiService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna un llistat de famílies",
                  notes="Retorna un JSON amb un llistat de famílies")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response list() {
        try {
            return Response.status(200).entity(familiService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @GET
    @Path("/findFamiliById/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna les dades d'una família",
                  notes="Retorna un JSON amb les dades d'una família")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findFamiliById(
        @PathParam("value")
        @ApiParam(value="Identificador de familia")
        Integer value
    ) {
        try {
            FamiliEntity Famili = familiService.findFamiliById(value);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                    .header("Access-Control-Max-Age", "1209600")
                    .entity(Famili)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/listNoms")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna un llistat de noms de famílies",
                  notes="Retorna un JSON amb el llistat de noms de famílies.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response listNoms() {
        try {
            List my_result = new ArrayList();
            for (FamiliEntity famili: familiService.list() ) {
                String famNombre = "";
                if (famili.getFamNombre() != null){
                    famNombre = famili.getFamNombre();
                }
                List<String> my_row =  Arrays.asList(famNombre);
                my_result.add(famNombre);
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

    @GET
    @Path("/findFamiliByGrupo/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna un llistat de famílies",
                  notes="Retorna un JSON amb el llistat de famílies d’espècies enviant un grup.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findFamiliByGrupo(
            @PathParam("value")
            @ApiParam(value="Identificador de grup")
                    Integer value)
    {
        try {

//            List my_result = new ArrayList();
//            for (FamiliEntity famili: familiService.findFamiliByGrupo(value) ) {
//                String famNombre = "-";
//                if(famili.getFamNombre() != null){
//                    famNombre = famili.getFamNombre();
//                }
//                List<String> my_row =  Arrays.asList(famNombre);
//                my_result.add(my_row);
//            }
            List<FamiliEntity> my_result = familiService.findFamiliByGrupo(value);
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(my_result).build();
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
//    public Response addFamiliEntity(@PathParam("value")
//                                 @ApiParam(value="Valor de la entidad")
//                                 String value) {
//        try {
//            FamiliEntity FamiliEntity = new FamiliEntity();
//            FamiliEntity.setFamNombre(value);
//            return Response.status(200).entity(familiService.addFamiliEntity(FamiliEntity)).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }

}
