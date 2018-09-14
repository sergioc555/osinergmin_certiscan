package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.mapper.EstadosMapper;
import com.certicom.certiscan.mapper.ExpedienteMapper;
import com.certicom.certiscan.mapper.ExpedienteTrackingMapper;
import com.certicom.certiscan.mapper.LogMapper;

public class ExpedienteTrackingService implements ExpedienteTrackingMapper{
	
	ExpedienteTrackingMapper expedienteTrackingMapper = (ExpedienteTrackingMapper) ServiceFinder.findBean("expedienteTrackingMapper");

	@Override
	public ExpedienteTracking findById(Integer id_expediente_tracking)
			throws Exception {
		return expedienteTrackingMapper.findById(id_expediente_tracking);
		
	}

	@Override
	public List<ExpedienteTracking> findAll() throws Exception {
		return expedienteTrackingMapper.findAll();
	}

	@Override
	public void crearExpedienteTracking(ExpedienteTracking expedienteTracking)
			throws Exception {
		expedienteTrackingMapper.crearExpedienteTracking(expedienteTracking);
	}

	@Override
	public List<ExpedienteTracking> findByExpedienteId(Integer p_expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteTrackingMapper.findByExpedienteId(p_expediente_id);
	}
	
	public void insertBatchExpedienteTracking(List<ExpedienteTracking> listExpTrack){
		
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteTrackingMapper daoObj= (ExpedienteTrackingMapper)sqlSession.getMapper(ExpedienteTrackingMapper.class);
		
		try{
			
			//sqlSession.commit(true);
			for (ExpedienteTracking exptrack:listExpTrack) {
				daoObj.crearExpedienteTracking(exptrack);
				
			}
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
	}

	public void updateBatchExpedienteTrackingxFacturado(
			List<ExpedienteTracking> listExpedienteTracking) throws Exception {
		// TODO Auto-generated method stub
		
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteTrackingMapper daoObj= (ExpedienteTrackingMapper)sqlSession.getMapper(ExpedienteTrackingMapper.class);

		try{
		
			//sqlSession.commit(true);
			for (ExpedienteTracking exptrack:listExpedienteTracking) {
				daoObj.actualizarExpedienteTrackingxFacturado(exptrack);
				
			}
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		//expedienteTrackingMapper.actualizarExpedienteTrackingxPagado(expedienteTracking);
	}

	@Override
	public void actualizarExpedienteTrackingxFacturado(
			ExpedienteTracking expedienteTracking) throws Exception {
		// TODO Auto-generated method stub
		expedienteTrackingMapper.actualizarExpedienteTrackingxFacturado(expedienteTracking);
	}
	
	
	public boolean insertarTrackingGeneral(Expediente _ex, ExpedienteTracking expTrack,Integer _idUsuario, Integer _idEstado ){
		Boolean resultado = Boolean.FALSE;
		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		
		ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
		ExpedienteMapper daoObj4 = (ExpedienteMapper) sqlSession.getMapper(ExpedienteMapper.class);
		EstadosMapper daoObj6 = (EstadosMapper) sqlSession.getMapper(EstadosMapper.class);
		LogMapper daoObj5 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		Log log = new Log();
		
		expTrack.setExpediente_id(_ex.getExpediente_id());
		expTrack.setFecha_registro(new Date());
		expTrack.setIdusuario(_idUsuario);
		expTrack.setIdusuario_registro(expTrack.getIdusuario());
		expTrack.setId_estado(_idEstado);
		expTrack.setCondicion(_ex.getCondicion());
		expTrack.setFecha_recepcion(_ex.getFecha_recepcion());
		
		try {
			daoObj3.crearExpedienteTracking(expTrack);
			log.setAccion("INSERT");
			log.setDescripcion("Se crea Tracking del Nro de Expediente "+ _ex.getNro_expediente());
			daoObj5.insertLog(log);
			Estados estados = daoObj6.findById(_idEstado);
			daoObj4.updateEstadoExpedientebyId(_idEstado, _ex.getExpediente_id());
			log.setAccion("UPDATE");
			log.setDescripcion("Se cambió al estado "+estados.getDescripcion()+" el Nro de Expediente "+ _ex.getNro_expediente());
			daoObj5.insertLog(log);
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

	@Override
	public List<ExpedienteTracking> findByIdDetalle(
			Integer id_facturacion_planilla_cabecera) throws Exception {
		// TODO Auto-generated method stub
		return expedienteTrackingMapper.findByIdDetalle(id_facturacion_planilla_cabecera);
	}

}
