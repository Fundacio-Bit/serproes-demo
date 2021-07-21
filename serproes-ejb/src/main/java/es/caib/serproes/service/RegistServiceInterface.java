package es.caib.serproes.service;

import es.caib.serproes.entity.RegistEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) RegistService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface RegistServiceInterface {

   String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/RegistService";

	String getDefaultValue();

//	Boolean addRegistEntity(RegistEntity especiEntity);

	List<RegistEntity> list();

	List<RegistEntity> findRegistByEspeci(Double espCodi);

	List<RegistEntity> findRegistByCuadri(Long cuaCodi);

}
