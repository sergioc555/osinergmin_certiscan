package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CategoriaIndicadoresProducto;

public interface CategoriaIndicadoresProductoMapper {
	@Select("select * from t_categoria_producto e where e.id_cat_producto = #{p_id_cat_producto}")
	public CategoriaIndicadoresProducto findById(@Param("p_id_cat_producto") Integer codigoEstado) throws Exception;
	
	@Select("select * from t_categoria_producto")
	public List<CategoriaIndicadoresProducto> findAll() throws Exception;
	
	@Select("select * from t_categoria_producto where estado = 'TRUE' order by descripcion asc")
	public List<CategoriaIndicadoresProducto> listaCategoriaIndicadoresProductoActivo() throws Exception;
	
	@Insert("insert into t_categoria_producto (descripcion, estado) values (#{descripcion},#{estado})")
	public void crearCategoriaIndicadoresProducto(CategoriaIndicadoresProducto resultado) throws Exception;
	
	@Update("update t_categoria_producto set descripcion = #{descripcion}, estado = #{estado} where id_cat_producto= #{id_cat_producto}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCategoriaIndicadoresProducto(CategoriaIndicadoresProducto categoriaIndicadoresProducto) throws Exception;
	
	@Delete("delete  from t_categoria_producto  where id_cat_producto = #{id_cat_producto}")
	@Options(flushCache=true)
	public void eliminarCategoriaIndicadoresProducto(@Param("id_cat_producto") Integer id_cat_producto) throws Exception;
	

}
