package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.SubIndicadorCall;
import com.certicom.certiscan.mapper.SubIndicadorCallMapper;

public class SubIndicadorCallServices implements SubIndicadorCallMapper{
	
	SubIndicadorCallMapper subindicadorCallMapper = (SubIndicadorCallMapper)ServiceFinder.findBean("subindicadorCallMapper");

	@Override
	public List<SubIndicadorCall> findById(Integer codigoIndicadorCall, Integer codigoCategoriaCall) throws Exception {
		return subindicadorCallMapper.findById(codigoIndicadorCall,codigoCategoriaCall);
	}

	@Override
	public List<SubIndicadorCall> findByIndicadorCall(Integer p_indicadorCall)
			throws Exception {
		// TODO Auto-generated method stub
		return subindicadorCallMapper.findByIndicadorCall(p_indicadorCall);
	}
	
	@Override
	public void actualizarSubIndicadorCall(SubIndicadorCall subindicadorCall) throws Exception{
		// TODO Auto-generated method stub
		subindicadorCallMapper.actualizarSubIndicadorCall(subindicadorCall);
	}
	
	@Override
	public void crearSubIndicadorCall(SubIndicadorCall subindicadorCall) throws Exception{
		// TODO Auto-generated method stub
		subindicadorCallMapper.crearSubIndicadorCall(subindicadorCall);
	}

	public void eliminarSubIndicadorCall(Integer id_indicadores_call) throws Exception{
		// TODO Auto-generated method stub
		subindicadorCallMapper.eliminarSubIndicadorCall(id_indicadores_call);
	}

	/*@Override
	public List<IndicadorCall> findAll() throws Exception {		
		return indicadorCallMapper.findAll();
	}

	@Override
	public List<IndicadorCall> listaIndicadorCallActivo() throws Exception {		
		return indicadorCallMapper.listaIndicadorCallActivo();
	}

	@Override
	public void crearIndicadorCall(IndicadorCall indicadorCall) throws Exception {		
		indicadorCallMapper.crearIndicadorCall(indicadorCall);
	}

	@Override
	public void actualizarIndicadorCall(IndicadorCall indicadorCall)
			throws Exception {
		indicadorCallMapper.actualizarIndicadorCall(indicadorCall);
	}

	@Override
	public void eliminarIndicadorCall(Integer id_indicadores_call) throws Exception {	
		indicadorCallMapper.eliminarIndicadorCall(id_indicadores_call);
	}

	@Override
	public List<IndicadorCall> listaIndxCategoria(Integer id_categoria_call)
			throws Exception {
		// TODO Auto-generated method stub
		return indicadorCallMapper.listaIndxCategoria(id_categoria_call);
	}*/




}
