package es.caib.serproes.service;

import es.caib.serproes.entity.EspeciEntity;
import es.caib.serproes.entity.FamiliEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) EspeciService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface EspeciServiceInterface {

   String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/EspeciService";

	String getDefaultValue();

//	Boolean addEspeciEntity(EspeciEntity especiEntity);

	List<EspeciEntity> list();

	List listNoms();

	List<EspeciEntity> findEspeciById(Double espCodi);

	List<EspeciEntity> findEspeciByFamili(Integer espCodfam);
}
