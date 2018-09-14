package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cabecera;
import com.certicom.certiscan.domain.CabeceraNombre;


public interface CabeceraNombreMapper {


	@Select("select * from t_cabecera_nombre e where e.idCabeceraNombre = #{idCabeceraNombre}")
	public CabeceraNombre findById(@Param("idCabeceraNombre") Integer idCabeceraNombre) throws Exception;
	
	@Select("select * from t_cabecera_nombre e where e.idCabecera = #{idCabecera} and e.estado=true")
	public List<CabeceraNombre> findByCabeceraId(@Param("idCabecera") Integer idCabecera) throws Exception;
	
	@Select("select * from t_cabecera_nombre e where e.descripcion = #{descripcion}")
	public List<CabeceraNombre> findByDescripcion(@Param("descripcion") String descripcion) throws Exception;
	
	@Select("select * from t_cabecera_nombre")
	public List<CabeceraNombre> findAll() throws Exception;	
	
	@Insert("insert into t_cabecera_nombre (idcabecera, descripcion, estado) values (#{idcabecera}, #{descripcion}, #{estado})")
	public void crearCabeceraNombre(CabeceraNombre cabeceraNombre) throws Exception;
	
	@Delete("delete  from t_cabecera_nombre  where idcabeceraNombre = #{idcabeceraNombre}")
	@Options(flushCache=true)
	public void eliminarCabeceraNombre(@Param("idcabeceraNombre") Integer idcabeceraNombre) throws Exception;	
	
	
}
