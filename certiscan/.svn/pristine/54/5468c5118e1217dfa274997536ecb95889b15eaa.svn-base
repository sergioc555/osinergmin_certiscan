package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.mapper.NegocioMapper;

public class NegocioServices implements NegocioMapper {

	NegocioMapper negocioMapper = (NegocioMapper) ServiceFinder.findBean("negocioMapper");

	@Override
	public Negocio findById(Integer codigoNegocio) throws Exception {
		return negocioMapper.findById(codigoNegocio);
	}

	@Override
	public List<Negocio> findAll() throws Exception {
		return negocioMapper.findAll();
	}

	@Override
	public List<Negocio> listaNegociosActivo() throws Exception {
		return negocioMapper.listaNegociosActivo();
	}

	@Override
	public void crearNegocio(Negocio negocio) throws Exception {
		negocioMapper.crearNegocio(negocio);
	}

	@Override
	public void actualizarNegocio(Negocio negocio) throws Exception {
		negocioMapper.actualizarNegocio(negocio);
	}

	@Override
	public void eliminarNegocio(Integer id_negocio) throws Exception {
		negocioMapper.eliminarNegocio(id_negocio);
	}

	@Override
	public List<Negocio> listaNegocioxEmpresa(Integer id_centro_atencion) throws Exception {
		return negocioMapper.listaNegocioxEmpresa(id_centro_atencion);
	}

	// no elimina Negocio asociado a un Producto
	@Override
	public Integer listProductoxNegocio(Integer id_negocio) throws Exception {
		return negocioMapper.listProductoxNegocio(id_negocio);
	}

	@Override
	public List<Negocio> findByIdUsuario(Integer idusuario) throws Exception {
		return negocioMapper.findByIdUsuario(idusuario);
	}

}
