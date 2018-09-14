package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.Facturacion;
import com.certicom.certiscan.mapper.FacturacionMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.ExpedienteItem;

public class FacturacionService implements FacturacionMapper{

	FacturacionMapper facturacionMapper = (FacturacionMapper)ServiceFinder.findBean("facturacionMapper");

	@Override
	public Facturacion findById(Integer facturacionId) throws Exception {
		return facturacionMapper.findById(facturacionId);
	}


	@Override
	public List<Facturacion> findAll() throws Exception {
		return facturacionMapper.findAll();
	}

	@Override
	public List<Facturacion> findByPeriodo(Date periodo) throws Exception {
		return facturacionMapper.findByPeriodo(periodo);
	}

	@Override
	public List<Facturacion> buscarFacturacionPorPeriodoPorProducto(Date periodo, Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.buscarFacturacionPorPeriodoPorProducto(periodo, id_producto);
	}
	
	@Override
	public void crearFacturacionALL(Facturacion facturacion) throws Exception {
		 facturacionMapper.crearFacturacionALL(facturacion);
	}

	@Override
	public List<Facturacion> buscarFacturacionPorPeriodo(Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.buscarFacturacionPorPeriodo(periodo);
	}


	@Override
	public void crearFacturacionALL2(Facturacion facturacion) throws Exception {
		// TODO Auto-generated method stub
		 facturacionMapper.crearFacturacionALL2(facturacion);
	}


	@Override
	public List<Facturacion> getUsuariosbyFacturacionProducto(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getUsuariosbyFacturacionProducto(filter);
	}


	@Override
	public List<Facturacion> getFacturacionbyOrigenCabecera(
			Integer id_cabecera, String origen) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getFacturacionbyOrigenCabecera(id_cabecera, origen);
	}
	
	@Override
	public List<Facturacion> getFacturacionbyCabecera(Integer id_cabecera)
			throws Exception {
		return facturacionMapper.getFacturacionbyCabecera(id_cabecera);
	}


	@Override
	public void actualizarFacturacion(Facturacion facturacion) throws Exception {
		facturacionMapper.actualizarFacturacion(facturacion);
	}


	@Override
	public void eliminarFacturacion(Integer facturacion_id) throws Exception {
		facturacionMapper.eliminarFacturacion(facturacion_id);
	}
	
	@Override
	public void eliminarFacturacionxIdFactCabecera(Integer id_facturacion_cabecera) throws Exception {
		facturacionMapper.eliminarFacturacionxIdFactCabecera(id_facturacion_cabecera);
	}


	@Override
	public void insertarDescuento(Facturacion facturacion) throws Exception {
		facturacionMapper.insertarDescuento(facturacion);
	}


	@Override
	public List<Facturacion> getComplementos(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getComplementos(filter);
	}


	@Override
	public List<Facturacion> getDescuentos(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getDescuentos(filter);
	}


	@Override
	public Integer getCountPAGINATOR(Map<String, Object> filters,
			Integer id_cabecera) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getCountPAGINATOR(filters, id_cabecera);
	}


	@Override
	public List<Facturacion> getFacturacionbyCabecera2(Integer first,
			Integer pageSize, Map<String, Object> filters, String sortField,
			String sortOrder, Integer id_cabecera) throws Exception {
		if(sortOrder!=null){
			 if(sortOrder.equals("ASCENDING"))
				 sortOrder="ASC";
			 else
				 sortOrder="DESC";
		}
		return facturacionMapper.getFacturacionbyCabecera2(first, pageSize, filters, sortField, sortOrder, id_cabecera);
	}

	@Override
	public List<Facturacion> obtenerFacturacionbyCabecera(Integer id_cabecera)
			throws Exception {
		return facturacionMapper.obtenerFacturacionbyCabecera(id_cabecera);
	}

	@Override
	public void modificarFacturacion(Facturacion facturacion) throws Exception {
		facturacionMapper.modificarFacturacion(facturacion);
	}

	@Override
	public Facturacion verifySetSupervisorByCabecera(
			Integer id_facturacion_cabecera, Integer idusuario, Integer idcargo)
			throws Exception {
		return facturacionMapper.verifySetSupervisorByCabecera(id_facturacion_cabecera, idusuario, idcargo);
	}


	@Override
	public List<Facturacion> getMontoDesembolsadoByFacturacionProducto(
			ExpedienteFilter expedienteFilter) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getMontoDesembolsadoByFacturacionProducto(expedienteFilter);
	}


	@Override
	public void updateEstadoevaluacion(Integer facturacion_id,
			boolean estado_validacion) throws Exception {
		// TODO Auto-generated method stub
		facturacionMapper.updateEstadoevaluacion(facturacion_id, estado_validacion);
	}


	@Override
	public void actualizarComisionBackOffice(Facturacion facturacion)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionMapper.actualizarComisionBackOffice(facturacion);
	}


	@Override
	public void actualizarComisionEjecutivoCampo(Facturacion facturacion)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionMapper.actualizarComisionEjecutivoCampo(facturacion);
	}


	@Override
	public void actualizarComisionSupervisorCampo(Facturacion facturacion)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionMapper.actualizarComisionSupervisorCampo(facturacion);
	}


	@Override
	public List<Facturacion> getUsuariosbyFacturacionProducto2(
			ExpedienteFilter expedienteFilter) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getUsuariosbyFacturacionProducto2(expedienteFilter);
	}


	@Override
	public List<Facturacion> getUsuariosbyFacturacionCuartiles(
			ExpedienteFilter expedienteFilter) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getUsuariosbyFacturacionCuartiles(expedienteFilter);
	}


	@Override
	public List<Facturacion> listarProductosFacturadosByAnio(String anio)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.listarProductosFacturadosByAnio(anio);
	}


	@Override
	public List<Facturacion> listarTotalFacturadosPagadoByAnio(String anio)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.listarTotalFacturadosPagadoByAnio(anio);
	}


	@Override
	public List<Facturacion> listarTotalFacturadosxEjecutivoByAnio(
			ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.listarTotalFacturadosxEjecutivoByAnio(filter);
	}


	@Override
	public List<Facturacion> getDesembolsado(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getDesembolsado(filter);
	}


	@Override
	public List<Facturacion> getDesembolsadoDetallado(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getDesembolsadoDetallado(filter);
	}


	@Override
	public List<Facturacion> listarProductosFacturadosByAnioByProductoByNegocio(
			ExpedienteFilter expedienteFilter) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.listarProductosFacturadosByAnioByProductoByNegocio(expedienteFilter);
	}


	@Override
	public List<ExpedienteItem> getRankingEjecutivoPH(Integer idproducto,
			Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getRankingEjecutivoPH(idproducto, periodo);
	}


	@Override
	public List<ExpedienteItem> getRankingEmpresaPH(Integer idproducto, Date periodo)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getRankingEmpresaPH(idproducto, periodo);
	}


	@Override
	public List<ExpedienteItem> getCantidadEmpresaPH(Integer idproducto,
			Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getCantidadEmpresaPH(idproducto, periodo);
	}


	@Override
	public List<ExpedienteItem> getResPlanConsolidadoBP(Integer idproducto,
			Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getResPlanConsolidadoBP(idproducto, periodo);
	}


	@Override
	public List<ExpedienteItem> getResPlanConsolidadPYMES(Integer idnegocio,
			Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return facturacionMapper.getResPlanConsolidadPYMES(idnegocio, periodo);
	}


	
	
}