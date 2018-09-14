package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CategoriaIndicador;
import com.certicom.certiscan.domain.Estado;
import com.certicom.certiscan.domain.Test;
import com.certicom.certiscan.mapper.CategoriaIndicadorMapper;

public class CategoriaIndicadorServices implements CategoriaIndicadorMapper {

	CategoriaIndicadorMapper categoriaIndicadorMapper = (CategoriaIndicadorMapper)ServiceFinder.findBean("categoriaIndicadorMapper");

	@Override
	public CategoriaIndicador findById(Integer codigoCategoria) throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadorMapper.findById(codigoCategoria);
	}

	@Override
	public List<CategoriaIndicador> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoriaIndicadorMapper.findAll();
	}

	@Override
	public void crearCategoriaIndicador(CategoriaIndicador categoria) throws Exception {
		// TODO Auto-generated method stub
		categoriaIndicadorMapper.crearCategoriaIndicador(categoria);
	}

	@Override
	public void actualizarCategoriaIndicador(CategoriaIndicador Categoria) throws Exception {
		// TODO Auto-generated method stub
		categoriaIndicadorMapper.actualizarCategoriaIndicador(Categoria);
	}

	@Override
	public void eliminarCategoriaIndicador(Integer codigoCategoria) throws Exception {
		// TODO Auto-generated method stub
		categoriaIndicadorMapper.eliminarCategoriaIndicador(codigoCategoria);
	}


	
}
