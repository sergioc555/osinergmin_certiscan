package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Estado;
import com.certicom.certiscan.domain.Test;



public interface EstadoMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_estado e where e.id_estado = #{idEstado}")
	public Estado findById(@Param("idEstado") Integer codigoEstado) throws Exception;
	
	@Select("select * from t_estado order by orden asc")
	public List<Estado> findAll() throws Exception;
	
	@Insert("insert into t_estado (descripcion, estado, abreviacion, orden) values (#{descripcion}, #{estado}, #{abreviacion}, #{orden})")
	public void crearEstado(Estado estado) throws Exception;
	
	
	@Update("update t_estado set descripcion = #{descripcion},abreviacion = #{abreviacion},orden = #{orden} where id_estado= #{id_estado}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstado(Estado Estado) throws Exception;
	
	@Update("update t_estado set estado = #{estado} where id_estado= #{id_estado}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstadoEstado(Estado Estado) throws Exception;
	
	@Delete("delete from t_estado where id_estado = #{idEstado}")
	@Options(flushCache=true)
	public void eliminarEstado(@Param("idEstado") Integer codigoEstado) throws Exception;
	
}
