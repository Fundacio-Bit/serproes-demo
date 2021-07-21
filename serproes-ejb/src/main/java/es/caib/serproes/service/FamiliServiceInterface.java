package es.caib.serproes.service;

import es.caib.serproes.entity.FamiliEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) FamiliService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface FamiliServiceInterface {

   String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/FamiliService";

	String getDefaultValue();

//	Boolean addFamiliEntity(FamiliEntity especiEntity);

	List<FamiliEntity> list();

	FamiliEntity findFamiliById(Integer id);

	List<FamiliEntity> findFamiliByGrupo(Integer gruCodi);
}
