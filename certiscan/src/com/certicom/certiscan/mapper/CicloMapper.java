package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Ciclo;

public interface CicloMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	
	@Select("select e.* from t_ciclo e where e.cod_ciclo = #{p_codCiclo}")
	public Ciclo findById(@Param("p_codCiclo") Integer codigoCiclo) throws Exception;
	
	@Select("select e.*, tc.descripcion as ntipoCiclo from t_ciclo e, t_tipo_ciclo tc where e.cod_tipo_ciclo = tc.cod_tipo_ciclo order by e.anio, e.mes asc")
	public List<Ciclo> findAll() throws Exception;
	
	@Select("select distinct e.*, tc.descripcion as ntipoCiclo from t_ciclo e, t_tipo_ciclo tc, t_tarifa tbc where e.cod_ciclo = tbc.cod_ciclo and e.flag_tab=true and e.cod_tipo_ciclo = tc.cod_tipo_ciclo order by e.anio, e.mes asc")
	public List<Ciclo> findCiclosActivos() throws Exception;
	
	@Select("select distinct e.*, tc.descripcion as ntipoCiclo from t_ciclo e, t_tipo_ciclo tc, t_tarifa tbc where e.cod_ciclo = tbc.cod_ciclo and e.flag_tab is null and e.cod_tipo_ciclo = tc.cod_tipo_ciclo order by e.anio, e.mes asc")
	public List<Ciclo> findCiclosInactivosActivos() throws Exception;
	
	@Select("select distinct e.* from t_ciclo e left join t_tarifa tbc on e.cod_ciclo = tbc.cod_ciclo and e.flag_tab=true where e.cod_tipo_ciclo = #{p_codTipoCiclo}")
	public List<Ciclo> findByTipoCicloActivo(@Param("p_codTipoCiclo") Integer codigoTipoCiclo) throws Exception;
	
	@Select("select distinct e.* from t_ciclo e inner join t_tarifa tbc on e.cod_ciclo = tbc.cod_ciclo where e.cod_ciclo = #{p_codCiclo}")
	public Ciclo findByIdCiclo(@Param("p_codCiclo") Integer codigoCiclo) throws Exception;
	
	@Select("select distinct e.* from t_ciclo e where e.cod_tipo_ciclo = #{p_codTipoCiclo}")
	public List<Ciclo> findByTipoCicloActivoSinTablero(@Param("p_codTipoCiclo") Integer codigoTipoCiclo) throws Exception;
	
	@Select("select e.* from t_ciclo e where e.flag_tab is null and e.cod_tipo_ciclo = #{p_codTipoCiclo}")
	public List<Ciclo> findByTipoCicloInactivo(@Param("p_codTipoCiclo") Integer codigoTipoCiclo) throws Exception;
	
	@Select("select * from t_ciclo where mes=#{mes} and anio=#{anio} and cod_tipo_ciclo =#{p_codTipoCiclo} order by anio, mes asc")
	public List<Ciclo> buscarXPeriodo(@Param("mes") String mes, @Param("anio") Integer anio, @Param("p_codTipoCiclo") Integer codTipoCiclo) throws Exception;
	
	@Insert("insert into t_ciclo (mes, anio, fecini, fecfin, cod_tipo_ciclo) values (#{mes},#{anio},#{fecini},#{fecfin},#{cod_tipo_ciclo})")
	public void crearCiclo(Ciclo estado) throws Exception;
	
	@Update("update t_ciclo set mes = #{mes}, anio = #{anio}, fecini = #{fecini}, fecfin = #{fecfin}, cod_tipo_ciclo = #{cod_tipo_ciclo} where cod_ciclo= #{cod_ciclo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCiclo(Ciclo ciclo) throws Exception;
	
	@Update("update t_ciclo set estado = #{estado} where cod_ciclo= #{cod_ciclo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstadoCiclo(Ciclo ciclo) throws Exception;
	
	@Update("update t_ciclo set flag_tab = #{flag_tab} where cod_ciclo= #{cod_ciclo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarFlagTab(Ciclo ciclo) throws Exception;
	
	@Delete("delete from t_ciclo  where cod_ciclo = #{cod_ciclo}")
	@Options(flushCache=true)
	public void eliminarCiclo(@Param("cod_ciclo") Integer cod_ciclo) throws Exception;
	

}
