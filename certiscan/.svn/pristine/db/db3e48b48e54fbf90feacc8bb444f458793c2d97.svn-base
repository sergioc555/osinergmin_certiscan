package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.FacturacionCabecera;
import com.certicom.certiscan.mapper.FacturacionCabeceraMapper;

public class FacturacionCabeceraService implements FacturacionCabeceraMapper{

	FacturacionCabeceraMapper facturacionCabeceraMapper = (FacturacionCabeceraMapper)ServiceFinder.findBean("facturacionCabeceraMapper");
	
	
	@Override
	public List<FacturacionCabecera> findAll() throws Exception {
		// TODO Auto-generated method stub
		return facturacionCabeceraMapper.findAll();
	}

	@Override
	public FacturacionCabecera findByUltimoByUsuarioByProducto(Integer idUsuario, Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionCabeceraMapper.findByUltimoByUsuarioByProducto(idUsuario, idProducto, periodo);
	}
	
	@Override
	public FacturacionCabecera findByPeriodo(Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionCabeceraMapper.findByPeriodo(periodo);
	}
	
	@Override
	public void crearFacturacionCabecera(FacturacionCabecera facturacionCabecera) throws Exception {
		// TODO Auto-generated method stub
		facturacionCabeceraMapper.crearFacturacionCabecera(facturacionCabecera);
	}
	
	
	@Override
	public void eliminarFacturacionCabecera(Integer idcabeceraindecopy) throws Exception {
		// TODO Auto-generated method stub
		facturacionCabeceraMapper.eliminarFacturacionCabecera(idcabeceraindecopy);
	}
	
	@Override
	public void eliminarFacturacionCabeceraxId(Integer id_facturacion_cabecera) throws Exception {
		// TODO Auto-generated method stub
		facturacionCabeceraMapper.eliminarFacturacionCabeceraxId(id_facturacion_cabecera);
	}

	@Override
	public FacturacionCabecera findByPeriodoByProducto(
			Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionCabeceraMapper.findByPeriodoByProducto(idProducto, periodo);
	}

	@Override
	public void actualizarRegistrosAsignados(Integer idFacturacionCabecera,Integer numeroAsignados) throws Exception {
		// TODO Auto-generated method stub
		facturacionCabeceraMapper.actualizarRegistrosAsignados(idFacturacionCabecera, numeroAsignados);
	}

	@Override
	public List<FacturacionCabecera> findByPAGINATOR(Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, String tipoBase,Integer id_negocio,Date periodo) throws Exception {
		// TODO Auto-generated method stub
		if(sortOrder!=null){
			 if(sortOrder.equals("ASCENDING"))
				 sortOrder="ASC";
			 else
				 sortOrder="DESC";
		}
		return facturacionCabeceraMapper.findByPAGINATOR(first, pageSize, filters, sortField, sortOrder, tipoBase,id_negocio,periodo);
	}

	@Override
	public Integer getCountPAGINATOR(Map<String, Object> filters,  String tipoBase,Integer id_negocio,Date periodo)
			throws Exception {
		return facturacionCabeceraMapper.getCountPAGINATOR(filters, tipoBase,id_negocio,periodo);
	}

	@Override
	public void crearFacturacionCabecera2(
			FacturacionCabecera facturacionCabecera) throws Exception {
		facturacionCabeceraMapper.crearFacturacionCabecera2(facturacionCabecera);
	}

	@Override
	public Integer getLastInsertId(FacturacionCabecera facturacionCabecera)
			throws Exception {
		return facturacionCabeceraMapper.getLastInsertId(facturacionCabecera);
	}

	@Override
	public List<FacturacionCabecera> verificarCargaByPeriodoxNombre(Date periodo, String nombarchivo)
			throws Exception {
		return facturacionCabeceraMapper.verificarCargaByPeriodoxNombre(periodo, nombarchivo);
	}

	@Override
	public List<FacturacionCabecera> findByNegocioPeriodo(Integer id_negocio,
			Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionCabeceraMapper.findByNegocioPeriodo(id_negocio, periodo);
	}

	

	

}
