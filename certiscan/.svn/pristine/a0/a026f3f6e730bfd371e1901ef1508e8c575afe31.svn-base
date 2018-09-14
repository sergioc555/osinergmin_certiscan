package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.TipoCiclo;

public interface TipoCicloMapper {

	@Select("select * from t_tipo_ciclo e where e.cod_tipo_ciclo = #{p_cod_tipoCiclo}")
	public TipoCiclo findById(@Param("p_cod_tipoCiclo") Integer codigoTipoCiclo) throws Exception;
	
	@Select("select * from t_tipo_ciclo")
	public List<TipoCiclo> findAll() throws Exception;
	
	@Insert("insert into t_tipo_ciclo (descripcion) values (#{descripcion})")
	public void crearTipoCiclo(TipoCiclo resultado) throws Exception;
	
	@Update("update t_tipo_ciclo set descripcion = #{descripcion} where cod_tipo_ciclo= #{cod_tipo_ciclo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTipoCiclo(TipoCiclo tipociclo) throws Exception;
	
	@Delete("delete  from t_tipo_ciclo  where cod_tipo_ciclo = #{cod_tipoCiclo}")
	@Options(flushCache=true)
	public void eliminarTipoCiclo(@Param("cod_tipoCiclo") Integer cod_tipoCiclo) throws Exception;
	
}
