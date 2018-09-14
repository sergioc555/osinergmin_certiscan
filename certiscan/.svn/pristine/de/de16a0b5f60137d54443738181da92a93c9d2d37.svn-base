package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CategoriaIndicadoresIndice;



public interface CategoriaIndicadoresIndiceMapper {


	@Select("select * from t_categoria_call e where e.id_categoria_call = #{p_categoria_call}")
	public CategoriaIndicadoresIndice findById(@Param("p_categoria_call") Integer codigoCategoriaCall) throws Exception;
	
	@Select("select * from t_categoria_call e where e.descripcion = #{p_descripcion}")
	public CategoriaIndicadoresIndice findByNombre(@Param("p_descripcion") String descripcion) throws Exception;
	
	@Select("select * from t_categoria_call e where e.id_producto = 1 order by descripcion")
	public List<CategoriaIndicadoresIndice> findByProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	@Select("select * from t_categoria_call order by descripcion asc")
	public List<CategoriaIndicadoresIndice> findAll() throws Exception;
	
	@Select("select * from t_categoria_call where estado = 'TRUE' order by descripcion asc")
	//@Select("select * from t_categoria_call order by descripcion asc")
	public List<CategoriaIndicadoresIndice> listaCategoriaIndicadoresCallActivo() throws Exception;
	
	@Insert("insert into t_categoria_call (descripcion, estado, id_producto, orden) values (#{descripcion},#{estado},#{id_producto},#{orden})")
	public void crearCategoriaIndicadoresCall(CategoriaIndicadoresIndice categoriaIndicadoresCall) throws Exception;
	
	@Update("update t_categoria_call set descripcion = #{descripcion}, estado = #{estado}, id_producto = #{id_producto}, orden = #{orden} where id_categoria_call= #{id_categoria_call}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCategoriaIndicadoresIndice(CategoriaIndicadoresIndice categoriaIndicadoresCall) throws Exception;
	
	@Delete("delete  from t_categoria_call  where id_categoria_call = #{id_categoria_call}")
	@Options(flushCache=true)
	public void eliminarCategoriaIndicadoresIndice(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	@Select("select count(*) from t_indice where id_categoria_call = #{id_categoria_call} ")	
	public Integer listIndicadorxCategroriaIndice(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	//@Select("select * from t_categoria_call e where e.id_producto = #{id_producto} order by descripcion")
	@Select("select distinct e.* from t_categoria_call e inner join t_indice ic on e.id_categoria_call = ic.id_categoria_call where e.id_producto = #{id_producto} order by descripcion")
	public List<CategoriaIndicadoresIndice> listarCategoriaCallByProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	public List<CategoriaIndicadoresIndice> getCatIndicadoresExisProducto(@Param("producto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;
	
	
	@Select("select distinct e.* from t_categoria_call e inner join t_indice ic on e.id_categoria_call = ic.id_categoria_call where e.id_producto = #{id_producto} "
			+ "and ic.estado and not ic.generar_expediente  order by e.orden")
	public List<CategoriaIndicadoresIndice> listarCategoriaCallByProductoCall(@Param("id_producto") Integer id_producto) throws Exception;

	
}
