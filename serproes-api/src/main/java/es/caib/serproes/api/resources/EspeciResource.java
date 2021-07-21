package es.caib.serproes.api.resources;

import es.caib.serproes.entity.EspeciEntity;
import es.caib.serproes.service.EspeciServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/especi")
@Api(value="/especi")
public class EspeciResource {

    @EJB
    private EspeciServiceInterface especiService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna el llistat complet d'espècies", notes="Retorna un JSON amb el llistat complet d'espècies.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response list() {
        try {
            return Response.status(200).entity(especiService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @GET
    @Path("/listNoms")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna llistat amb els noms de les especies", notes="Retorna un JSON amb el llistat amb els noms de les especies.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response listNoms() {
        try {
            List my_result = new ArrayList();
            for (EspeciEntity especi: especiService.list() ) {
                String espTaxon = "";
                String espNomcom = "";
                String espNomcomes = "";
                if (especi.getEspTaxon() != null){
                    espTaxon = especi.getEspTaxon();
                }
                if (especi.getEspNomcom() != null){
                    espNomcom = especi.getEspNomcom();
                }
                if (especi.getEspNomcomes() != null){
                    espNomcomes = especi.getEspNomcomes();
                }
                List<String> my_row =  Arrays.asList(espTaxon, espNomcom, espNomcomes, "354");
                my_result.add(my_row);

//                EspeciEntity especiEntity = new EspeciEntity();
//                especiEntity.setEspTaxon(espTaxon);
//                especiEntity.setEspNomcom(espNomcom);
//                especiEntity.setEspNomcomes(espNomcomes);
//                my_result.add(especiEntity);
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
//    public Response addEspeciEntity(@PathParam("value")
//                                 @ApiParam(value="Valor de la entidad")
//                                 String value) {
//        try {
//            EspeciEntity especiEntity = new EspeciEntity();
//            especiEntity.setEspNomcom(value);
//            return Response.status(200).entity(especiService.addEspeciEntity(especiEntity)).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }

    @GET
    @Path("/findEspeciById/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna un llistat de d’espècies", notes="Retorna un JSON amb l'espècie (i tots els seus atributs) enviant un id.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findEspeciById(
            @PathParam("value")
            @ApiParam(value="Identificador de especie")
                    Double value)
    {
        try {

//            List my_result = new ArrayList();
//            for (EspeciEntity especi: especiService.findEspeciByFamili(value) ) {
//                String taxon = "";
//                String nomcom = "";
//                String nomcomes = "";
//                String catalo = "-";
//                String amenaz = "-";
//                String endemi = "-";
//                String vis1x1 = "-";
//                if (especi.getEspTaxon() != null){
//                    taxon = especi.getEspTaxon();
//                }
//                if (especi.getEspNomcom() != null){
//                    nomcom = especi.getEspNomcom();
//                }
//                if (especi.getEspNomcomes() != null){
//                    nomcomes = especi.getEspNomcomes();
//                }
//                if (especi.getEspCatalo() != null){
//                    catalo = especi.getEspCatalo();
//                }
//                if (especi.getEspAmenaz() != null){
//                    amenaz = especi.getEspAmenaz();
//                }
//                if (especi.getEspEndemi() != null){
//                    endemi = String.valueOf(especi.getEspEndemi());
//                }
//                if (especi.getEspVis1X1() != null){
//                    vis1x1 = especi.getEspVis1X1();
//                }
//
//                List<String> my_row =  Arrays.asList(taxon, nomcom, nomcomes, catalo, amenaz, endemi, vis1x1);
//                my_result.add(my_row);
//            }
            List<EspeciEntity> my_result = especiService.findEspeciById(value);
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(my_result).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/findEspeciByFamili/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna un llistat de d’espècies", notes="Retorna un JSON amb el llistat d'espècies (i tots els seus atributs) enviant una família.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findEspeciByFamili(
            @PathParam("value")
            @ApiParam(value="Identificador de familia")
                    Integer value)
    {
        try {

//            List my_result = new ArrayList();
//            for (EspeciEntity especi: especiService.findEspeciByFamili(value) ) {
//                String taxon = "";
//                String nomcom = "";
//                String nomcomes = "";
//                String catalo = "-";
//                String amenaz = "-";
//                String endemi = "-";
//                String vis1x1 = "-";
//                if (especi.getEspTaxon() != null){
//                    taxon = especi.getEspTaxon();
//                }
//                if (especi.getEspNomcom() != null){
//                    nomcom = especi.getEspNomcom();
//                }
//                if (especi.getEspNomcomes() != null){
//                    nomcomes = especi.getEspNomcomes();
//                }
//                if (especi.getEspCatalo() != null){
//                    catalo = especi.getEspCatalo();
//                }
//                if (especi.getEspAmenaz() != null){
//                    amenaz = especi.getEspAmenaz();
//                }
//                if (especi.getEspEndemi() != null){
//                    endemi = String.valueOf(especi.getEspEndemi());
//                }
//                if (especi.getEspVis1X1() != null){
//                    vis1x1 = especi.getEspVis1X1();
//                }
//
//                List<String> my_row =  Arrays.asList(taxon, nomcom, nomcomes, catalo, amenaz, endemi, vis1x1);
//                my_result.add(my_row);
//            }
            List<EspeciEntity> my_result = especiService.findEspeciByFamili(value);
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(my_result).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
