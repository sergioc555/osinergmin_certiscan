package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.ContactoEmpresa;
import com.certicom.certiscan.mapper.ContactoEmpresaMapper;

public class ContactoEmpresaService implements ContactoEmpresaMapper{

	ContactoEmpresaMapper contactoEmpresaMapper = (ContactoEmpresaMapper) ServiceFinder.findBean("contactoEmpresaMapper");

	@Override
	public ContactoEmpresa findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return contactoEmpresaMapper.findById(id);
	}

	@Override
	public List<ContactoEmpresa> findAll() throws Exception {
		// TODO Auto-generated method stub
		return contactoEmpresaMapper.findAll();
	}

	@Override
	public List<ContactoEmpresa> findbyUsuario(Integer usuario_registro)
			throws Exception {
		// TODO Auto-generated method stub
		return contactoEmpresaMapper.findbyUsuario(usuario_registro);
	}

	@Override
	public List<ContactoEmpresa> findbyExpedienteProducto(
			Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		return contactoEmpresaMapper.findbyExpedienteProducto(id_expediente_producto);
	}

	@Override
	public void crearContactoEmpresa(ContactoEmpresa contactoEmpresa)
			throws Exception {
		// TODO Auto-generated method stub
		contactoEmpresaMapper.crearContactoEmpresa(contactoEmpresa);
	}

	@Override
	public void actualizarContactoEmpresa(ContactoEmpresa contactoEmpresa)
			throws Exception {
		// TODO Auto-generated method stub
		contactoEmpresaMapper.actualizarContactoEmpresa(contactoEmpresa);
	}

	@Override
	public void eliminarContactoEmpresa(Integer id) throws Exception {
		// TODO Auto-generated method stub
		contactoEmpresaMapper.eliminarContactoEmpresa(id);
	}
}
