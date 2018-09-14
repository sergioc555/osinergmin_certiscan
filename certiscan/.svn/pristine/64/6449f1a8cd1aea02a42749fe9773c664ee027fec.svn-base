package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.BaseIndecopy;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.mapper.BaseIndecopyMapper;
import com.certicom.certiscan.mapper.ExpedienteMapper;

public class CargaIndecopyService implements BaseIndecopyMapper{

	BaseIndecopyMapper baseMapper = (BaseIndecopyMapper)ServiceFinder.findBean("baseIndecopyMapper");
	
	@Override
	public BaseIndecopy findById(Integer baseIndecopyId) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findById(baseIndecopyId);
	}
	
	@Override
	public BaseIndecopy findByUltimo() throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findByUltimo();
	}

	@Override
	public List<BaseIndecopy> findAll() throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findAll();
	}

	@Override
	public void crearBaseIndecopy(BaseIndecopy baseIndecopy) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.crearBaseIndecopy(baseIndecopy);
	}
	
	public boolean insertBatchBaseIndecopy(List<BaseIndecopy> lista, Integer idCabeceraBaseIndecopy, Date periodo) {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		BaseIndecopyMapper daoObj= (BaseIndecopyMapper)sqlSession.getMapper(BaseIndecopyMapper.class);

		try{
		
			//sqlSession.commit(true);
			for (BaseIndecopy bi:lista) {
				bi.setIdCabeceraIndecopy(idCabeceraBaseIndecopy);
				bi.setPeriodo(periodo);
				daoObj.crearBaseIndecopy(bi);
				
			}
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		return resultado;
	}
	

	@Override
	public void eliminarBaseIndecopy(Integer baseIndecopyId) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.eliminarBaseIndecopy(baseIndecopyId);
	}

	@Override
	public List<BaseIndecopy> findByPeriodo(Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findByPeriodo(periodo);
	}

	
	@Override
	public List<BaseIndecopy> findByPeriodoLimitado(Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findByPeriodoLimitado(periodo);
	}
	
	@Override
	public List<BaseIndecopy> findByAnioMes(Integer annio, Integer mes) throws Exception{
		// TODO Auto-generated method stub
		return baseMapper.findByAnioMes(annio, mes);
	}
	
	@Override
	public List<BaseIndecopy> findByAnioMesPAGINATOR(Integer annio, Integer mes, Integer  first, Integer pageSize,  Map<String,Object> filters, String sortField, String sortOrder)
			throws Exception {
		// TODO Auto-generated method stub
		if(sortOrder!=null){
			if(sortOrder.equals("ASCENDING"))
				 sortOrder="ASC";
			 else
				 sortOrder="DESC";
		}
		return baseMapper.findByAnioMesPAGINATOR(annio, mes, first, pageSize,  filters, sortField, sortOrder);
	}
	
	@Override
	public Integer getCountBaseIndecopyPAGINATOR(Integer annio, Integer mes, Map<String, Object> filters) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.getCountBaseIndecopyPAGINATOR(annio, mes, filters);
	}
	
	@Override
	public Integer getCountBaseIndecopy(Integer annio, Integer mes) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.getCountBaseIndecopy(annio, mes);
	}
	
	@Override
	public List<BaseIndecopy> findByValor(String valor) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findByValor(valor);
	}

	@Override
	public void eliminarBaseIndecopyByCabecera(Integer idCabeceraIndecopy)
			throws Exception {
		// TODO Auto-generated method stub
		baseMapper.eliminarBaseIndecopyByCabecera(idCabeceraIndecopy);
	}

	@Override
	public List<BaseIndecopy> findByIdCabecera(Integer idCabeceraIndecopy)
			throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findByIdCabecera(idCabeceraIndecopy);
	}

	
	public List<BaseIndecopy> findByIdCabeceraBATH(Integer idCabeceraIndecopy){
		// TODO Auto-generated method stub
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		BaseIndecopyMapper daoObj= (BaseIndecopyMapper)sqlSession.getMapper(BaseIndecopyMapper.class);
		List<BaseIndecopy> lista = null;
		try{
			lista=daoObj.findByIdCabecera(idCabeceraIndecopy);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			sqlSession.close();
		}
		return lista;
	}

	@Override
	public BaseIndecopy findByNumeroByCabecera(Integer idcabeceraindecopy,
			String valor) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.findByNumeroByCabecera(idcabeceraindecopy, valor);
	}
	

}
