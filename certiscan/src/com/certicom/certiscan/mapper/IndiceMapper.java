package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Indice;

import src.com.certicom.certiscan.utils.ExpedienteFilter;

public interface IndiceMapper {

	@Select("select * from t_indice e where e.id_indice = #{p_indice}")
	public Indice findById(@Param("p_indice") Integer codigoIndice) throws Exception;

	@Select("select * from t_indice e where e.id_categoria_call = #{id_categoria_call}")
	public List<Indice> findByCategoria(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	// asociar Indicador Call y Categoria Call
	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call order by ic.descripcion asc")
	public List<Indice> findAll() throws Exception;

	@Select("select * from t_indice where estado = 'TRUE' order by descripcion asc")
	public List<Indice> listaIndiceActivo() throws Exception;

	@Insert("insert into t_indice (id_categoria_call, descripcion, estado, generar_expediente, valor, medida) values (#{id_categoria_call},#{descripcion},#{estado},#{generar_expediente},#{valor},#{medida})")
	public void crearIndice(Indice Indice) throws Exception;

	@Update("update t_indice set id_categoria_call = #{id_categoria_call}, descripcion = #{descripcion}, estado = #{estado}, generar_expediente = #{generar_expediente}, valor = #{valor}, medida = #{medida} where id_indice= #{id_indice}")
	@Options(flushCache = true, useCache = true)
	public void actualizarIndice(Indice Indice) throws Exception;

	@Delete("delete  from t_indice  where id_indice = #{id_indice}")
	@Options(flushCache = true)
	public void eliminarIndice(@Param("id_indice") Integer id_indice) throws Exception;

	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente,ic.valor,ic.medida " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} order by ic.orden asc")
	public List<Indice> listaIndxTipoDocumento(@Param("id_tipo_documento") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente,ic.valor,ic.medida " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} order by ic.orden asc")
	public List<Indice> listaIndxTipoDato(@Param("id_tipo_dato") Integer id_categoria_call) throws Exception;
	
	public List<Indice> getCountIndicadoresxCategoria(ExpedienteFilter filter) throws Exception;

	public List<Indice> getCountExpxIndCallxTipPlanilla(ExpedienteFilter filter) throws Exception;

	public List<Indice> distinctIndCategorias(ExpedienteFilter filter) throws Exception;

	public List<Indice> srptCountIndicadorexTipoPlanilla(ExpedienteFilter filter) throws Exception;

	public List<Indice> getCountExpxIndCallxTerritorio(ExpedienteFilter filter) throws Exception;

	public List<Indice> getCountExpxIndCallxPrioridad(ExpedienteFilter filter) throws Exception;

	@Select("select * from t_indice where estado = 'TRUE' and id_categoria_call = #{id_categoria_call}")
	public List<Indice> listByIdCatecoriaCall(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.id_indice != 1066 order by ic.orden asc")
	public List<Indice> listaIndxCategoriaSupervisorPyme(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.id_indice != 1066 order by ic.orden asc")
	public List<Indice> listaIndxCategoriaBackPyme(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	
	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.flag_consesionario = 'CONS' order by ic.descripcion asc")
	public List<Indice> listaIndxCategoriaConsesionario(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;

	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call}  and ic.id_indice != 1066 order by ic.orden asc")
	public List<Indice> listaIndxCategoriaSupervisorHipotecario(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	@Select("select * from t_indice where id_estado_padre = #{id_indicadores_padre} and flag_banco = 'SUBFAMILIA'")
	public List<Indice> getxSubFamiliaResultadosFeedBack(@Param("id_indicadores_padre") Integer id_indice);
	
	@Select("select ic.* from t_categoria_call cc inner join t_indice ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and ic.flag_banco = 'FAMILIA' and ic.descripcion != 'EN PROCESO' and  ic.descripcion != 'REGISTROS PERDIDOS' ")
	public List<Indice> getxFamiliaResultadosFeedBack2(@Param("id_producto") Integer id_producto);
	
	
	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente, ic.orden_backlog " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.flag_banco = 'BACK_LOG' order by ic.orden_backlog asc")
	public List<Indice> listaIndxCategoriaBackLog(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	@Select("select ic.* from t_categoria_call cc inner join t_indice ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and cc.descripcion = 'ACEPTO PRODUCTO' ")
	public List<Indice> getxIndicadoresAceptaProducto(@Param("id_producto") Integer id_producto);
	
	
	@Select("select ic.* from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call "
			+ " where ic.id_categoria_call = 261 and ic.estado order by ic.descripcion ")
	public List<Indice> getxIndicadoresVisita();
	
	
	@Select("select ic.id_indice, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion ,ic.generar_expediente, ic.orden_backlog " + "from t_indice ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call " + " where cc.id_categoria_call= #{id_categoria_call} and ic.orden_backlog2 is not null order by ic.orden_backlog2 asc")
	public List<Indice> listaIndxCategoriaBackLog2(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;
	
	
	@Select("select ic.* from t_categoria_call cc inner join t_indice ic on cc.id_categoria_call = ic.id_categoria_call where cc.id_producto = #{id_producto} "
			+ "and ic.estado order by ic.descripcion ")
	public List<Indice> getxIndicadoresActivosByProducto(@Param("id_producto") Integer id_producto);
	
	

}
