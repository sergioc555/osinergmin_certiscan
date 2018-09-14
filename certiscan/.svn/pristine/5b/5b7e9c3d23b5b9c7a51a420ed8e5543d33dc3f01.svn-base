package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CategoriaIndicadoresIndice;
import com.certicom.certiscan.mapper.CategoriaIndicadoresIndiceMapper;

public class CategoriaIndicadoresIndiceServices implements CategoriaIndicadoresIndiceMapper{
	
	CategoriaIndicadoresIndiceMapper categoriaIndicadoresIndiceMapper = (CategoriaIndicadoresIndiceMapper)ServiceFinder.findBean("categoriaIndicadoresIndiceMapper");

	@Override
	public CategoriaIndicadoresIndice findById(Integer codigoCategoriaIndice) throws Exception {
		return categoriaIndicadoresIndiceMapper.findById(codigoCategoriaIndice);
	}

	@Override
	public List<CategoriaIndicadoresIndice> findAll() throws Exception {		
		return categoriaIndicadoresIndiceMapper.findAll();
	}

	@Override
	public List<CategoriaIndicadoresIndice> listaCategoriaIndicadoresCallActivo() throws Exception {		
		return categoriaIndicadoresIndiceMapper.listaCategoriaIndicadoresCallActivo();
	}

	@Override
	public void crearCategoriaIndicadoresCall(CategoriaIndicadoresIndice categoriaIndicadoresIndice) throws Exception {		
		categoriaIndicadoresIndiceMapper.crearCategoriaIndicadoresCall(categoriaIndicadoresIndice);
	}

	@Override
	public void actualizarCategoriaIndicadoresIndice(CategoriaIndicadoresIndice categoriaIndicadoresIndice)
			throws Exception {
		categoriaIndicadoresIndiceMapper.actualizarCategoriaIndicadoresIndice(categoriaIndicadoresIndice);
	}

	@Override
	public void eliminarCategoriaIndicadoresIndice(Integer id_categoria_call) throws Exception {	
		categoriaIndicadoresIndiceMapper.eliminarCategoriaIndicadoresIndice(id_categoria_call);
	}

	@Override
	public Integer listIndicadorxCategroriaIndice(Integer id_categoria_indice)
			throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresIndiceMapper.listIndicadorxCategroriaIndice(id_categoria_indice);
	}

	@Override
	public CategoriaIndicadoresIndice findByNombre(String descripcion)
			throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresIndiceMapper.findByNombre(descripcion);
	}

	@Override
	public List<CategoriaIndicadoresIndice> findByProducto(Integer id_producto)
			throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresIndiceMapper.findByProducto(id_producto);
	}

	@Override
	public List<CategoriaIndicadoresIndice> listarCategoriaCallByProducto(
			Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresIndiceMapper.listarCategoriaCallByProducto(id_producto);
	}

	@Override
	public List<CategoriaIndicadoresIndice> getCatIndicadoresExisProducto(
			Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresIndiceMapper.getCatIndicadoresExisProducto(idProducto, periodo);
	}

	@Override
	public List<CategoriaIndicadoresIndice> listarCategoriaCallByProductoCall(
			Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresIndiceMapper.listarCategoriaCallByProductoCall(id_producto);
	}




}
