package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Estados;

public interface EstadosMapper {

	@Select("select * from t_estado where id_estado = #{id_estado}")
	public Estados findById(@Param("id_estado") Integer id_estado) throws Exception;

	// asociar estados y categoria de estados
	// @Select("select est.id_estado, ce.id_categoria_estado, ce.descripcion as des_categoria_estados, est.estado, est.descripcion from t_estado est inner join t_categoria_estado ce on est.id_categoria = ce.id_categoria_estado order by est.descripcion asc")
	// @Select("select * from t_estado")
	@Select("select e.id_estado,e.id_categoria_estado,e.descripcion,e.estado,ct.descripcion  as des_categoria_estados from t_estado e,t_categoria_estado ct where e.id_categoria_estado = ct.id_categoria_estado  order by 5 ASC")
	public List<Estados> findAll() throws Exception;

	@Select("select * from t_estado order by descripcion asc")
	// @Select("select * from t_estado where estado = 'TRUE' order by descripcion asc")
	public List<Estados> listaEstadosActivo() throws Exception;

	@Insert("insert into t_estado (id_categoria_estado, descripcion, estado, id_producto, flag_banco) values (#{id_categoria_estado},#{descripcion},#{estado}, #{idProducto}, #{flag_banco} )")
	public void crearEstados(Estados estados) throws Exception;

	@Update("update t_estado set id_categoria_estado = #{id_categoria_estado}, descripcion = #{descripcion}, estado = #{estado}, id_producto=#{idProducto} where id_estado= #{id_estado}")
	@Options(flushCache = true, useCache = true)
	public void actualizarEstados(Estados estados) throws Exception;

	@Update("update t_estado set id_estado_padre = #{id_estado_padre}, descripcion_banco = #{descripcion_banco} where id_estado= #{id_estado}")
	@Options(flushCache = true, useCache = true)
	public void actualizarEstadosBanco(Estados estados) throws Exception;

	@Delete("delete  from t_estado  where id_estado = #{id_estado}")
	@Options(flushCache = true)
	public void eliminarEstados(@Param("id_estado") Integer id_categoria_estado) throws Exception;

	@Select("select e.id_estado,e.id_categoria_estado,e.descripcion,e.estado,ct.descripcion  as des_categoria_estados " + "from t_estado e,t_categoria_estado ct where e.id_categoria_estado = ct.id_categoria_estado  and ct.id_categoria_estado= #{id_categoria_estado}" + "  and e.id_producto= #{idProducto} ")
	public List<Estados> getxCategoria(@Param("id_categoria_estado") Integer id_categoria_estado, @Param("idProducto") Integer idProducto) throws Exception;

	@Select("select e.id_estado,e.id_categoria_estado,e.descripcion,e.estado,ct.descripcion  as des_categoria_estados " + "from t_estado e,t_categoria_estado ct where e.id_categoria_estado = ct.id_categoria_estado  and ct.id_categoria_estado= #{id_categoria_estado}" + "  and e.id_producto= #{idProducto} and flag_banco = 'FAMILIA' ")
	public List<Estados> getxCategoriadeBanco(@Param("id_categoria_estado") Integer id_categoria_estado, @Param("idProducto") Integer idProducto) throws Exception;

	@Select("select * from t_estado where descripcion=#{descripcion} and id_producto=#{id_producto} and id_categoria_estado=#{id_categoria_estado}")
	public Estados buscarByEstadoProductoCategoria(@Param("id_producto") Integer id_producto, @Param("descripcion") String descripcion, @Param("id_categoria_estado") Integer id_categoria_estado) throws Exception;

	@Select("select * from t_estado es inner join t_categoria_estado ce on ce.id_categoria_estado=es.id_categoria_estado " + " where es.descripcion=#{descripcion} and es.id_producto=#{idProducto} and ce.descripcion=#{descripcion_cat}")
	public Estados buscarByEstadoProductoDescCategoria(@Param("idProducto") Integer idProducto, @Param("descripcion") String descripcion, @Param("descripcion_cat") String descripcion_cat) throws Exception;

	@Select("select * from t_estado e where e.id_producto=#{idProducto} and e.descripcion=#{descripcion} and e.id_categoria_estado=#{id_categoria_estado} and e.id_estado!=#{id_estado}")
	public List<Estados> buscarEstadoRepetido(Estados estado) throws Exception;

	@Select("select * from t_estado where id_producto=#{idProducto} and descripcion=#{descripcion} and id_categoria_estado=#{id_categoria_estado}")
	public List<Estados> buscarIgual(@Param("idProducto") Integer idProducto, @Param("descripcion") String descripcion, @Param("id_categoria_estado") Integer id_categoria_estado) throws Exception;

	@Select("select * from t_estado where  id_producto=#{id_producto} and id_categoria_estado=#{id_categoria_estado}  order by  id_estado ")
	public List<Estados> buscarByEstadoCategoria(@Param("id_producto") Integer id_producto, @Param("id_categoria_estado") Integer id_categoria_estado) throws Exception;

	@Select("select * from t_estado where  id_producto=#{id_producto} and codigo_categoria=#{codigo_categoria} and codigo_estado_padre = #{codigo_estado_padre}  order by  id_estado ")
	public List<Estados> buscarByProductoCategoriaFamilia(@Param("id_producto") Integer id_producto, @Param("codigo_categoria") String codigo_categoria, @Param("codigo_estado_padre") String codigo_estado_padre) throws Exception;

	@Select("select * from t_estado where  id_producto=#{id_producto} and codigo_categoria=#{codigo_categoria} and flag_banco = 'FAMILIA' order by  id_estado ")
	public List<Estados> buscarByProductoCategoria(@Param("id_producto") Integer id_producto, @Param("codigo_categoria") String codigo_categoria) throws Exception;

	@Select("select id_estado from t_estado where id_producto = #{id_producto} and id_categoria_estado = 10 and descripcion = 'CONCLUIDO' order by  id_estado ")
	public Integer getIdEstadoByProduct(@Param("id_producto") Integer id_producto) throws Exception;

	@Select("select e.id_estado,e.id_categoria_estado,e.descripcion,e.estado,ct.descripcion  as des_categoria_estados " + "from t_estado e,t_categoria_estado ct where e.id_categoria_estado = ct.id_categoria_estado  and ct.id_categoria_estado= #{id_categoria_estado}" + "  and e.id_producto= #{idProducto} and e.flag_banco = 'FAMILIA' and e.estado  order by  id_estado ")
	public List<Estados> getxCategoriaFamilia(@Param("id_categoria_estado") Integer id_categoria_estado, @Param("idProducto") Integer idProducto) throws Exception;

	@Select("select  * from t_estado e where e.id_estado_padre = #{id_estado} and e.flag_banco = 'SUBFAMILIA' order by e.descripcion ")
	public List<Estados> getxSubFamiliaxPadre(@Param("id_estado") Integer id_estado) throws Exception;
}
