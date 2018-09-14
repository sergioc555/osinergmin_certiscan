package com.certicom.certiscan.services;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.ExpedienteIncidencia;
import com.certicom.certiscan.domain.Estado;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.mapper.ExpedienteIncidenciaMapper;
import com.certicom.certiscan.mapper.EstadoMapper;
import com.certicom.certiscan.mapper.LogMapper;

public class ExpedienteIncidenciaServices implements ExpedienteIncidenciaMapper{

	ExpedienteIncidenciaMapper expedienteincidenciaMapper = (ExpedienteIncidenciaMapper)ServiceFinder.findBean("expedienteincidenciaMapper");

	@Override
	public ExpedienteIncidencia findById(Integer codigoExpIncidencia)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteincidenciaMapper.findById(codigoExpIncidencia);
	}

	@Override
	public List<ExpedienteIncidencia> findAll() throws Exception {
		// TODO Auto-generated method stub
		return expedienteincidenciaMapper.findAll();
	}

	@Override
	public void crearExpedienteIncidencia(ExpedienteIncidencia expincidencia)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteincidenciaMapper.crearExpedienteIncidencia(expincidencia);
	}

	@Override
	public void actualizarExpedienteIncidencia(
			ExpedienteIncidencia expincidencia) throws Exception {
		// TODO Auto-generated method stub
		expedienteincidenciaMapper.actualizarExpedienteIncidencia(expincidencia);
	}

	@Override
	public void eliminarExpedienteIncidencia(Integer codigoExpIncidencia)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteincidenciaMapper.eliminarExpedienteIncidencia(codigoExpIncidencia);
	}

	@Override
	public List<ExpedienteIncidencia> listarIncidencias(Integer expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteincidenciaMapper.listarIncidencias(expediente_id);
	}	
	
	public void insertBatchExpedienteIncidencia(
			List<ExpedienteIncidencia> listExpInc, String nro_expediente, Log log) throws Exception {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteIncidenciaMapper daoObj= (ExpedienteIncidenciaMapper)sqlSession.getMapper(ExpedienteIncidenciaMapper.class);
		EstadoMapper daoObj3 = (EstadoMapper)sqlSession.getMapper(EstadoMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		try{
		
			//sqlSession.commit(true);
			for (ExpedienteIncidencia expInc:listExpInc) {
				
				Estado inci = daoObj3.findById(expInc.getId_incidencia());
				daoObj.crearExpedienteIncidencia(expInc);
				log.setAccion("INSERT");
				log.setDescripcion("Se agregó la incidencia "+ inci.getDescripcion()+" al Nro de Expediente "+nro_expediente);
				daoObj4.insertLog(log);
				
			}
				//daoObj.actualizarEstadoExpediente(expediente);
				
			
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		//return resultado;
		
	}
	
	public void updateBatchExpedienteIncidencia(
			List<ExpedienteIncidencia> listExpInc, String nro_expediente, Log log) throws Exception {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteIncidenciaMapper daoObj= (ExpedienteIncidenciaMapper)sqlSession.getMapper(ExpedienteIncidenciaMapper.class);
		EstadoMapper daoObj3 = (EstadoMapper)sqlSession.getMapper(EstadoMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		try{
		
			//sqlSession.commit(true);
			for (ExpedienteIncidencia expInc:listExpInc) {
				Estado inci = daoObj3.findById(expInc.getId_incidencia());
				daoObj.actualizarObsExpedienteIncidencia(expInc);
				log.setAccion("UPDATE");
				log.setDescripcion("Se actualizó la incidencia "+ inci.getDescripcion()+" al Nro de Expediente "+nro_expediente);
				daoObj4.insertLog(log);
			}
				//daoObj.actualizarEstadoExpediente(expediente);
				
			
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		//return resultado;
		
	}
	
	public void deleteBatchExpedienteIncidencia(
			List<Integer> listid, String nro_expediente, Log log) throws Exception {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteIncidenciaMapper daoObj= (ExpedienteIncidenciaMapper)sqlSession.getMapper(ExpedienteIncidenciaMapper.class);
		EstadoMapper daoObj3 = (EstadoMapper)sqlSession.getMapper(EstadoMapper.class);
		LogMapper daoObj4 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		//Log log = new Log();
		
		try{
		
			//sqlSession.commit(true);
			for (Integer id:listid) {
				ExpedienteIncidencia expinci = daoObj.findById(id);
				Estado inci = daoObj3.findById(expinci.getId_incidencia());
				daoObj.eliminarExpedienteIncidencia(id.intValue());
				log.setAccion("UPDATE");
				log.setDescripcion("Se eliminó la incidencia "+ inci.getDescripcion()+" del Nro de Expediente "+nro_expediente);
				daoObj4.insertLog(log);
				
			}
				//daoObj.actualizarEstadoExpediente(expediente);
				
			
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		//return resultado;
		
	}

	@Override
	public void actualizarObsExpedienteIncidencia(
			ExpedienteIncidencia expedienteIncidencia) throws Exception {
		// TODO Auto-generated method stub
		expedienteincidenciaMapper.actualizarObsExpedienteIncidencia(expedienteIncidencia);
	}

	@Override
	public List<ExpedienteIncidencia> findAll2() throws Exception {
		// TODO Auto-generated method stub
		return expedienteincidenciaMapper.findAll2();
	}

	@Override
	public List<ExpedienteIncidencia> listarIncidenciasIndicadores(
			Integer expediente_id, Integer id_indicador_call, String proceso) throws Exception {
		// TODO Auto-generated method stub
		return expedienteincidenciaMapper.listarIncidenciasIndicadores(expediente_id, id_indicador_call, proceso);
	}

	@Override
	public List<ExpedienteIncidencia> listarIncidenciasExp(
			Integer expediente_id, Integer id_indicador_call, String proceso) throws Exception {
		// TODO Auto-generated method stub
		return expedienteincidenciaMapper.listarIncidenciasExp(expediente_id, id_indicador_call, proceso);
	}
	
}
