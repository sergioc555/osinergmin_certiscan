package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Lote;
import com.certicom.certiscan.domain.Test;



public interface LoteMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_lote e where e.id_lote = #{idLote}")
	public Lote findById(@Param("idLote") Integer idLote) throws Exception;
	
	@Select("select * from t_lote order by cod_lote asc")
	public List<Lote> findAll() throws Exception;
	
	//@Select("select coalesce(max(id_lote), 0) from t_lote;")
	@Select("SELECT last_value from sec_lote")	
	public Integer maxIdLote() throws Exception;
	
	@Insert("insert into t_lote (cod_lote, fecha_registro, cantidad_expedientes, estado, entregado_a) values (#{cod_lote},#{fecha_registro},#{cantidad_expedientes},#{estado},#{entregado_a})")
	public void crearLote(Lote lote) throws Exception;
	
	
	@Update("update t_lote set cod_lote = #{cod_lote}, fecha_registro = #{fecha_registro}, cantidad_expedientes = #{cantidad_expedientes}, estado = #{estado}, entregado_a = #{entregado_a} where id_lote= #{id_lote}")
	@Options(flushCache=true,useCache=true)
    public void actualizarLote(Lote lote) throws Exception;
	
	@Update("update t_lote set estado = #{estado}, entregado_a = #{entregado_a} where id_lote= #{id_lote}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstado(Lote lote) throws Exception;
	
	@Delete("delete from t_lote where id_lote = #{idLote}")
	@Options(flushCache=true)
	public void eliminarLote(@Param("idLote") Integer idLote) throws Exception;
	
	public List<Lote> consultaLote(Lote filter)throws Exception;
	
}