package es.caib.serproes.service;

import es.caib.serproes.entity.FamiliEntity;
import es.caib.serproes.entity.RegistEntity;
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
public class FamiliService implements FamiliServiceInterface {

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
	
//	public Boolean addFamiliEntity(FamiliEntity grupoEntity) {
//		try {
//			this.entityManager.persist(grupoEntity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'especificació JPA. */
	public List<FamiliEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM FamiliEntity f", FamiliEntity.class).getResultList();
	}

	public FamiliEntity findFamiliById (Integer famCodi) {
		TypedQuery<FamiliEntity > query = this.entityManager.createQuery(
				"SELECT f FROM FamiliEntity f WHERE f.famCodi = :famCodi", FamiliEntity.class);
		return query.setParameter("famCodi", famCodi).getSingleResult();
	}

	public List listNoms() {
		return this.entityManager.createQuery("SELECT f FROM FamiliEntity f", FamiliEntity.class).getResultList();
	}

	public List<FamiliEntity> findFamiliByGrupo (Integer gruCodi) {
		TypedQuery<FamiliEntity> query = this.entityManager.createQuery(
				"SELECT f FROM FamiliEntity f JOIN f.famCodgru e WHERE e.gruCodi = :gruCodi", FamiliEntity.class);
		return query.setParameter("gruCodi", gruCodi).getResultList();
	}

}
