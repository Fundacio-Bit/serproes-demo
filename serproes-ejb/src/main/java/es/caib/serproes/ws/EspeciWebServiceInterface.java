package es.caib.serproes.ws;

import es.caib.serproes.entity.EspeciEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EspeciWebServiceInterface {
	
	@WebResult(name="EspecisRecuperados")
	@WebMethod(operationName="recuperarEspecis")
	List<EspeciEntity> getEspecis();
	
	@WebResult(name="EspeciAgregado")
	@WebMethod(operationName="agregarEspeci")
	EspeciEntity addEspeci(@WebParam(name="value") String value);
	
}
