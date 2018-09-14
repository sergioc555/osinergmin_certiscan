package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.TipoBono;
import com.certicom.certiscan.mapper.TipoBonoMapper;

public class TipoBonoService implements TipoBonoMapper{

	TipoBonoMapper tipoBonoMapper = (TipoBonoMapper)ServiceFinder.findBean("tipoBonoMapper");

	@Override
	public TipoBono findById(Integer codigoTipoCiclo) throws Exception {
		// TODO Auto-generated method stub
		return tipoBonoMapper.findById(codigoTipoCiclo);
	}

	@Override
	public List<TipoBono> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tipoBonoMapper.findAll();
	}

	@Override
	public void crearTipoBono(TipoBono tipoBono) throws Exception {
		// TODO Auto-generated method stub
		tipoBonoMapper.crearTipoBono(tipoBono);
	}

	@Override
	public void actualizarTipoBono(TipoBono tipoBono) throws Exception {
		// TODO Auto-generated method stub
		tipoBonoMapper.actualizarTipoBono(tipoBono);
	}

	@Override
	public void eliminarTipoBono(Integer idTipoBono) throws Exception {
		// TODO Auto-generated method stub
		tipoBonoMapper.eliminarTipoBono(idTipoBono);
	}

	
	
}
