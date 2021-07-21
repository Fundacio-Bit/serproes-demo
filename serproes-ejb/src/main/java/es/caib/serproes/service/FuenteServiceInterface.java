package es.caib.serproes.service;

import es.caib.serproes.entity.FuenteEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) FuenteService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface FuenteServiceInterface {

   String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/FuenteService";

	String getDefaultValue();

//	Boolean addFuenteEntity(FuenteEntity especiEntity);

	List<FuenteEntity> list();

	FuenteEntity findFuenteById(Long id);
}
