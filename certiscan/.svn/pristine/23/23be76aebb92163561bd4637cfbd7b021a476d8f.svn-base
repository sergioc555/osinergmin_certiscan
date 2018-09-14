package com.certicom.certiscan.services;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Cliente;
import com.certicom.certiscan.mapper.ClienteMapper;

public class ClienteServices implements ClienteMapper {

	ClienteMapper clienteMapper = (ClienteMapper)ServiceFinder.findBean("clienteMapper");

	@Override
	public List<Cliente> findAll() throws Exception {
		return clienteMapper.findAll();
	}

	@Override
	public Cliente findById(@Param("p_rucCliente") String clienteRuc)
			throws Exception {
		return clienteMapper.findById(clienteRuc);
	}

	@Override
	public void eliminarCliente(@Param("p_rucCliente") String clienteRuc)
			throws Exception {
		clienteMapper.eliminarCliente(clienteRuc);
	}

	@Override
	public void crearCliente(Cliente cliente) throws Exception {
		clienteMapper.crearCliente(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws Exception {
		clienteMapper.actualizarCliente(cliente);
	}
	
}
