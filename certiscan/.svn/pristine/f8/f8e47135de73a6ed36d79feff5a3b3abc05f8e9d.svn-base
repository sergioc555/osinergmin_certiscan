package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.mapper.NegocioResponsablesMapper;

public class NegocioResponsablesService implements NegocioResponsablesMapper {

	NegocioResponsablesMapper negocioResponsablesMapper = (NegocioResponsablesMapper)ServiceFinder.findBean("negocioResponsablesMapper");

	@Override
	public NegocioResponsables findById(Integer id_negocio_responsable)
			throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.findById(id_negocio_responsable);
	}

	@Override
	public List<NegocioResponsables> findAll() throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.findAll();
	}

	@Override
	public List<NegocioResponsables> listaResponsables(Integer id_negocio)
			throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.listaResponsables(id_negocio);
	}

	@Override
	public void crearNegocioResponsables(NegocioResponsables negocioResponsables)
			throws Exception {
		negocioResponsablesMapper.crearNegocioResponsables(negocioResponsables);
		
	}

	@Override
	public void actualizarNegocioResponsables(
			NegocioResponsables negocioResponsables) throws Exception {
		// TODO Auto-generated method stub
		negocioResponsablesMapper.actualizarNegocioResponsables(negocioResponsables);
	}

	@Override
	public void eliminarNegocioResponsables(Integer id_negocio_responsable)
			throws Exception {
		// TODO Auto-generated method stub
		negocioResponsablesMapper.eliminarNegocioResponsables(id_negocio_responsable);
	}

	@Override
	public void actualizarEstado(NegocioResponsables negocioResponsables)
			throws Exception {
		negocioResponsablesMapper.actualizarEstado(negocioResponsables);
	}

	@Override
	public Integer cantResponsablesxNegocio(
			NegocioResponsables negocioResponsables) throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.cantResponsablesxNegocio(negocioResponsables);
	}

	@Override
	public List<NegocioResponsables> listaSupervisorxNegocio(Integer id_negocio)
			throws Exception {
		return negocioResponsablesMapper.listaSupervisorxNegocio(id_negocio);
	}
	
	
	
	@Override
	public List<NegocioResponsables> listaBackxNegocio(Integer id_negocio)
			throws Exception {
		return negocioResponsablesMapper.listaBackxNegocio(id_negocio);
	}

	@Override
	public Integer getCountEjecutivosxNegocio(Integer id_negocio)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NegocioResponsables> listaEjecutivoxNegocio(Integer id_negocio)
			throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.listaEjecutivoxNegocio(id_negocio);
	}

	@Override
	public List<NegocioResponsables> rptRelacionPromotores(Integer id_negocio)
			throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.rptRelacionPromotores(id_negocio);
	}

	@Override
	public List<NegocioResponsables> listaSupervisorxNegocioxResponsable(
			Integer id_negocio, Integer id_negocio_responsable)
			throws Exception {
		
		return negocioResponsablesMapper.listaSupervisorxNegocioxResponsable(id_negocio, id_negocio_responsable);
	}

	@Override
	public Integer obtenerIdUsuario(Integer id_negocio_responsable)
			throws Exception {
	
		return negocioResponsablesMapper.obtenerIdUsuario(id_negocio_responsable);
	}

	@Override
	public List<NegocioResponsables> getNegocioResponsableByUser(Integer idusuario)
			throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.getNegocioResponsableByUser(idusuario);
	}

	@Override
	public void updateNegocioResponsablesSomeFields(
			NegocioResponsables negocioResponsables) throws Exception {
		negocioResponsablesMapper.updateNegocioResponsablesSomeFields(negocioResponsables);
	}

	@Override
	public List<NegocioResponsables> listaUsuarioxNegocio(Integer id_negocio)
			throws Exception {
		return negocioResponsablesMapper.listaUsuarioxNegocio(id_negocio);
	}

	@Override
	public List<NegocioResponsables> listaSupervisorPHxNegocio(
			Integer id_negocio, Integer cod_perfil) throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.listaSupervisorPHxNegocio(id_negocio, cod_perfil);
	}

	@Override
	public List<NegocioResponsables> rptReporteLectura(Integer id_negocio,String id_examen ,Integer idlectura )
			throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.rptReporteLectura(id_negocio,id_examen,idlectura);
	}

	@Override
	public List<NegocioResponsables> listaSupervisorxNegocioReasignar(
			Integer id_negocio, Integer id_suervisor) throws Exception {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.listaSupervisorxNegocioReasignar(id_negocio, id_suervisor);
	}

	@Override
	public List<NegocioResponsables> getUsuariosByNegocioNR(int id_negocio) {
		// TODO Auto-generated method stub
		return negocioResponsablesMapper.getUsuariosByNegocioNR(id_negocio);
	}
	
}
