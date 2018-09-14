package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CabeceraFacturacion;
import com.certicom.certiscan.mapper.CabeceraFacturacionMapper;



public class CabeceraFacturacionService implements CabeceraFacturacionMapper{
	
	CabeceraFacturacionMapper cabeceraFacturacionMapper = (CabeceraFacturacionMapper)ServiceFinder.findBean("cabeceraFacturacionMapper");

	@Override
	public CabeceraFacturacion findById(Integer idcabeceraFacturacion) throws Exception {
		return cabeceraFacturacionMapper.findById(idcabeceraFacturacion);
	}

	@Override
	public List<CabeceraFacturacion> findAll() throws Exception {
		return cabeceraFacturacionMapper.findAll();
	}

	@Override
	public void crearCabeceraFacturacion(CabeceraFacturacion cabeceraFacturacion) throws Exception {
		cabeceraFacturacionMapper.crearCabeceraFacturacion(cabeceraFacturacion);
		
	}

	@Override
	public void eliminarCabeceraFacturacion(Integer idcabeceraFacturacion) throws Exception {
		cabeceraFacturacionMapper.eliminarCabeceraFacturacion(idcabeceraFacturacion);
		
	}

	@Override
	public List<CabeceraFacturacion> cabeceraFacturacionComplemento(Integer idformato)
			throws Exception {
		
		return cabeceraFacturacionMapper.cabeceraFacturacionComplemento(idformato);
	}

	@Override
	public List<CabeceraFacturacion> cabeceraFacturacionByDetalleFormato(Integer idformato)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraFacturacionMapper.cabeceraFacturacionByDetalleFormato(idformato);
	}

	@Override
	public List<CabeceraFacturacion> findByDescripcion(String descripcion)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraFacturacionMapper.findByDescripcion(descripcion);
	}

	
}
