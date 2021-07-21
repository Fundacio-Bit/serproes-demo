package es.caib.serproes.service;

import es.caib.serproes.entity.EspeciEntity;
import es.caib.serproes.entity.FamiliEntity;
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
public class EspeciService implements EspeciServiceInterface {

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
	
//	public Boolean addEspeciEntity(EspeciEntity especiEntity) {
//		try {
//			this.entityManager.persist(especiEntity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'especificació JPA. */
	public List<EspeciEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM EspeciEntity f", EspeciEntity.class).getResultList();
	}

	public List listNoms() {
		return this.entityManager.createQuery("SELECT f FROM EspeciEntity f", EspeciEntity.class).getResultList();
	}

	public List<EspeciEntity> findEspeciById(Double espCodi) {
		TypedQuery<EspeciEntity> query = this.entityManager.createQuery(
				"SELECT e FROM EspeciEntity e WHERE e.espCodi = :espCodi", EspeciEntity.class);
		return query.setParameter("espCodi", espCodi).getResultList();
	}

	public List<EspeciEntity> findEspeciByFamili(Integer famCodi) {
		TypedQuery<EspeciEntity> query = this.entityManager.createQuery(
				"SELECT f FROM EspeciEntity f JOIN f.espCodfam e WHERE e.famCodi = :famCodi", EspeciEntity.class);
		return query.setParameter("famCodi", famCodi).getResultList();
	}

}
