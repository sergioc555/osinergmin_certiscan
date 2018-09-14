package com.certicom.certiscan.mapper;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.BaseIndecopy;
import com.certicom.certiscan.domain.CabeceraBaseIndecopy;
import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.domain.ExpedienteCabecera;




public interface ExpedienteCabeceraMapper {
	
	
	@Select("select t_expediente_cabecera.id_expediente_cabecera as idExpedienteCabecera, t_expediente_cabecera.idusuario, t_expediente_cabecera.fecha, t_expediente_cabecera.numeroregistro, "
			+ "t_expediente_cabecera.periodo, (t_usuario.nombre || ' ' || t_usuario.apellido_paterno || ' ' || t_usuario.apellido_materno) as nombreUsuario, "
			+ "t_expediente_cabecera.id_producto as idProducto, t_expediente_cabecera.numeroasignados, t_producto.descripcion as nombreProducto, t_negocio.descripcion as nombreNegocio from t_expediente_cabecera "
			+ "inner join t_usuario on t_expediente_cabecera.idusuario=t_usuario.idusuario "
			+ "inner join t_producto on t_producto.id_producto = t_expediente_cabecera.id_producto "
			+ "inner join t_negocio on t_producto.id_negocio = t_negocio.id_negocio "
			+ "order by t_expediente_cabecera.periodo desc, t_producto.descripcion ")
	public List<ExpedienteCabecera> findAll() throws Exception;
	
	public List<ExpedienteCabecera> findByPAGINATORxUser(@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("tipoBase") String tipoBase,@Param("idusuario") Integer idusuario,@Param("negocio") Integer id_negocio,@Param("periodo") Date periodo) throws Exception;
	
	public Integer getCountPAGINATORxUser(@Param("filters") Map<String,Object> filters, @Param("tipoBase") String tipoBase,@Param("idusuario") Integer idusuario,@Param("negocio") Integer id_negocio,@Param("periodo") Date periodo)throws Exception;
	
	public List<ExpedienteCabecera> findByPAGINATOR(@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("tipoBase") String tipoBase) throws Exception;
	public Integer getCountPAGINATOR(@Param("filters") Map<String,Object> filters, @Param("tipoBase") String tipoBase)throws Exception;
	
	
	@Select("select * from t_expediente_cabecera where periodo=#{periodo}")
	public ExpedienteCabecera findByPeriodo(@Param("periodo") Date periodo) throws Exception;
	
	
	@Select("select * from t_expediente_cabecera where t_expediente_cabecera.idusuario=#{idUsuario} and"
			+ " t_expediente_cabecera.id_producto=#{idProducto} and t_expediente_cabecera.periodo=#{periodo} order by fecha DESC OFFSET 0 LIMIT 1")
	public ExpedienteCabecera findByUltimoByUsuarioByProducto(@Param("idUsuario") Integer idUsuario, @Param("idProducto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;
	
	@Select("select id_expediente_cabecera as idExpedienteCabecera, idusuario as idUsuario, fecha, numeroregistro as numeroRegistro, periodo, id_producto as idProducto, numeroasignados as numeroAsignados,"
			+ " nombrearchivo as nombreArchivo, numerocondatos as numeroConDatos, numerosindatos as numeroSinDatos, numeroexcluidobanco from t_expediente_cabecera where "
			+ " id_producto=#{idProducto} and periodo=#{periodo} ORDER BY id_expediente_cabecera DESC LIMIT 1")
	public ExpedienteCabecera findByPeriodoByProducto(@Param("idProducto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;
	
	//@SelectKey(statement="select currval('sec_cabecera_expediente')", resultType = int.class, before = true, keyProperty = "id_expediente_cabecera")
	//@Options(useGeneratedKeys=true, keyProperty="idExpedienteCabecera")
	//@Insert("insert into t_expediente_cabecera (idusuario, fecha, numeroregistro,periodo, id_producto) values (#{idUsuario},#{fecha},#{numeroRegistro},#{periodo},#{idProducto})")
	public void crearExpedienteCabecera(ExpedienteCabecera expedienteCabecera) throws Exception;
		
	@Update("update t_expediente_cabecera set numeroasignados = (numeroasignados + #{numeroAsignados}) where id_expediente_cabecera= #{idExpedienteCabecera}")
	@Options(flushCache=true,useCache=true)
    public void actualizarRegistrosAsignados(@Param("idExpedienteCabecera") Integer idExpedienteCabecera, @Param("numeroAsignados") Integer numeroAsignados) throws Exception;
	
	
	@Delete("delete  from t_expediente_cabecera  where id_expediente_cabecera = #{idExpedienteCabecera}")
	@Options(flushCache=true)
	public void eliminarExpedienteCabecera(@Param("idExpedienteCabecera") Integer idExpedienteCabecera) throws Exception;
	
	@Update("update t_expediente_cabecera set numeroexcluidobanco = #{numeroexcluidobanco} where id_expediente_cabecera= #{idExpedienteCabecera}")
	@Options(flushCache=true,useCache=true)
    public void actualizarRegistrosExcluidosBanco(@Param("idExpedienteCabecera") Integer idExpedienteCabecera, @Param("numeroexcluidobanco") Integer numeroexcluidobanco) throws Exception;
	
	
	
}
