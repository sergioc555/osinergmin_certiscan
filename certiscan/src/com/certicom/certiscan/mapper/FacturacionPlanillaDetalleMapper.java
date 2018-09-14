package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.FacturacionPlanillaDetalle;

public interface FacturacionPlanillaDetalleMapper {

	@Select("select * from t_facturacion_planilla_detalle e where e.id_facturacion_planilla_detalle = #{p_codFacturacionPlanillaDetalle}")
	public FacturacionPlanillaDetalle findById(@Param("p_codFacturacionPlanillaDetalle") Integer codFacturacionPlanillaDetalle) throws Exception;
	
	@Select("(select d.id_facturacion_planilla_cabecera,car.descripcion as desCargo, tp.descripcion as desTipo_Planilla, u.cod_quality as cod_quality, u.idusuario as idusuario, u.dni as dni, (u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as usuarioAsignado, "
			+ "sum(CASE WHEN d.cod_subtipo=1 THEN d.costo_planilla_facturacion "
			+ "END) as montoComision, sum(CASE WHEN d.cod_subtipo=2 THEN d.costo_planilla_facturacion "
			+ "END) as montoComisionOperativo "
			+ "from (((t_facturacion_planilla_detalle d inner join t_facturacion_planilla_cabecera c "
			+ "on d.id_facturacion_planilla_cabecera = c.id_facturacion_planilla_cabecera) inner join "
			+ "t_usuario u on u.idusuario=d.idusuario) join t_cargo car on car.idcargo = u.idcargo) inner join t_tipo_planilla tp on tp.idtipo_planilla = u.idtipo_planilla "
			+ "where c.cod_ciclo=#{p_codCiclo} and d.idusuario not in (211,538,595,596,601,608,631,646,628,661,701,702,703,704,705,706,707,708,709,710,697,539,693,683,573,599) and "
			+ "u.estado = true and d.cod_expediente is not null and d.flgcomisionado = true "
			+ "group by u.cod_quality, usuarioAsignado, u.idusuario, u.dni,car.descripcion, tp.descripcion,d.id_facturacion_planilla_cabecera order by u.idusuario asc)"
			/*+ "union "
			+ "select distinct 0, car.descripcion as desCargo, '' as desTipo_Planilla, u.cod_quality as cod_quality, u.idusuario as idusuario, u.dni as dni, (u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as usuarioAsignado, "
			+ "0.0 as montoComision, 0.0 as montoComisionOperativo "
			+ "from t_usuario u inner join t_cargo car on u.idcargo = car.idcargo where u.idcargo in (2,3,5) and u.estado = true"*/)
	public List<FacturacionPlanillaDetalle> findByIdCiclo(@Param("p_codCiclo") Integer codCiclo) throws Exception;
	
	public List<FacturacionPlanillaDetalle> obtenerFueraPlanilla(FacturacionPlanillaDetalle fpd) throws Exception;
	
	@Select("select fpd.* from t_facturacion_planilla_detalle fpd where fpd.cod_ciclo=#{p_codCiclo}")
	public List<FacturacionPlanillaDetalle> findByIdCicloDetalle(@Param("p_codCiclo") Integer codCiclo) throws Exception;
	
	@Select("select fpd.* from t_facturacion_planilla_detalle fpd where fpd.id_facturacion_planilla_cabecera=#{p_id_facturacion_planilla_cabecera}")
	public List<FacturacionPlanillaDetalle> findByIdCabeceraDetalle(@Param("p_id_facturacion_planilla_cabecera") Integer idCabecera) throws Exception;
	
