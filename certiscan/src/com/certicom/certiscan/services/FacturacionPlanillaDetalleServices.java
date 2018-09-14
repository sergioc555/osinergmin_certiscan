package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.FacturacionPlanillaDetalle;
import com.certicom.certiscan.mapper.FacturacionPlanillaDetalleMapper;

public class FacturacionPlanillaDetalleServices implements FacturacionPlanillaDetalleMapper{

	FacturacionPlanillaDetalleMapper facturacionPlanillaDetalleMapper = (FacturacionPlanillaDetalleMapper)ServiceFinder.findBean("facturacionPlanillaDetalleMapper");

	@Override 
	public FacturacionPlanillaDetalle findById(
			Integer codFacturacionPlanillaDetalle) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findById(codFacturacionPlanillaDetalle);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findAll() throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findAll();
	}

	@Override
	public void crearFacturacionPlanillaDetalle(
			FacturacionPlanillaDetalle facturacionPlanillaDetalle)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaDetalleMapper.crearFacturacionPlanillaDetalle(facturacionPlanillaDetalle);
	}

	@Override
	public void actualizarFacturacionPlanillaDetalle(
			FacturacionPlanillaDetalle facturacionPlanillaDetalle)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaDetalleMapper.actualizarFacturacionPlanillaDetalle(facturacionPlanillaDetalle);
	}

	@Override
	public void eliminarFacturacionPlanillaDetalle(
			Integer codFacturacionPlanillaDetalle) throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaDetalleMapper.eliminarFacturacionPlanillaDetalle(codFacturacionPlanillaDetalle);
	}
	
	public void insertBatchFacturacionPlanillaDetalle(
			List<FacturacionPlanillaDetalle> fpDetalle) throws Exception {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		FacturacionPlanillaDetalleMapper daoObj= (FacturacionPlanillaDetalleMapper)sqlSession.getMapper(FacturacionPlanillaDetalleMapper.class);

		try{
		
			//sqlSession.commit(true);
			for (FacturacionPlanillaDetalle fpd:fpDetalle) {
				daoObj.crearFacturacionPlanillaDetalle(fpd);
				
			}
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		//return resultado;
		//facturacionPlanillaDetalleMapper.eliminarFacturacionPlanillaDetalle(codFacturacionPlanillaDetalle);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findByIdCiclo(Integer codCiclo)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findByIdCiclo(codCiclo);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findConsolidadoByIdCiclo(
			Integer codCiclo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findConsolidadoByIdCiclo(codCiclo);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findConsolidadoPreliminarByIdCiclo(
			Integer codCiclo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findConsolidadoPreliminarByIdCiclo(codCiclo);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findByIdCicloDetalle(
			Integer codCiclo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findByIdCicloDetalle(codCiclo);
	}

	@Override
	public List<FacturacionPlanillaDetalle> obtenerFueraPlanilla(FacturacionPlanillaDetalle fpd)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.obtenerFueraPlanilla(fpd);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findConsolidadoPlanillaByIdCiclo(
			Integer codCiclo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findConsolidadoPlanillaByIdCiclo(codCiclo);
	}

	@Override
	public void eliminarFacturacionPlanillaDetalleXCabecera(
			Integer codFacturacionPlanillaDetalle) throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaDetalleMapper.eliminarFacturacionPlanillaDetalleXCabecera(codFacturacionPlanillaDetalle);
	}

	@Override
	public List<FacturacionPlanillaDetalle> findByIdCabeceraDetalle(
			Integer idCabecera) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaDetalleMapper.findByIdCabeceraDetalle(idCabecera);
	}

}
