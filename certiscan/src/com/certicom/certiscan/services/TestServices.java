package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Test;
import com.certicom.certiscan.mapper.TestMapper;

public class TestServices implements TestMapper{

	TestMapper testMapper = (TestMapper)ServiceFinder.findBean("testMapper");

	@Override
	public Test findById(Integer codigoTest)
			throws Exception {
		return testMapper.findById(codigoTest);
	}

	@Override
	public List<Test> findAll() throws Exception {
		// TODO Auto-generated method stub
		return testMapper.findAll();
	}

	@Override
	public void crearTest(Test test) throws Exception {
		// TODO Auto-generated method stub
		testMapper.crearTest(test);
	}

	@Override
	public void actualizarTest(Test test) throws Exception {
		testMapper.actualizarTest(test);
		
	}

	@Override
	public void eliminarTest(Integer cod_test) throws Exception {
		// TODO Auto-generated method stub
		testMapper.eliminarTest(cod_test);
	}
}
