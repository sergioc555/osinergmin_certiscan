package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.ExpedienteTracking;

public interface ExpedienteTrackingMapper {

	@Select("select * from t_expediente_tracking e where e.id_expediente_tracking = #{id_expediente_tracking}")
	public ExpedienteTracking findById(@Param("id_expediente_tracking") Integer id_expediente_tracking) throws Exception;
	
	@Select("select * from t_expediente_tracking")
	public List<ExpedienteTracking> findAll() throws Exception;
	
	@Select(" select ex.fecha_recepcion,ex.cant_imagenes,ex.planos_a3,ex.planos_a2,ex.planos_a1,ex.planos_a0,ex.planos_excede,ex.cant_planos_imagenes,e.descripcion as desestado, "
			+ "(u.nombre || ', ' || u.apellido_paterno || ' ' || u.apellido_materno) as usuario, o.nombre as desoficina,"
			+ "exp.nro_expediente, ex.fecha_registro "+
		"from ((t_expediente_tracking ex inner join t_estado e on e.id_estado = ex.id_estado) "+
		"inner join t_usuario u on ex.idusuario = u.idusuario) "
		+ "inner join t_expediente exp on exp.expediente_id = ex.expediente_id "
		+ "left join t_oficina o on o.id_oficina = ex.id_oficina where ex.expediente_id = #{p_expediente_id}"
		+ "order by ex.fecha_registro")
	public List<ExpedienteTracking> findByExpedienteId(@Param("p_expediente_id") Integer p_expediente_id) throws Exception;
	
	@Insert(" insert into t_expediente_tracking (expediente_id, fecha_registro, idusuario, id_estado , condicion, idusuario_registro, comentario, fecha_recepcion, observacion, id_oficina, cant_imagenes, planos_a3, planos_a2, planos_a1, planos_a0, planos_excede, cant_planos_imagenes) "
			+ "values (#{expediente_id},#{fecha_registro},#{idusuario},#{id_estado},#{condicion},#{idusuario_registro},#{comentario}, #{fecha_recepcion},#{observacion},#{id_oficina}, #{cant_imagenes}, #{planos_a3}, #{planos_a2}, #{planos_a1}, #{planos_a0}, #{planos_excede}, #{cant_planos_imagenes})")
	public void crearExpedienteTracking(ExpedienteTracking expedienteTracking) throws Exception;
	
	@Update("update t_expediente_tracking set facturado = #{facturado} where id_expediente_tracking= #{id_expediente_tracking}")
	@Options(flushCache=true,useCache=true)
    public void actualizarExpedienteTrackingxFacturado(ExpedienteTracking expedienteTracking) throws Exception;
	
	@Select("select e.* from t_expediente_tracking e inner join t_facturacion_planilla_detalle f on f.cod_exp_tracking = e.id_expediente_tracking "
			+ "where f.id_facturacion_planilla_cabecera = #{p_id_facturacion_planilla_cabecera}")
	public List<ExpedienteTracking> findByIdDetalle(@Param("p_id_facturacion_planilla_cabecera") Integer id_facturacion_planilla_cabecera) throws Exception;
	
}
