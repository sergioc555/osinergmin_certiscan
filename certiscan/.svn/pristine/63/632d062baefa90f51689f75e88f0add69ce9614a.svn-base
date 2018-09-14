package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cabecera;
import com.certicom.certiscan.domain.CabeceraFacturacionNombre;
import com.certicom.certiscan.domain.CabeceraNombre;


public interface CabeceraFacturacionNombreMapper {


	@Select("select * from t_cabecera_facturacion_nombre e where e.id_cabecera_facturacion_nombre = #{idCabeceraFacturacionNombre}")
	public CabeceraFacturacionNombre findById(@Param("idCabeceraFacturacionNombre") Integer idCabeceraFacturacionNombre) throws Exception;
	
	@Select("select * from t_cabecera_facturacion_nombre e where e.id_cabecera_facturacion = #{idCabeceraFacturacion} and e.estado=true")
	public List<CabeceraFacturacionNombre> findByCabeceraId(@Param("idCabeceraFacturacion") Integer idCabeceraFacturacion) throws Exception;
	
	@Select("select * from t_cabecera_facturacion_nombre e where e.descripcion = #{descripcion}")
	public List<CabeceraFacturacionNombre> findByDescripcion(@Param("descripcion") String descripcion) throws Exception;
	
	@Select("select * from t_cabecera_facturacion_nombre")
	public List<CabeceraFacturacionNombre> findAll() throws Exception;	
	
	@Insert("insert into t_cabecera_facturacion_nombre (id_cabecera_facturacion, descripcion, estado) values (#{idcabecera}, #{descripcion}, #{estado})")
	public void crearCabeceraFacturacionNombre(CabeceraFacturacionNombre cabeceraFacturacionNombre) throws Exception;
	
	@Delete("delete  from t_cabecera_facturacion_nombre  where id_cabecera_facturacion_nombre = #{idCabeceraFacturacionNombre}")
	@Options(flushCache=true)
	public void eliminarCabeceraFacturacionNombre(@Param("idCabeceraFacturacionNombre") Integer idCabeceraFacturacionNombre) throws Exception;	
	
	
}
