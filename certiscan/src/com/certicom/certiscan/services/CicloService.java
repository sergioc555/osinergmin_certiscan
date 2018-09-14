package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.mapper.CicloMapper;

public class CicloService implements CicloMapper{

	CicloMapper cicloMapper = (CicloMapper)ServiceFinder.findBean("cicloMapper");

	@Override
	public Ciclo findById(Integer cod_expediente) throws Exception {
		return cicloMapper.findById(cod_expediente);
	}

	@Override
	public List<Ciclo> findAll() throws Exception {
		return cicloMapper.findAll();
	}

	@Override
	public void crearCiclo(Ciclo ciclo) throws Exception {
		cicloMapper.crearCiclo(ciclo);
	}

	@Override
	public void actualizarCiclo(Ciclo ciclo) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.actualizarCiclo(ciclo);
	}

	@Override
	public void eliminarCiclo(Integer cod_ciclo) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.eliminarCiclo(cod_ciclo);
	}

	@Override
	public List<Ciclo> buscarXPeriodo(String mes, Integer anio, Integer codTipoCiclo) throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.buscarXPeriodo(mes, anio, codTipoCiclo);
	}

	@Override
	public List<Ciclo> findByTipoCicloActivo(Integer codigoTipoCiclo) throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findByTipoCicloActivo(codigoTipoCiclo);
	}

	@Override
	public void actualizarEstadoCiclo(Ciclo ciclo) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.actualizarEstadoCiclo(ciclo);
	}

	@Override
	public List<Ciclo> findCiclosActivos() throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findCiclosActivos();
	}

	@Override
	public List<Ciclo> findCiclosInactivosActivos() throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findCiclosInactivosActivos();
	}

	@Override
	public List<Ciclo> findByTipoCicloInactivo(Integer codigoTipoCiclo)
			throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findByTipoCicloInactivo(codigoTipoCiclo);
	}

	@Override
	public List<Ciclo> findByTipoCicloActivoSinTablero(Integer codigoTipoCiclo)
			throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findByTipoCicloActivoSinTablero(codigoTipoCiclo);
	}

	@Override
	public void actualizarFlagTab(Ciclo ciclo) throws Exception {
		// TODO Auto-generated method stub
		cicloMapper.actualizarFlagTab(ciclo);
	}

	@Override
	public Ciclo findByIdCiclo(Integer codigoCiclo) throws Exception {
		// TODO Auto-generated method stub
		return cicloMapper.findByIdCiclo(codigoCiclo);
	}	
	
}
