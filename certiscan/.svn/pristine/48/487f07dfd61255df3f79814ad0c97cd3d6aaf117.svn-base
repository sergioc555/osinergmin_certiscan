package com.certicom.certiscan.services;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Centro_Atencion;
import com.certicom.certiscan.mapper.Centro_AtencionMapper;

public class Centro_AtencionServices implements Centro_AtencionMapper, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	Centro_AtencionMapper centro_atencionMapper = (Centro_AtencionMapper)ServiceFinder.findBean("centro_atencionMapper");

	@Override
	public List<Centro_Atencion> findAll() throws Exception {
		return centro_atencionMapper.findAll();
	}

	@Override
	public Centro_Atencion findById(@Param("p_id_centro_atencion") Integer id_centro_atencion)
			throws Exception {
		return centro_atencionMapper.findById(id_centro_atencion);
	}
	//
	@Override
	public List<Centro_Atencion> listaCentroAtencionActivo() throws Exception {		
		return centro_atencionMapper.listaCentroAtencionActivo();
	}
	//
	@Override
	public void eliminarCentro_Atencion(@Param("p_id_centro_atencion") Integer id_centro_atencion)
			throws Exception {
		centro_atencionMapper.eliminarCentro_Atencion(id_centro_atencion);
	}

	@Override
	public void crearCentro_Atencion(Centro_Atencion centro_atencion) throws Exception {
		centro_atencionMapper.crearCentro_Atencion(centro_atencion);
	}

	@Override
	public void actualizarCentro_Atencion(Centro_Atencion centro_atencion) throws Exception {
		centro_atencionMapper.actualizarCentro_Atencion(centro_atencion);
	}

	@Override
	public List<Centro_Atencion> listaProyectosxEmpresa(Integer id_empresa) throws Exception {
		// TODO Auto-generated method stub
		return centro_atencionMapper.listaProyectosxEmpresa(id_empresa);
	}
	//no elimina Proyecto asociado a Negocio
	@Override
	public Integer listNegocioxProyecto(Integer id_centro_atencion)
			throws Exception {		
		return centro_atencionMapper.listNegocioxProyecto(id_centro_atencion);
	}
	
}
