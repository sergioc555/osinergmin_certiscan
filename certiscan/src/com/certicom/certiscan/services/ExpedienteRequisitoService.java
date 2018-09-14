package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.ExpedienteRequisito;
import com.certicom.certiscan.mapper.ExpedienteRequisitoMapper;

public class ExpedienteRequisitoService implements ExpedienteRequisitoMapper{

	ExpedienteRequisitoMapper expedienteRequisitoMapper = (ExpedienteRequisitoMapper)ServiceFinder.findBean("expedienteRequisitoMapper");

	@Override
	public void crearExpedienteRequisito(ExpedienteRequisito expedienteRequisito)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteRequisitoMapper.crearExpedienteRequisito(expedienteRequisito);
	}

	@Override
	public List<ExpedienteRequisito> listarRequisitosxExpediente(
			Integer cod_expediente) throws Exception {
		// TODO Auto-generated method stub
		return expedienteRequisitoMapper.listarRequisitosxExpediente(cod_expediente);
	}

	@Override
	public void actualizarEstadoReq(Integer id, boolean estado)
			throws Exception {
		// TODO Auto-generated method stub
		expedienteRequisitoMapper.actualizarEstadoReq(id, estado);
	}
	

}
