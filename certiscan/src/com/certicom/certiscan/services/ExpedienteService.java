package com.certicom.certiscan.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteProducto;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.mapper.DocumentoPaginaMapper;
import com.certicom.certiscan.mapper.ExpedienteDocumentoMapper;
import com.certicom.certiscan.mapper.ExpedienteMapper;
import com.certicom.certiscan.mapper.ExpedienteTrackingMapper;
import com.certicom.certiscan.mapper.LogMapper;

import src.com.certicom.certiscan.utils.DateBean;
import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.VGeneric;

public class ExpedienteService implements ExpedienteMapper {

	ExpedienteMapper expedienteMapper = (ExpedienteMapper) ServiceFinder.findBean("expedienteMapper");

	@Override
	public Expediente findById(Integer expedienteId) throws Exception {
		return expedienteMapper.findById(expedienteId);
	}

	@Override
	public List<Expediente> findAll() throws Exception {
		return expedienteMapper.findAll();
	}

	@Override
	public List<Expediente> findByPeriodo(Date periodo) throws Exception {
		return expedienteMapper.findByPeriodo(periodo);
	}

	@Override
	public List<Expediente> buscarExpedientePorPeriodoPorProducto(Date periodo, Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.buscarExpedientePorPeriodoPorProducto(periodo, id_producto);
	}

	@Override
	public void crearExpediente(Expediente expediente) throws Exception {
		 expedienteMapper.crearExpediente(expediente);
	}
	
	public boolean insertExpediente(Expediente expediente){
		Boolean resultado = Boolean.FALSE;
		Integer _id = 0;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteMapper daoObj = (ExpedienteMapper) sqlSession.getMapper(ExpedienteMapper.class);
		
		try {
			 daoObj.crearExpediente(expediente);
			System.out.println("id " +expediente.getExpediente_id());
		}catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
			sqlSession.rollback();
		} finally {
			System.out.println("Espere unos segundos...");
			sqlSession.close();
		}
		
		return resultado;
	}
	
	public void updateBatchExpedienteEstados(List<Expediente> listExpediente){
		
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteMapper daoObj = (ExpedienteMapper) sqlSession.getMapper(ExpedienteMapper.class);
		
		try {
		
			for (Expediente ex : listExpediente) {
				daoObj.updateEstados(ex.getId_estado(), ex.getExpediente_id(), ex.getId_lote(), ex.getCod_lote());
			}
		
		}catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean updateBatchExpediente(List<Expediente> listExpediente,Integer _idusuario, Log log){
		
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ExpedienteTrackingMapper daoObj3 = (ExpedienteTrackingMapper) sqlSession.getMapper(ExpedienteTrackingMapper.class);
		ExpedienteMapper daoObj4 = (ExpedienteMapper) sqlSession.getMapper(ExpedienteMapper.class);
		LogMapper daoObj5 = (LogMapper) sqlSession.getMapper(LogMapper.class);
		try {
			
			int cont_ed = 0;
			
			for (Expediente ex : listExpediente) {
				
				cont_ed =cont_ed+1;
				ExpedienteTracking et = new ExpedienteTracking();
				et.setExpediente_id(ex.getExpediente_id());
				et.setFecha_registro(new Date());
				et.setIdusuario(_idusuario);
				et.setIdusuario_registro(et.getIdusuario());
				et.setId_estado(Constante.EST_SUBIO_ARCHIVOS);
				et.setCondicion(ex.getCondicion());
				et.setFecha_recepcion(ex.getFecha_recepcion());				
				
				daoObj3.crearExpedienteTracking(et);
				log.setAccion("UPDATE");
		        log.setDescripcion("Se cre� el tracking del expediente: "+ex.getNro_expediente() + " para el estado ENVIADO A APENS");
		        daoObj5.insertLog(log);
				
								
				daoObj4.updateEstadoExpedientebyId(Constante.EST_SUBIO_ARCHIVOS, ex.getExpediente_id());		
				log.setAccion("UPDATE");
		        log.setDescripcion("Se transfiri� el expediente: "+ex.getNro_expediente() + " al estado ENVIADO A APENS");
		        daoObj5.insertLog(log);
				
			}			
			
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
	
	@Override
	public Expediente findByNroExpediente(String nroexpediente)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByNroExpediente(nroexpediente);
	}

	@Override
	public Expediente findByNroExp(String nro_Expediente) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByNroExp(nro_Expediente);
	}

	@Override
	public Expediente findByNroExpedientePrepToDigi(String nroexpediente)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByNroExpedientePrepToDigi(nroexpediente);
	}

	@Override
	public void updateEstadoExpedientebyId(Integer id_estado,
			Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		expedienteMapper.updateEstadoExpedientebyId(id_estado, expediente_id);
	}

	@Override
	public Expediente findByNroExpedientePrepToCalidad(String nroexpediente)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByNroExpedientePrepToCalidad(nroexpediente);
	}

	@Override
	public List<Expediente> consultaMovimientoExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaMovimientoExpediente(filter);
	}

	@Override
	public List<Expediente> buscarExpedientes(Integer id_oficina)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.buscarExpedientes(id_oficina);
	}
	@Override
	public List<Expediente> procesaFacturacion(Expediente filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.procesaFacturacion(filter);
	}

	@Override
	public void eliminarExpediente(Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		expedienteMapper.eliminarExpediente(expediente_id);
	}

	public List<Expediente> buscarExpedienteTransferencia(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.buscarExpedienteTransferencia(filter);
	}

	@Override
	public List<Expediente> procesaPlanilla(Expediente filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.procesaPlanilla(filter);
	}

	@Override
	public void updateRutaCarpetaExpedientebyId(Expediente exp)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteMapper.updateRutaCarpetaExpedientebyId(exp);
	}

	@Override
	public Expediente findByNroExpNroSol(String nro_expediente,
			String nro_solicitud) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByNroExpNroSol(nro_expediente, nro_solicitud);
	}

	@Override
	public Expediente findByNroExpedienteNroSolicitudPrepToCalidad(
			String nroexpediente, String nrosolicitud) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByNroExpedienteNroSolicitudPrepToCalidad(nroexpediente, nrosolicitud);
	}

	@Override
	public List<Expediente> consultaPreparacionExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaPreparacionExpediente(filter);
	}

	@Override
	public List<Expediente> consultaDigitalizacionExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaDigitalizacionExpediente(filter);
	}

	@Override
	public List<Expediente> consultaCalidadExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaCalidadExpediente(filter);
	}

	@Override
	public List<Expediente> consultaCalidadFedatarioExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaCalidadFedatarioExpediente(filter);
	}

	@Override
	public List<Expediente> consultaReprocesoExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaReprocesoExpediente(filter);
	}

	@Override
	public List<Expediente> consultaReagruparExpediente(Expediente filter)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.consultaReagruparExpediente(filter);
	}

	@Override
	public void updateEstados(Integer id_estado, Integer expediente_id,
			Integer id_lote, String cod_lote) throws Exception {
		// TODO Auto-generated method stub
		expedienteMapper.updateEstados(id_estado, expediente_id, id_lote, cod_lote);
	}

	@Override
	public List<Expediente> findByIdLote(Integer id_lote) throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.findByIdLote(id_lote);
	}

	@Override
	public List<Expediente> obtenerCarpetas(Expediente expediente)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteMapper.obtenerCarpetas(expediente);
	}
	

}