	/*@Select("select c.descripcion as desConcepto, (case when d.cod_canal = 1 then 1 else 2 end) as codConcepto, "+
			"count(case when d.cod_canal=2 then d.cod_canal end) as cantidadEmpresa,count(case when d.cod_canal=1 then d.cod_canal end) as cantidadPersona, "+
			"0 as cantidadReniec, d.cod_tab_com as codTabCom from (t_facturacion_planilla_detalle d inner join t_canales c on c.cod_canales=d.cod_canal) "+
			"inner join t_facturacion_planilla_cabecera ca on ca.id_facturacion_planilla_cabecera = d.id_facturacion_planilla_cabecera where ca.cod_ciclo=#{p_codCiclo} group by c.descripcion,codConcepto, d.cod_tab_com "+
			"union select 'RENIEC' as desConcepto,3 as codConcepto,0 as cantidadEmpresa,0 as cantidadPersona,  sum(CASE WHEN (e.ruta_imagen) is null THEN 0 ELSE 1 END) as cantidadReniec, 0 as codTabCom "+
			"from ((((((t_expediente e left join t_expediente_tracking tr on	tr.cod_expediente = e.cod_expediente) "+
			"inner join t_estado s on tr.cod_estado = s.cod_estado) inner join t_resultado r on tr.cod_resultado = r.cod_resultado) "+
			"inner join t_canales c on e.cod_canal = c.cod_canales)	inner join t_centros_atencion ca on ca.id_centro_atencion = e.idoficina) "+
			"inner join t_tipoorden tod on tod.cod_tipoorden = e.cod_tipoorden) inner join t_usuario usu on usu.idusuario = tr.idusuario "+
			"left join t_dealer de on de.cod_dealer = e.cod_recep_dealer "+
			"where cast(tr.fecha_reg as date) >= #{fec_ini} and cast(tr.fecha_reg as date) <= #{fec_fin} "+
			"and tr.cod_estado not in (1,3,20,19,16, 8, 9, 15) and tr.cod_resultado not in (1,8) and e.ruta_imagen is not null "+
			"union select 'REPORTERIA' as desConcepto, 4 as codConcepto,0 as cantidadEmpresa,0 as cantidadPersona,0 as cantidadReniec, 0 as codTabCom from t_facturacion_planilla_detalle group by codConcepto")
	public List<FacturacionPlanillaDetalle> findConsolidadoByIdCiclo(@Param("p_codCiclo") Integer codCiclo, @Param("fec_ini") Date fec_ini, @Param("fec_fin") Date fec_fin) throws Exception;*/
	
	@Select("select tr.id_tarifa as id_tarifa, tr.descripcion as desConcepto, fpd.costo_planilla_facturacion, "+
			"sum((select sum(ed.nro_paginas) from t_expediente_documento ed where ed.expediente_id = e.expediente_id and ed.estado_accion= 'AG' and ed.estado = true and ed.id_padre_group is null and "+
			"ed.orden_expediente is null)) as cantidadPag "+
			"from t_facturacion_planilla_detalle fpd inner join t_expediente e on fpd.cod_expediente = e.expediente_id "+
			"inner join t_expediente_tracking et on fpd.cod_exp_tracking = et.id_expediente_tracking inner join t_facturacion_planilla_cabecera fpc "+
			"on fpc.id_facturacion_planilla_cabecera = fpd.id_facturacion_planilla_cabecera	inner join t_tarifa tr on tr.id_tarifa = fpd.cod_tab_com "+
			"where fpc.cod_ciclo = #{p_codCiclo} group by fpd.costo_planilla_facturacion, tr.descripcion, tr.id_tarifa")
	public List<FacturacionPlanillaDetalle> findConsolidadoByIdCiclo(@Param("p_codCiclo") Integer codCiclo) throws Exception;
	
	@Select("select tr.id_tarifa  as id_tarifa, tr.descripcion as desConcepto, fpd.costo_planilla_facturacion, (u.nombre || ', ' || u.apellido_paterno || ' ' ||u.apellido_materno) as usuarioAsignado, u.idusuario, "+
			"sum((select sum(ed.nro_paginas) from t_expediente_documento ed where ed.expediente_id = e.expediente_id and ed.estado_accion in ('AG','R','D') and ed.estado = true and ed.id_padre_group is null)) as cantidadPag "+
			"from t_facturacion_planilla_detalle fpd inner join t_expediente e on fpd.cod_expediente = e.expediente_id "+ 
			"inner join t_expediente_tracking et on fpd.cod_exp_tracking = et.id_expediente_tracking inner join t_facturacion_planilla_cabecera fpc "+
			"on fpc.id_facturacion_planilla_cabecera = fpd.id_facturacion_planilla_cabecera inner join t_tarifa tr on tr.id_tarifa = fpd.cod_tab_com "+
			"inner join t_usuario u on u.idusuario = fpd.idusuario "+
			"where fpc.cod_ciclo = #{p_codCiclo} group by fpd.costo_planilla_facturacion, tr.descripcion, tr.id_tarifa,  e.idusuario,u.nombre , u.apellido_paterno ,u.apellido_materno, u.idusuario")
	public List<FacturacionPlanillaDetalle> findConsolidadoPlanillaByIdCiclo(@Param("p_codCiclo") Integer codCiclo) throws Exception;
	
