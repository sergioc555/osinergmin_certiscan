package com.certicom.certiscan.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.ProyeccionVentas;
import com.certicom.certiscan.mapper.ProyeccionVentasMapper;

public class ProyeccionVentasServices implements ProyeccionVentasMapper {

	ProyeccionVentasMapper proyeccionVentasMapper = (ProyeccionVentasMapper) ServiceFinder.findBean("proyeccionVentasMapper");

	
	@Override
	public List<ProyeccionVentas> listaProyeccionVentas(ProyeccionVentas pv) throws Exception {
		return proyeccionVentasMapper.listaProyeccionVentas(pv);
	}


	@Override
	public void crearProyeccionVentas(ProyeccionVentas pv) throws Exception {
		// TODO Auto-generated method stub
		proyeccionVentasMapper.crearProyeccionVentas(pv);
	}
	

	public void insertBatchProyeccionesVentas(List<ProyeccionVentas> listpv)
			throws Exception {
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ProyeccionVentasMapper daoObj= (ProyeccionVentasMapper)sqlSession.getMapper(ProyeccionVentasMapper.class);
		
		try{
			
			//sqlSession.commit(true);
			for (ProyeccionVentas pv:listpv) {
				daoObj.crearProyeccionVentas(pv);
				
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
		//tableroComisionesMapper.crearTableroComisiones(tabcom);
	}
	
	public void updateBatchProyeccionesVentas(List<ProyeccionVentas> listpv)
			throws Exception {
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ProyeccionVentasMapper daoObj= (ProyeccionVentasMapper)sqlSession.getMapper(ProyeccionVentasMapper.class);
		
		try{
			
			//sqlSession.commit(true);
			for (ProyeccionVentas pv:listpv) {
				if(!pv.getIngresar()){
					daoObj.actualizarProyeccionVenta(pv);
					
				}else {
					daoObj.crearProyeccionVentas(pv);
				}
				
			}
			resultado=Boolean.TRUE;
			//sqlSession.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
			//e.getStackTrace();
			resultado=Boolean.FALSE;
		}finally{
			sqlSession.close();
		}
		
		// TODO Auto-generated method stub
		//tableroComisionesMapper.crearTableroComisiones(tabcom);
	}

	@Override
	public void actualizarProyeccionVenta(ProyeccionVentas pv) throws Exception {
		// TODO Auto-generated method stub
		proyeccionVentasMapper.actualizarProyeccionVenta(pv);
	}


	@Override
	public void eliminarProyeccionVenta(ProyeccionVentas pv) throws Exception {
		// TODO Auto-generated method stub
		proyeccionVentasMapper.eliminarProyeccionVenta(pv);
	}

	

}
