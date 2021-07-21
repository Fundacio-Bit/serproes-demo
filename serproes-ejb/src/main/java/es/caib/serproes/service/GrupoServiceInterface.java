package es.caib.serproes.service;

import es.caib.serproes.entity.GrupoEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) GrupoService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface GrupoServiceInterface {

   String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/GrupoService";

	String getDefaultValue();

//	Boolean addGrupoEntity(GrupoEntity especiEntity);

	List<GrupoEntity> list();

	GrupoEntity findGrupoById(Integer id);
}
