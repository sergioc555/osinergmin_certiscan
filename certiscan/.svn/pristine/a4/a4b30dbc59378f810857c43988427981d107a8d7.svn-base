package com.certicom.certiscan.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.ExpedienteProducto;
import com.certicom.certiscan.mapper.ExpedienteProductoMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.VGeneric;

public class ExpedienteProductoService implements ExpedienteProductoMapper {

	ExpedienteProductoMapper expedienteProdMapper = (ExpedienteProductoMapper) ServiceFinder.findBean("expedienteProductoMapper");

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotor(Date periodo, Integer p_idusuario) throws Exception {
		return expedienteProdMapper.buscarExpedientePorPromotor(periodo, p_idusuario);
	}

	@Override
	public void cambiarEstadoExpedientePromotor(Integer estado, Integer id_expediente_producto) throws Exception {
		expedienteProdMapper.cambiarEstadoExpedientePromotor(estado, id_expediente_producto);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATOR(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, Integer id_producto, String tipo_base) throws Exception {
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATOR(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEO(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad, String des_estado, String prioridadAtencion, String segmento, Integer e_idusuarioBack,String des_tipoplanilla,Integer cod_perfil, String f_buro ) throws Exception {
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";  
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORxUBIGEO(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, segmento, e_idusuarioBack,des_tipoplanilla,cod_perfil, f_buro);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTE(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad, String sdniS) throws Exception {
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTE(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad,sdniS);
	}

	@Override
	public Integer getCountExpedientePorPromotor(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Map<String, Object> filters, Integer id_producto, String tipo_base) throws Exception {
		return expedienteProdMapper.getCountExpedientePorPromotor(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base);
	}

	@Override
	public Integer getCountExpedientePorPromotorxUBIGEO(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Map<String, Object> filters, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad, String des_estado, String prioridadAtencion, String segmento, Integer idusuario_back, String des_tipoplanilla,Integer cod_perfil, String f_buro ) throws Exception {
		return expedienteProdMapper.getCountExpedientePorPromotorxUBIGEO(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, segmento, idusuario_back, des_tipoplanilla,cod_perfil,f_buro);
	}

	@Override
	public Integer getCountExpedientePorPromotorxUBIGEOxEXPEDIENTE(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Map<String, Object> filters, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad, String sdniS) throws Exception {
		return expedienteProdMapper.getCountExpedientePorPromotorxUBIGEOxEXPEDIENTE(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, sdniS);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorSupervisorPAGINATOR(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder) throws Exception {
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedientePorSupervisorPAGINATOR(e_periodo, e_idusuario, e_idusuarioC, first, pageSize, filters, sortField, sortOrder);
	}

	@Override
	public Integer getCountExpedientePorSupervisor(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Map<String, Object> filters) throws Exception {
		return expedienteProdMapper.getCountExpedientePorSupervisor(e_periodo, e_idusuario, e_idusuarioC, filters);
	}

	@Override
	public Integer getCountExpedientePorSupervisorTODOS(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto) throws Exception {
		return expedienteProdMapper.getCountExpedientePorSupervisorTODOS(e_periodo, e_idusuario, e_idusuarioC, idProducto);
	}

	@Override
	public Integer getCountExpedientePorSupervisorTODOSxREGION(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.getCountExpedientePorSupervisorTODOSxREGION(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorIgualALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorIgualALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorIgualALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorIgualALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorIgualALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorIgualALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorIgualALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorIgualALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas);
	}

	@Override
	public BigDecimal getSumaMontoExpedientePorSupervisorTODOSxREGION(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.getSumaMontoExpedientePorSupervisorTODOSxREGION(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito, List<String> oficinas, BigDecimal monto) throws Exception {
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";

		return expedienteProdMapper.sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoALL(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito, oficinas, monto);
	}

	@Override
	public BigDecimal getSumaMontoExpedientePorSupervisorTODOS(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto) throws Exception {
		return expedienteProdMapper.getSumaMontoExpedientePorSupervisorTODOS(e_periodo, e_idusuario, e_idusuarioC, idProducto);
	}

	@Override
	public List<ExpedienteProducto> findByPeriodoByProductoLIMITADO(Integer idProducto, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByPeriodoByProductoLIMITADO(idProducto, periodo);
	}

	@Override
	public Integer getCountExpedientePorPromotorByEstado(Date periodo, Integer p_idusuario, Integer estado) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountExpedientePorPromotorByEstado(periodo, p_idusuario, estado);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedAsignadosxPeriodoNegocio(ExpedienteProducto ep) throws Exception {
		return expedienteProdMapper.buscarExpedAsignadosxPeriodoNegocio(ep);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedAsignadosxPeriodoNegocioOfi(ExpedienteProducto ep) throws Exception {
		return expedienteProdMapper.buscarExpedAsignadosxPeriodoNegocioOfi(ep);
	}

	@Override
	public void reasignarUsuario(Integer idusuario, Integer id_expediente_producto) throws Exception {
		expedienteProdMapper.reasignarUsuario(idusuario, id_expediente_producto);
	}

	@Override
	public ExpedienteProducto getCountExpedientePorUsuarioByEstado(Date periodo, Integer p_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, List<String> estadoDescripcion, Integer idproducto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountExpedientePorUsuarioByEstado(periodo, p_idusuario, e_idusuarioC, e_idusuarioS, estadoDescripcion, idproducto);
	}

	@Override
	public ExpedienteProducto findByRucByIdUsuarioByProductoByPeriodoByTipoBaseLIMITADO(Date periodo, Integer p_idusuario, String ruc, Integer idProducto, String tipoBase) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByRucByIdUsuarioByProductoByPeriodoByTipoBaseLIMITADO(periodo, p_idusuario, ruc, idProducto, tipoBase);
	}

	@Override
	public List<ExpedienteProducto> findByPeriodoByPeriodoByNumDoc(Date periodo, String numDoc, Integer id_negocio, Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByPeriodoByPeriodoByNumDoc(periodo, numDoc, id_negocio, idusuario);
	}

	@Override
	public List<ExpedienteProducto> findByPeriodoByProductoByPeriodoByNumDoc(Date periodo, Integer idProducto, String numDoc, Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByPeriodoByProductoByPeriodoByNumDoc(periodo, idProducto, numDoc, idusuario);
	}

	@Override
	public ExpedienteProducto findByExpedienteULTIMO(Integer idusuario, Integer p_expedienteId, Integer p_productoId, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByExpedienteULTIMO(idusuario, p_expedienteId, p_productoId, periodo);
	}

	@Override
	public void cambiarEstadoBaseExpedientePromotor(Integer estado, Integer id_categoria_call, Integer id_indicadores_call, Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		expedienteProdMapper.cambiarEstadoBaseExpedientePromotor(estado, id_categoria_call, id_indicadores_call, id_expediente_producto);
	}

	@Override
	public List<ExpedienteProducto> getExpedientesPorSupervisorTODOSxREGIONxOFICINA(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, String departamento, String provincia, String distrito) throws Exception {
		// TODO Auto-generated method stub
		departamento = "%" + departamento.trim() + "%";
		provincia = "%" + provincia.trim() + "%";
		distrito = "%" + distrito.trim() + "%";
		return expedienteProdMapper.getExpedientesPorSupervisorTODOSxREGIONxOFICINA(e_periodo, e_idusuario, e_idusuarioC, idProducto, departamento, provincia, distrito);
	}

	@Override
	public Integer getCountExpedientePorSupervisorALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountExpedientePorSupervisorALL(e_periodo, e_idusuario, e_idusuarioC, idProducto);
	}

	@Override
	public List<ExpedienteProducto> rptReporteVip(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.rptReporteVip(filter);
	}

	@Override
	public List<ExpedienteProducto> rptSemaforo(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.rptSemaforo(filter);
	}

	@Override
	public Integer getCountExpedienteCountPAGINATOR(Map<String, Object> filters) throws Exception {
		return expedienteProdMapper.getCountExpedienteCountPAGINATOR(filters);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedienteCountPAGINATOR(Date periodo, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, Integer idnegocio, String estado_envioph, String filter_fecha, Date filter_datefecha) throws Exception {
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedienteCountPAGINATOR(periodo, first, pageSize, filters, sortField, sortOrder, idnegocio, estado_envioph, filter_fecha, filter_datefecha);
	}

	@Override
	public List<ExpedienteProducto> rptGestionLlamada(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.rptGestionLlamada(filter);
	}

	@Override
	public List<ExpedienteProducto> rptExportGestionLlamada(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.rptExportGestionLlamada(filter);
	}

	@Override
	public List<ExpedienteProducto> expAsignadosxEjecutivo(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.expAsignadosxEjecutivo(filter);
	}

	@Override
	public List<ExpedienteProducto> rptExportExpPresentadosByEjecutivo(ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.rptExportExpPresentadosByEjecutivo(filter);
	}

	public void actualizarExpedienteProducto(Date periodo, Integer id_producto, Integer id_expediente_producto, Integer id_indicadores_call) {
		this.expedienteProdMapper.actualizarExpedienteProducto(periodo, id_producto, id_expediente_producto, id_indicadores_call);

	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORReferido(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder) throws Exception {
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORReferido(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder);
	}

	@Override
	public Integer getCountExpedientePorPromotorReferido(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Map<String, Object> filters) throws Exception {
		return expedienteProdMapper.getCountExpedientePorPromotorReferido(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters);
	}

	@Override
	public List<ExpedienteProducto> cantidadxProducto(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer id_negocio, Integer idusuario_back) throws Exception {
		return expedienteProdMapper.cantidadxProducto(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_negocio, idusuario_back);
	}

	@Override
	public void eliminarExpedienteProducto(ExpedienteProducto expedienteProducto) throws Exception {
		this.expedienteProdMapper.eliminarExpedienteProducto(expedienteProducto);

	}

	@Override
	public List<ExpedienteProducto> rptRankingLLamadas(ExpedienteFilter filter) throws Exception {
		return expedienteProdMapper.rptRankingLLamadas(filter);
	}

	@Override
	public Integer getCountExpedientePorPromotorxUBIGEOxEXPEDIENTExALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad) throws Exception {
		return expedienteProdMapper.getCountExpedientePorPromotorxUBIGEOxEXPEDIENTExALL(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad);
	}

	@Override
	public BigDecimal sumaExpedientePorPromotorxUBIGEOxEXPEDIENTExMONTOAPROBADOxALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad) throws Exception {
		return expedienteProdMapper.sumaExpedientePorPromotorxUBIGEOxEXPEDIENTExMONTOAPROBADOxALL(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad);
	}

	@Override
	public BigDecimal sumaExpedientePorPromotorxUBIGEOxEXPEDIENTExMONTODESEMBOLSADOxALL(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad) throws Exception {
		return expedienteProdMapper.sumaExpedientePorPromotorxUBIGEOxEXPEDIENTExMONTODESEMBOLSADOxALL(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad);
	}

	@Override
	public Integer countBasesByEstados(Date periodo, Integer idproducto, String des_estado) throws Exception {
		return expedienteProdMapper.countBasesByEstados(periodo, idproducto, des_estado);
	}

	@Override
	public ExpedienteProducto buscarExpedientePorPromotorPAGINATORxUBIGEOxIdExpedienteProducto(Integer idExpedienteProducto) throws Exception {
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORxUBIGEOxIdExpedienteProducto(idExpedienteProducto);
	}

	@Override
	public List<ExpedienteProducto> rptProduccionEjecutivo(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS, Integer id_producto, String tipo_base, String departamento, String provincia, String distrito, Integer idNegocio, Integer idUsuario, String f_prioridad) throws Exception {
		return expedienteProdMapper.rptProduccionEjecutivo(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad);
	}

	@Override
	public List<ExpedienteProducto> rptRankingLLamdasxEjecutivoProducto(ExpedienteFilter filter) throws Exception {
		return expedienteProdMapper.rptRankingLLamdasxEjecutivoProducto(filter);
	}

	@Override
	public Integer contarExpedienteCountPAGINATORPH(Date periodo, Map<String, Object> filters, Integer idnegocio, String estado_envioph, String filter_fecha, Date filter_datefecha) throws Exception {
		return expedienteProdMapper.contarExpedienteCountPAGINATORPH(periodo, filters, idnegocio, estado_envioph, filter_fecha, filter_datefecha);
	}

	@Override
	public Integer buscarConcurrenciaCargadeBolsa(Date periodo, Integer idPromotor, String ruc, Integer idNegocio, String segmento, Integer id_producto) throws Exception {
		return expedienteProdMapper.buscarConcurrenciaCargadeBolsa(periodo, idPromotor, ruc, idNegocio, segmento, id_producto);
	}

	@Override
	public void actualizarExpedienteProductoBase(ExpedienteProducto expedienteProducto) throws Exception {
		expedienteProdMapper.actualizarExpedienteProductoBase(expedienteProducto);
	}

	@Override
	public List<ExpedienteProducto> getExpedientesPorSupervisorxTERRITORIO(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getExpedientesPorSupervisorxTERRITORIO(e_periodo, e_idusuario, e_idusuarioC, idProducto);
	}

	@Override
	public List<ExpedienteProducto> getOficinasxTerritorioxAsignarSupervisor(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, List<String> descripcionTerritorios) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getOficinasxTerritorioxAsignarSupervisor(e_periodo, e_idusuario, e_idusuarioC, idProducto, descripcionTerritorios);
	}

	@Override
	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoxProductoxTerritorioPYMES(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, List<String> oficinas, List<String> descripcionTerritorios, BigDecimal monto, Integer tipoComparacion) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.expedientesPorSupervisorNoAsignadosxPeriodoxProductoxTerritorioPYMES(e_periodo, e_idusuario, e_idusuarioC, idProducto, oficinas, descripcionTerritorios, monto, tipoComparacion);
	}

	@Override
	public BigDecimal sumaExpedientesPorSupervisorNoAsignadosxPeriodoxProductoxTerritorioPYMES(Date e_periodo, Integer e_idusuario, Integer e_idusuarioC, Integer idProducto, List<String> oficinas, List<String> descripcionTerritorios, BigDecimal monto, Integer tipoComparacion) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.sumaExpedientesPorSupervisorNoAsignadosxPeriodoxProductoxTerritorioPYMES(e_periodo, e_idusuario, e_idusuarioC, idProducto, oficinas, descripcionTerritorios, monto, tipoComparacion);
	}

	@Override
	public List<ExpedienteProducto> buscarCuentasEnviadasBanco(Date periodo, Integer first, Integer pageSize, Map<String, Object> filters, String sortField, String sortOrder, Integer idnegocio, String estado_envioph) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.buscarCuentasEnviadasBanco(periodo, first, pageSize, filters, sortField, sortOrder, idnegocio, estado_envioph);
	}

	@Override
	public Integer contarCuentasEnviadasBanco(Date periodo, Map<String, Object> filters, Integer idnegocio, String estado_envioph) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.contarCuentasEnviadasBanco(periodo, filters, idnegocio, estado_envioph);
	}


