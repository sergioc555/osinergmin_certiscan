package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Comentario;
import com.certicom.certiscan.mapper.ComentarioMapper;

public class ComentarioService implements ComentarioMapper {

	ComentarioMapper comentarioMapper = (ComentarioMapper)ServiceFinder.findBean("comentarioMapper");

	@Override
	public void actualizarComentario(Comentario comentario) throws Exception {
		// TODO Auto-generated method stub
		comentarioMapper.actualizarComentario(comentario);
	}

	@Override
	public void crearComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		comentarioMapper.crearComentario(comentario);
	}

	@Override
	public void eliminarComentario(Integer idcomentario) {
		// TODO Auto-generated method stub
		comentarioMapper.eliminarComentario(idcomentario);
	}

	@Override
	public List<Comentario> find_ByIdComentario(Integer idcomentario)
			throws Exception {
		// TODO Auto-generated method stub
		return comentarioMapper.find_ByIdComentario(idcomentario);
	}

	@Override
	public List<Comentario> findAll() throws Exception {
		// TODO Auto-generated method stub
		return comentarioMapper.findAll();
	}

	@Override
	public Comentario find_ByIdExpediente(Integer idexpediente)
			throws Exception {
		// TODO Auto-generated method stub
		return comentarioMapper.find_ByIdExpediente(idexpediente);
	}

	
}
