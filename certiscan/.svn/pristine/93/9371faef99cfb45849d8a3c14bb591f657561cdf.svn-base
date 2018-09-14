package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.SubIndicadorCall;


public interface SubIndicadorCallMapper {


	@Select("select e.* from t_sub_indicadores_call e where e.id_indicadores_call = #{p_indicadorCall} and e.id_categoria_call = #{p_categoriaCall} ")
	public List<SubIndicadorCall> findById(@Param("p_indicadorCall") Integer codigoIndicadorCall, @Param("p_categoriaCall") Integer codigoCategoriaCall) throws Exception;
	
	@Select("select e.* from t_sub_indicadores_call e where e.id_indicadores_call = #{p_indicadorCall}")
	public List<SubIndicadorCall> findByIndicadorCall(@Param("p_indicadorCall") Integer p_indicadorCall) throws Exception;
	
	@Update("update t_sub_indicadores_call set id_categoria_call = #{id_categoria_call}, id_indicadores_call = #{id_indicadores_call}, descripcion = #{descripcion} where idsubindicadores_call= #{idsubindicadores_call}")
	@Options(flushCache=true,useCache=true)
	public void actualizarSubIndicadorCall(SubIndicadorCall subindicadorCall) throws Exception;

	@Insert("insert into t_sub_indicadores_call (descripcion, id_indicadores_call, id_categoria_call) values (#{descripcion},#{id_indicadores_call},#{id_categoria_call})")
	public void crearSubIndicadorCall(SubIndicadorCall subindicadorCall) throws Exception;
	
	@Delete("delete  from t_sub_indicadores_call  where idsubindicadores_call = #{idsubindicadores_call}")
	@Options(flushCache=true)
	public void eliminarSubIndicadorCall(@Param("idsubindicadores_call")Integer id_indicadores_call) throws Exception;
	
	
	/*//asociar Indicador Call y Categoria Call
	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion "
			+ "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call order by ic.descripcion asc")
	public List<IndicadorCall> findAll() throws Exception;	
	
	@Select("select * from t_indicadores_call where estado = 'TRUE' order by descripcion asc")
	public List<IndicadorCall> listaIndicadorCallActivo() throws Exception;
	
	
	@Insert("insert into t_indicadores_call (id_categoria_call, descripcion, estado) values (#{id_categoria_call},#{descripcion},#{estado})")
	public void crearIndicadorCall(IndicadorCall indicadorCall) throws Exception;
	
	@Update("update t_indicadores_call set id_categoria_call = #{id_categoria_call}, descripcion = #{descripcion}, estado = #{estado} where id_indicadores_call= #{id_indicadores_call}")
	@Options(flushCache=true,useCache=true)
    public void actualizarIndicadorCall(IndicadorCall indicadorCall) throws Exception;
	
	@Delete("delete  from t_indicadores_call  where id_indicadores_call = #{id_indicadores_call}")
	@Options(flushCache=true)
	public void eliminarIndicadorCall(@Param("id_indicadores_call") Integer id_indicadores_call) throws Exception;	
	
	@Select("select ic.id_indicadores_call, cc.id_categoria_call, cc.descripcion as des_ctgCall, ic.estado, ic.descripcion "
			+ "from t_indicadores_call ic inner join t_categoria_call cc on ic.id_categoria_call = cc.id_categoria_call "
			+ " where cc.id_categoria_call= #{id_categoria_call} order by ic.descripcion asc")
	public List<IndicadorCall> listaIndxCategoria(@Param("id_categoria_call") Integer id_categoria_call) throws Exception;	

	*/
}
