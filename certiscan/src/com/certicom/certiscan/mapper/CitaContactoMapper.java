package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CitaContacto;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.ExpedienteItem;

public interface CitaContactoMapper {

	@Select("select * from t_cita_contacto bc where bc.citacontacotid = #{citacontacotid}")
	public CitaContacto findById(@Param("citacontacotid") Integer citaContactoId) throws Exception;
	
	@Select("select * from t_cita_contacto")
	public List<CitaContacto> findAll() throws Exception;
	
	@Select("select t_cita_contacto.*, "
			+ "	(case when t_expediente_producto.id_producto = 6 then t_expediente.nombre_empresa else  concat(t_expediente.apellido_paterno, ' ', t_expediente.apellido_materno, ' ', t_expediente.nombre_completo) end) AS nombreCliente,"
			+ " t_expediente_producto.id_producto as id_producto "
			+ "from t_cita_contacto "
			+ "inner join t_expediente_producto on t_cita_contacto.id_expediente_producto = t_expediente_producto.id_expediente_producto "
			+ "inner join t_expediente on t_expediente_producto.expediente_id = t_expediente.expediente_id "
			+ "where t_cita_contacto.usuarioid = #{usuarioid}")
	public List<CitaContacto> findbyUsuario(@Param("usuarioid") Integer usuarioid) throws Exception;
	
