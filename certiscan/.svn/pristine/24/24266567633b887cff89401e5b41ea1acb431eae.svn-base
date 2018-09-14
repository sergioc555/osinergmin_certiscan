package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cabecera;
import com.certicom.certiscan.domain.CabeceraFacturacion;
import com.certicom.certiscan.domain.Centro_Atencion;


public interface CabeceraFacturacionMapper {


	@Select("select * from t_cabecera_facturacion e where e.id_cabecera = #{idcabecera}")
	public CabeceraFacturacion findById(@Param("idcabecera") Integer idcabecera) throws Exception;
	    
	@Select("select id_cabecera_facturacion as idCabeceraFacturacion, descripcion, estado, propiedad, columna from t_cabecera_facturacion")
	public List<CabeceraFacturacion> findAll() throws Exception;	
	
	@Select("select * from t_cabecera_facturacion where descripcion=#{descripcion}")
	public List<CabeceraFacturacion> findByDescripcion(@Param("descripcion") String descripcion) throws Exception;	
	
	@Select("select * from t_cabecera_facturacion where idcabecera in (select d.idcabecera from t_formato_detalle d "
			+ "where d.idformato= #{idformato}) order by descripcion")
	public List<CabeceraFacturacion> cabeceraFacturacionByDetalleFormato(@Param("idformato") Integer idformato) throws Exception;
	
	
	@Select("select * from t_cabecera_facturacion where idcabecera not in (select d.idcabecera from t_formato_detalle d "
			+ "where d.idformato= #{idformato}) order by descripcion")
	public List<CabeceraFacturacion> cabeceraFacturacionComplemento(@Param("idformato") Integer idformato) throws Exception;	

	
	@Insert("insert into t_cabecera_facturacion (descripcion, estado) values (#{descripcion},#{estado})")
	public void crearCabeceraFacturacion(CabeceraFacturacion cabecera) throws Exception;
	
	@Delete("delete  from t_cabecera_facturacion  where idcabecera = #{idcabecera}")
	@Options(flushCache=true)
	public void eliminarCabeceraFacturacion(@Param("idcabecera") Integer idcabecera) throws Exception;	
	
}
