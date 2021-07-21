package es.caib.serproes.service;

import es.caib.serproes.entity.EspeciEntity;
import es.caib.serproes.entity.FuenteEntity;
import es.caib.serproes.entity.RegistEntity;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.criteria.Join;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * Servicio (EJB) para realizar inserciones y consultas de prueba en BBDD
 * @author [u91310] Pedro Bauzá Mascar~~ó
 */
@Stateless
//@RolesAllowed({"tothom", "PB_ADMIN"})
public class RegistService implements RegistServiceInterface {

	@Inject
	private Logger log;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private String value = "Hola soy Regist de Projecte BIOATLES!!!";

	@PostConstruct
	public void init() {
		log.info("Proxy a entityManager: "+this.entityManager);
	}
	
	public String getDefaultValue() {
		return value;
	}
	
//	public Boolean addRegistEntity(RegistEntity registEntity) {
//		try {
//			this.entityManager.persist(registEntity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'registficació JPA. */
	public List<RegistEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM RegistEntity f", RegistEntity.class).getResultList();
	}


	public List<RegistEntity> findRegistByEspeci (Double espCodi) {
		TypedQuery<RegistEntity> query = this.entityManager.createQuery(
				"SELECT f FROM RegistEntity f JOIN f.regCodesp e WHERE e.espCodi = :espCodi", RegistEntity.class);
		return query.setParameter("espCodi", espCodi).getResultList();
	}

	public List<RegistEntity> findRegistByCuadri (Long cuaCodi) {
		TypedQuery<RegistEntity> query = this.entityManager.createQuery(
				"SELECT f FROM RegistEntity f JOIN f.regCodcua c WHERE c.cuaCodi = :cuaCodi", RegistEntity.class);
		return query.setParameter("cuaCodi", cuaCodi).getResultList();
	}
}
