package com.certicom.certiscan.services;


import java.util.Date;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.SubProducto;
import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;
import com.certicom.certiscan.mapper.TelefonoExpedienteTemporalMapper;

public class TelefonoExpedienteTemporalService implements TelefonoExpedienteTemporalMapper {
	
	TelefonoExpedienteTemporalMapper telefonoExpedienteTemporalMapper = (TelefonoExpedienteTemporalMapper)ServiceFinder.findBean("telefonoExpedienteTemporalMapper");

	@Override
	public TelefonoExpedienteTemporal findById(Integer id) throws Exception {
		return telefonoExpedienteTemporalMapper.findById(id);
	}

	@Override
	public List<TelefonoExpedienteTemporal> findAll() throws Exception {
		return telefonoExpedienteTemporalMapper.findAll();
	}

	@Override
	public void crearTelefonoExpedienteTemporal(TelefonoExpedienteTemporal telefonoExpedienteTemporal) throws Exception {
		telefonoExpedienteTemporalMapper.crearTelefonoExpedienteTemporal(telefonoExpedienteTemporal);
		
	}

	@Override
	public void eliminarTelefonoExpedienteTemporal(Integer id) throws Exception {
		telefonoExpedienteTemporalMapper.eliminarTelefonoExpedienteTemporal(id);
		
	}

	@Override
	public void actualizarTelefonoExpedienteTemporal(TelefonoExpedienteTemporal telefonoExpedienteTemporal) throws Exception {
		telefonoExpedienteTemporalMapper.actualizarTelefonoExpedienteTemporal(telefonoExpedienteTemporal);
		
	}

	@Override
	public List<TelefonoExpedienteTemporal> findByIndecopy(Integer idProducto,
			Date periodo, Integer idUsuario, Integer idCabeceraIndecopy)
			throws Exception {
		// TODO Auto-generated method stub
		return telefonoExpedienteTemporalMapper.findByIndecopy(idProducto, periodo, idUsuario, idCabeceraIndecopy);
	}

	@Override
	public void crearTelefonoExpedienteExcluidoTemporal(Integer idProducto,
			Date periodo, Integer idUsuario, String valor) throws Exception {
		// TODO Auto-generated method stub
		telefonoExpedienteTemporalMapper.crearTelefonoExpedienteExcluidoTemporal(idProducto, periodo, idUsuario, valor);
	}
	
	
}
