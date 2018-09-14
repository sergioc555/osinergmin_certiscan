package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CategoriaEstados;

public interface CategoriaEstadosMapper {

	@Select("select * from t_categoria_estado e where e.id_categoria_estado = #{p_categoria_estado}")
	public CategoriaEstados findById(@Param("p_categoria_estado") Integer codigoCategoriaEstados) throws Exception;

	@Select("select * from t_categoria_estado e where e.codigo = #{codigo}")
	public CategoriaEstados findByCodigo(@Param("codigo") String codigo) throws Exception;

	@Select("select * from t_categoria_estado order by descripcion asc")
	public List<CategoriaEstados> findAll() throws Exception;

	// Llena el combo categoria solo con valores true
	// @Select("select * from t_categoria_estado where estado = 'TRUE' order by descripcion asc")
	// Llena el combo categoria con valores true o false
	@Select("select * from t_categoria_estado order by descripcion asc")
	public List<CategoriaEstados> listaCategoriaEstadosActivo() throws Exception;

	@Insert("insert into t_categoria_estado (descripcion, observacion, estado) values (#{descripcion},#{observacion},#{estado})")
	public void crearCategoriaEstados(CategoriaEstados categoriaEstados) throws Exception;

	@Update("update t_categoria_estado set descripcion = #{descripcion}, observacion = #{observacion}, estado = #{estado} where id_categoria_estado= #{id_categoria_estado}")
	@Options(flushCache = true, useCache = true)
	public void actualizarCategoriaEstados(CategoriaEstados categoriaEstados) throws Exception;

	@Delete("delete  from t_categoria_estado  where id_categoria_estado = #{id_categoria_estado}")
	@Options(flushCache = true)
	public void eliminarCategoriaEstados(@Param("id_categoria_estado") Integer id_categoria_estado) throws Exception;

	@Select("select count(*) from t_estado where id_categoria_estado =  #{id_categoria_estado} ")
	public Integer listCategoriaxEstado(@Param("id_categoria_estado") Integer id_categoria_estado) throws Exception;

}
