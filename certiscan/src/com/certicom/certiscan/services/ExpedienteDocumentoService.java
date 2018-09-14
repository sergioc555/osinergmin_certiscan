package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.mapper.DocumentoPaginaMapper;
import com.certicom.certiscan.mapper.ExpedienteDocumentoMapper;
import com.certicom.certiscan.mapper.ExpedienteMapper;
import com.certicom.certiscan.mapper.ExpedienteTrackingMapper;
import com.certicom.certiscan.mapper.LogMapper;

import src.com.certicom.certiscan.utils.ResultBean;
import src.com.certicom.certiscan.utils.Utils;

public class ExpedienteDocumentoService implements ExpedienteDocumentoMapper {
	ExpedienteDocumentoMapper expedienteDocumentoMapper  = (ExpedienteDocumentoMapper)ServiceFinder.findBean("expedienteDocumentoMapper");
 
	@Override
	public void guardarExpedienteDocumento(ExpedienteDocumento expedienteDocumento) throws Exception {
		expedienteDocumentoMapper.guardarExpedienteDocumento(expedienteDocumento);
	}

	@Override
	public List<ExpedienteDocumento> listFilesbyExpediente_id(
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesbyExpediente_id(expediente_id);
	}
	
	
	
	public boolean insertDocumentosRegistros(Integer expediente_id, List<ExpedienteDocumento> listExpedienteDocumentos,Integer _idusuario,Expediente _ex, Log log){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
		ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
		ExpedienteMapper daoObj4 = (ExpedienteMapper) sqlSession.getMapper(ExpedienteMapper.class);
		LogMapper daoObj5 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		try {
			
			int cont_ed = 0;
			
			for (ExpedienteDocumento ed : listExpedienteDocumentos) {
				cont_ed =cont_ed+1;
				ed.setExpediente_id(expediente_id);
				ed.setId_usuario_creacion(_idusuario);
				ed.setFecha_subida(new Date());
				ed.setRuta(_ex.getRuta_carpeta()+"/"+ed.getNombre_archivo());
				ed.setEstado(Boolean.TRUE);
				ed.setEstado_accion("D");
				ed.setOrden_expediente(cont_ed);
				daoObj.guardarExpedienteDocumento(ed);
				Integer id_expd_doc = daoObj.getIdExpDocByExpedienteNroArchivo(expediente_id, ed.getNro_archivo());
				log.setAccion("INSERT");
				log.setDescripcion("Se cre� el Archivo "+ ed.getNombre_archivo()+" del Nro de Expediente "+_ex.getNro_expediente());
				daoObj5.insertLog(log);
				
				for (DocumentoPagina dpag : ed.getListaPaginas()) {
					dpag.setId_expediente_documento(id_expd_doc);
					dpag.setId_usuario_creacion(_idusuario);
					dpag.setFecha_subida(new Date());
					dpag.setEstado(Boolean.TRUE);
					dpag.setEstado_accion("D");
					daoObj2.guardarDocumentoPagina(dpag);
					log.setAccion("INSERT");
					log.setDescripcion("Se registr� la p�gina nro "+dpag.getNro_pagina()+" del Archivo "+ed.getNombre_archivo()+" del Nro de Expediente "+_ex.getNro_expediente());
					daoObj5.insertLog(log);
				}
				
			}
			
			ExpedienteTracking et = new ExpedienteTracking();
			et.setExpediente_id(expediente_id);
			et.setFecha_registro(new Date());
			et.setIdusuario(_idusuario);
			et.setIdusuario_registro(et.getIdusuario());
			et.setId_estado(Constante.EST_SUBIO_ARCHIVOS);
			et.setCondicion(_ex.getCondicion());
			et.setFecha_recepcion(_ex.getFecha_recepcion());
			
			daoObj3.crearExpedienteTracking(et);
			log.setAccion("INSERT");
			log.setDescripcion("Se cre� el tracking del Nro Expediente : " + _ex.getNro_expediente()+" para el estado SUBIDO Y DIGITALIZADO");
			daoObj5.insertLog(log);
			
			et.setFecha_registro(new Date());
			et.setId_estado(Constante.EST_PREPARADO_PARA_GENERAR_MEDIO_LOGICO);
			daoObj3.crearExpedienteTracking(et);
			log.setAccion("INSERT");
			log.setDescripcion("Se cre� el tracking del Nro Expediente : " + _ex.getNro_expediente()+" para el estado PREPARADO PARA CONTROL DE CALIDAD");
			daoObj5.insertLog(log);
			
			
			daoObj4.updateEstadoExpedientebyId(Constante.EST_PREPARADO_PARA_GENERAR_MEDIO_LOGICO, expediente_id);
			log.setAccion("UPDATE");
			log.setDescripcion("Se actualiz� el Nro de Expediente : " + _ex.getNro_expediente()+" al estado PREPARADO PARA CONTROL DE CALIDAD");
			daoObj5.insertLog(log);
			
			resultado = Boolean.TRUE;
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		
		return resultado;
	}

	
	public boolean updateEstadoEliminadoArchivoPaginas(ExpedienteDocumento _ed){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
		ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
		
		try {
			
			daoObj2.updateEstadoEliminadoByIdExpDoc(_ed.getId_expediente_documento());
			daoObj.updateEstadoEliminadoByIdExpDoc(_ed.getId_expediente_documento());
			resultado = Boolean.TRUE;
			
		} catch (Exception e) {
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		} finally{
			sqlSession.close();
		}
		
		return resultado;
	}
	
	public boolean updateEstadoReemplazoArchivoPaginas(ExpedienteDocumento _ed, ExpedienteDocumento _edNuevo, Integer _idusuario, Log log){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
		ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		
		try {
			
	        
			System.out.println("ENTRO REEMPLAZO");
			
			daoObj2.updateEstadoReemplazadoByIdExpDoc(_ed.getId_expediente_documento());
			log.setAccion("UPDATE");
			log.setDescripcion("Se cambi� al estado REEMPLAZADO las p�ginas del Archivo "+_ed.getNombre_archivo());
			daoObj4.insertLog(log);
			
			
			for (DocumentoPagina dpag : _edNuevo.getListaPaginas()) {
				dpag.setId_expediente_documento(_ed.getId_expediente_documento());
				dpag.setId_usuario_creacion(_idusuario);
				dpag.setFecha_subida(new Date());
				dpag.setEstado(Boolean.TRUE);
				dpag.setEstado_accion("R");
				daoObj2.guardarDocumentoPagina(dpag);
				log.setAccion("INSERT");
				log.setDescripcion("Se ingres� la p�gina nro "+ dpag.getNro_pagina() + "al Archivo "+_ed.getNombre_archivo());
				daoObj4.insertLog(log);
			}
			daoObj.reemplazarDocumentoByIdExpDoc(_edNuevo.getPeso(), _edNuevo.getDescripcion_peso(), _edNuevo.getNro_paginas(), _ed.getId_expediente_documento());
			log.setAccion("UPDATE");
			log.setDescripcion("Se actualiz� el nro de p�ginas "+_edNuevo.getNro_paginas()+", estado REEMPLAZO y peso "+Utils.convertirLongBytes(Long.valueOf(_edNuevo.getPeso()), false) +" del Archivo"+ _edNuevo.getNombre_archivo());
			daoObj4.insertLog(log);
			resultado = Boolean.TRUE;
			
		} catch (Exception e) {
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		} finally{
			sqlSession.close();
		}
		
		return resultado;
	}
	
	
	public boolean subirNuevoArchivo(String tipo_operacion, Integer expediente_id,ExpedienteDocumento _ed, ExpedienteDocumento _edNuevo, Integer _idusuario, Expediente _ex, Log log){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
		ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		try {
			
			
			//daoObj2.updateEstadoReemplazadoByIdExpDoc(_ed.getId_expediente_documento());
			int corre_lativo = 0;
			if(tipo_operacion.equals("Anterior")){
				corre_lativo = _ed.getOrden_expediente();
			//	daoObj.updateCorrelativoArchivos(expediente_id,_ed.getOrden_expediente());
			} else if(tipo_operacion.equals("Despues")){
				corre_lativo = _ed.getOrden_expediente()+1;
			//	daoObj.updateCorrelativoArchivos(expediente_id,_ed.getOrden_expediente()+1);
			}
			daoObj.updateCorrelativoArchivos(expediente_id,corre_lativo);
			
			log.setAccion("UPDATE");
			log.setDescripcion("Se actualiz� el correlativo para el Archivo "+_edNuevo.getNombre_archivo()+" que reemplaza al Archivo "+_ed.getNombre_archivo()+" del Nro de Expediente "+_ed.getNro_expediente());
			daoObj4.insertLog(log);
			
			_edNuevo.setExpediente_id(expediente_id);
			_edNuevo.setId_usuario_creacion(_idusuario);
			_edNuevo.setFecha_subida(new Date());
			_edNuevo.setRuta(_ex.getRuta_carpeta()+"/"+_edNuevo.getNombre_archivo());
			_edNuevo.setEstado(Boolean.TRUE);
			_edNuevo.setEstado_accion("D");
			_edNuevo.setOrden_expediente(corre_lativo);
			daoObj.guardarExpedienteDocumento(_edNuevo);
			
			Integer id_expd_doc = daoObj.getIdExpDocByExpedienteNroArchivo(expediente_id, _edNuevo.getNro_archivo());
			
			log.setAccion("UPDATE");
			log.setDescripcion("Se ingres� Archivo "+_edNuevo.getNombre_archivo()+" que reemplaza al Archivo "+_ed.getNombre_archivo()+" del Nro de Expediente "+_edNuevo.getNro_expediente());
			daoObj4.insertLog(log);
			
			for (DocumentoPagina dpag : _edNuevo.getListaPaginas()) {
				dpag.setId_expediente_documento(id_expd_doc);
				dpag.setId_usuario_creacion(_idusuario);
				dpag.setFecha_subida(new Date());
				dpag.setEstado(Boolean.TRUE);
				dpag.setEstado_accion("D");
				daoObj2.guardarDocumentoPagina(dpag);
				
				log.setAccion("INSERT");
				log.setDescripcion("Se ingres� la p�gina del Archivo "+_edNuevo.getNombre_archivo()+" del Nro de Expediente "+_edNuevo.getNro_expediente());
				daoObj4.insertLog(log);
			}
			
			//daoObj.reemplazarDocumentoByIdExpDoc(_edNuevo.getPeso(), _edNuevo.getDescripcion_peso(), _edNuevo.getNro_paginas(), _ed.getId_expediente_documento());
			resultado = Boolean.TRUE;
			
		} catch (Exception e) {
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		} finally{
			sqlSession.close();
		}
		
		return resultado;
	}
	
	
	
	public boolean updateEstadoAgrupadoArchivosPaginas(List<ExpedienteDocumento> _listaArchivos, Integer _idusuario,
				ExpedienteDocumento _nuevoArchivo, Integer _expediente_id,Expediente _ex, Log log){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		try {
			
			for (ExpedienteDocumento ed : _listaArchivos) {
				daoObj2.updateEstadoAgrupadoByIdExpDoc(ed.getId_expediente_documento());
				daoObj.updateEstadoAgrupadoByIdExpDoc(ed.getId_expediente_documento());
				daoObj.actualizarDescExpDoc(ed);
				
				log.setAccion("UPDATE");
				log.setDescripcion("Se cambi� al estado AGRUPADO las p�ginas del Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
				daoObj4.insertLog(log);
	            
	            log.setAccion("UPDATE");
				log.setDescripcion("Se cambi� al estado AGRUPADO el Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
				daoObj4.insertLog(log);
				
				log.setAccion("UPDATE");
				log.setDescripcion("Se cambi� la descripci�n del Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
				daoObj4.insertLog(log);
			}
			
			_nuevoArchivo.setExpediente_id(_expediente_id);
			_nuevoArchivo.setId_usuario_creacion(_idusuario);
			_nuevoArchivo.setFecha_subida(new Date());
			_nuevoArchivo.setRuta(_ex.getRuta_carpeta()+"/"+_nuevoArchivo.getNombre_archivo());
			_nuevoArchivo.setEstado(Boolean.TRUE);
			_nuevoArchivo.setEstado_accion("AG");
			daoObj.guardarExpedienteDocumento(_nuevoArchivo);
			Integer id_expd_doc = daoObj.getIdExpDocByExpedienteNroArchivo(_expediente_id, _nuevoArchivo.getNro_archivo());
			
			log.setAccion("INSERT");
			log.setDescripcion("Se cre� el Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
			daoObj4.insertLog(log);
			
			for (ExpedienteDocumento ed : _listaArchivos) {
				daoObj.updatePadreGrupo(id_expd_doc, ed.getId_expediente_documento());
				log.setAccion("UPDATE");
				log.setDescripcion("Se asigna como padre el Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente() + " al Archivo "+ed.getNombre_archivo());
				daoObj4.insertLog(log);
			}
			
			for (DocumentoPagina dpag : _nuevoArchivo.getListaPaginas()) {
				dpag.setId_expediente_documento(id_expd_doc);
				dpag.setId_usuario_creacion(_idusuario);
				dpag.setFecha_subida(new Date());
				dpag.setEstado(Boolean.TRUE);
				dpag.setEstado_accion("AG");
				daoObj2.guardarDocumentoPagina(dpag);
				
				log.setAccion("INSERT");
				log.setDescripcion("Se ingresa la p�gina "+dpag.getNro_pagina()+" al Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
				daoObj4.insertLog(log);
			}
			
			resultado = Boolean.TRUE;
			
		} catch (Exception e) {
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		} finally{
			sqlSession.close();
		}
		
		return resultado;
	}
	
	public boolean updateEstadoAgrupadoArchivosPaginas2(ExpedienteDocumento archivos, Integer _idusuario,
			ExpedienteDocumento _nuevoArchivo, Integer _expediente_id,Expediente _ex, Log log){
	Boolean resultado = Boolean.FALSE;
	SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
	SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
	
	ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
	DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
	LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
	//Log log = new Log();
	try {
		
		
			daoObj2.updateEstadoAgrupadoByIdExpDoc(archivos.getId_expediente_documento());
			daoObj.updateEstadoAgrupadoByUnExpDoc(archivos.getId_expediente_documento());
			daoObj.actualizarDescExpDoc(archivos);
			
			log.setAccion("UPDATE");
			log.setDescripcion("Se cambi� al estado AGRUPADO las p�ginas del Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
			daoObj4.insertLog(log);
            
            log.setAccion("UPDATE");
			log.setDescripcion("Se cambi� al estado AGRUPADO el Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
			daoObj4.insertLog(log);
			
			log.setAccion("UPDATE");
			log.setDescripcion("Se cambi� la descripci�n del Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
			daoObj4.insertLog(log);
		
		
		/*_nuevoArchivo.setExpediente_id(_expediente_id);
		_nuevoArchivo.setId_usuario_creacion(_idusuario);
		_nuevoArchivo.setFecha_subida(new Date());
		_nuevoArchivo.setRuta(_ex.getRuta_carpeta()+"/"+_nuevoArchivo.getNombre_archivo());
		_nuevoArchivo.setEstado(Boolean.TRUE);
		_nuevoArchivo.setEstado_accion("AG");
		daoObj.guardarExpedienteDocumento(_nuevoArchivo);
		Integer id_expd_doc = daoObj.getIdExpDocByExpedienteNroArchivo(_expediente_id, _nuevoArchivo.getNro_archivo());
		
		log.setAccion("INSERT");
		log.setDescripcion("Se cre� el Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
		daoObj4.insertLog(log);*/
		
		
			daoObj.updatePadreGrupo(archivos.getId_expediente_documento(), archivos.getId_expediente_documento());
			log.setAccion("UPDATE");
			log.setDescripcion("Se asigna como padre el Archivo "+ archivos.getNombre_archivo()+" del Nro de Expediente "+archivos.getNro_expediente() + " al Archivo "+archivos.getNombre_archivo());
			daoObj4.insertLog(log);
		
		
		for (DocumentoPagina dpag : archivos.getListaPaginas()) {
			dpag.setId_expediente_documento(archivos.getId_expediente_documento());
			dpag.setId_usuario_creacion(_idusuario);
			dpag.setFecha_subida(new Date());
			dpag.setEstado(Boolean.TRUE);
			dpag.setEstado_accion("AG");
			daoObj2.guardarDocumentoPagina(dpag);
			
			log.setAccion("INSERT");
			log.setDescripcion("Se ingresa la p�gina "+dpag.getNro_pagina()+" al Archivo "+ _nuevoArchivo.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivo.getNro_expediente());
			daoObj4.insertLog(log);
		}
		
		resultado = Boolean.TRUE;
		
	} catch (Exception e) {
		resultado = Boolean.FALSE;
		e.printStackTrace();
		sqlSession.rollback();
	} finally{
		sqlSession.close();
	}
	
	return resultado;
}
	
	public boolean desagruparArchivos(ExpedienteDocumento _edDoc){
	Boolean resultado = Boolean.FALSE;
	SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
	SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
	
	ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
	
	try {
		
		daoObj.quitarPadreGrupo(_edDoc.getId_expediente_documento());
		daoObj.eliminarPadreGrupo(_edDoc.getId_expediente_documento());
		
		resultado = Boolean.TRUE;
		
	} catch (Exception e) {
		resultado = Boolean.FALSE;
		e.printStackTrace();
		sqlSession.rollback();
	} finally{
		sqlSession.close();
	}
	
	return resultado;
}
	
	
	
	public ResultBean removerHijoArchivoAgrupado(List<ExpedienteDocumento> _listaArchivos, Integer _idusuario,
				ExpedienteDocumento _nuevoArchivoPadre, Integer _expediente_id, ExpedienteDocumento _antigArchivoPadre, ExpedienteDocumento _antiguoHijo,Expediente _ex, Log log){
		ResultBean result = new ResultBean();
		
		result.setResultado(Boolean.FALSE);
		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		DocumentoPaginaMapper daoObj2 = (DocumentoPaginaMapper) sqlSession.getMapper(DocumentoPaginaMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		try {
			
			daoObj2.eliminarPaginasByIdDocumento(_antigArchivoPadre.getId_expediente_documento());
			daoObj.eliminarExpedienteDocumento(_antigArchivoPadre.getId_expediente_documento());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se eliminaron las p�ginas del Archivo "+ _antigArchivoPadre.getNombre_archivo()+" del Nro de Expediente "+_antigArchivoPadre.getNro_expediente());
			daoObj4.insertLog(log);
            
            log.setAccion("DELETE");
			log.setDescripcion("Se elimin� el Archivo "+ _antigArchivoPadre.getNombre_archivo()+" del Nro de Expediente "+_antigArchivoPadre.getNro_expediente());
			daoObj4.insertLog(log);			
			
			daoObj.quitarPadreGrupoHijo(_antiguoHijo.getId_expediente_documento());
			
			for (ExpedienteDocumento ed : _listaArchivos) {
				daoObj2.updateEstadoAgrupadoByIdExpDoc(ed.getId_expediente_documento());
				daoObj.updateEstadoAgrupadoByIdExpDoc(ed.getId_expediente_documento());
				log.setAccion("UPDATE");
				log.setDescripcion("Se cambi� al estado AGRUPADO las p�ginas del Archivo "+ ed.getNombre_archivo()+" del Nro de Expediente "+ed.getNro_expediente());
				daoObj4.insertLog(log);
	            
	            log.setAccion("UPDATE");
				log.setDescripcion("Se cambi� al estado AGRUPADO el Archivo "+ ed.getNombre_archivo()+" del Nro de Expediente "+ed.getNro_expediente());
				daoObj4.insertLog(log);
			}
			
			_nuevoArchivoPadre.setExpediente_id(_expediente_id);
			_nuevoArchivoPadre.setId_usuario_creacion(_idusuario);
			_nuevoArchivoPadre.setFecha_subida(new Date());
			_nuevoArchivoPadre.setRuta(_ex.getRuta_carpeta()+"/"+_nuevoArchivoPadre.getNombre_archivo());
			_nuevoArchivoPadre.setEstado(Boolean.TRUE);
			_nuevoArchivoPadre.setEstado_accion("AG");
			daoObj.guardarExpedienteDocumento(_nuevoArchivoPadre);
			Integer id_expd_doc = daoObj.getIdExpDocByExpedienteNroArchivo(_expediente_id, _nuevoArchivoPadre.getNro_archivo());
			log.setAccion("INSERT");
			log.setDescripcion("Se ingresa el Archivo "+ _nuevoArchivoPadre.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivoPadre.getNro_expediente());
			daoObj4.insertLog(log);
			
			for (ExpedienteDocumento ed : _listaArchivos) {
				daoObj.updatePadreGrupo(id_expd_doc, ed.getId_expediente_documento());
				log.setAccion("UPDATE");
				log.setDescripcion("Se asigna como padre el Archivo "+ _nuevoArchivoPadre.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivoPadre.getNro_expediente() + " al Archivo "+ed.getNombre_archivo());
				daoObj4.insertLog(log);
			}
			
			for (DocumentoPagina dpag : _nuevoArchivoPadre.getListaPaginas()) {
				dpag.setId_expediente_documento(id_expd_doc);
				dpag.setId_usuario_creacion(_idusuario);
				dpag.setFecha_subida(new Date());
				dpag.setEstado(Boolean.TRUE);
				dpag.setEstado_accion("AG");
				daoObj2.guardarDocumentoPagina(dpag);
				log.setAccion("INSERT");
				log.setDescripcion("Se ingresa la p�gina "+dpag.getNro_pagina()+" al Archivo "+ _nuevoArchivoPadre.getNombre_archivo()+" del Nro de Expediente "+_nuevoArchivoPadre.getNro_expediente());
				daoObj4.insertLog(log);
			}
			
			result.setResultado(Boolean.TRUE);
			result.setId_expediente_documento(id_expd_doc);
			
		} catch (Exception e) {
			result.setResultado(Boolean.FALSE);
			e.printStackTrace();
			sqlSession.rollback();
		} finally{
			sqlSession.close();
		}
		
		return result;
	}
	
	
	@Override
	public Integer getNroArchivoSeq() throws Exception {
		return expedienteDocumentoMapper.getNroArchivoSeq();
	}

	@Override
	public Integer getIdExpDocByExpedienteNroArchivo(Integer expediente_id,
			Integer nro_archivo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.getIdExpDocByExpedienteNroArchivo(expediente_id, nro_archivo);
	}

	@Override
	public ExpedienteDocumento findByID(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.findByID(id_expediente_documento);
	}

	@Override
	public void updateEstadoEliminadoByIdExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.updateEstadoEliminadoByIdExpDoc(id_expediente_documento);
	}

	@Override
	public void reemplazarDocumentoByIdExpDoc(Long peso,
			String descripcion_peso, Integer nro_paginas,
			Integer id_expediente_documento) throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.reemplazarDocumentoByIdExpDoc(peso, descripcion_peso, nro_paginas, id_expediente_documento);
	}

	@Override
	public void updateEstadoAgrupadoByIdExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCorrelativoArchivos(Integer expediente_id,Integer _coore)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePadreGrupo(Integer expediente_id,
			Integer id_expediente_documento) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitarPadreGrupo(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPadreGrupo(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExpedienteDocumento> listArchivosByPadre(
			Integer id_expediente_documento) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listArchivosByPadre(id_expediente_documento);
	}

	@Override
	public List<ExpedienteDocumento> listFilesGroupedByExpediente_id(
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesGroupedByExpediente_id(expediente_id);
	}

	@Override
	public List<ExpedienteDocumento> listArchivosHermanos(Integer id_ed_padre,
			Integer id_ed_hijo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listArchivosHermanos(id_ed_padre, id_ed_hijo);
	}
	@Override
	public List<ExpedienteDocumento> listFilesDisGroupedByExpediente_idAG(
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesDisGroupedByExpediente_idAG(expediente_id);
	}

	@Override
	public void eliminarExpedienteDocumento(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitarPadreGrupoHijo(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.quitarPadreGrupoHijo(id_expediente_documento);
	}

	@Override
	public Integer listFilesDigitalizados(Integer expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesDigitalizados(expediente_id);
	}

	@Override
	public Integer listFilesDigitalizadosEliminados(Integer expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesDigitalizadosEliminados(expediente_id);
	}

	@Override
	public Integer listFilesDigitalizadosReempl(Integer expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesDigitalizadosReempl(expediente_id);
	}

	@Override
	public ExpedienteDocumento FilesGroupedByExpediente_idAGI(
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.FilesGroupedByExpediente_idAGI(expediente_id);
	}

	@Override
	public List<ExpedienteDocumento> listFilesGroupedByExpediente_idAGI(
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesGroupedByExpediente_idAGI(expediente_id);
	}

	@Override
	public void actualizarDescExpDoc(ExpedienteDocumento expedienteDocumento)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.actualizarDescExpDoc(expedienteDocumento);
	}

	@Override
	public void actualizarNombreArchivo(ExpedienteDocumento exp)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.actualizarNombreArchivo(exp);
	}

	@Override
	public void updateCorrelativoArchivosSinExpDrag(Integer expediente_id,
			Integer corr, Integer id_expediente_documento) throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.updateCorrelativoArchivosSinExpDrag(expediente_id, corr, id_expediente_documento);
	}

	public ExpedienteDocumentoMapper getExpedienteDocumentoMapper() {
		return expedienteDocumentoMapper;
	}

	public void setExpedienteDocumentoMapper(
			ExpedienteDocumentoMapper expedienteDocumentoMapper) {
		this.expedienteDocumentoMapper = expedienteDocumentoMapper;
	}

	@Override
	public void updateCorrelativoArchivosExpDrag(Integer expediente_id,
			Integer orden_expediente, Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.updateCorrelativoArchivosExpDrag(expediente_id, orden_expediente, id_expediente_documento);
	}

	@Override
	public void updateCorrelativoArchivosRango(Integer expediente_id,
			Integer corr, Integer corr1) throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.updateCorrelativoArchivosRango(expediente_id, corr, corr1);
	}

	@Override
	public void updateEstadoAgrupadoByUnExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.updateEstadoAgrupadoByUnExpDoc(id_expediente_documento);
	}

	@Override
	public void updateEstadoDesgrupadoByIdExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.updateEstadoDesgrupadoByIdExpDoc(id_expediente_documento);
	}

	@Override
	public List<ExpedienteDocumento> listFilesSinEliminadosbyExpediente_id(
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listFilesSinEliminadosbyExpediente_id(expediente_id);
	}

	@Override
	public List<ExpedienteDocumento> consultaPreparadoMedioExpediente(
			ExpedienteDocumento filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.consultaPreparadoMedioExpediente(filter);
	}

	@Override
	public void actualizarMedio(ExpedienteDocumento exp) throws Exception {
		// TODO Auto-generated method stub
		expedienteDocumentoMapper.actualizarMedio(exp);
	}

	@Override
	public List<ExpedienteDocumento> listExpDocAgrupados(Integer expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listExpDocAgrupados(expediente_id);
	}

	@Override
	public List<ExpedienteDocumento> listExpDocMedio(Integer id_medio)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.listExpDocMedio(id_medio);
	}

	@Override
	public List<ExpedienteDocumento> obtenerCarpetas(String grupo_formato, String zona, String entregable)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.obtenerCarpetas(grupo_formato, zona, entregable);
	}

	@Override
	public List<ExpedienteDocumento> obtenerSedes(String grupo_formato, String zona, String entregable)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteDocumentoMapper.obtenerSedes(grupo_formato, zona, entregable);
	}

}
