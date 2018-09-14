package com.certicom.certiscan.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.ExpedienteProducto;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.VGeneric;

public interface ExpedienteProductoMapper {

	@Select("select xp.id_expediente_producto, ex.expediente_id expedienteid, " + "us.idusuario usuarioid,pr.id_producto productoid,ex.periodo,xp.estado,xp.fecha_asignacion " + "from t_expediente ex, t_producto pr, t_usuario us, t_expediente_producto xp " + "where " + "ex.expediente_id=xp.expediente_id and " + "pr.id_producto = xp.id_producto and " + "us.idusuario = xp.idusuario and " + "ex.periodo=#{periodo} and " + "us.idusuario = #{p_idusuario} order by ex.prestamo_soles desc")
	public List<ExpedienteProducto> buscarExpedientePorPromotor(@Param("periodo") Date periodo, @Param("p_idusuario") Integer p_idusuario) throws Exception;

	@Update("update t_expediente_producto  set id_estado = #{p_estado} where id_expediente_producto = #{id_expediente_producto}")
	@Options(flushCache = true, useCache = true)
	public void cambiarEstadoExpedientePromotor(@Param("p_estado") Integer estado, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;
	
	@Update("update t_expediente_producto  set flgcklist = #{p_flgckList} where id_expediente_producto = #{id_expediente_producto}")
	@Options(flushCache = true, useCache = true)
	public void cambiarEstadoExpedienteCheckList(@Param("p_flgckList") Boolean estado, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;

	@Update("update t_expediente_producto  set id_estado = #{p_estado}, id_categoria_call=#{id_categoria_call}, id_indicadores_call = #{id_indicadores_call} where id_expediente_producto = #{id_expediente_producto}")
	@Options(flushCache = true, useCache = true)
	public void cambiarEstadoBaseExpedientePromotor(@Param("p_estado") Integer estado, @Param("id_categoria_call") Integer id_categoria_call, @Param("id_indicadores_call") Integer id_indicadores_call, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;

	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATOR(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base) throws Exception;

	public Integer getCountExpedientePorPromotor(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base) throws Exception;

	public ExpedienteProducto buscarExpedientePorPromotorPAGINATORxUBIGEOxIdExpedienteProducto(@Param("idExpedienteProducto") Integer idExpedienteProducto) throws Exception;
	
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEO(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad,
			@Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack, @Param("des_tipoplanilla") String des_tipoplanilla, @Param("cod_perfil") Integer cod_perfil, @Param("f_buro") String buro ) throws Exception;

	public Integer getCountExpedientePorPromotorxUBIGEO(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack,@Param("des_tipoplanilla") String des_tipoplanilla,@Param("cod_perfil") Integer cod_perfil,@Param("f_buro") String buro ) throws Exception;
	
	public Integer getCountExpedientePorPromotorxUBIGEOCant(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack,@Param("des_tipoplanilla") String des_tipoplanilla,@Param("cod_perfil") Integer cod_perfil) throws Exception;
	
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTE(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("sdniS") String sdniS) throws Exception;
	
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTExDOCU(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("sdniS") String sdniS) throws Exception;
	
	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORxUBIGEOxEXPEDIENTExDOCU2(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("sdniS") String sdniS) throws Exception;
		
	public Integer getCountExpedientePorPromotorxUBIGEOxEXPEDIENTE(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("sdniS") String sdniS) throws Exception;

	public Integer getCountExpedientePorPromotorxUBIGEOxEXPEDIENTExDOCU(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("sdniS") String sdniS) throws Exception;
	
	public Integer getCountExpedientePorPromotorxUBIGEOxEXPEDIENTExALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad) throws Exception;

	public BigDecimal sumaExpedientePorPromotorxUBIGEOxEXPEDIENTExMONTOAPROBADOxALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad) throws Exception;

	public BigDecimal sumaExpedientePorPromotorxUBIGEOxEXPEDIENTExMONTODESEMBOLSADOxALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad) throws Exception;

	public List<ExpedienteProducto> buscarExpedientePorSupervisorPAGINATOR(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder) throws Exception;

	public Integer getCountExpedientePorSupervisor(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("filters") Map<String, Object> filters) throws Exception;

	public Integer getCountExpedientePorSupervisorALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto) throws Exception;

	public Integer getCountExpedientePorSupervisorTODOS(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto) throws Exception;

	public BigDecimal getSumaMontoExpedientePorSupervisorTODOS(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto) throws Exception;

	public Integer getCountExpedientePorSupervisorTODOSxREGION(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito) throws Exception;

	public BigDecimal getSumaMontoExpedientePorSupervisorTODOSxREGION(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito) throws Exception;

	public List<ExpedienteProducto> getExpedientesPorSupervisorTODOSxREGIONxOFICINA(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito) throws Exception;

	public List<ExpedienteProducto> getExpedientesPorSupervisorxTERRITORIO(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto) throws Exception;

	public List<ExpedienteProducto> getOficinasxTerritorioxAsignarSupervisor(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("descripcionTerritorios") List<String> descripcionTerritorios) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas) throws Exception;

	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorIgualALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMenorIgualALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorIgualALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public BigDecimal sumaMontoExpedientesPorSupervisorNoAsignadosxPeriodoRegionxOficinaMontoMayorIgualALL(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("oficinas") List<String> oficinas, @Param("monto") BigDecimal monto) throws Exception;

	public Integer getCountExpedientePorUsuarioByEstadoANT(@Param("periodo") Date periodo, @Param("e_idusuario") Integer p_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("estadoDescripcion") String estadoDescripcion, @Param("e_producto") Integer idproducto) throws Exception;
	
	public ExpedienteProducto getCountExpedientePorUsuarioByEstado(@Param("periodo") Date periodo, @Param("e_idusuario") Integer p_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("resultados1") List<String> estadoDescripcion, @Param("e_producto") Integer idproducto) throws Exception;
	
	@Select("select count(*) " + " from t_expediente_producto expedienteProducto" + " inner join t_expediente expediente on expedienteProducto.expediente_id=expediente.expediente_id" + " where " + "	expediente.periodo=#{periodo} and expedienteProducto.idusuario = #{p_idusuario}  and expedienteProducto.id_estado = #{estado}")
	public Integer getCountExpedientePorPromotorByEstado(@Param("periodo") Date periodo, @Param("p_idusuario") Integer p_idusuario, @Param("estado") Integer estado) throws Exception;

	@Select("select * from t_expediente_producto where " + " id_producto=#{idProducto} and periodo=#{periodo} LIMIT 5")
	public List<ExpedienteProducto> findByPeriodoByProductoLIMITADO(@Param("idProducto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;

	public List<ExpedienteProducto> buscarExpedAsignadosxPeriodoNegocio(ExpedienteProducto ep) throws Exception;

	public List<ExpedienteProducto> buscarExpedAsignadosxPeriodoNegocioOfi(ExpedienteProducto ep) throws Exception;

	@Update("update t_expediente_producto  set idusuario = #{idusuario} where id_expediente_producto = #{id_expediente_producto}")
	@Options(flushCache = true, useCache = true)
	public void reasignarUsuario(@Param("idusuario") Integer idusuario, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;

	@Select("select expedienteProducto.* " + " from t_expediente_producto expedienteProducto" + " inner join t_expediente expediente on expedienteProducto.expediente_id=expediente.expediente_id" + " where " + " expediente.periodo=#{periodo} and expedienteProducto.idusuario = #{p_idusuario}  and expediente.ruc = #{ruc}" + " and expediente.id_producto=#{idProducto} and expediente.tipo_base=#{tipoBase}" + " LIMIT 1")
	public ExpedienteProducto findByRucByIdUsuarioByProductoByPeriodoByTipoBaseLIMITADO(@Param("periodo") Date periodo, @Param("p_idusuario") Integer p_idusuario, @Param("ruc") String ruc, @Param("idProducto") Integer idProducto, @Param("tipoBase") String tipoBase) throws Exception;

	public ExpedienteProducto findByExpedienteULTIMO(@Param("p_idusuario") Integer idusuario, @Param("p_expedienteId") Integer p_expedienteId, @Param("p_productoId") Integer p_productoId, @Param("periodo") Date periodo) throws Exception;

	public List<ExpedienteProducto> findByPeriodoByPeriodoByNumDoc(@Param("periodo") Date periodo, @Param("numDoc") String numDoc, @Param("id_negocio") Integer id_negocio, @Param("idusuario") Integer idusuario) throws Exception;

	public List<ExpedienteProducto> findByPeriodoByProductoByPeriodoByNumDoc(@Param("periodo") Date periodo, @Param("idProducto") Integer idProducto, @Param("numDoc") String numDoc, @Param("idusuario") Integer idusuario) throws Exception;

	public List<ExpedienteProducto> rptReporteVip(ExpedienteFilter filter) throws Exception;

	public List<ExpedienteProducto> rptSemaforo(ExpedienteFilter filter) throws Exception;

	public Integer getCountExpedienteCountPAGINATOR(@Param("filters") Map<String, Object> filters) throws Exception;

	public List<ExpedienteProducto> buscarExpedienteCountPAGINATOR(@Param("periodo") Date periodo, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("idnegocio") Integer idnegocio, @Param("estado_envioph") String estado_envioph,@Param("filter_fecha") String filter_fecha, @Param("filter_datefecha")Date filter_datefecha) throws Exception;

	public Integer contarExpedienteCountPAGINATORPH(@Param("periodo") Date periodo, @Param("filters") Map<String, Object> filters, @Param("idnegocio") Integer idnegocio, @Param("estado_envioph") String estado_envioph, @Param("filter_fecha") String filter_fecha,@Param("filter_datefecha") Date filter_datefecha) throws Exception;

	public List<ExpedienteProducto> rptGestionLlamada(ExpedienteFilter filter) throws Exception;

	public List<ExpedienteProducto> rptExportGestionLlamada(ExpedienteFilter filter) throws Exception;

	public List<ExpedienteProducto> expAsignadosxEjecutivo(ExpedienteFilter filter) throws Exception;

	public List<ExpedienteProducto> rptExportExpPresentadosByEjecutivo(ExpedienteFilter filter) throws Exception;

	@Update("update t_expediente_producto set periodo=#{periodo}, id_producto=#{id_producto}, id_indicadores_call=#{id_indicadores_call}" + " where id_expediente_producto = #{id_expediente_producto}")
	@Options(flushCache = true, useCache = true)
	public void actualizarExpedienteProducto(@Param("periodo") Date periodo, @Param("id_producto") Integer id_producto, @Param("id_expediente_producto") Integer id_expediente_producto, @Param("id_indicadores_call") Integer id_indicadores_call);

	public List<ExpedienteProducto> buscarExpedientePorPromotorPAGINATORReferido(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder) throws Exception;

	public Integer getCountExpedientePorPromotorReferido(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters) throws Exception;

	public List<ExpedienteProducto> cantidadxProducto(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("idNegocio") Integer idNegocio, @Param("e_idusuarioBack") Integer idusuario_back) throws Exception;

	@Delete("delete  from t_expediente_producto  where id_expediente_producto = #{id_expediente_producto}")
	@Options(flushCache = true)
	public void eliminarExpedienteProducto(ExpedienteProducto expedienteProducto) throws Exception;

	public List<ExpedienteProducto> rptRankingLLamadas(ExpedienteFilter filter) throws Exception;

	@Select("select count(ep.id_expediente_producto) from t_expediente_producto ep inner join t_expediente ex on ep.expediente_id = ex.expediente_id inner join  t_estado es " + "on es.id_estado = ep.id_estado where  ex.periodo = #{periodo} and ex.id_producto = #{id_producto} and  ex.estado_general=true  " + "and es.descripcion = #{des_estado} ")
	public Integer countBasesByEstados(@Param("periodo") Date periodo, @Param("id_producto") Integer idproducto, @Param("des_estado") String des_estado) throws Exception;

	public List<ExpedienteProducto> rptProduccionEjecutivo(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad) throws Exception;

	public List<ExpedienteProducto> rptRankingLLamdasxEjecutivoProducto(ExpedienteFilter filter) throws Exception;

	@Select("select count(expediente.expediente_id) from t_expediente_producto expedienteProducto 	inner join t_expediente expediente 	on expedienteProducto.expediente_id=expediente.expediente_id  	inner join t_producto p 	on expedienteProducto.id_producto = p.id_producto 	 where 		  expediente.periodo=#{periodo} and 		  expedienteProducto.idusuario = #{idPromotor}  " + "			  and expediente.ruc = #{ruc} " + "			  and p.id_negocio= #{idNegocio}  " + "			  and expediente.segmento= #{segmento} and expedienteProducto.id_producto = #{id_producto} ")
	public Integer buscarConcurrenciaCargadeBolsa(@Param("periodo") Date periodo, @Param("idPromotor") Integer idPromotor, @Param("ruc") String ruc, @Param("idNegocio") Integer idNegocio, @Param("segmento") String segmento, @Param("id_producto") Integer id_producto) throws Exception;

	@Update("update t_expediente_producto set id_producto = #{productoid}, idusuario = #{idusuario} where id_expediente_producto = #{id_expediente_producto} and expediente_id = #{expedienteid}")
	public void actualizarExpedienteProductoBase(ExpedienteProducto expedienteProducto) throws Exception;

	public List<ExpedienteProducto> expedientesPorSupervisorNoAsignadosxPeriodoxProductoxTerritorioPYMES(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("oficinas") List<String> oficinas, @Param("descripcionTerritorios") List<String> descripcionTerritorios, @Param("monto") BigDecimal monto, @Param("tipoComparacion") Integer tipoComparacion) throws Exception;

	public BigDecimal sumaExpedientesPorSupervisorNoAsignadosxPeriodoxProductoxTerritorioPYMES(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("idProducto") Integer idProducto, @Param("oficinas") List<String> oficinas, @Param("descripcionTerritorios") List<String> descripcionTerritorios, @Param("monto") BigDecimal monto, @Param("tipoComparacion") Integer tipoComparacion) throws Exception;

	public List<ExpedienteProducto> buscarCuentasEnviadasBanco(@Param("periodo") Date periodo, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("idnegocio") Integer idnegocio, @Param("estado_envioph") String estado_envioph) throws Exception;

	public Integer contarCuentasEnviadasBanco(@Param("periodo") Date periodo, @Param("filters") Map<String, Object> filters, @Param("idnegocio") Integer idnegocio, @Param("estado_envioph") String estado_envioph) throws Exception;

	public List<VGeneric> rptEmpresaFiltrar(@Param("e_periodo") Date e_periodo, @Param("condicion_ph") String condicion_ph) throws Exception;

	public Integer countCuentasxEstadoEnvioxFiltros(@Param("periodo") Date periodo, @Param("idnegocio") Integer idnegocio, @Param("estado_envioph") String estado_envioph,@Param("filter_fecha") String filter_fecha,@Param("filter_datefecha") Date filter_datefecha) throws Exception;
	
	public List<ExpedienteProducto> buscarBasesAsigSupervisorxPeriodoNegocio(ExpedienteProducto expedienteProducto) throws Exception;
	
	
	@Update("update t_expediente_producto  set idusuario_supervisor = #{idusuario_supervisor} where id_expediente_producto = #{id_expediente_producto} and idusuario is null ")
	@Options(flushCache = true, useCache = true)
	public void reasignarSupervisor(@Param("idusuario_supervisor") Integer idusuario_supervisor, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;
	
	
	public List<ExpedienteProducto> buscarBasesAsigEjecutivosxSupervisor(ExpedienteProducto expedienteProducto) throws Exception;
	
	@Update("update t_expediente_producto  set idusuario = #{idusuario} where id_expediente_producto = #{id_expediente_producto} and id_indicadores_call = 1065 ")
	@Options(flushCache = true, useCache = true)
	public void reasignarUsuarioPYMES(@Param("idusuario") Integer idusuario, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;
	
	public List<ExpedienteProducto> validandoOpercionRepet(@Param("nombre_empresa") String nombre_empresa,@Param("num_doc")String num_doc,@Param("id_producto") Integer id_producto,@Param("prestamo_soles") BigDecimal prestamo_soles,@Param("importe_dolares") BigDecimal importe_dolares, @Param("id_supervisor")Integer id_supervisor,@Param("idusuario")Integer idusuario,@Param("periodo") Date periodo)throws Exception;
	
	public List<ExpedienteProducto> obtenerExpedientebyProductobyPeriodo(@Param("id_negocio") Integer id_negocio,@Param("id_producto") Integer id_producto,@Param("periodo") Date periodo)throws Exception;

	
	
	public List<String> buscarExpedientePorPromotorPAGINATORxUBIGEO_rucs(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS,  @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad,
			@Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack) throws Exception;

	public List<VGeneric> buscarExpedientePorSupervisorVGeneric(@Param("e_periodo") Date e_periodo, @Param("idNegocio") Integer idNegocio, @Param("ruc") String ruc) throws Exception;
	
	
	@Update("update t_expediente_producto  set idusuario = #{idusuario} where id_expediente_producto = #{id_expediente_producto} ")
	@Options(flushCache = true, useCache = true)
	public void updateUsuarioAsignado(@Param("idusuario") Integer idusuario, @Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;
	
	
	public List<ExpedienteProducto> findByPeriodoByProductoByPeriodoByRUC(@Param("periodo") Date periodo, @Param("idProducto") Integer idProducto, @Param("ruc") String ruc, @Param("idusuario") Integer idusuario) throws Exception;
	
	
	@Select("select * from t_expediente_producto where expediente_id = #{expediente_id} ")
	public List<ExpedienteProducto> findByExpediente(@Param("expediente_id") Integer expediente_id) throws Exception;
	
	public List<ExpedienteProducto> buscarExpeAsigDepuracionxProductoNegocio(ExpedienteProducto ep) throws Exception;
	
	
	
	public ExpedienteProducto findById(@Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;
	
	
	@Update("update t_expediente_producto set id_estado =#{id_estado}, "
			+ "id_indicadores_call= #{id_indicadores_call},  id_categoria_call =#{id_categoria_call} , idusuario = #{idusuario} "
			+ "where id_expediente_producto = #{id_expediente_producto}")
	public void actualizarEstadoIndicador(@Param("id_estado") Integer id_estado,@Param("id_indicadores_call") Integer id_indicadores_call,
			@Param("id_categoria_call") Integer id_categoria_call,@Param("id_expediente_producto") Integer id_expediente_producto,
			@Param("idusuario") Integer idusuario) throws Exception;
	

	/** Consulta Venta Banca Persona */
	
	
	public Integer getCountVentasBancaPersona(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack,@Param("des_tipoplanilla") String des_tipoplanilla,@Param("cod_perfil") Integer cod_perfil,@Param("f_buro") String buro ) throws Exception;
	public List<ExpedienteProducto> getListVentasBancaPersona(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad,
			@Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack, @Param("des_tipoplanilla") String des_tipoplanilla, @Param("cod_perfil") Integer cod_perfil, @Param("f_buro") String buro ) throws Exception;

	/** Consulta Venta Consesionario */
	
	public Integer getCountVentasConcesionario(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack,@Param("des_tipoplanilla") String des_tipoplanilla,@Param("cod_perfil") Integer cod_perfil,@Param("f_buro") String buro ) throws Exception;
	public List<ExpedienteProducto> getListVentasConcesionario(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad,
			@Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack, @Param("des_tipoplanilla") String des_tipoplanilla, @Param("cod_perfil") Integer cod_perfil, @Param("f_buro") String buro ) throws Exception;

	/** Consulta Venta PYMES */
	
	public Integer getCountVentasPYMES(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("filters") Map<String, Object> filters, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad, @Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack,@Param("des_tipoplanilla") String des_tipoplanilla,@Param("cod_perfil") Integer cod_perfil,@Param("f_buro") String buro ) throws Exception;
	public List<ExpedienteProducto> getListVentasPYMES(@Param("e_periodo") Date e_periodo, @Param("e_idusuario") Integer e_idusuario, @Param("e_idusuarioC") Integer e_idusuarioC, @Param("e_idusuarioS") Integer e_idusuarioS, @Param("first") Integer first, @Param("pageSize") Integer pageSize, @Param("filters") Map<String, Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_productoS") Integer id_producto, @Param("tipo_baseS") String tipo_base, @Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito, @Param("idNegocio") Integer idNegocio, @Param("idUsuario") Integer idUsuario, @Param("f_prioridad") String f_prioridad,
			@Param("des_estadoS") String des_estado, @Param("prioridadAtencion") String prioridadAtencion, @Param("f_segmento") String f_segmento, @Param("e_idusuarioBack") Integer e_idusuarioBack, @Param("des_tipoplanilla") String des_tipoplanilla, @Param("cod_perfil") Integer cod_perfil, @Param("f_buro") String buro ) throws Exception;

	
}
