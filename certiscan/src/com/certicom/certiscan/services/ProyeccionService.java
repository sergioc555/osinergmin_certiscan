package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Proyeccion;
import com.certicom.certiscan.mapper.ProyeccionMapper;

public class ProyeccionService implements ProyeccionMapper {

	ProyeccionMapper proyeccionMapper = (ProyeccionMapper) ServiceFinder.findBean("proyeccionMapper");
	
	@Override
	public Proyeccion findById(Integer id_proyeccion) throws Exception {
		// TODO Auto-generated method stub
		return proyeccionMapper.findById(id_proyeccion);
	}

	@Override
	public List<Proyeccion> findAll() throws Exception {
		// TODO Auto-generated method stub
		return proyeccionMapper.findAll();
	}

	@Override
	public void crearProyeccion(Proyeccion proyeccion) throws Exception {
		// TODO Auto-generated method stub
		proyeccionMapper.crearProyeccion(proyeccion);
	}

	@Override
	public void actualizarProyeccion(Proyeccion proyeccion) throws Exception {
		// TODO Auto-generated method stub
		proyeccionMapper.actualizarProyeccion(proyeccion);
	}

	@Override
	public void eliminarProyeccion(Integer id_proyeccion) throws Exception {
		// TODO Auto-generated method stub
		proyeccionMapper.eliminarProyeccion(id_proyeccion);
	}

}
