package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Indice;
import com.certicom.certiscan.mapper.IndiceMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;

public class IndiceServices implements IndiceMapper {

	IndiceMapper indiceMapper = (IndiceMapper) ServiceFinder.findBean("indiceMapper");

	@Override
	public Indice findById(Integer codigoIndicadorCall) throws Exception {
		return indiceMapper.findById(codigoIndicadorCall);
	}

	@Override
	public List<Indice> findAll() throws Exception {
		return indiceMapper.findAll();
	}

	@Override
	public List<Indice> listaIndiceActivo() throws Exception {
		return indiceMapper.listaIndiceActivo();
	}

	@Override
	public void crearIndice(Indice indice) throws Exception {
		indiceMapper.crearIndice(indice);
	}

	@Override
	public void actualizarIndice(Indice indice) throws Exception {
		indiceMapper.actualizarIndice(indice);
	}

	@Override
	public void eliminarIndice(Integer id_indice) throws Exception {
		indiceMapper.eliminarIndice(id_indice);
	}

	@Override
	public List<Indice> listaIndxTipoDocumento(Integer id_tipo_documento) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.listaIndxTipoDocumento(id_tipo_documento);
	}

	@Override
	public List<Indice> listaIndxTipoDato(Integer id_tipo_dato) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.listaIndxTipoDato(id_tipo_dato);
	}

	@Override
	public List<Indice> findByCategoria(Integer id_indice) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.findByCategoria(id_indice);
	}

	@Override
	public List<Indice> getCountIndicadoresxCategoria(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.getCountIndicadoresxCategoria(filter);
	}

	@Override
	public List<Indice> getCountExpxIndCallxTipPlanilla(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.getCountExpxIndCallxTipPlanilla(filter);
	}

	@Override
	public List<Indice> distinctIndCategorias(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.distinctIndCategorias(filter);
	}

	@Override
	public List<Indice> srptCountIndicadorexTipoPlanilla(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.srptCountIndicadorexTipoPlanilla(filter);
	}

	@Override
	public List<Indice> getCountExpxIndCallxTerritorio(ExpedienteFilter filter) throws Exception {
		return indiceMapper.getCountExpxIndCallxTerritorio(filter);
	}

	@Override
	public List<Indice> getCountExpxIndCallxPrioridad(ExpedienteFilter filter) throws Exception {
		return indiceMapper.getCountExpxIndCallxPrioridad(filter);
	}

	public List<Indice> listByIdCatecoriaCall(Integer id) throws Exception {
		return indiceMapper.listByIdCatecoriaCall(id);
	}

	@Override
	public List<Indice> listaIndxCategoriaSupervisorPyme(Integer id_indice) throws Exception {
		return indiceMapper.listaIndxCategoriaSupervisorPyme(id_indice);
	}

	@Override
	public List<Indice> listaIndxCategoriaBackPyme(Integer id_indice) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.listaIndxCategoriaBackPyme(id_indice);
	}

	@Override
	public List<Indice> listaIndxCategoriaSupervisorHipotecario(Integer id_indice) throws Exception {
		return indiceMapper.listaIndxCategoriaSupervisorHipotecario(id_indice);
	}

	@Override
	public List<Indice> getxSubFamiliaResultadosFeedBack(
			Integer id_indice) {
		// TODO Auto-generated method stub
		return indiceMapper.getxSubFamiliaResultadosFeedBack(id_indice);
	}

	@Override
	public List<Indice> getxFamiliaResultadosFeedBack2(
			Integer id_producto) {
		// TODO Auto-generated method stub
		return indiceMapper.getxFamiliaResultadosFeedBack2(id_producto);
	}

	@Override
	public List<Indice> listaIndxCategoriaBackLog(
			Integer id_indice) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.listaIndxCategoriaBackLog(id_indice);
	}

	@Override
	public List<Indice> getxIndicadoresAceptaProducto(Integer id_indice) {
		// TODO Auto-generated method stub
		return indiceMapper.getxIndicadoresAceptaProducto(id_indice);
	}

	@Override
	public List<Indice> getxIndicadoresVisita() {
		// TODO Auto-generated method stub
		return indiceMapper.getxIndicadoresVisita();
	}

	@Override
	public List<Indice> listaIndxCategoriaConsesionario(
			Integer id_indice) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.listaIndxCategoriaConsesionario(id_indice);
	}

	@Override
	public List<Indice> listaIndxCategoriaBackLog2(
			Integer id_indice) throws Exception {
		// TODO Auto-generated method stub
		return indiceMapper.listaIndxCategoriaBackLog2(id_indice);
	}


	@Override
	public List<Indice> getxIndicadoresActivosByProducto(
			Integer id_producto) {
		// TODO Auto-generated method stub
		return indiceMapper.getxIndicadoresActivosByProducto(id_producto);
	}

}
