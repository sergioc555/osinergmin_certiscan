package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.mapper.DocumentoPaginaMapper;
import com.certicom.certiscan.mapper.ExpedienteDocumentoMapper;

public class DocumentoPaginaService implements DocumentoPaginaMapper {
	//ExpedienteDocumentoMapper expedienteDocumentoMapper  = (ExpedienteDocumentoMapper)ServiceFinder.findBean("expedienteDocumentoMapper");
	DocumentoPaginaMapper documentoPaginaMapper  = (DocumentoPaginaMapper)ServiceFinder.findBean("documentoPaginaMapper");


	public boolean insertDocumentosRegistros(Integer expediente_id, List<ExpedienteDocumento> listExpedienteDocumentos,Integer _idusuario){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteDocumentoMapper daoObj = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
		
		try {
			for (ExpedienteDocumento ed : listExpedienteDocumentos) {
				ed.setExpediente_id(expediente_id);
				ed.setId_usuario_creacion(_idusuario);
				ed.setFecha_subida(new Date());
				ed.setRuta(Constante.RUTA_DIGITILZACION+ed.getNombre_archivo());
				daoObj.guardarExpedienteDocumento(ed);
			}
			resultado = Boolean.TRUE;
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		
		return resultado;
	}

	@Override
	public void guardarDocumentoPagina(DocumentoPagina documentoPagina)
			throws Exception {
		documentoPaginaMapper.guardarDocumentoPagina(documentoPagina);
	}

	@Override
	public List<DocumentoPagina> listByIdExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		return documentoPaginaMapper.listByIdExpDoc(id_expediente_documento);
	}

	@Override
	public void updateEstadoEliminadoByIdExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		documentoPaginaMapper.updateEstadoEliminadoByIdExpDoc(id_expediente_documento);
	}

	@Override
	public void updateEstadoReemplazadoByIdExpDoc(
			Integer id_expediente_documento) throws Exception {
		// TODO Auto-generated method stub
		documentoPaginaMapper.updateEstadoReemplazadoByIdExpDoc(id_expediente_documento);
	}

	@Override
	public void updateEstadoAgrupadoByIdExpDoc(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		documentoPaginaMapper.updateEstadoAgrupadoByIdExpDoc(id_expediente_documento);
	}

	@Override
	public void eliminarPaginasByIdDocumento(Integer id_expediente_documento)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
