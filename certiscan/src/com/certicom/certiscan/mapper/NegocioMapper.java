package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Negocio;

public interface NegocioMapper {

	@Select("select * from t_negocio e where e.id_negocio = #{p_negocio}")
	public Negocio findById(@Param("p_negocio") Integer codigoNegocio) throws Exception;

	@Select("select * from t_negocio  where id_negocio in (select id_negocio from t_usuario where idusuario = #{idusuario})")
	public List<Negocio> findByIdUsuario(@Param("idusuario") Integer idusuario) throws Exception;

	// @Select("select * from t_negocio order by descripcion asc")
	// @Select("select n.id_negocio, n.descripcion,n.estado from  t_negocio n" )
	@Select("select n.id_negocio, n.descripcion,n.estado,n.id_centro_atencion,ca.descripcion as des_proyecto from " + " t_negocio n inner join t_centros_atencion ca on n.id_centro_atencion = ca.id_centro_atencion order by ca.descripcion asc")
	public List<Negocio> findAll() throws Exception;

	@Select("select * from t_negocio where estado = 'TRUE' order by descripcion asc")
	public List<Negocio> listaNegociosActivo() throws Exception;

	@Insert("insert into t_negocio (descripcion, estado, id_centro_atencion) values (#{descripcion},#{estado},#{id_centro_atencion})")
	public void crearNegocio(Negocio negocio) throws Exception;

	@Update("update t_negocio set descripcion = #{descripcion}, estado = #{estado}, id_centro_atencion = #{id_centro_atencion} where id_negocio= #{id_negocio}")
	@Options(flushCache = true, useCache = true)
	public void actualizarNegocio(Negocio negocio) throws Exception;

	@Delete("delete  from t_negocio  where id_negocio = #{id_negocio}")
	@Options(flushCache = true)
	public void eliminarNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;

	@Select("select * from t_negocio where id_centro_atencion = #{id_centro_atencion}")
	public List<Negocio> listaNegocioxEmpresa(@Param("id_centro_atencion") Integer id_centro_atencion) throws Exception;

	// No permite eliminar un Negocio Asociado a un Producto devuelve la
	// cantidad de Negocio asociado
	@Select("select count(*) from t_producto where id_negocio =  #{id_negocio} ")
	public Integer listProductoxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;

}
