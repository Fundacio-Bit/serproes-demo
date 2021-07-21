package es.caib.serproes.service;

import es.caib.serproes.entity.FuenteEntity;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Servicio (EJB) para realizar inserciones y consultas de prueba en BBDD
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Stateless
//@RolesAllowed({"tothom", "PB_ADMIN"})
public class FuenteService implements FuenteServiceInterface {

	@Inject
	private Logger log;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private String value = "Hola soy Projecte BIOATLES!!!";

	@PostConstruct
	public void init() {
		log.info("Proxy a entityManager: "+this.entityManager);
	}
	
	public String getDefaultValue() {
		return value;
	}
	
//	public Boolean addFuenteEntity(FuenteEntity fuenteEntity) {
//		try {
//			this.entityManager.persist(fuenteEntity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'especificació JPA. */
	public List<FuenteEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM FuenteEntity f", FuenteEntity.class).getResultList();
	}

	public FuenteEntity findFuenteById (Long fueCodi) {
		TypedQuery<FuenteEntity > query = this.entityManager.createQuery(
				"SELECT f FROM FuenteEntity f WHERE f.fueCodi = :fueCodi", FuenteEntity.class);
		return query.setParameter("fueCodi", fueCodi).getSingleResult();
	}

}
