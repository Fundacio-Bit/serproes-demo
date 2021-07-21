package es.caib.serproes.api.resources;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.caib.serproes.service.CuadriServiceInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;

@Path("/cuadri")
@Api(value="/cuadri")
public class CuadriResource {

    @EJB
    private CuadriServiceInterface cuadriService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna totes les fonts", notes="Retorna un JSON amb totes les fonts.")
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
            return Response.status(200)
//                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                    .header("Access-Control-Max-Age", "1209600")
                    .entity(cuadriService.list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
