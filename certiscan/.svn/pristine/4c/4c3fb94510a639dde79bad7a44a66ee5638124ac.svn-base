package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.mapper.OficinaMapper;

public class OficinaService implements OficinaMapper {

	OficinaMapper oficinaMapper = (OficinaMapper) ServiceFinder.findBean("oficinaMapper");

	@Override
	public Oficina findById(Integer codigoEstado) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.findById(codigoEstado);
	}

	@Override
	public List<Oficina> findAll() throws Exception {
		return oficinaMapper.findAll();
	}

	@Override
	public void crearOficina(Oficina oficina) throws Exception {
		oficinaMapper.crearOficina(oficina);
	}

	@Override
	public void actualizarOficina(Oficina oficina) throws Exception {
		oficinaMapper.actualizarOficina(oficina);
	}

	@Override
	public void eliminarOficina(Integer id_oficina) throws Exception {
		oficinaMapper.eliminarOficina(id_oficina);

	}

	@Override
	public List<Oficina> obtenerOficinaxUbigeo(Integer sid_ubigeo) throws Exception {
		return oficinaMapper.obtenerOficinaxUbigeo(sid_ubigeo);
	}

	@Override
	public Oficina findByCodigoByNegocio(String codigo, Integer id_negocio) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.findByCodigoByNegocio(codigo, id_negocio);
	}

	@Override
	public List<Oficina> findByNegocio(Integer id_negocio) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.findByNegocio(id_negocio);
	}

	@Override
	public List<Oficina> find_ByNegocio(Integer id_negocio) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.find_ByNegocio(id_negocio);
	}

	@Override
	public List<Oficina> findByNegocioByTerritorio(Integer id_negocio, String codigoTerritorio) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.findByNegocioByTerritorio(id_negocio, codigoTerritorio);
	}

	@Override
	public List<Oficina> obtenerOficinaxUbigeoNegocioTerritorio(Integer id_negocio, String distrito, String departamento, String provincia) throws Exception {
		return this.oficinaMapper.obtenerOficinaxUbigeoNegocioTerritorio(id_negocio, distrito, departamento, provincia);
	}

	@Override
	public List<Oficina> findByIdTerritorios(List<String> descripcionTerritorios) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.findByIdTerritorios(descripcionTerritorios);
	}

	@Override
	public List<Oficina> listOficinaxNegocioORTodos(Integer id_negocio) throws Exception {
		return oficinaMapper.listOficinaxNegocioORTodos(id_negocio);
	}

	@Override
	public void actualizarOficinaPorTerritorio(Oficina oficina) throws Exception {
		// TODO Auto-generated method stub
		oficinaMapper.actualizarOficinaPorTerritorio(oficina);
	}

	@Override
	public List<Oficina> find_ByNegocioByDepartamento(Integer id_negocio, String departamento) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.find_ByNegocioByDepartamento(id_negocio, departamento);
	}

	@Override
	public List<Oficina> getByNegocioExclusion(Integer id_negocio)
			throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.getByNegocioExclusion(id_negocio);
	}

	@Override
	public void updateEstadoCobertura(Oficina oficina) throws Exception {
		// TODO Auto-generated method stub
		oficinaMapper.updateEstadoCobertura(oficina);
	}

	@Override
	public Oficina findByCodigoByNegocio2(String codigo, Integer id_negocio,
			Integer id_territorio) throws Exception {
		// TODO Auto-generated method stub
		return oficinaMapper.findByCodigoByNegocio2(codigo, id_negocio, id_territorio);
	}

}
