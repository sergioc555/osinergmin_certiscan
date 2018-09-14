package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Centro_Atencion;

public interface Centro_AtencionMapper {
	
	//asociar centro de atencion(proyecto) y empresa
	//@Select("select * from t_centros_atencion order by id_centro_atencion asc")
	@Select("select ca.id_centro_atencion,ca.nombre,ca.descripcion,ca.responsable,e.razon_social as des_razon_social "
			+ "from t_centros_atencion ca inner join t_empresa e on ca.id_empresa = e.id_empresa order by ca.nombre asc;")
	public List<Centro_Atencion> findAll() throws Exception;
	
	@Select("select * from t_centros_atencion where id_centro_atencion = #{p_id_centro_atencion}")
	public Centro_Atencion findById(@Param("p_id_centro_atencion") Integer id_centro_atencion) throws Exception;
	//
	@Select("select * from t_centros_atencion order by descripcion asc")
	public List<Centro_Atencion> listaCentroAtencionActivo() throws Exception;
	//
	@Delete("delete  from t_centros_atencion  where id_centro_atencion = #{p_id_centro_atencion}")
	@Options(flushCache=true)
	public void eliminarCentro_Atencion(@Param("p_id_centro_atencion") Integer id_centro_atencion) throws Exception;
	
	@Insert("insert into t_centros_atencion (nombre, descripcion,responsable,id_empresa) values (#{nombre},#{descripcion},#{responsable},#{id_empresa})")
	public void crearCentro_Atencion(Centro_Atencion centro_atencion) throws Exception;
	
	@Update("update t_centros_atencion set nombre=#{nombre},descripcion=#{descripcion} ,responsable=#{responsable}, id_empresa = #{id_empresa} where id_centro_atencion= #{id_centro_atencion}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCentro_Atencion(Centro_Atencion centro_atencion) throws Exception;
	
	@Select("select * from t_centros_atencion where id_empresa= #{id_empresa}")
	public List<Centro_Atencion> listaProyectosxEmpresa(@Param("id_empresa") Integer id_empresa)throws Exception;
		
	//No permite eliminar un Proyecto Asociado a un Negocio devuelve la cantidad de Proyecto asociado
	@Select("select count(*) from t_negocio where id_centro_atencion =  #{id_centro_atencion} ")	
	public Integer listNegocioxProyecto(@Param("id_centro_atencion") Integer id_centro_atencion) throws Exception;	  
	
}
