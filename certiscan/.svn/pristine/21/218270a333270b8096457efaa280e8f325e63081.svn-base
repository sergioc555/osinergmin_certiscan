package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Cabecera;
import com.certicom.certiscan.mapper.CabeceraMapper;



public class CabeceraService implements CabeceraMapper{
	
	CabeceraMapper cabeceraMapper = (CabeceraMapper)ServiceFinder.findBean("cabeceraMapper");

	@Override
	public Cabecera findById(Integer idcabecera) throws Exception {
		return cabeceraMapper.findById(idcabecera);
	}

	@Override
	public List<Cabecera> findAll() throws Exception {
		return cabeceraMapper.findAll();
	}

	@Override
	public void crearCabecera(Cabecera cabecera) throws Exception {
		cabeceraMapper.crearCabecera(cabecera);
		
	}

	@Override
	public void eliminarCabecera(Integer idcabecera) throws Exception {
		cabeceraMapper.eliminarCabecera(idcabecera);
		
	}

	@Override
	public List<Cabecera> cabecerasComplemento(Integer idformato)
			throws Exception {
		
		return cabeceraMapper.cabecerasComplemento(idformato);
	}

	@Override
	public List<Cabecera> cabecerasByDetalleFormato(Integer idformato)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraMapper.cabecerasByDetalleFormato(idformato);
	}

	@Override
	public List<Cabecera> findByDescripcion(String descripcion)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraMapper.findByDescripcion(descripcion);
	}

	
}
