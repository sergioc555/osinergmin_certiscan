package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;


public interface TelefonoExpedienteTemporalMapper {

	@Select("select * from t_telefonosexpedientes_temporal e where e.id = #{id}")
	public TelefonoExpedienteTemporal findById(@Param("id") Integer id) throws Exception;
	
	@Select("select * from t_telefonosexpedientes_temporal")
	public List<TelefonoExpedienteTemporal> findAll() throws Exception;	
	
	@Select("select * from t_telefonosexpedientes_temporal where "
			+ "id_producto= #{idProducto} and periodo= #{periodo} and idusuario=#{idUsuario} and "
			+ "valor in "
			+ "(select valor from t_baseindecopy where idcabeceraindecopy=#{idCabeceraIndecopy} and tiporegistro<>'CORREO_ELECTRONICO')")
	public List<TelefonoExpedienteTemporal> findByIndecopy(@Param("idProducto") Integer idProducto, @Param("periodo") Date periodo,
			@Param("idUsuario") Integer idUsuario, @Param("idCabeceraIndecopy") Integer idCabeceraIndecopy) throws Exception;	
	
	@Insert("insert into t_telefonosexpedientesexcluidos_temporal (valor, id_producto, periodo, idusuario) "
			+ " VALUES (#{valor}, #{idProducto}, #{periodo}, #{idUsuario})")
	public void crearTelefonoExpedienteExcluidoTemporal(@Param("idProducto") Integer idProducto, @Param("periodo") Date periodo,
			@Param("idUsuario") Integer idUsuario, @Param("valor") String valor) throws Exception;	
	
	@Insert("insert into t_telefonosexpedientes_temporal (codigo, estado) values (#{codigo},#{estado})")
	public void crearTelefonoExpedienteTemporal(TelefonoExpedienteTemporal telefonoExpedienteTemporal) throws Exception;
	
	@Update("update t_telefonosexpedientes_temporal set codigo = #{codigo}, estado = #{estado} where idtelefonoExpedienteTemporal= #{idtelefonoExpedienteTemporal}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTelefonoExpedienteTemporal(TelefonoExpedienteTemporal telefonoExpedienteTemporal) throws Exception;
	
	@Delete("delete  from t_telefonosexpedientes_temporal  where id = #{id}")
	@Options(flushCache=true)
	public void eliminarTelefonoExpedienteTemporal(@Param("id") Integer id) throws Exception;
	
		
	
	
}
