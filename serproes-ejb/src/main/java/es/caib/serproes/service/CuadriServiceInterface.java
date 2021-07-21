package es.caib.serproes.service;

import es.caib.serproes.entity.CuadriEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) CuadriService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface CuadriServiceInterface {

   String JNDI_NAME = "java:app/serproes-ejb-0.0.1-SNAPSHOT/CuadriService";

	String getDefaultValue();

	List<CuadriEntity> list();

}
