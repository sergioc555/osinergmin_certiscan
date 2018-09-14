package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.domain.ProyeccionVentas;

public interface ProyeccionVentasMapper {

	@Select("select pv.*, (u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno ) as nomUsuario from t_proyeccion_ventas pv, t_usuario u where u.idusuario = pv.id_ejecutivo and pv.id_negocio = #{id_negocio} and pv.id_producto = #{id_producto} "
			+ "and pv.periodo = #{periodo}")
	// @Select("select * from t_estado where estado = 'TRUE' order by descripcion asc")
	public List<ProyeccionVentas> listaProyeccionVentas(ProyeccionVentas pv) throws Exception;

	@Insert("insert into t_proyeccion_ventas (id_negocio, id_producto, id_ejecutivo, id_ejecutivo_padre, meta, periodo, tipo_nivel) values (#{id_negocio},#{id_producto},#{id_ejecutivo}, #{id_ejecutivo_padre}, #{meta}, #{periodo}, #{tipo_nivel} )")
	public void crearProyeccionVentas(ProyeccionVentas pv) throws Exception;
	
	@Update("update t_proyeccion_ventas set id_negocio=#{id_negocio}, id_producto=#{id_producto}, "
			+ "id_ejecutivo=#{id_ejecutivo}, meta=#{meta}, periodo=#{periodo}, id_ejecutivo_padre=#{id_ejecutivo_padre}, "
			+ "tipo_nivel=#{tipo_nivel} "
			+ "where id_proyeccion_venta = #{id_proyeccion_venta}")
	@Options(flushCache=true,useCache=true)
	public void actualizarProyeccionVenta(ProyeccionVentas pv)throws Exception;
	
	@Update("delete from t_proyeccion_ventas "
			+ "where id_proyeccion_venta = #{id_proyeccion_venta}")
	@Options(flushCache=true,useCache=true)
	public void eliminarProyeccionVenta(ProyeccionVentas pv)throws Exception;
	
}
