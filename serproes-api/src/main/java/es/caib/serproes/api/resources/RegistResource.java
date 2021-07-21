package es.caib.serproes.api.resources;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.caib.serproes.entity.RegistEntity;
import es.caib.serproes.service.RegistServiceInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.*;

@Path("/regist")
@Api(value="/regist")
public class RegistResource {

    @EJB
    private RegistServiceInterface registService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna tots els albiraments", notes="Retorna un JSON amb tots els albiraments.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response list(@HeaderParam("Authorization") String token) {
        System.out.println("LISTING SOURCES ..................... ");
        System.out.println("token:  " + token);

        try {
            // Decode the token to retrieve the kid (key ID) from the header
            DecodedJWT jwt = JWT.decode(token.replace("Bearer", "").trim());
            System.out.println("jwt: " + jwt);
            // Construct a JwkProvider using the jwks-rsa-java library
            JwkProvider provider =
                    new UrlJwkProvider(new URL("http://localhost:8180/auth/realms/GOIB/protocol/openid-connect/certs"));
            System.out.println("provider: " + provider);
            Jwk jwk = provider.get(jwt.getKeyId());
            System.out.println("jwk: " + jwk);

            // Use the public key retrieved from the JWKS and use it to verify the token
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            algorithm.verify(jwt);

            System.out.println("*********** JWT token verified properly *************");
            System.out.println(jwk.getPublicKey());
            return Response.status(200).entity(registService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @GET
    @Path("/findRegistByEspeci/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna tots els albiraments d'una espècie", notes="Retorna un JSON amb tots els albiraments geoposicionats i els seus atributs enviant una espècie.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findRegistByEspeci(
            @HeaderParam("Authorization") String token,
            @PathParam("value") @ApiParam(value="Identificador de la especie") Double value
    )
    {
        System.out.println("LISTING SOURCES ..................... ");
        System.out.println("token:  " + token);
        System.out.println("id:  " + value.intValue());

        try {

//            List my_result = new ArrayList();
//            for (RegistEntity regist: registService.findRegistByEspeci(value) ) {
//                String posX = "";
//                String posY = "";
//                String illa = "-";
//                String illot = "-";
//                String d_obsevacio = "-";
//                String d_registre = "-";
//                String especi = "-";
//                String catalogat = "-";
//                String amenazado = "-";
//                String endemic = "-";
//                String tipus = "-";
//                String font = "-";
//                if (regist.getRegCodcua().getCuaX() != null){
//                    posX = String.valueOf(regist.getRegCodcua().getCuaX());
//                }
//                if (regist.getRegCodcua().getCuaY() != null){
//                    posY = String.valueOf(regist.getRegCodcua().getCuaY());
//                }
//                if (regist.getRegCodcua().getCuaCodisl() != null){
//                    illa = String.valueOf(regist.getRegCodcua().getCuaCodisl());
//                }
//                if(regist.getRegCodill() != null){
//                    illot = String.valueOf(regist.getRegCodill().getIllNom());
//                }
//                if(regist.getRegFecha() != null){
//                    d_obsevacio = String.valueOf(regist.getRegFecha());
//                }
//                if(regist.getRegFecbd() != null){
//                    d_registre = String.valueOf(regist.getRegFecbd());
//                }
//                if(regist.getRegCodesp() != null){
//                    if(regist.getRegCodesp().getEspNomcom() != null) {
//                        especi = String.valueOf(regist.getRegCodesp().getEspNomcom());
//                    }
//                    if(regist.getRegCodesp().getEspEndemi() != null) {
//                        endemic = String.valueOf(regist.getRegCodesp().getEspEndemi());
//                    }
//                    if(regist.getRegCodesp().getEspAmenaz() != null) {
//                        amenazado = String.valueOf(regist.getRegCodesp().getEspAmenaz());
//                    }
//                }
//                if(regist.getRegCodfue() != null){
//                    font = String.valueOf(regist.getRegCodfue().getFueAutor());
//                }
//                if(regist.getRegCodtir() != null){
//                    tipus = String.valueOf(regist.getRegCodtir());
//                }
//                List<String> my_row =  Arrays.asList(posX, posY, illa, illot, d_obsevacio, d_registre, especi, catalogat, amenazado, endemic, tipus, font);
//                my_result.add(my_row);
//            }
            List<RegistEntity> my_result = registService.findRegistByEspeci(value);

            return Response.status(200)
                    .entity(my_result).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @GET
    @Path("/findRegistByCuadri/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna tots els albiraments d'una espècie", notes="Retorna un JSON amb tots els albiraments geoposicionats i els seus atributs enviant una espècie.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response findRegistByCuadri(
            @PathParam("value")
            @ApiParam(value="Identificador de la cuadricula")
                    Long value)
    {
        try {
            List<RegistEntity> my_result = registService.findRegistByCuadri(value);

            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(my_result).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/listDWC")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna tots els albiraments", notes="Retorna un JSON amb tots els albiraments.")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response listDWC() {
        try {
            List my_result = new ArrayList();
            for (RegistEntity regist: registService.list() ) {
                String occurrenceID = "";
                String basisOfRecord = "-";
                String eventDate = "-";
                String scientificName = "-";
                String kingdom = "-";
                String taxonRank = "-";
                String decimalLatitude = "-";
                String decimalLongitude = "-";
                String geodeticDatum = "-";
                String countryCode = "-";
                String individualCount = "-";
                String organismQuantity = "-";
                String organismQuantityType = "-";
                Map<String, String> registryDWC = new HashMap<>();
                if(regist.getRegCodi() != null){
                    occurrenceID = String.valueOf(regist.getRegCodi());
                    registryDWC.put("occurrenceID", "bioatles_" + occurrenceID);
                }

                registryDWC.put("basisOfRecord", "HumanObservation");

                if(regist.getRegFecha() != null){
                    eventDate = String.valueOf(regist.getRegFecha());
                    registryDWC.put("eventDate", eventDate);
                }
                if(regist.getRegCodesp() != null){
                    if(regist.getRegCodesp().getEspNomcom() != null) {
                        scientificName = regist.getRegCodesp().getEspNomcom();
                        registryDWC.put("scientificName", scientificName);
                    }
                }
                registryDWC.put("kingdom", kingdom);
                registryDWC.put("taxonRank", taxonRank);
                if(regist.getRegCodcua() != null){
                    if(regist.getRegCodcua().getCuaLatitu() != null) {
                        decimalLatitude = String.valueOf(regist.getRegCodcua().getCuaLatitu());
                        registryDWC.put("decimalLatitude", decimalLatitude);
                    }
                    if(regist.getRegCodcua().getCuaLongit() != null) {
                        decimalLongitude = String.valueOf(regist.getRegCodcua().getCuaLongit());
                        registryDWC.put("decimalLongitude", decimalLongitude);
                    }
                }
                if(regist.getRegTipcua() != null){
                    scientificName = regist.getRegTipcua();
                    registryDWC.put("geodeticDatum", geodeticDatum);
                }
                registryDWC.put("countryCode", countryCode);
                registryDWC.put("individualCount", individualCount);
                registryDWC.put("organismQuantity", organismQuantity);
                registryDWC.put("organismQuantityType", organismQuantityType);

                my_result.add(registryDWC);
            }

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
//    public Response addRegistEntity(
//            @PathParam("value")
//            @ApiParam(value="Valor de la entidad")
//            Timestamp value) {
//        try {
//            RegistEntity registEntity = new RegistEntity();
//            registEntity.setRegFecha(value);
//            return Response.status(200).entity(registService.addRegistEntity(registEntity)).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//    }

}