	@Override
	public List<VGeneric> rptEmpresaFiltrar(Date e_periodo, String condicion_ph) throws Exception {
		return expedienteProdMapper.rptEmpresaFiltrar(e_periodo, condicion_ph);
	}


	@Override
	public Integer countCuentasxEstadoEnvioxFiltros(Date periodo,
			Integer idnegocio, String estado_envioph, String filter_fecha,
			Date filter_datefecha) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.countCuentasxEstadoEnvioxFiltros(periodo, idnegocio, estado_envioph, filter_fecha, filter_datefecha);
		
		
	}

	@Override
	public List<ExpedienteProducto> buscarBasesAsigSupervisorxPeriodoNegocio(
			ExpedienteProducto expedienteProducto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.buscarBasesAsigSupervisorxPeriodoNegocio(expedienteProducto);
	}

	@Override
	public void reasignarSupervisor(Integer idusuario_supervisor,
			Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		expedienteProdMapper.reasignarSupervisor(idusuario_supervisor, id_expediente_producto);
	}

	@Override
	public List<ExpedienteProducto> buscarBasesAsigEjecutivosxSupervisor(
			ExpedienteProducto expedienteProducto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.buscarBasesAsigEjecutivosxSupervisor(expedienteProducto);
	}

	@Override
	public void reasignarUsuarioPYMES(Integer idusuario,
			Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		expedienteProdMapper.reasignarUsuarioPYMES(idusuario, id_expediente_producto);
	}

	@Override
	public List<ExpedienteProducto> validandoOpercionRepet(
			String nombre_empresa, String num_doc, Integer id_producto,
			BigDecimal prestamo_soles, BigDecimal importe_dolares,
			Integer id_supervisor, Integer idusuario, Date periodo)
			throws Exception {
		return expedienteProdMapper.validandoOpercionRepet(nombre_empresa, num_doc, id_producto, prestamo_soles, importe_dolares, id_supervisor, idusuario, periodo);
	}

	@Override
	public List<ExpedienteProducto> obtenerExpedientebyProductobyPeriodo(Integer id_negocio, Integer id_producto, Date periodo) throws Exception {
		return expedienteProdMapper.obtenerExpedientebyProductobyPeriodo(id_negocio, id_producto, periodo);
	}

	@Override
	public List<String> buscarExpedientePorPromotorPAGINATORxUBIGEO_rucs(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad,
			@Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack) throws Exception{
		
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORxUBIGEO_rucs(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack);
	}

	@Override
	public List<VGeneric> buscarExpedientePorSupervisorVGeneric(@Param("e_periodo") Date e_periodo, @Param("idNegocio") Integer idNegocio, @Param("ruc") String ruc) throws Exception {
		return expedienteProdMapper.buscarExpedientePorSupervisorVGeneric(e_periodo, idNegocio, ruc);
	}

	@Override
	public void updateUsuarioAsignado(Integer idusuario,
			Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		expedienteProdMapper.updateUsuarioAsignado(idusuario, id_expediente_producto);
	}

	@Override
	public List<ExpedienteProducto> findByPeriodoByProductoByPeriodoByRUC(
			Date periodo, Integer idProducto, String ruc, Integer idusuario)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByPeriodoByProductoByPeriodoByRUC(periodo, idProducto, ruc, idusuario);
	}

	@Override
	public List<ExpedienteProducto> findByExpediente(Integer expediente_id)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findByExpediente(expediente_id);
	}

	@Override
	public List<ExpedienteProducto> buscarExpeAsigDepuracionxProductoNegocio(
			ExpedienteProducto ep) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.buscarExpeAsigDepuracionxProductoNegocio(ep);
	}

	@Override
	public ExpedienteProducto findById(Integer id_expediente_producto)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.findById(id_expediente_producto);
	}

	@Override
	public void actualizarEstadoIndicador(Integer id_estado,
			Integer id_indicadores_call, Integer id_categoria_call,
			Integer id_expediente_producto,Integer idusuario) throws Exception {
		// TODO Auto-generated method stub
		expedienteProdMapper.actualizarEstadoIndicador(id_estado, id_indicadores_call, id_categoria_call, id_expediente_producto,idusuario);
	}

	@Override
	public void cambiarEstadoExpedienteCheckList(Boolean estado,
			Integer id_expediente_producto) throws Exception {
		// TODO Auto-generated method stub
		expedienteProdMapper.cambiarEstadoExpedienteCheckList(estado, id_expediente_producto);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTExDOCU(
			Date e_periodo, Integer e_idusuario, Integer e_idusuarioC,
			Integer e_idusuarioS, Integer first, Integer pageSize,
			Map<String, Object> filters, String sortField, String sortOrder,
			Integer id_producto, String tipo_base, String departamento,
			String provincia, String distrito, Integer idNegocio,
			Integer idUsuario, String f_prioridad, String sdniS)
			throws Exception {
		// TODO Auto-generated method stub
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTExDOCU(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, sdniS);
	}

	@Override
	public Integer getCountExpedientePorPromotorxUBIGEOxEXPEDIENTExDOCU(
			Date e_periodo, Integer e_idusuario, Integer e_idusuarioC,
			Integer e_idusuarioS, Map<String, Object> filters,
			Integer id_producto, String tipo_base, String departamento,
			String provincia, String distrito, Integer idNegocio,
			Integer idUsuario, String f_prioridad, String sdniS)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountExpedientePorPromotorxUBIGEOxEXPEDIENTExDOCU(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, sdniS);
	}

	@Override
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTExDOCU2(
			Date e_periodo, Integer e_idusuario, Integer e_idusuarioC,
			Integer e_idusuarioS,
			Integer id_producto, String tipo_base, String departamento,
			String provincia, String distrito, Integer idNegocio,
			Integer idUsuario, String f_prioridad, String sdniS)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTExDOCU2(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, sdniS);
	}

	@Override
	public Integer getCountExpedientePorPromotorxUBIGEOCant(Date e_periodo,
			Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			Map<String, Object> filters, Integer id_producto, String tipo_base,
			String departamento, String provincia, String distrito,
			Integer idNegocio, Integer idUsuario, String f_prioridad,
			String des_estado, String prioridadAtencion, String f_segmento,
			Integer e_idusuarioBack, String des_tipoplanilla, Integer cod_perfil)
			throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountExpedientePorPromotorxUBIGEOCant(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil);
	}

	@Override
	public Integer getCountVentasBancaPersona(Date e_periodo,
			Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			Map<String, Object> filters, Integer id_producto, String tipo_base,
			String departamento, String provincia, String distrito,
			Integer idNegocio, Integer idUsuario, String f_prioridad,
			String des_estado, String prioridadAtencion, String f_segmento,
			Integer e_idusuarioBack, String des_tipoplanilla,
			Integer cod_perfil, String buro) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountVentasBancaPersona(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil, buro);
	}

	@Override
	public List<ExpedienteProducto> getListVentasBancaPersona(Date e_periodo,
			Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			Integer first, Integer pageSize, Map<String, Object> filters,
			String sortField, String sortOrder, Integer id_producto,
			String tipo_base, String departamento, String provincia,
			String distrito, Integer idNegocio, Integer idUsuario,
			String f_prioridad, String des_estado, String prioridadAtencion,
			String f_segmento, Integer e_idusuarioBack,
			String des_tipoplanilla, Integer cod_perfil, String buro)
			throws Exception {
		// TODO Auto-generated method stub
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";  
			else
				sortOrder = "DESC";
		}
		
		return expedienteProdMapper.getListVentasBancaPersona(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil, buro);
	}

	@Override
	public Integer getCountExpedientePorUsuarioByEstadoANT(Date periodo,
			Integer p_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			String estadoDescripcion, Integer idproducto) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountExpedientePorUsuarioByEstadoANT(periodo, p_idusuario, e_idusuarioC, e_idusuarioS, estadoDescripcion, idproducto);
	}

	@Override
	public Integer getCountVentasConcesionario(Date e_periodo,
			Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			Map<String, Object> filters, Integer id_producto, String tipo_base,
			String departamento, String provincia, String distrito,
			Integer idNegocio, Integer idUsuario, String f_prioridad,
			String des_estado, String prioridadAtencion, String f_segmento,
			Integer e_idusuarioBack, String des_tipoplanilla,
			Integer cod_perfil, String buro) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountVentasConcesionario(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil, buro);
	}

	@Override
	public List<ExpedienteProducto> getListVentasConcesionario(Date e_periodo,
			Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			Integer first, Integer pageSize, Map<String, Object> filters,
			String sortField, String sortOrder, Integer id_producto,
			String tipo_base, String departamento, String provincia,
			String distrito, Integer idNegocio, Integer idUsuario,
			String f_prioridad, String des_estado, String prioridadAtencion,
			String f_segmento, Integer e_idusuarioBack,
			String des_tipoplanilla, Integer cod_perfil, String buro)
			throws Exception {
		// TODO Auto-generated method stub
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";  
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.getListVentasConcesionario(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil, buro);
	}

	@Override
	public Integer getCountVentasPYMES(Date e_periodo, Integer e_idusuario,
			Integer e_idusuarioC, Integer e_idusuarioS,
			Map<String, Object> filters, Integer id_producto, String tipo_base,
			String departamento, String provincia, String distrito,
			Integer idNegocio, Integer idUsuario, String f_prioridad,
			String des_estado, String prioridadAtencion, String f_segmento,
			Integer e_idusuarioBack, String des_tipoplanilla,
			Integer cod_perfil, String buro) throws Exception {
		// TODO Auto-generated method stub
		return expedienteProdMapper.getCountVentasPYMES(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, filters, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil, buro);
	}

	@Override
	public List<ExpedienteProducto> getListVentasPYMES(Date e_periodo,
			Integer e_idusuario, Integer e_idusuarioC, Integer e_idusuarioS,
			Integer first, Integer pageSize, Map<String, Object> filters,
			String sortField, String sortOrder, Integer id_producto,
			String tipo_base, String departamento, String provincia,
			String distrito, Integer idNegocio, Integer idUsuario,
			String f_prioridad, String des_estado, String prioridadAtencion,
			String f_segmento, Integer e_idusuarioBack,
			String des_tipoplanilla, Integer cod_perfil, String buro)
			throws Exception {
		// TODO Auto-generated method stub}
		if (sortOrder != null) {
			if (sortOrder.equals("ASCENDING"))
				sortOrder = "ASC";  
			else
				sortOrder = "DESC";
		}
		return expedienteProdMapper.getListVentasPYMES(e_periodo, e_idusuario, e_idusuarioC, e_idusuarioS, first, pageSize, filters, sortField, sortOrder, id_producto, tipo_base, departamento, provincia, distrito, idNegocio, idUsuario, f_prioridad, des_estado, prioridadAtencion, f_segmento, e_idusuarioBack, des_tipoplanilla, cod_perfil, buro);
	}
	
	


}
