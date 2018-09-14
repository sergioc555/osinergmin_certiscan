package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cabecera;
import com.certicom.certiscan.domain.Centro_Atencion;


public interface CabeceraMapper {


	@Select("select * from t_cabecera e where e.id_cabecera = #{idcabecera}")
	public Cabecera findById(@Param("idcabecera") Integer idcabecera) throws Exception;
	
	@Select("select * from t_cabecera")
	public List<Cabecera> findAll() throws Exception;	
	
	@Select("select * from t_cabecera where descripcion=#{descripcion}")
	public List<Cabecera> findByDescripcion(@Param("descripcion") String descripcion) throws Exception;	
	
	@Select("select * from t_cabecera where idcabecera in (select d.idcabecera from t_formato_detalle d "
			+ "where d.idformato= #{idformato}) order by descripcion")
	public List<Cabecera> cabecerasByDetalleFormato(@Param("idformato") Integer idformato) throws Exception;
	
	
	@Select("select * from t_cabecera where idcabecera not in (select d.idcabecera from t_formato_detalle d "
			+ "where d.idformato= #{idformato}) order by descripcion")
	public List<Cabecera> cabecerasComplemento(@Param("idformato") Integer idformato) throws Exception;	

	
	@Insert("insert into t_cabecera (descripcion, estado) values (#{descripcion},#{estado})")
	public void crearCabecera(Cabecera cabecera) throws Exception;
	
	@Delete("delete  from t_cabecera  where idcabecera = #{idcabecera}")
	@Options(flushCache=true)
	public void eliminarCabecera(@Param("idcabecera") Integer idcabecera) throws Exception;	
	
	
}
