package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cargo;
import com.certicom.certiscan.domain.Test;



public interface CargoMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_cargo e where e.id_cargo = #{idCargo}")
	public Cargo findById(@Param("idCargo") Integer codigoCargo) throws Exception;
	
	@Select("select * from t_cargo order by descripcion asc")
	public List<Cargo> findAll() throws Exception;
	
	@Insert("insert into t_cargo (descripcion) values (#{descripcion})")
	public void crearCargo(Cargo cargo) throws Exception;
	
	
	@Update("update t_cargo set descripcion = #{descripcion} where id_cargo= #{id_cargo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCargo(Cargo cargo) throws Exception;
	
	@Delete("delete from t_cargo where id_cargo = #{idCargo}")
	@Options(flushCache=true)
	public void eliminarCargo(@Param("idCargo") Integer cod_estado) throws Exception;
	
}
