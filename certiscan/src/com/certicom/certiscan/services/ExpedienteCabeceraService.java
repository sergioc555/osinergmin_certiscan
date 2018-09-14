package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.ExpedienteCabecera;
import com.certicom.certiscan.mapper.ExpedienteCabeceraMapper;

public class ExpedienteCabeceraService implements ExpedienteCabeceraMapper{

	ExpedienteCabeceraMapper expedienteCabeceraMapper = (ExpedienteCabeceraMapper)ServiceFinder.findBean("expedienteCabeceraMapper");
	
	
	@Override
	public List<ExpedienteCabecera> findAll() throws Exception {
		// TODO Auto-generated method stub
		return expedienteCabeceraMapper.findAll();
	}

	@Override
	public ExpedienteCabecera findByUltimoByUsuarioByProducto(Integer idUsuario, Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteCabeceraMapper.findByUltimoByUsuarioByProducto(idUsuario, idProducto, periodo);
	}
	
	@Override
	public ExpedienteCabecera findByPeriodo(Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteCabeceraMapper.findByPeriodo(periodo);
	}
	
	@Override
	public void crearExpedienteCabecera(ExpedienteCabecera expedienteCabecera) throws Exception {
		// TODO Auto-generated method stub
		expedienteCabeceraMapper.crearExpedienteCabecera(expedienteCabecera);
	}
	
	
	@Override
	public void eliminarExpedienteCabecera(Integer idcabeceraindecopy) throws Exception {
		// TODO Auto-generated method stub
		expedienteCabeceraMapper.eliminarExpedienteCabecera(idcabeceraindecopy);
	}

	@Override
	public ExpedienteCabecera findByPeriodoByProducto(
			Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteCabeceraMapper.findByPeriodoByProducto(idProducto, periodo);
	}

	@Override
	public void actualizarRegistrosAsignados(Integer idExpedienteCabecera,Integer numeroAsignados) throws Exception {
		// TODO Auto-generated method stub
		expedienteCabeceraMapper.actualizarRegistrosAsignados(idExpedienteCabecera, numeroAsignados);
	}

	@Override
	public List<ExpedienteCabecera> findByPAGINATOR(Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, String tipoBase) throws Exception {
		// TODO Auto-generated method stub
		if(sortOrder!=null){
			 if(sortOrder.equals("ASCENDING"))
				 sortOrder="ASC";
			 else
				 sortOrder="DESC";
		}
		return expedienteCabeceraMapper.findByPAGINATOR(first, pageSize, filters, sortField, sortOrder, tipoBase);
	}
	
	@Override
	public List<ExpedienteCabecera> findByPAGINATORxUser(Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, String tipoBase, Integer idusuario, Integer id_negocio, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		if(sortOrder!=null){
			 if(sortOrder.equals("ASCENDING"))
				 sortOrder="ASC";
			 else
				 sortOrder="DESC";
		}
		System.out.println("entro en este metodo " + idusuario);
		
		return expedienteCabeceraMapper.findByPAGINATORxUser(first, pageSize, filters, sortField, sortOrder, tipoBase, idusuario,id_negocio,periodo);
	}

	@Override
	public Integer getCountPAGINATOR(Map<String, Object> filters,  String tipoBase)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteCabeceraMapper.getCountPAGINATOR(filters, tipoBase);
	}

	@Override
	public Integer getCountPAGINATORxUser(Map<String, Object> filters,  String tipoBase, Integer idusuario, Integer id_negocio, Date periodo)
			throws Exception {
		return expedienteCabeceraMapper.getCountPAGINATORxUser(filters, tipoBase, idusuario,id_negocio,periodo);
	}

	@Override
	public void actualizarRegistrosExcluidosBanco(Integer idExpedienteCabecera,
			Integer numeroexcluidobanco) throws Exception {
		// TODO Auto-generated method stub
		expedienteCabeceraMapper.actualizarRegistrosExcluidosBanco(idExpedienteCabecera, numeroexcluidobanco);
	}


	

}
