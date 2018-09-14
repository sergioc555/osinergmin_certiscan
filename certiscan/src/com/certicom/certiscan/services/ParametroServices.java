package com.certicom.certiscan.services; 

import java.util.List; 

import org.apache.ibatis.annotations.Param;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Parametro;
import com.certicom.certiscan.mapper.ParametroMapper;

public class ParametroServices implements ParametroMapper{

	ParametroMapper parametroMapper = (ParametroMapper)ServiceFinder.findBean("parametroMapper");

	public List<Parametro> listParametro() throws Exception{
	    return parametroMapper.listParametro();
	}

	public void updateParametro(Parametro parametro) throws Exception{
	    parametroMapper.updateParametro(parametro);
	}

	public void insertParametro(Parametro parametro) throws Exception{
	    parametroMapper.insertParametro(parametro);
	}

	public Parametro findParametro(Parametro parametro)  throws Exception{
	    return parametroMapper.findParametro(parametro);
	}

	public List<Parametro>  findParametros(Parametro parametro)  throws Exception{
	    return parametroMapper.findParametros(parametro);
	}

	@Override
	public Parametro findParametroPorCodigo(int cod_parametro) throws Exception {
		// TODO Auto-generated method stub
		return parametroMapper.findParametroPorCodigo(cod_parametro);
	}

	@Override
	public void deleteParametro(int cod_parametro) throws Exception {
		parametroMapper.deleteParametro(cod_parametro);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String findParametro_byNombre(@Param("p_nombre_parametro") String nombre_parametro)
			throws Exception {
		// TODO Auto-generated method stub
		return parametroMapper.findParametro_byNombre(nombre_parametro); 
	}

	@Override
	public Parametro findByNombre(String nombre_parametro) throws Exception {
		return parametroMapper.findByNombre(nombre_parametro);
	}

} 