	public List<CitaContacto> findByEjecutivo(@Param("usuarioid") Integer usuarioid,@Param("id_vendedor") Integer id_vendedor) throws Exception;
	public List<CitaContacto> findByVendedor(@Param("id_vendedor") Integer id_vendedor) throws Exception;
	
	
	@Select("select * from t_cita_contacto where id_expediente_producto = #{id_expediente_producto}")
	public List<CitaContacto> findbyExpedienteProducto(@Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;
	
	
	@Insert("insert into t_cita_contacto (tcita, fecha, hora,departamento,provincia,distrito,usuarioid, id_expediente_producto, comentario, estado,persona_contacto, direccion ) "
			+ " values (#{tcita},#{fecha},#{hora},#{departamento},#{provincia},#{distrito},#{usuarioid}, #{id_expediente_producto}, #{comentario}, #{estado}, #{persona_contacto}, #{direccion})")
	public void crearCitaContacto(CitaContacto direccionContacto) throws Exception;
	

	@Update("update t_cita_contacto set id_indicador = #{id_indicador} where citacontactoid= #{citacontactoid}")
	@Options(flushCache=true,useCache=true)
    public void actualizarIndCita(CitaContacto citaContacto) throws Exception;
	
	
	@Delete("delete  from t_cita_contacto  where citacontactoid = #{citacontactoid}")
	@Options(flushCache=true)
	public void eliminarCitaContacto(@Param("citacontactoid") Integer citaContacotId) throws Exception;
	
	@Select("select ex.ruc, ex.nombre_empresa, ex.gestion_nrocuentas as nro_total_cuentas, "
			+ "	 (eje.nombre || ' ' || eje.apellido_paterno || ' ' || eje.apellido_materno) as nombrecompleto_ejecutivo, "
			+ " 	c.persona_contacto as personal_contacto, c.estado as estado_cita, c.citacontactoid, c.comentario as observacion_cita "
			+ " from t_cita_contacto c inner join t_expediente_producto ep "
			+ " inner join t_expediente ex on ex.expediente_id = ep.expediente_id "
			+ " inner join t_usuario eje on eje.idusuario = ep.idusuario "
			+ " on c.id_expediente_producto = ep.id_expediente_producto "
			+ " where ep.id_producto = #{idproducto} and c.fecha >= #{fini} and c.fecha <= #{ffin} ")
	public List<ExpedienteItem> listarCitasByProductoFecha(ExpedienteFilter filter) throws Exception;
	
	
	@Update("update t_cita_contacto set estado = 'VALIDADO' where citacontactoid= #{citacontactoid}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstadoToValidado(@Param("citacontactoid") Integer citaContacotId) throws Exception;
	
	

	@Update("update t_cita_contacto set tcita = #{tcita}, fecha = #{fecha},  hora = #{hora},departamento = #{departamento},provincia = #{provincia},distrito = #{distrito}, "
			+ "direccion =#{direccion} where citacontactoid= #{citacontactoid}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCitaContacto(CitaContacto citaContacto) throws Exception;
	
	
	@Select("select ex.ruc,ex.nombre_empresa ,ex.lat_empresa as latitud,ex.long_empresa as longitud, "
			+ "(cast(cc.fecha as timestamp)+ (extract(hour from  cc.hora)||' hours')::interval)+ (extract(minute from  cc.hora)||' minutes')::interval as fecha_cita "
			+ " from t_cita_contacto cc inner join "
			+ " t_expediente_producto ep on cc.id_expediente_producto = ep.id_expediente_producto "
			+ "	inner join t_expediente ex on ep.expediente_id = ex.expediente_id "
			+ " where cc.usuarioid  =#{idusuario} and ep.periodo = #{periodo} and cc.fecha =#{fecha_cita} order by cc.fecha,cc.hora ")
	public List<ExpedienteItem> listarEmpresasHojaRuta(@Param("periodo") Date periodo,@Param("fecha_cita") Date fecha_cita,@Param("idusuario") Integer idusuario) throws Exception;
	
	
	@Select("select ex.ruc, ex.nombre_empresa, "
			+ "(cast(cc.fecha as timestamp)+ (extract(hour from  cc.hora)||' hours')::interval)+ (extract(minute from  cc.hora)||' minutes')::interval as fecha_cita, "
			+ "	cc.estado_gps as estado_gps_cita, "
			+ " (usu.nombre || ' ' ||  usu.apellido_paterno || ' ' || usu.apellido_materno) as nombre_ejecutivo, "
			+ "cc.fecha_cruce_gps "
			+ " from t_cita_contacto cc inner join t_expediente_producto ep "
			+ "	on cc.id_expediente_producto = ep.id_expediente_producto "
			+ "	inner join t_expediente ex on ex.expediente_id = ep.expediente_id "
			+ " inner join t_usuario usu on usu.idusuario = ep.idusuario "
			+ " where cc.fecha = CURRENT_DATE and ep.id_producto = 6 "
			+ " and ex.id_producto = 6 "
			+ "	order by cc.fecha, cc.hora ")
	public List<ExpedienteItem> listarDetalleHojaRuta() throws Exception;
	
	@Select("select cc.usuarioid as idusuario,cc.fecha as fecha_cita, count(*) as cantidad from t_cita_contacto cc inner join t_expediente_producto ep on cc.id_expediente_producto = ep.id_expediente_producto "
			+ "where ep.id_producto = 6 "
			+ " and cc.fecha >= #{fini} "
			+ " and cc.fecha <= #{ffin} "
			+ " group by cc.usuarioid,cc.fecha "
			+ " order by cc.fecha,cc.usuarioid ")
	public List<ExpedienteItem> listarCantCitasGroupByUsuarioFecha(@Param("id_producto")Integer id_producto, @Param("fini") Date fini, @Param("ffin") Date ffin ) throws Exception;
	
	
	
	@Select("select cc.usuarioid as idusuario,cc.fecha as fecha_cita, count(*) as cantidad from t_cita_contacto cc inner join t_expediente_producto ep on cc.id_expediente_producto = ep.id_expediente_producto "
			+ "where ep.id_producto = 6 "
			+ " and cc.fecha >= #{fini} "
			+ " and cc.fecha <= #{ffin} "
			+ " and cc.estado_gps = 'VALIDADO' "
			+ " group by cc.usuarioid,cc.fecha "
			+ " order by cc.fecha,cc.usuarioid ")
	public List<ExpedienteItem> listarCantCitasGroupByUsuarioVALIDADOgps(@Param("id_producto")Integer id_producto, @Param("fini") Date fini, @Param("ffin") Date ffin ) throws Exception;
	
	
	
	@Select("select ex.nombre_empresa as nombre_empresa, cc.fecha ,cc.hora, "
			+ "	(cast(cc.fecha as timestamp)+ (extract(hour from  cc.hora)||' hours')::interval)+ (extract(minute from  cc.hora)||' minutes')::interval as fecha_cita "
			+ " from t_cita_contacto cc inner join t_expediente_producto ep on cc.id_expediente_producto = ep.id_expediente_producto "
			+ " inner join t_expediente ex on ex.expediente_id = ep.expediente_id "
			+ " where ep.id_producto = 6 "
			+ " and cc.fecha = #{fec_cita} "
			+ " and cc.usuarioid = #{id_usuario} "
			+ " order by cc.fecha,cc.usuarioid ; ")
	public List<ExpedienteItem> listDetalleCitasByUsuario(@Param("id_producto")Integer id_producto, @Param("fec_cita") Date fec_cita, @Param("id_usuario")Integer id_usuario ) throws Exception;
	
	
	
	
	
}
