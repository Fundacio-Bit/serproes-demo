package es.caib.serproes.service;

import es.caib.serproes.entity.EspeciEntity;
import es.caib.serproes.entity.GrupoEntity;
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
public class GrupoService implements GrupoServiceInterface {

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
	
//	public Boolean addGrupoEntity(GrupoEntity grupoEntity) {
//		try {
//			this.entityManager.persist(grupoEntity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'especificació JPA. */
	public List<GrupoEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM GrupoEntity f", GrupoEntity.class).getResultList();
	}

	public GrupoEntity findGrupoById (Integer gruCodi) {
		TypedQuery<GrupoEntity > query = this.entityManager.createQuery(
				"SELECT f FROM GrupoEntity f WHERE f.gruCodi = :gruCodi", GrupoEntity.class);
		return query.setParameter("gruCodi", gruCodi).getSingleResult();
	}

	public List listNoms() {
		return this.entityManager.createQuery("SELECT f FROM GrupoEntity f", GrupoEntity.class).getResultList();
	}

}
