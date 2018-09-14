package com.certicom.certiscan.services;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Tarifa;
import com.certicom.certiscan.mapper.TarifaMapper;

public class TarifaService implements TarifaMapper{

	TarifaMapper tarifaMapper = (TarifaMapper)ServiceFinder.findBean("tarifaMapper");

	@Override
	public Tarifa findById(Integer cod_expediente) throws Exception {
		return tarifaMapper.findById(cod_expediente);
	}

	@Override
	public List<Tarifa> findAll() throws Exception {
		return tarifaMapper.findAll();
	}

	@Override
	public void crearTarifa(Tarifa tarifa) throws Exception {
		tarifaMapper.crearTarifa(tarifa);
	}

	@Override
	public void actualizarTarifa(Tarifa tarifa) throws Exception {
		// TODO Auto-generated method stub
		tarifaMapper.actualizarTarifa(tarifa);
	}

	@Override
	public void eliminarTarifa(Integer cod_tarifa) throws Exception {
		// TODO Auto-generated method stub
		tarifaMapper.eliminarTarifa(cod_tarifa);
	}

	public void insertBatchTarifa(List<Tarifa> listTarcom)
			throws Exception {
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		TarifaMapper daoObj= (TarifaMapper)sqlSession.getMapper(TarifaMapper.class);
		
		try{
			
			//sqlSession.commit(true);
			for (Tarifa tarcom:listTarcom) {
				daoObj.crearTarifa(tarcom);
				
			}
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		// TODO Auto-generated method stub
		//tableroComisionesMapper.crearTarifa(tabcom);
	}

	@Override
	public List<Tarifa> listarTarifa(Integer codTipoCiclo, Integer codCiclo)
			throws Exception {
		// TODO Auto-generated method stub
		return tarifaMapper.listarTarifa(codTipoCiclo, codCiclo);
	}

	@Override
	public List<Tarifa> findByIdCiclo(Integer codIdCiclo) throws Exception {
		// TODO Auto-generated method stub
		return tarifaMapper.findByIdCiclo(codIdCiclo);
	}
	
}
