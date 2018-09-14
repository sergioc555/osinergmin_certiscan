package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.Medios;
import com.certicom.certiscan.domain.Test;



public interface MediosMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_medios e where e.id_medio = #{idMedios}")
	public Medios findById(@Param("idMedios") Integer codigoMedios) throws Exception;
	
	@Select("select * from t_medios order by descripcion asc")
	public List<Medios> findAll() throws Exception;
	
	//@Select("select * from t_medios where creado = false order by descripcion asc")
	public List<Medios> buscarPendientes(Medios filter) throws Exception;
	
	@Select("select coalesce(max(id_medio),0) from t_medios")
	public Integer findMax() throws Exception;
	
	@Insert("insert into t_medios (id_medio, descripcion, total_archivos, peso, descripcion_peso, fecha_registro, ruta, id_estado, tipo_medio, creado) values (#{id_medio},#{descripcion},#{total_archivos},#{peso}, #{descripcion_peso},#{fecha_registro},#{ruta},#{id_estado}, #{tipo_medio}, #{creado})")
	public void crearMedio(Medios medio) throws Exception;	
	
	@Update("update t_medios set descripcion = #{descripcion}, total_archivos = #{total_archivos}, peso = #{peso}, descripcion_peso = #{descripcion_peso}, fecha_registro = #{fecha_registro}, ruta = #{ruta}, id_estado = #{id_estado}, tipo_medio = #{tipo_medio}, creado = #{creado} where id_medio= #{id_medio}")
	@Options(flushCache=true,useCache=true)
    public void actualizarMedio(Medios cargo) throws Exception;
	
	@Update("update t_medios set ruta = #{ruta} where id_medio= #{id_medio}")
	@Options(flushCache=true,useCache=true)
    public void actualizarMedioRuta(Medios cargo) throws Exception;
	
	@Update("update t_medios set creado = #{creado} where id_medio= #{id_medio}")
	@Options(flushCache=true,useCache=true)
    public void actualizarMedioCreado(Medios cargo) throws Exception;
	
	@Update("update t_medios set fecha_ini = #{fecha_ini} where id_medio= #{id_medio}")
	@Options(flushCache=true,useCache=true)
    public void actualizarFechaInicio(Medios cargo) throws Exception;
	
	@Update("update t_medios set fecha_fin = #{fecha_fin} where id_medio= #{id_medio}")
	@Options(flushCache=true,useCache=true)
    public void actualizarFechaFin(Medios cargo) throws Exception;
	
	@Update("update t_medios set fecha_fin = #{fecha_fin}, fecha_ini = #{fecha_ini} where id_medio= #{id_medio}")
	@Options(flushCache=true,useCache=true)
    public void actualizarFechaInicioFin(Medios cargo) throws Exception;
	
	@Delete("delete from t_medios where id_medio = #{idMedios}")
	@Options(flushCache=true)
	public void eliminarMedio(@Param("idMedios") Integer cod_medio) throws Exception;
	
	public List<Medios> consultaMedios(Medios filter)throws Exception;
	
}
