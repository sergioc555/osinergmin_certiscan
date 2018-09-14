package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CitaContacto;
import com.certicom.certiscan.mapper.CitaContactoMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.ExpedienteItem;

public class CitaContactoService implements CitaContactoMapper{

	CitaContactoMapper citaMapper = (CitaContactoMapper)ServiceFinder.findBean("citaContactoMapper");
	
	@Override
	public CitaContacto findById(Integer citaContactoId) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.findById(citaContactoId);
	}

	@Override
	public List<CitaContacto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.findAll();
	}

	@Override
	public void crearCitaContacto(CitaContacto direccionContacto)
			throws Exception {
		// TODO Auto-generated method stub
		citaMapper.crearCitaContacto(direccionContacto);
	}

	@Override
	public void eliminarCitaContacto(Integer citaContacotId) throws Exception {
		// TODO Auto-generated method stub
		citaMapper.eliminarCitaContacto(citaContacotId);
	}

	@Override
	public List<CitaContacto> findbyExpedienteProducto(Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.findbyExpedienteProducto(id_expediente_producto);
	}
	
	@Override
	public List<CitaContacto> findbyUsuario(Integer usuarioid) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.findbyUsuario(usuarioid);
	}

	@Override
	public void actualizarCitaContacto(CitaContacto citaContacto)
			throws Exception {
		// TODO Auto-generated method stub
		citaMapper.actualizarCitaContacto(citaContacto);
	}

	@Override
	public List<ExpedienteItem> listarCitasByProductoFecha(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.listarCitasByProductoFecha(filter);
	}

	@Override
	public void actualizarEstadoToValidado(Integer citaContacotId)
			throws Exception {
		// TODO Auto-generated method stub
		citaMapper.actualizarEstadoToValidado(citaContacotId);
	}

	@Override
	public void actualizarIndCita(CitaContacto citaContacto) throws Exception {
		// TODO Auto-generated method stub
		citaMapper.actualizarIndCita(citaContacto);
	}

	@Override
	public List<ExpedienteItem> listarEmpresasHojaRuta(Date periodo,
			Date fecha_cita, Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.listarEmpresasHojaRuta(periodo, fecha_cita, idusuario);
	}

	@Override
	public List<ExpedienteItem> listarDetalleHojaRuta() throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.listarDetalleHojaRuta();
	}

	@Override
	public List<ExpedienteItem> listarCantCitasGroupByUsuarioFecha(
			Integer id_producto, Date fini, Date ffin) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.listarCantCitasGroupByUsuarioFecha(id_producto, fini, ffin);
	}

	@Override
	public List<ExpedienteItem> listDetalleCitasByUsuario(Integer id_producto,
			Date fec_cita, Integer id_usuario) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.listDetalleCitasByUsuario(id_producto, fec_cita, id_usuario);
	}

	@Override
	public List<ExpedienteItem> listarCantCitasGroupByUsuarioVALIDADOgps(
			Integer id_producto, Date fini, Date ffin) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.listarCantCitasGroupByUsuarioVALIDADOgps(id_producto, fini, ffin);
	}

	@Override
	public List<CitaContacto> findByEjecutivo(Integer usuarioid,
			Integer id_vendedor) throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.findByEjecutivo(usuarioid, id_vendedor);
	}

	@Override
	public List<CitaContacto> findByVendedor(Integer id_vendedor)
			throws Exception {
		// TODO Auto-generated method stub
		return citaMapper.findByVendedor(id_vendedor);
	}

}
