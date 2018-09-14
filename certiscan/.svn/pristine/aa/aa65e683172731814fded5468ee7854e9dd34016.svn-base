package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Cargo;
import com.certicom.certiscan.domain.Test;
import com.certicom.certiscan.mapper.CargoMapper;
import com.certicom.certiscan.mapper.TestMapper;

public class CargoServices implements CargoMapper{

	CargoMapper cargoMapper = (CargoMapper)ServiceFinder.findBean("cargoMapper");

	@Override
	public Cargo findById(Integer codigoCargo) throws Exception {
		// TODO Auto-generated method stub
		return cargoMapper.findById(codigoCargo);
	}

	@Override
	public List<Cargo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return cargoMapper.findAll();
	}

	@Override
	public void crearCargo(Cargo cargo) throws Exception {
		// TODO Auto-generated method stub
		cargoMapper.crearCargo(cargo);
	}

	@Override
	public void actualizarCargo(Cargo cargo) throws Exception {
		// TODO Auto-generated method stub
		cargoMapper.actualizarCargo(cargo);
	}

	@Override
	public void eliminarCargo(Integer cod_estado) throws Exception {
		// TODO Auto-generated method stub
		cargoMapper.eliminarCargo(cod_estado);
	}
	
}
