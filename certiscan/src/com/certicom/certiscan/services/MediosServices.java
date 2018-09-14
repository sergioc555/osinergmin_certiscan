package com.certicom.certiscan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Medios;
import com.certicom.certiscan.mapper.DocumentoPaginaMapper;
import com.certicom.certiscan.mapper.ExpedienteDocumentoMapper;
import com.certicom.certiscan.mapper.ExpedienteMapper;
import com.certicom.certiscan.mapper.ExpedienteTrackingMapper;
import com.certicom.certiscan.mapper.LogMapper;
import com.certicom.certiscan.mapper.MediosMapper;

public class MediosServices implements MediosMapper{

	MediosMapper mediosMapper = (MediosMapper)ServiceFinder.findBean("mediosMapper");

	@Override
	public Medios findById(Integer codigoMedios) throws Exception {
		// TODO Auto-generated method stub
		return mediosMapper.findById(codigoMedios);
	}

	@Override
	public List<Medios> findAll() throws Exception {
		// TODO Auto-generated method stub
		return mediosMapper.findAll();
	}

	@Override
	public void crearMedio(Medios cargo) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.crearMedio(cargo);
	}

	@Override
	public void actualizarMedio(Medios cargo) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.actualizarMedio(cargo);
	}

	@Override
	public void eliminarMedio(Integer cod_medio) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.eliminarMedio(cod_medio);
	}
	
	public void actualizarRutaMedios(List<Medios> listMedios){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		MediosMapper daoObj = (MediosMapper) sqlSession.getMapper(MediosMapper.class);
		
		try {		
		
		for (Medios medios : listMedios) {
			
			daoObj.actualizarMedioRuta(medios);
			
		}
		
		resultado = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		
	}
	
	public void actualizarMediosCreados(List<Medios> listMedios){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		MediosMapper daoObj = (MediosMapper) sqlSession.getMapper(MediosMapper.class);
		
		try {		
		
		for (Medios medios : listMedios) {
			medios.setCreado(Boolean.TRUE);
			daoObj.actualizarMedioCreado(medios);
			
		}
		
		resultado = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		
	}
	
	public void generarMedios(List<Medios> listMedios, Integer usuario, Integer oficina) {
			SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
			SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
			ExpedienteDocumentoMapper daoObj1 = (ExpedienteDocumentoMapper) sqlSession.getMapper(ExpedienteDocumentoMapper.class);
			MediosMapper daoObj2 = (MediosMapper) sqlSession.getMapper(MediosMapper.class);
			ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
			ExpedienteMapper daoObj4 = (ExpedienteMapper) sqlSession.getMapper(ExpedienteMapper.class);
			//LogMapper daoObj5 = (LogMapper) sqlSession.getMapper(LogMapper.class);			
			
			try {	
				List<Integer> listaCodigos = new ArrayList<Integer>();
			for (Medios medios : listMedios) {				
				medios.setCreado(Boolean.FALSE);				
				daoObj2.crearMedio(medios);				
				//Integer codigo = daoObj2.findMax();				
				for (ExpedienteDocumento exped : medios.getListExpedienteDocumentos()) {					
					exped.setId_medio(medios.getId_medio());
					exped.setMedio(Boolean.TRUE);					
					daoObj1.actualizarMedio(exped);				
					listaCodigos.add(exped.getExpediente_id());					
				}				
			}
			
			//System.out.println("cantidad de codigos antes: "+listaCodigos.size());
			
			Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();
			linkedHashSet.addAll(listaCodigos);
			listaCodigos.clear();
			listaCodigos.addAll(linkedHashSet);
			
			//System.out.println("cantidad de codigos despues: "+listaCodigos.size());
			
			for (Integer i : listaCodigos) {
				
				List<ExpedienteDocumento> list = daoObj1.listExpDocAgrupados(i);
				
				if(list.size() == 0){
				
					Expediente ex = new Expediente();
					ex.setExpediente_id(i);
					ex.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
					
					daoObj4.updateEstadoExpedientebyId(ex.getId_estado(), ex.getExpediente_id());
					
					ExpedienteTracking et = new ExpedienteTracking();
					et.setExpediente_id(ex.getExpediente_id());
					et.setFecha_registro(new Date());
					et.setIdusuario(usuario);
					et.setId_estado(Constante.EST_GENERO_MEDIO_LOGICO);
					et.setIdusuario_registro(usuario);		
					et.setId_oficina(oficina);
					
					daoObj3.crearExpedienteTracking(et);
					
					et = new ExpedienteTracking();
					et.setExpediente_id(ex.getExpediente_id());
					et.setFecha_registro(new Date());
					et.setIdusuario(usuario);
					et.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
					et.setIdusuario_registro(usuario);		
					et.setId_oficina(oficina);
					
					daoObj3.crearExpedienteTracking(et);
				
				}
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				sqlSession.rollback();
			}finally {
				sqlSession.close();
			}		
	}

	@Override
	public Integer findMax() throws Exception {
		// TODO Auto-generated method stub
		return mediosMapper.findMax();
	}

	@Override
	public void actualizarMedioRuta(Medios medio) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.actualizarMedioRuta(medio);
	}

	@Override
	public List<Medios> consultaMedios(Medios filter) throws Exception {
		// TODO Auto-generated method stub
		return mediosMapper.consultaMedios(filter);
	}

	@Override
	public void actualizarMedioCreado(Medios cargo) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.actualizarMedioCreado(cargo);
	}

	/*@Override
	public List<Medios> buscarPendientes() throws Exception {
		// TODO Auto-generated method stub
		return mediosMapper.buscarPendientes();
	}*/

	@Override
	public List<Medios> buscarPendientes(Medios filter) throws Exception {
		// TODO Auto-generated method stub
		return mediosMapper.buscarPendientes(filter);
	}

	@Override
	public void actualizarFechaInicio(Medios cargo) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.actualizarFechaInicio(cargo);
	}

	@Override
	public void actualizarFechaFin(Medios cargo) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.actualizarFechaFin(cargo);
	}

	@Override
	public void actualizarFechaInicioFin(Medios cargo) throws Exception {
		// TODO Auto-generated method stub
		mediosMapper.actualizarFechaInicioFin(cargo);
	}
	
}
