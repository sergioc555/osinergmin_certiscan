package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Requisito;

public interface RequisitoMapper {

	@Select("select * from t_requisito e where e.cod_requisito = #{cod_requisito}")
	public Requisito findById(@Param("cod_requisito") Integer cod_requisito) throws Exception;
	
	@Select("select *,false as defecto from t_requisito")
	public List<Requisito> findAll() throws Exception;
	
	@Insert("insert into t_requisito (descripcion, estado) values (#{descripcion},#{estado})")
	public void crearRequisito(Requisito requisito) throws Exception;
	
	@Update("update t_requisito set descripcion = #{descripcion}, estado = #{estado} where cod_requisito= #{cod_requisito}")
	@Options(flushCache=true,useCache=true)
    public void actualizarRequisito(Requisito requisito) throws Exception;
	
	@Delete("delete  from t_requisito  where cod_requisito = #{cod_requisito}")
	@Options(flushCache=true)
	public void eliminarRequisito(@Param("cod_requisito") Integer cod_requisito) throws Exception;
	
}