	@Select("select c.descripcion as desConcepto, (case when d.cod_canal = 1 then 1 else 2 end) as codConcepto, "+
			"count(case when d.cod_canal=2 then d.cod_canal end) as cantidadEmpresa,count(case when d.cod_canal=1 then d.cod_canal end) as cantidadPersona, "+
			"0 as cantidadReniec, d.cod_tab_com as codTabCom from (t_facturacion_planilla_detalle d inner join t_canales c on c.cod_canales=d.cod_canal) "+
			"inner join t_facturacion_planilla_cabecera ca on ca.id_facturacion_planilla_cabecera = d.id_facturacion_planilla_cabecera where ca.cod_ciclo=#{p_codCiclo} group by c.descripcion,codConcepto, d.cod_tab_com ")
	public List<FacturacionPlanillaDetalle> findConsolidadoPreliminarByIdCiclo(@Param("p_codCiclo") Integer codCiclo) throws Exception;
	
	@Select("select * from t_facturacion_planilla_detalle")
	public List<FacturacionPlanillaDetalle> findAll() throws Exception;
	
	@Insert("insert into t_facturacion_planilla_detalle (cod_exp_tracking,cod_expediente,costo_planilla_facturacion,id_facturacion_planilla_cabecera,idusuario,cod_tab_com,flgcomisionado)"
			+ " values (#{cod_exp_tracking},#{cod_expediente},#{costo_planilla_facturacion},#{id_facturacion_planilla_cabecera},#{idusuario},#{cod_tab_com},#{flgcomisionado})")
	public void crearFacturacionPlanillaDetalle(FacturacionPlanillaDetalle facturacionPlanillaDetalle) throws Exception;
	
	@Update("update t_facturacion_planilla_detalle set cod_exp_tracking = #{cod_exp_tracking},cod_expediente = #{cod_expediente},cod_subtipo = #{cod_subtipo},costo_planilla_facturacion = #{costo_planilla_facturacion} "
			+ ",id_facturacion_planilla_cabecera = #{id_facturacion_planilla_cabecera} ,idtipo_planilla = #{idtipo_planilla} ,idusuario = #{idusuario},cod_canal = #{cod_canal},flgexistearchivo = #{flgexistearchivo}, flgcomisionado = #{flgcomisionado}"
			+ " where id_facturacion_planilla_detalle= #{id_facturacion_planilla_detalle}")
	@Options(flushCache=true,useCache=true)
    public void actualizarFacturacionPlanillaDetalle(FacturacionPlanillaDetalle facturacionPlanillaDetalle) throws Exception;
	
	@Delete("delete  from t_facturacion_planilla_detalle  where id_facturacion_planilla_detalle = #{p_codFacturacionPlanillaDetalle}")
	@Options(flushCache=true)
	public void eliminarFacturacionPlanillaDetalle(@Param("p_codFacturacionPlanillaDetalle") Integer codFacturacionPlanillaDetalle) throws Exception;
	
	@Delete("delete  from t_facturacion_planilla_detalle  where id_facturacion_planilla_cabecera = #{p_codFacturacionPlanillaCabecera}")
	@Options(flushCache=true)
	public void eliminarFacturacionPlanillaDetalleXCabecera(@Param("p_codFacturacionPlanillaCabecera") Integer codFacturacionPlanillaDetalle) throws Exception;
	
}
