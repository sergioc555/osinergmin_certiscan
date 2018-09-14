package com.certicom.certiscan.services;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CompresionLectura;
import com.certicom.certiscan.mapper.ComprensionLecturaMapper;

public class ComprensionLecturaService implements ComprensionLecturaMapper{

	ComprensionLecturaMapper comprensionLecturaMapper = (ComprensionLecturaMapper)ServiceFinder.findBean("comprensionLecturaMapper");

	@Override
	public void insertarComprensionLectura(CompresionLectura compresionLectura)
			throws Exception {
		comprensionLecturaMapper.insertarComprensionLectura(compresionLectura);
	}

	@Override
	public Integer getLecturasByUser(Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		return comprensionLecturaMapper.getLecturasByUser(idusuario);
	}
	
}
