package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.LogSBS;


public interface LogSBSMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	
	@Select("select * from t_log_sbs e where e.idlog = #{p_logsbs}")
	public LogSBS findById(@Param("p_logsbs") Integer codigoLogSBS) throws Exception;
	
	@Select("select e.*, (u.nombre ||' ' ||u.apellido_paterno||' '||u.apellido_materno) as usuarioAsignado from t_log_sbs e inner join t_usuario u on u.idusuario = e.idusuario where cast(e.fecha_consulta as Date) >= #{p_fecini} and cast(e.fecha_consulta as Date) <= #{p_fecfin}")
	public List<LogSBS> findByFechaIniFin(@Param("p_fecini") Date fec_ini,@Param("p_fecfin") Date fec_fin) throws Exception;
	
	@Select("select * from t_log_sbs order by idusuario asc")
	public List<LogSBS> findAll() throws Exception;
	
	@Insert("insert into t_log_sbs (fecha_consulta, idusuario,tipdoc,numdoc) values (#{fecha_consulta},#{idusuario},#{tipdoc},#{numdoc})")
	public void crearLogSBS(LogSBS logSBS) throws Exception;
	
	@Update("update t_log_sbs set fecha_consulta = #{fecha_consulta}, idusuario = #{idusuario},tipdoc = #{tipdoc}, numdoc = #{numdoc} where idlog= #{idlog}")
	@Options(flushCache=true,useCache=true)
    public void actualizarLogSBS(LogSBS logSBS) throws Exception;
	
	@Delete("delete from t_log_sbs  where idlog = #{p_logsbs}")
	@Options(flushCache=true)
	public void eliminarLogSBS(@Param("p_logsbs") Integer codigoLogSBS) throws Exception;
	
}
