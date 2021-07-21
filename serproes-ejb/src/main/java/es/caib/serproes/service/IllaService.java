package es.caib.serproes.service;

import es.caib.serproes.entity.IllaEntity;
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
public class IllaService implements IllaServiceInterface {

	@Inject
	private Logger log;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private String value = "Tenerife";

	@PostConstruct
	public void init() {
		log.info("Proxy a entityManager: "+this.entityManager);
	}
	
	public String getDefaultValue() {
		return value;
	}
	
	public Boolean addIllaEntity(IllaEntity especiEntity) {
		try {
			this.entityManager.persist(especiEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'especificació JPA. */
	public List<IllaEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM IllaEntity f", IllaEntity.class).getResultList();
	}

	public IllaEntity findIllaById (Long codi) {
		TypedQuery<IllaEntity > query = this.entityManager.createQuery(
				"SELECT f FROM IllaEntity f WHERE f.ilaCodi = :ilaCodi", IllaEntity.class);
		return query.setParameter("ilaCodi", codi).getSingleResult();
	}
}
