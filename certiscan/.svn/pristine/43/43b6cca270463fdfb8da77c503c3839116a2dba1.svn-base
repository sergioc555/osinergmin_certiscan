package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.TipoCiclo;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.mapper.TipoCicloMapper;

public class TipoCicloService implements TipoCicloMapper{

	TipoCicloMapper cicloMapper = (TipoCicloMapper)ServiceFinder.findBean("tipoCicloMapper");

	@Override
	public TipoCiclo findById(Integer codigoTipoCiclo) throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findById(codigoTipoCiclo);
	}

	@Override
	public List<TipoCiclo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findAll();
	}

	@Override
	public void crearTipoCiclo(TipoCiclo resultado) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.crearTipoCiclo(resultado);
	}

	@Override
	public void actualizarTipoCiclo(TipoCiclo tipociclo) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.actualizarTipoCiclo(tipociclo);
	}

	@Override
	public void eliminarTipoCiclo(Integer cod_tipoCiclo) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.eliminarTipoCiclo(cod_tipoCiclo);
	}

	
	
}
