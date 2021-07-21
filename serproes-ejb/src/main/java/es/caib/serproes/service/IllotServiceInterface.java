package es.caib.serproes.service;

import es.caib.serproes.entity.IllaEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) IllaService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface IllotServiceInterface {

   	String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/IllaService";

	String getDefaultValue();

	Boolean addIllaEntity(IllaEntity especiEntity);

	List<IllaEntity> list();
	
}
