package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CabeceraNombre;
import com.certicom.certiscan.mapper.CabeceraNombreMapper;

public class CabeceraNombreService implements CabeceraNombreMapper{
	
	CabeceraNombreMapper cabeceraNombreMapper = (CabeceraNombreMapper)ServiceFinder.findBean("cabeceraNombreMapper");

	@Override
	public CabeceraNombre findById(Integer idCabeceraNombre) throws Exception {
		// TODO Auto-generated method stub
		return  cabeceraNombreMapper.findById(idCabeceraNombre);
	}

	@Override
	public List<CabeceraNombre> findByCabeceraId(Integer idCabecera)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraNombreMapper.findByCabeceraId(idCabecera);
	}

	@Override
	public List<CabeceraNombre> findAll() throws Exception {
		// TODO Auto-generated method stub
		return cabeceraNombreMapper.findAll();
	}

	@Override
	public void crearCabeceraNombre(CabeceraNombre cabeceraNombre)
			throws Exception {
		// TODO Auto-generated method stub
		cabeceraNombreMapper.crearCabeceraNombre(cabeceraNombre);
	}

	@Override
	public void eliminarCabeceraNombre(Integer idcabeceraNombre)
			throws Exception {
		// TODO Auto-generated method stub
		cabeceraNombreMapper.eliminarCabeceraNombre(idcabeceraNombre);
	}

	@Override
	public List<CabeceraNombre> findByDescripcion(String descripcion)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraNombreMapper.findByDescripcion(descripcion);
	}

	



}
