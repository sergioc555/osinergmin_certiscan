package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Test;



public interface TestMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_test e where e.cod_estado = #{p_codTest}")
	public Test findById(@Param("p_codEstad o") Integer codigoTest) throws Exception;
	
	@Select("select * from t_test order by descripcion asc")
	public List<Test> findAll() throws Exception;
	
	@Insert("insert into t_test (descripcion, estado) values (#{descripcion},#{estado})")
	public void crearTest(Test test) throws Exception;
	
	
	@Update("update t_test set descripcion = #{descripcion}, estado = #{estado} where cod_estado= #{cod_estado}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTest(Test test) throws Exception;
	
	@Delete("delete  from t_test  where cod_estado = #{cod_estado}")
	@Options(flushCache=true)
	public void eliminarTest(@Param("cod_estado") Integer cod_estado) throws Exception;
	
}
