package com.certicom.certiscan.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.FacturacionPlanillaCabecera;
import com.certicom.certiscan.mapper.FacturacionPlanillaCabeceraMapper;

public class FacturacionPlanillaCabeceraServices implements FacturacionPlanillaCabeceraMapper{

	FacturacionPlanillaCabeceraMapper facturacionPlanillaCabeceraMapper = (FacturacionPlanillaCabeceraMapper)ServiceFinder.findBean("facturacionPlanillaCabeceraMapper");

	@Override
	public FacturacionPlanillaCabecera findById(
			Integer codFacturacionPlanillaCabecera) throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaCabeceraMapper.findById(codFacturacionPlanillaCabecera);
	}

	@Override
	public List<FacturacionPlanillaCabecera> findAll() throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaCabeceraMapper.findAll();
	}

	@Override
	public void crearFacturacionPlanillaCabecera(
			FacturacionPlanillaCabecera facturacionPlanillaCabecera)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaCabeceraMapper.crearFacturacionPlanillaCabecera(facturacionPlanillaCabecera);
	}

	@Override
	public void actualizarFacturacionPlanillaCabecera(
			FacturacionPlanillaCabecera facturacionPlanillaCabecera)
			throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaCabeceraMapper.actualizarFacturacionPlanillaCabecera(facturacionPlanillaCabecera);
	}

	@Override
	public void eliminarFacturacionPlanillaCabecera(
			Integer codFacturacionPlanillaCabecera) throws Exception {
		// TODO Auto-generated method stub
		facturacionPlanillaCabeceraMapper.eliminarFacturacionPlanillaCabecera(codFacturacionPlanillaCabecera);
	}

	@Override
	public FacturacionPlanillaCabecera findByIdCiclo(Integer codCiclo)
			throws Exception {
		// TODO Auto-generated method stub
		return facturacionPlanillaCabeceraMapper.findByIdCiclo(codCiclo);
	}


}
