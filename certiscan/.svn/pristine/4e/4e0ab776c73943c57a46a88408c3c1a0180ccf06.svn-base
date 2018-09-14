package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.IndicadorCall;

import src.com.certicom.certiscan.utils.ExpedienteFilter;

public interface IndicadorCallMapper {

	@Select("select * from t_indicadores_call e where e.id_indicadores_call = #{p_indicadorCall}")
	public IndicadorCall findById(@Param("p_indicadorCall") Integer codigoIndicadorCall) throws Exception;

	@Select("select * from t_indicadores_call e where e.id_categoria_call = #{id_categoria_call}")
	public List<IndicadorCall> findByCategoria(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	// asociar Indicador Call y Categoria Call
	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call order by ic.descripcion asc")
	public List<IndicadorCall> findAll() throws Exception;

	@Select("select * from t_indicadores_call where estado = 'TRUE' order by descripcion asc")
	public List<IndicadorCall> listaIndicadorCallActivo() throws Exception;

	@Select("select i.* from t_indicadores_call i inner join t_categoria_call c on i.id_categoria_call = c.id_categoria_call " + " where i.estado = 'TRUE' and c.id_producto=#{idProducto} and flag_banco IS NULL  order by i.descripcion asc")
	public List<IndicadorCall> listaIndicadorCallByProducto(@Param("idProducto") Integer idProducto) throws Exception;
	
	@Select("select a.descripcion from t_indicadores_call a inner join t_categoria_call b on a.id_categoria_call = b.id_categoria_call "+
			"where b.id_categoria_call = #{idCategoriaCall} and b.id_producto = #{idProducto} and a.estado = 'TRUE' and a.flag_banco IS NULL "+
			"order by a.descripcion asc")
	public List<String> listaIndicadorCallByProductos(@Param("idProducto") Integer idProducto,@Param("idCategoriaCall") Integer idCategoriaCall) throws Exception;
	
	
	public List<String> listaIndicadorCallByNegocios(ExpedienteFilter filter) throws Exception;

	@Insert("insert into t_indicadores_call (id_categoria_call, descripcion, estado, generar_expediente, valor, medida) values (#{id_categoria_call},#{descripcion},#{estado},#{generar_expediente},#{valor},#{medida})")
	public void crearIndicadorCall(IndicadorCall indicadorCall) throws Exception;

	@Update("update t_indicadores_call set id_categoria_call = #{id_categoria_call}, descripcion = #{descripcion}, estado = #{estado}, generar_expediente = #{generar_expediente}, valor = #{valor}, medida = #{medida} where id_indicadores_call= #{id_indicadores_call}")
	@Options(flushCache = true, useCache = true)
	public void actualizarIndicadorCall(IndicadorCall indicadorCall) throws Exception;

	@Delete("delete  from t_indicadores_call  where id_indicadores_call = #{id_indicadores_call}")
	@Options(flushCache = true)
	public void eliminarIndicadorCall(@Param("id_indicadores_call") Integer id_indicadores_call) throws Exception;

	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente,ic.valor,ic.medida " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} order by ic.orden asc")
	public List<IndicadorCall> listaIndxCategoria(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	public List<IndicadorCall> getCountIndicadoresxCategoria(ExpedienteFilter filter) throws Exception;

	public List<IndicadorCall> getCountExpxIndCallxTipPlanilla(ExpedienteFilter filter) throws Exception;

	public List<IndicadorCall> distinctIndCategorias(ExpedienteFilter filter) throws Exception;

	public List<IndicadorCall> srptCountIndicadorexTipoPlanilla(ExpedienteFilter filter) throws Exception;

	public List<IndicadorCall> getCountExpxIndCallxTerritorio(ExpedienteFilter filter) throws Exception;

	public List<IndicadorCall> getCountExpxIndCallxPrioridad(ExpedienteFilter filter) throws Exception;

	@Select("select * from t_indicadores_call where estado = 'TRUE' and id_categoria_call = #{id_categoria_call}")
	public List<IndicadorCall> listByIdCatecoriaCall(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.id_indicadores_call != 1066 order by ic.orden asc")
	public List<IndicadorCall> listaIndxCategoriaSupervisorPyme(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.id_indicadores_call != 1066 order by ic.orden asc")
	public List<IndicadorCall> listaIndxCategoriaBackPyme(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	
	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.flag_consesionario = 'CONS' order by ic.descripcion asc")
	public List<IndicadorCall> listaIndxCategoriaConsesionario(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call}  and ic.id_indicadores_call != 1066 order by ic.orden asc")
	public List<IndicadorCall> listaIndxCategoriaSupervisorHipotecario(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	@Select("select ic.* from t_categoria_call cc inner join t_indicadores_call ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and ic.flag_banco = 'FAMILIA' ")
	public List<IndicadorCall> getxFamiliaResultadosFeedBack(@Param("id_producto") Integer id_producto);
	
	@Select("select * from t_indicadores_call where id_estado_padre = #{id_indicadores_padre} and flag_banco = 'SUBFAMILIA'")
	public List<IndicadorCall> getxSubFamiliaResultadosFeedBack(@Param("id_indicadores_padre") Integer id_indicadores_call);
	
	@Select("select ic.* from t_categoria_call cc inner join t_indicadores_call ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and ic.flag_banco = 'FAMILIA' and ic.descripcion != 'EN PROCESO' and  ic.descripcion != 'REGISTROS PERDIDOS' ")
	public List<IndicadorCall> getxFamiliaResultadosFeedBack2(@Param("id_producto") Integer id_producto);
	
	
	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente, ic.orden_backlog " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.flag_banco = 'BACK_LOG' order by ic.orden_backlog asc")
	public List<IndicadorCall> listaIndxCategoriaBackLog(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	@Select("select ic.* from t_categoria_call cc inner join t_indicadores_call ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and cc.descripcion = 'ACEPTO PRODUCTO' ")
	public List<IndicadorCall> getxIndicadoresAceptaProducto(@Param("id_producto") Integer id_producto);
	
	
	@Select("select ic.* from t_indicadores_call ic where ic.id_estado_padre = #{id_estado_padre} order by ic.descripcion")
	public List<IndicadorCall> getxIndicadoresxPadre(@Param("id_estado_padre") Integer id_estado_padre);
	
	@Select("select ic.* from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call "
			+ " where ic.id_categoria_call = 261 and ic.estado order by ic.descripcion ")
	public List<IndicadorCall> getxIndicadoresVisita();
	
	
	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente, ic.orden_backlog " + "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.orden_backlog2 is not null order by ic.orden_backlog2 asc")
	public List<IndicadorCall> listaIndxCategoriaBackLog2(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	
	@Select("select ic.* from t_categoria_call cc inner join t_indicadores_call ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and ic.estado order by ic.descripcion ")
	public List<IndicadorCall> getxIndicadoresActivosByProducto(@Param("id_producto") Integer id_producto);
	
	@Select("select * from t_indicadores_call where id_categoria_call  = #{id_categoria_call}")
	public List<IndicadorCall> getProceso(@Param("id_categoria_call") Integer id_categoria_call);
	
	@Select("select * from t_indicadores_call where id_categoria_call  = #{id_categoria_call}")
	public List<IndicadorCall> getTipoRecepcion(@Param("id_categoria_call") Integer id_categoria_call);
	
	@Select("select * from t_indicadores_call where id_categoria_call  = #{id_categoria_call}")
	public List<IndicadorCall> getIncidencia(@Param("id_categoria_call") Integer id_categoria_call);

}
