package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CategoriaEstados;
import com.certicom.certiscan.mapper.CategoriaEstadosMapper;

public class CategoriaEstadosServices implements CategoriaEstadosMapper {

	CategoriaEstadosMapper categoriaEstadosMapper = (CategoriaEstadosMapper) ServiceFinder.findBean("categoriaEstadosMapper");

	@Override
	public CategoriaEstados findById(Integer codigoCategoriaEstados) throws Exception {
		return categoriaEstadosMapper.findById(codigoCategoriaEstados);
	}

	@Override
	public List<CategoriaEstados> findAll() throws Exception {
		return categoriaEstadosMapper.findAll();
	}

	@Override
	public List<CategoriaEstados> listaCategoriaEstadosActivo() throws Exception {
		return categoriaEstadosMapper.listaCategoriaEstadosActivo();
	}

	@Override
	public void crearCategoriaEstados(CategoriaEstados categoriaEstados) throws Exception {
		categoriaEstadosMapper.crearCategoriaEstados(categoriaEstados);
	}

	@Override
	public void actualizarCategoriaEstados(CategoriaEstados categoriaEstados) throws Exception {
		categoriaEstadosMapper.actualizarCategoriaEstados(categoriaEstados);
	}

	@Override
	public void eliminarCategoriaEstados(Integer id_categoria_estado) throws Exception {
		categoriaEstadosMapper.eliminarCategoriaEstados(id_categoria_estado);
	}

	public Integer listCategoriaxEstado(Integer id_categoria_estados) throws Exception {
		return categoriaEstadosMapper.listCategoriaxEstado(id_categoria_estados);
	}

	@Override
	public CategoriaEstados findByCodigo(String codigo) throws Exception {
		return categoriaEstadosMapper.findByCodigo(codigo);
	}

}
