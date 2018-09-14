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
import com.certicom.certiscan.domain.FacturacionCabecera;




public interface FacturacionCabeceraMapper {
	
	
	@Select("select t_expediente_cabecera.id_expediente_cabecera as idFacturacionCabecera, t_expediente_cabecera.idusuario, t_expediente_cabecera.fecha, t_expediente_cabecera.numeroregistro, "
			+ "t_expediente_cabecera.periodo, (t_usuario.nombre || ' ' || t_usuario.apellido_paterno || ' ' || t_usuario.apellido_materno) as nombreUsuario, "
			+ "t_expediente_cabecera.id_producto as idProducto, t_expediente_cabecera.numeroasignados, t_producto.descripcion as nombreProducto, t_negocio.descripcion as nombreNegocio from t_expediente_cabecera "
			+ "inner join t_usuario on t_expediente_cabecera.idusuario=t_usuario.idusuario "
			+ "inner join t_producto on t_producto.id_producto = t_expediente_cabecera.id_producto "
			+ "inner join t_negocio on t_producto.id_negocio = t_negocio.id_negocio "
			+ "order by t_expediente_cabecera.periodo desc, t_producto.descripcion ")
	public List<FacturacionCabecera> findAll() throws Exception;
	
	public List<FacturacionCabecera> findByPAGINATOR(@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("tipoBase") String tipoBase,@Param("negocio") Integer id_negocio,@Param("periodo") Date periodo) throws Exception;
	public Integer getCountPAGINATOR(@Param("filters") Map<String,Object> filters, @Param("tipoBase") String tipoBase,@Param("negocio") Integer id_negocio,@Param("periodo") Date periodo)throws Exception;
	
	
	@Select("select * from t_expediente_cabecera where periodo=#{periodo}")
	public FacturacionCabecera findByPeriodo(@Param("periodo") Date periodo) throws Exception;
	
	
	@Select("select * from t_expediente_cabecera where t_expediente_cabecera.idusuario=#{idUsuario} and"
			+ " t_expediente_cabecera.id_producto=#{idProducto} and t_expediente_cabecera.periodo=#{periodo} order by fecha DESC OFFSET 0 LIMIT 1")
	public FacturacionCabecera findByUltimoByUsuarioByProducto(@Param("idUsuario") Integer idUsuario, @Param("idProducto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;
	
	@Select("select id_expediente_cabecera as idFacturacionCabecera, idusuario as idUsuario, fecha, numeroregistro as numeroRegistro, periodo, id_producto as idProducto, numeroasignados as numeroAsignados,"
			+ " nombrearchivo as nombreArchivo, numerocondatos as numeroConDatos, numerosindatos as numeroSinDatos  from t_expediente_cabecera where "
			+ " id_producto=#{idProducto} and periodo=#{periodo}")
	public FacturacionCabecera findByPeriodoByProducto(@Param("idProducto") Integer idProducto, @Param("periodo") Date periodo) throws Exception;
	
	//@SelectKey(statement="select currval('sec_cabecera_expediente')", resultType = int.class, before = true, keyProperty = "id_expediente_cabecera")
	//@Options(useGeneratedKeys=true, keyProperty="idFacturacionCabecera")
	//@Insert("insert into t_expediente_cabecera (idusuario, fecha, numeroregistro,periodo, id_producto) values (#{idUsuario},#{fecha},#{numeroRegistro},#{periodo},#{idProducto})")
	public void crearFacturacionCabecera(FacturacionCabecera facturacionCabecera) throws Exception;
	public void crearFacturacionCabecera2(FacturacionCabecera facturacionCabecera) throws Exception;
		
	@Update("update t_expediente_cabecera set numeroasignados = (numeroasignados + #{numeroAsignados}) where id_expediente_cabecera= #{idFacturacionCabecera}")
	@Options(flushCache=true,useCache=true)
    public void actualizarRegistrosAsignados(@Param("idFacturacionCabecera") Integer idFacturacionCabecera, @Param("numeroAsignados") Integer numeroAsignados) throws Exception;
	
	
	@Delete("delete  from t_expediente_cabecera  where id_expediente_cabecera = #{idFacturacionCabecera}")
	@Options(flushCache=true)
	public void eliminarFacturacionCabecera(@Param("idFacturacionCabecera") Integer idFacturacionCabecera) throws Exception;
	
	
	@Delete("delete  from t_facturacion_cabecera  where id_facturacion_cabecera = #{id_facturacion_cabecera}")
	@Options(flushCache=true)
	public void eliminarFacturacionCabeceraxId(@Param("id_facturacion_cabecera") Integer id_facturacion_cabecera) throws Exception;
	
	@Select(" SELECT max(t_facturacion_cabecera.id_facturacion_cabecera) from t_facturacion_cabecera where "
			+" t_facturacion_cabecera.idusuario=#{idUsuario} and t_facturacion_cabecera.id_negocio=#{id_negocio} and t_facturacion_cabecera.periodo=#{periodo}  "
			+" and t_facturacion_cabecera.fecha=#{fecha} ")
	public Integer getLastInsertId(FacturacionCabecera facturacionCabecera) throws Exception;
	
	@Select("select * from t_facturacion_cabecera where periodo = #{periodo} and nombrearchivo = #{nombarchivo} ")
	public List<FacturacionCabecera> verificarCargaByPeriodoxNombre(@Param("periodo") Date periodo, @Param("nombarchivo") String nombarchivo)throws Exception;
	
	public List<FacturacionCabecera> findByNegocioPeriodo(@Param("negocio") Integer id_negocio,@Param("periodo") Date periodo) throws Exception;
}
