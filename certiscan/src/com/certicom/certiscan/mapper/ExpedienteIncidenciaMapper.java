package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.ExpedienteIncidencia;
import com.certicom.certiscan.domain.Test;



public interface ExpedienteIncidenciaMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_expediente_incidencia e where e.cod_expincidencia = #{idExpIncidencia}")
	public ExpedienteIncidencia findById(@Param("idExpIncidencia") Integer codigoExpIncidencia) throws Exception;
	
	@Select("select * from t_expediente_incidencia order by descripcion asc")
	public List<ExpedienteIncidencia> findAll() throws Exception;
	
	@Select("select id_incidencia, descripcion as des_incidencia,0 as valor_exp, null as observacion from t_incidencia where estado = true order by descripcion asc")
	public List<ExpedienteIncidencia> findAll2() throws Exception;
	
	@Insert("insert into t_expediente_incidencia (expediente_id, id_incidencia, fecregistro, observacion, proceso) values (#{expediente_id}, #{id_incidencia} , #{fecregistro} , #{observacion}, #{proceso})")
	public void crearExpedienteIncidencia(ExpedienteIncidencia expincidencia) throws Exception;	
	
	@Update("update t_expediente_incidencia set expediente_id = #{expediente_id}, id_incidencia = #{id_incidencia}, fecregistro = #{fecregistro}, observacion = #{observacion}, proceso = #{proceso} where cod_expincidencia= #{cod_expincidencia}")
	@Options(flushCache=true,useCache=true)
    public void actualizarExpedienteIncidencia(ExpedienteIncidencia expincidencia) throws Exception;
	
	/*@Update("update t_expediente_incidencia set estado = #{estado} where id_incidencia= #{id_incidencia}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstadoIncidencia(ExpedienteIncidencia expincidencia) throws Exception;*/
	
	@Delete("delete from t_expediente_incidencia where cod_expincidencia = #{idExpIncidencia}")
	@Options(flushCache=true)
	public void eliminarExpedienteIncidencia(@Param("idExpIncidencia") Integer codigoExpIncidencia) throws Exception;
	
	@Select("select einc.cod_expincidencia,inc.id_incidencia, inc.descripcion as des_incidencia,coalesce(einc.expediente_id,0) as valor_exp, einc.observacion "
			+ "from t_incidencia inc left join t_expediente_incidencia einc on (inc.id_incidencia = einc.id_incidencia and einc.expediente_id = #{p_expediente_id}) where inc.estado = 'true'")
	public List<ExpedienteIncidencia> listarIncidencias(@Param("p_expediente_id") Integer expediente_id) throws Exception;
	
	@Select("select einc.cod_expincidencia,inc.id_indicadores_call, inc.descripcion as des_incidencia,coalesce(einc.expediente_id,0) as valor_exp, einc.observacion "
			+ "from t_indicadores_call inc inner join t_expediente_incidencia einc on (inc.id_indicadores_call = einc.id_incidencia and einc.expediente_id = #{p_expediente_id} and einc.proceso = #{proceso}) where inc.estado = 'true' and inc.id_categoria_call = #{id_indicador_call} ")
	public List<ExpedienteIncidencia> listarIncidenciasIndicadores(@Param("p_expediente_id") Integer expediente_id, @Param("id_indicador_call") Integer id_indicador_call, @Param("proceso") String proceso) throws Exception;
	
	@Select("select * from t_expediente_incidencia einc where einc.expediente_id = #{p_expediente_id} and einc.id_incidencia = #{id_indicador_call} and einc.proceso = #{proceso}")
	public List<ExpedienteIncidencia> listarIncidenciasExp(@Param("p_expediente_id") Integer expediente_id, @Param("id_indicador_call") Integer id_indicador_call, @Param("proceso") String proceso) throws Exception;
	
	@Update("update t_expediente_incidencia set observacion = #{observacion} where cod_expincidencia= #{cod_expincidencia}")
	@Options(flushCache=true,useCache=true)
    public void actualizarObsExpedienteIncidencia(ExpedienteIncidencia expedienteIncidencia) throws Exception;
	
}
