package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CabeceraFacturacionNombre;
import com.certicom.certiscan.mapper.CabeceraFacturacionNombreMapper;

public class CabeceraFacturacionNombreService implements CabeceraFacturacionNombreMapper{
	
	CabeceraFacturacionNombreMapper cabeceraFacturacionNombreMapper = (CabeceraFacturacionNombreMapper)ServiceFinder.findBean("cabeceraFacturacionNombreMapper");

	@Override
	public CabeceraFacturacionNombre findById(Integer idCabeceraFacturacionNombre) throws Exception {
		// TODO Auto-generated method stub
		return  cabeceraFacturacionNombreMapper.findById(idCabeceraFacturacionNombre);
	}

	@Override
	public List<CabeceraFacturacionNombre> findByCabeceraId(Integer idCabeceraFacturacion)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraFacturacionNombreMapper.findByCabeceraId(idCabeceraFacturacion);
	}

	@Override
	public List<CabeceraFacturacionNombre> findAll() throws Exception {
		// TODO Auto-generated method stub
		return cabeceraFacturacionNombreMapper.findAll();
	}

	@Override
	public void crearCabeceraFacturacionNombre(CabeceraFacturacionNombre cabeceraFacturacionNombre)
			throws Exception {
		// TODO Auto-generated method stub
		cabeceraFacturacionNombreMapper.crearCabeceraFacturacionNombre(cabeceraFacturacionNombre);
	}

	@Override
	public void eliminarCabeceraFacturacionNombre(Integer idcabeceraFacturacionNombre)
			throws Exception {
		// TODO Auto-generated method stub
		cabeceraFacturacionNombreMapper.eliminarCabeceraFacturacionNombre(idcabeceraFacturacionNombre);
	}

	@Override
	public List<CabeceraFacturacionNombre> findByDescripcion(String descripcion)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraFacturacionNombreMapper.findByDescripcion(descripcion);
	}

	



}
