package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CategoriaIndicadoresProducto;
import com.certicom.certiscan.mapper.CategoriaIndicadoresProductoMapper;



public class CategoriaIndicadoresProductoService implements CategoriaIndicadoresProductoMapper  {
	
	
	CategoriaIndicadoresProductoMapper categoriaIndicadoresProductoMapper = (CategoriaIndicadoresProductoMapper)ServiceFinder.findBean("categoriaIndicadoresProductoMapper");

	@Override
	public CategoriaIndicadoresProducto findById(Integer codigoEstado) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresProductoMapper.findById(codigoEstado);
	}

	@Override
	public List<CategoriaIndicadoresProducto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadoresProductoMapper.findAll();
	}

	@Override
	public void crearCategoriaIndicadoresProducto(CategoriaIndicadoresProducto resultado) throws Exception {
		categoriaIndicadoresProductoMapper.crearCategoriaIndicadoresProducto(resultado);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarCategoriaIndicadoresProducto(CategoriaIndicadoresProducto categoriaindicadoresproducto)
			throws Exception {
		categoriaIndicadoresProductoMapper.actualizarCategoriaIndicadoresProducto(categoriaindicadoresproducto);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCategoriaIndicadoresProducto(Integer id_cat_producto)
			throws Exception {
		categoriaIndicadoresProductoMapper.eliminarCategoriaIndicadoresProducto(id_cat_producto);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoriaIndicadoresProducto> listaCategoriaIndicadoresProductoActivo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
