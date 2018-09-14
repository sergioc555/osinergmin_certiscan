package com.certicom.certiscan.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.domain.UsuarioPerfil;
import com.certicom.certiscan.mapper.UsuarioMapper;
import com.certicom.certiscan.mapper.UsuarioPerfilMapper;

public class UsuarioPerfilServices implements UsuarioPerfilMapper{

	UsuarioPerfilMapper usuarioPerfilMapper = (UsuarioPerfilMapper)ServiceFinder.findBean("usuarioPerfilMapper");

	@Override
	public List<UsuarioPerfil> listarPerfilesPorUsuario(Integer usuarioId)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioPerfilMapper.listarPerfilesPorUsuario(usuarioId);
	}

	@Override
	public UsuarioPerfil buscarPerfilUsuario(Integer idusuario,Integer idperfil) throws Exception {
		return usuarioPerfilMapper.buscarPerfilUsuario(idusuario, idperfil);
	}

	@Override
	public void eliminarPerfilUsuario(Integer idusuario, Integer idperfil) throws Exception {
		usuarioPerfilMapper.eliminarPerfilUsuario(idusuario, idperfil);
	}

	@Override
	public void insertUsuarioPeril(UsuarioPerfil usuarioPerfil) throws Exception{
		usuarioPerfilMapper.insertUsuarioPeril(usuarioPerfil);
	}

	@Override
	public List<Perfil> obtenerPerfilxUsuario(Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		return usuarioPerfilMapper.obtenerPerfilxUsuario(idusuario);
	}

}
