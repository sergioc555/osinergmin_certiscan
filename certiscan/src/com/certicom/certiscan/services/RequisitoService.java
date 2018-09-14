package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Requisito;
import com.certicom.certiscan.mapper.RequisitoMapper;

public class RequisitoService implements RequisitoMapper {

	RequisitoMapper requisitoMapper = (RequisitoMapper)ServiceFinder.findBean("requisitoMapper");

	@Override
	public Requisito findById(Integer cod_requisito) throws Exception {
		return requisitoMapper.findById(cod_requisito);
	}

	@Override
	public List<Requisito> findAll() throws Exception {
		return requisitoMapper.findAll();
	}

	@Override
	public void crearRequisito(Requisito requisito) throws Exception {
		// TODO Auto-generated method stub
		requisitoMapper.crearRequisito(requisito);
	}

	@Override
	public void actualizarRequisito(Requisito requisito) throws Exception {
		// TODO Auto-generated method stub
		requisitoMapper.actualizarRequisito(requisito);
	}

	@Override
	public void eliminarRequisito(Integer cod_requisito) throws Exception {
		// TODO Auto-generated method stub
		requisitoMapper.eliminarRequisito(cod_requisito);
	}
}
