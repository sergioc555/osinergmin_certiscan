package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CategoriaIndicador;


public interface CategoriaIndicadorMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_categoria_indicador e where e.id_categoria = #{idCategoria}")
	public CategoriaIndicador findById(@Param("idCategoria") Integer codigoCategoria) throws Exception;
	
	@Select("select * from t_categoria_indicador order by descripcion asc")
	public List<CategoriaIndicador> findAll() throws Exception;
	
	@Insert("insert into t_categoria_indicador (descripcion) values (#{descripcion})")
	public void crearCategoriaIndicador(CategoriaIndicador categoria) throws Exception;
	
	
	@Update("update t_categoria_indicador set descripcion = #{descripcion} where id_categoria= #{id_categoria}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCategoriaIndicador(CategoriaIndicador Categoria) throws Exception;
	
	@Delete("delete from t_categoria_indicador where id_categoria = #{idCategoria}")
	@Options(flushCache=true)
	public void eliminarCategoriaIndicador(@Param("idCategoria") Integer codigoCategoria) throws Exception;
	
}
