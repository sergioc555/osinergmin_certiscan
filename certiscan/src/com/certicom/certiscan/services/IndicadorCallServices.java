package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.IndicadorCall;
import com.certicom.certiscan.mapper.IndicadorCallMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;

public class IndicadorCallServices implements IndicadorCallMapper {

	IndicadorCallMapper indicadorCallMapper = (IndicadorCallMapper) ServiceFinder.findBean("indicadorCallMapper");

	@Override
	public IndicadorCall findById(Integer codigoIndicadorCall) throws Exception {
		return indicadorCallMapper.findById(codigoIndicadorCall);
	}

	@Override
	public List<IndicadorCall> findAll() throws Exception {
		return indicadorCallMapper.findAll();
	}

	@Override
	public List<IndicadorCall> listaIndicadorCallActivo() throws Exception {
		return indicadorCallMapper.listaIndicadorCallActivo();
	}

	@Override
	public void crearIndicadorCall(IndicadorCall indicadorCall) throws Exception {
		indicadorCallMapper.crearIndicadorCall(indicadorCall);
	}

	@Override
	public void actualizarIndicadorCall(IndicadorCall indicadorCall) throws Exception {
		indicadorCallMapper.actualizarIndicadorCall(indicadorCall);
	}

	@Override
	public void eliminarIndicadorCall(Integer id_indicadores_call) throws Exception {
		indicadorCallMapper.eliminarIndicadorCall(id_indicadores_call);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoria(Integer id_categoria_call) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndxCategoria(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> findByCategoria(Integer id_categoria_call) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.findByCategoria(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> getCountIndicadoresxCategoria(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getCountIndicadoresxCategoria(filter);
	}

	@Override
	public List<IndicadorCall> listaIndicadorCallByProducto(Integer idNegocio) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndicadorCallByProducto(idNegocio);
	}

	@Override
	public List<IndicadorCall> getCountExpxIndCallxTipPlanilla(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getCountExpxIndCallxTipPlanilla(filter);
	}

	@Override
	public List<IndicadorCall> distinctIndCategorias(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.distinctIndCategorias(filter);
	}

	@Override
	public List<IndicadorCall> srptCountIndicadorexTipoPlanilla(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.srptCountIndicadorexTipoPlanilla(filter);
	}

	@Override
	public List<IndicadorCall> getCountExpxIndCallxTerritorio(ExpedienteFilter filter) throws Exception {
		return indicadorCallMapper.getCountExpxIndCallxTerritorio(filter);
	}

	@Override
	public List<IndicadorCall> getCountExpxIndCallxPrioridad(ExpedienteFilter filter) throws Exception {
		return indicadorCallMapper.getCountExpxIndCallxPrioridad(filter);
	}

	public List<IndicadorCall> listByIdCatecoriaCall(Integer id) throws Exception {
		return indicadorCallMapper.listByIdCatecoriaCall(id);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoriaSupervisorPyme(Integer id_categoria_call) throws Exception {
		return indicadorCallMapper.listaIndxCategoriaSupervisorPyme(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoriaBackPyme(Integer id_categoria_call) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndxCategoriaBackPyme(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoriaSupervisorHipotecario(Integer id_categoria_call) throws Exception {
		return indicadorCallMapper.listaIndxCategoriaSupervisorHipotecario(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> getxFamiliaResultadosFeedBack(Integer id_producto) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxFamiliaResultadosFeedBack(id_producto);
	}

	@Override
	public List<IndicadorCall> getxSubFamiliaResultadosFeedBack(
			Integer id_indicadores_call) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxSubFamiliaResultadosFeedBack(id_indicadores_call);
	}

	@Override
	public List<IndicadorCall> getxFamiliaResultadosFeedBack2(
			Integer id_producto) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxFamiliaResultadosFeedBack2(id_producto);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoriaBackLog(
			Integer id_categoria_call) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndxCategoriaBackLog(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> getxIndicadoresAceptaProducto(Integer id_producto) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxIndicadoresAceptaProducto(id_producto);
	}

	@Override
	public List<IndicadorCall> getxIndicadoresxPadre(Integer id_estado_padre) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxIndicadoresxPadre(id_estado_padre);
	}

	@Override
	public List<IndicadorCall> getxIndicadoresVisita() {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxIndicadoresVisita();
	}

	@Override
	public List<IndicadorCall> listaIndxCategoriaConsesionario(
			Integer id_categoria_call) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndxCategoriaConsesionario(id_categoria_call);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoriaBackLog2(
			Integer id_categoria_call) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndxCategoriaBackLog2(id_categoria_call);
	}

	@Override
	public List<String> listaIndicadorCallByProductos(
			Integer idProducto, Integer idCategoriaCall) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndicadorCallByProductos(idProducto, idCategoriaCall);
	}

	@Override
	public List<IndicadorCall> getxIndicadoresActivosByProducto(
			Integer id_producto) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getxIndicadoresActivosByProducto(id_producto);
	}

	@Override
	public List<String> listaIndicadorCallByNegocios(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndicadorCallByNegocios(filter);
	}

	@Override
	public List<IndicadorCall> getProceso(
			Integer id_categoria_call) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getProceso(id_categoria_call);
	}
	
	@Override
	public List<IndicadorCall> getTipoRecepcion(
			Integer id_categoria_call) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getTipoRecepcion(id_categoria_call);
	}
	
	@Override
	public List<IndicadorCall> getIncidencia(
			Integer id_categoria_call) {
		// TODO Auto-generated method stub
		return indicadorCallMapper.getIncidencia(id_categoria_call);
	}

	
}
