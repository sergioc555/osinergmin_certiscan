package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CategoriaIndicadoresCall;
import com.certicom.certiscan.mapper.CategoriaIndicadoresCallMapper;

public class CategoriaIndicadoresCallServices implements CategoriaIndicadoresCallMapper{
	
	CategoriaIndicadoresCallMapper categoriaIndicadoresCallMapper = (CategoriaIndicadoresCallMapper)ServiceFinder.findBean("categoriaIndicadoresCallMapper");

	@Override
	public CategoriaIndicadoresCall findById(Integer codigoCategoriaCall) throws Exception {
		return categoriaIndicadoresCallMapper.findById(codigoCategoriaCall);
	}

	@Override
	public List<CategoriaIndicadoresCall> findAll() throws Exception {		
		return categoriaIndicadoresCallMapper.findAll();
	}

	@Override
	public List<CategoriaIndicadoresCall> listaCategoriaIndicadoresCallActivo() throws Exception {		
		return categoriaIndicadoresCallMapper.listaCategoriaIndicadoresCallActivo();
	}

	@Override
	public void crearCategoriaIndicadoresCall(CategoriaIndicadoresCall categoriaIndicadoresCall) throws Exception {		
		categoriaIndicadoresCallMapper.crearCategoriaIndicadoresCall(categoriaIndicadoresCall);
	}

	@Override
	public void actualizarCategoriaIndicadoresCall(CategoriaIndicadoresCall categoriaIndicadoresCall)
			throws Exception {
		categoriaIndicadoresCallMapper.actualizarCategoriaIndicadoresCall(categoriaIndicadoresCall);
	}

	@Override
	public void eliminarCategoriaIndicadoresCall(Integer id_categoria_call) throws Exception {	
		categoriaIndicadoresCallMapper.eliminarCategoriaIndicadoresCall(id_categoria_call);
	}

	@Override
	public Integer listIndicadorxCategroriaCall(Integer id_categoria_call)
			throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresCallMapper.listIndicadorxCategroriaCall(id_categoria_call);
	}

	@Override
	public CategoriaIndicadoresCall findByNombre(String descripcion)
			throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresCallMapper.findByNombre(descripcion);
	}

	@Override
	public List<CategoriaIndicadoresCall> findByProducto(Integer id_producto)
			throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresCallMapper.findByProducto(id_producto);
	}

	@Override
	public List<CategoriaIndicadoresCall> listarCategoriaCallByProducto(
			Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresCallMapper.listarCategoriaCallByProducto(id_producto);
	}

	@Override
	public List<CategoriaIndicadoresCall> getCatIndicadoresExisProducto(
			Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresCallMapper.getCatIndicadoresExisProducto(idProducto, periodo);
	}

	@Override
	public List<CategoriaIndicadoresCall> listarCategoriaCallByProductoCall(
			Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresCallMapper.listarCategoriaCallByProductoCall(id_producto);
	}




}
