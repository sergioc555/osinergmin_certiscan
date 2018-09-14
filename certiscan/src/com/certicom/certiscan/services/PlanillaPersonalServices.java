package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.PlanillaPersonal;
import com.certicom.certiscan.mapper.PlanillaPersonalMapper;

public class PlanillaPersonalServices implements PlanillaPersonalMapper{
	
	PlanillaPersonalMapper planillaPersonalMapper = (PlanillaPersonalMapper)ServiceFinder.findBean("planillaPersonalMapper");
	
	
	@Override
	public PlanillaPersonal findById(Integer codigoPlanillaPersonal) throws Exception {
		return planillaPersonalMapper.findById(codigoPlanillaPersonal);
	}

	@Override
	public List<PlanillaPersonal> findAll() throws Exception {		
		return planillaPersonalMapper.findAll();
	}

	@Override
	public List<PlanillaPersonal> listaPlanillaPersonalActivo()
			throws Exception {
		// TODO Auto-generated method stub
		return planillaPersonalMapper.listaPlanillaPersonalActivo();
	}

	@Override
	public void crearPlanillaPersonal(NegocioResponsables planillaPersonal)
			throws Exception {
		// TODO Auto-generated method stub
		planillaPersonalMapper.crearPlanillaPersonal(planillaPersonal);
	}

	@Override
	public void actualizarPlanillaPersonal(NegocioResponsables planillaPersonal)
			throws Exception {
		// TODO Auto-generated method stub
		planillaPersonalMapper.actualizarPlanillaPersonal(planillaPersonal);
	}

	@Override
	public void eliminarPlanillaPersonal(Integer id_planilla_personal)
			throws Exception {
		planillaPersonalMapper.eliminarPlanillaPersonal(id_planilla_personal);
	}

	@Override
	public Integer listCantPlanillaPersonal(Integer idusuario) throws Exception {
		return planillaPersonalMapper.listCantPlanillaPersonal(idusuario);
	}

	@Override
	public List<NegocioResponsables> planillaxusuario(Integer idusuario)
			throws Exception {
		return planillaPersonalMapper.planillaxusuario(idusuario);
	}

	@Override
	public void actualizarEstadoPlanillaPersonal(
			PlanillaPersonal planillaPersonal) throws Exception {
		planillaPersonalMapper.actualizarEstadoPlanillaPersonal(planillaPersonal);
	}

	@Override
	public Integer cantUsuarioPlanilla(NegocioResponsables planillaPersonal)
			throws Exception {
		return planillaPersonalMapper.cantUsuarioPlanilla(planillaPersonal);
	}
	




}
