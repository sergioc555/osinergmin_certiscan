package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Proyeccion;

public interface ProyeccionMapper {

	@Select("select * from t_proyeccion  where id_proyeccion = #{id_proyeccion}")
	public Proyeccion findById(@Param("id_proyeccion") Integer id_proyeccion) throws Exception;
	
	@Select("select * from t_proyeccion")
	public List<Proyeccion> findAll() throws Exception;
	
	@Insert("INSERT INTO t_proyeccion(id_expediente_producto, expediente_id, periodo,cant_cuentas_ph, cant_cuentas_cts, usuario_registro, fecha_registro) "
			+ " VALUES (#{id_expediente_producto}, #{expediente_id}, #{periodo}, #{cant_cuentas_ph}, #{cant_cuentas_cts}, #{usuario_registro}, #{fecha_registro} )")
	public void crearProyeccion(Proyeccion proyeccion) throws Exception;
	
	@Update("update t_proyeccion set descripcion = #{descripcion}, estado = #{estado} where id_tipo_moneda= #{id_tipo_moneda}")
	@Options(flushCache=true,useCache=true)
    public void actualizarProyeccion(Proyeccion proyeccion) throws Exception;
	
	@Delete("delete  from t_proyeccion  where id_proyeccion = #{id_proyeccion}")
	@Options(flushCache=true)
	public void eliminarProyeccion(@Param("id_proyeccion") Integer id_proyeccion) throws Exception;
}
