package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CategoriaIndicadoresCall;



public interface CategoriaIndicadoresCallMapper {


	@Select("select * from t_categoria_call e where e.id_categoria_call = #{p_categoria_call}")
	public CategoriaIndicadoresCall findById(@Param("p_categoria_call") Integer codigoCategoriaCall) throws Exception;
	
	@Select("select * from t_categoria_call e where e.descripcion = #{p_descripcion}")
	public CategoriaIndicadoresCall findByNombre(@Param("p_descripcion") String descripcion) throws Exception;
	
	@Select("select * from t_categoria_call e where e.id_producto = 1 order by descripcion")
	public List<CategoriaIndicadoresCall> findByProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	@Select("select * from t_categoria_call order by descripcion asc")
	public List<CategoriaIndicadoresCall> findAll() throws Exception;
	
	@Select("select * from t_categoria_call where estado = 'TRUE' order by descripcion asc")
	//@Select("select * from t_categoria_call order by descripcion asc")
	public List<CategoriaIndicadoresCall> listaCategoriaIndicadoresCallActivo() throws Exception;
	
	@Insert("insert into t_categoria_call (descripcion, estado, id_producto, orden) values (#{descripcion},#{estado},#{id_producto},#{orden})")
	public void crearCategoriaIndicadoresCall(CategoriaIndicadoresCall categoriaIndicadoresCall) throws Exception;
	
	@Update("update t_categoria_call set descripcion = #{descripcion}, estado = #{estado}, id_producto = #{id_producto}, orden = #{orden} where id_categoria_call= #{id_categoria_call}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCategoriaIndicadoresCall(CategoriaIndicadoresCall categoriaIndicadoresCall) throws Exception;
	
	@Delete("delete  from t_categoria_call  where id_categoria_call = #{id_categoria_call}")
	@Options(flushCache=true)
	public void eliminarCategoriaIndicadoresCall(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	@Select("select count(*) from t_indicadores_call where id_categoria_call = #{id_categoria_call} ")	
	public Integer listIndicadorxCategroriaCall(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	//@Select("select * from t_categoria_call e where e.id_producto = #{id_producto} order by descripcion")
	@Select("select distinct e.* from t_categoria_call e inner join t_indicadores_call ic on e.id_categoria_call = ic.id_categoria_call where e.id_producto = #{id_producto} order by descripcion")
	public List<CategoriaIndicadoresCall> listarCategoriaCallByProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	public List<CategoriaIndicadoresCall> getCatIndicadoresExisProducto(@Param("producto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;
	
	
	@Select("select distinct e.* from t_categoria_call e inner join t_indicadores_call ic on e.id_categoria_call = ic.id_categoria_call where e.id_producto = #{id_producto} "
			+ "and ic.estado and not ic.generar_expediente  order by e.orden")
	public List<CategoriaIndicadoresCall> listarCategoriaCallByProductoCall(@Param("id_producto") Integer id_producto) throws Exception;

	
}
