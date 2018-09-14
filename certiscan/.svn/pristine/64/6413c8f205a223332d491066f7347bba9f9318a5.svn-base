package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CheckListImagen;
import com.certicom.certiscan.mapper.CheckListImagenMapper;

public class CheckListImagenService implements CheckListImagenMapper{

	CheckListImagenMapper checkListMapper = (CheckListImagenMapper)ServiceFinder.findBean("checkListImagenMapper");

	@Override
	public CheckListImagen findById(Integer codigoCheckListImagen)
			throws Exception {
		// TODO Auto-generated method stub
		return checkListMapper.findById(codigoCheckListImagen);
	}

	@Override
	public List<CheckListImagen> findAll() throws Exception {
		// TODO Auto-generated method stub
		return checkListMapper.findAll();
	}

	@Override
	public void crearCheckListImagen(CheckListImagen checkListImagen)
			throws Exception {
		// TODO Auto-generated method stub
		checkListMapper.crearCheckListImagen(checkListImagen);
	}

	@Override
	public void actualizarCheckListImagen(CheckListImagen checkListImagen)
			throws Exception {
		// TODO Auto-generated method stub
		checkListMapper.actualizarCheckListImagen(checkListImagen);
	}

	@Override
	public void eliminarCheckListImagen(Integer id_checklist_imagen)
			throws Exception {
		// TODO Auto-generated method stub
		checkListMapper.eliminarCheckListImagen(id_checklist_imagen);
	}

	@Override
	public List<CheckListImagen> findByIdCheckList(Integer codigoCheckList)
			throws Exception {
		// TODO Auto-generated method stub
		return checkListMapper.findByIdCheckList(codigoCheckList);
	}

	@Override
	public List<CheckListImagen> findByIdCheckListExpediente_id(
			Integer codigoCheckList, Integer expediente_id) throws Exception {
		// TODO Auto-generated method stub
		return checkListMapper.findByIdCheckListExpediente_id(codigoCheckList, expediente_id);
	}

	@Override
	public void actualizarCheckListImagenObs(CheckListImagen checkListImagen)
			throws Exception {
		// TODO Auto-generated method stub
		checkListMapper.actualizarCheckListImagenObs(checkListImagen);
	}

	@Override
	public void actualizarCheckListImagenArchivo(CheckListImagen checkListImagen)
			throws Exception {
		// TODO Auto-generated method stub
		checkListMapper.actualizarCheckListImagenArchivo(checkListImagen);
	}


	

}
