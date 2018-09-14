package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Tarifa;

public interface TarifaMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	
	@Select("select e.* from t_tarifa e where e.id_tarifa = #{p_codTarifa}")
	public Tarifa findById(@Param("p_codTarifa") Integer codigoTarifa) throws Exception;
	
	@Select("select e.* from t_tarifa e")
	public List<Tarifa> findAll() throws Exception;	
	
	@Select("select e.* from t_tarifa e where e.cod_ciclo = #{p_codIdCiclo}")
	public List<Tarifa> findByIdCiclo(@Param("p_codIdCiclo") Integer codIdCiclo) throws Exception;
	
	@Insert("insert into t_tarifa (descripcion, costo,costo_back, costo_digitalizador,costo_supervisor, cod_ciclo) values (#{descripcion},#{costo},#{costo_back},#{costo_digitalizador},#{costo_supervisor},#{cod_ciclo})")
	public void crearTarifa(Tarifa tarifa) throws Exception;
	
	@Update("update t_tarifa set descripcion = #{descripcion}, costo = #{costo}, costo_back = #{costo_back}, costo_digitalizador = #{costo_digitalizador}, costo_supervisor = #{costo_supervisor}, cod_ciclo = #{cod_ciclo} where id_tarifa= #{id_tarifa}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTarifa(Tarifa ciclo) throws Exception;
	
	@Delete("delete from t_tarifa where id_tarifa = #{p_id_tarifa}")
	@Options(flushCache=true)
	public void eliminarTarifa(@Param("p_id_tarifa") Integer codigoTarifa) throws Exception;
	
	@Select("select e.*, tp.descripcion as ntipociclo from t_tarifa e, t_tipo_ciclo tp where tp.cod_tipo_ciclo=#{p_codTipoCiclo} and e.cod_ciclo = #{p_codCiclo} order by e.id_tarifa asc")
	public List<Tarifa> listarTarifa(@Param("p_codTipoCiclo") Integer codTipoCiclo, @Param("p_codCiclo") Integer codCiclo) throws Exception;

}
