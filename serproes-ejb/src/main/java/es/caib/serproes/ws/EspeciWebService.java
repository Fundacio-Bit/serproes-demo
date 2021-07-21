package es.caib.serproes.ws;

import es.caib.serproes.entity.EspeciEntity;
import org.jboss.ws.api.annotation.WebContext;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@WebService(endpointInterface = "es.caib.serproes.ws.EspeciWebServiceInterface")
@WebContext(contextRoot="/SerproesServices")
public class EspeciWebService {

	@PersistenceContext
	EntityManager entityManager;
	
	@WebResult(name="EspecisRecuperados")
	@WebMethod(operationName="recuperarEspecis")
	public List<EspeciEntity> getEspecis() {
		return this.entityManager.createQuery("select f FROM EspeciEntity f", EspeciEntity.class).getResultList();
	}
	
	@WebResult(name="EspeciAgregado")
	@WebMethod(operationName="agregarEspeci")
	public EspeciEntity addEspeci(String value) {
		EspeciEntity especiEntity = new EspeciEntity();
		especiEntity.setEspNomcom(value);
		try {
			this.entityManager.persist(especiEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return especiEntity;
	}
	
}
