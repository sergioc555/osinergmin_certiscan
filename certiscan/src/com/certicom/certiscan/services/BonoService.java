package com.certicom.certiscan.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Bono;
import com.certicom.certiscan.mapper.BonoMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;

public class BonoService implements BonoMapper{

	BonoMapper bonoMapper = (BonoMapper)ServiceFinder.findBean("bonoMapper");

	@Override
	public List<Bono> listBonoByUsuario(Bono bono)throws Exception {
		return bonoMapper.listBonoByUsuario(bono);
	}

	@Override
	public void registraBono(Bono bono)throws Exception {
		bonoMapper.registraBono(bono);
	}

	@Override
	public void actualizaBono(Bono bono)throws Exception {
		bonoMapper.actualizaBono(bono);
	}

	@Override
	public List<Bono> listarxTipoBono(String fecha, String descripcion)
			throws Exception {
		return bonoMapper.listarxTipoBono(fecha, descripcion);
	}
	
	public BigDecimal obtenerMonto(Integer idusuario, Date periodo)throws Exception{
		return bonoMapper.obtenerMonto(idusuario, periodo);
	}	

	@Override
	public void eliminarBonosByIdFacturacionCabecera(
			Integer idFacturacionCabecera, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		bonoMapper.eliminarBonosByIdFacturacionCabecera(idFacturacionCabecera, periodo);
	}

	@Override
	public List<Bono> listarxIdCiclo(Integer codCiclo) throws Exception {
		// TODO Auto-generated method stub
		return bonoMapper.listarxIdCiclo(codCiclo);
	}

	@Override
	public void eliminarBono(Integer idbono) throws Exception {
		// TODO Auto-generated method stub
		bonoMapper.eliminarBono(idbono);
	}
	
}
