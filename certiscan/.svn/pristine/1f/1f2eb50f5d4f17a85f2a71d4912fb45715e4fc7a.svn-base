package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Estado;
import com.certicom.certiscan.domain.Test;
import com.certicom.certiscan.mapper.EstadoMapper;
import com.certicom.certiscan.mapper.TestMapper;

public class EstadoServices implements EstadoMapper {
	
	EstadoMapper estadoMapper = (EstadoMapper)ServiceFinder.findBean("estadoMapper");

	@Override
	public Estado findById(Integer codigoIndicador) throws Exception {
		// TODO Auto-generated method stub
		return estadoMapper.findById(codigoIndicador);
	}

	@Override
	public List<Estado> findAll() throws Exception {
		// TODO Auto-generated method stub
		return estadoMapper.findAll();
	}

	@Override
	public void crearEstado(Estado Estado) throws Exception {
		// TODO Auto-generated method stub
		estadoMapper.crearEstado(Estado);
	}

	@Override
	public void actualizarEstado(Estado Estado) throws Exception {
		// TODO Auto-generated method stub
		estadoMapper.actualizarEstado(Estado);
	}

	@Override
	public void eliminarEstado(Integer codigoEstado) throws Exception {
		// TODO Auto-generated method stub
		estadoMapper.eliminarEstado(codigoEstado);
	}

	@Override
	public void actualizarEstadoEstado(Estado Estado)
			throws Exception {
		// TODO Auto-generated method stub
		estadoMapper.actualizarEstadoEstado(Estado);
	}
	
}
