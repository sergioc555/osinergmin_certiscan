package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.FacturacionPlanillaCabecera;

public interface FacturacionPlanillaCabeceraMapper {

	@Select("select e.* from t_facturacion_planilla_cabecera e where e.id_facturacion_planilla_cabecera = #{p_codFacturacionPlanillaCabecera}")
	public FacturacionPlanillaCabecera findById(@Param("p_codFacturacionPlanillaCabecera") Integer codFacturacionPlanillaCabecera) throws Exception;
	
	@Select("select e.* from t_facturacion_planilla_cabecera e where e.cod_ciclo = #{p_codCiclo}")
	public FacturacionPlanillaCabecera findByIdCiclo(@Param("p_codCiclo") Integer codCiclo) throws Exception;
	
	@Select("select * from t_facturacion_planilla_cabecera")
	public List<FacturacionPlanillaCabecera> findAll() throws Exception;
	
	@Insert("insert into t_facturacion_planilla_cabecera (cod_ciclo,fecha,idusuario,nro_registros) values (#{cod_ciclo},#{fecha},#{idusuario},#{nro_registros})")
	public void crearFacturacionPlanillaCabecera(FacturacionPlanillaCabecera facturacionPlanillaCabecera) throws Exception;
	
	@Update("update t_facturacion_planilla_cabecera set cod_ciclo = #{cod_ciclo},fecha = #{fecha},idusuario = #{idusuario},nro_registros = #{nro_registros} where id_facturacion_planilla_cabecera= #{id_facturacion_planilla_cabecera}")
	@Options(flushCache=true,useCache=true)
    public void actualizarFacturacionPlanillaCabecera(FacturacionPlanillaCabecera facturacionPlanillaCabecera) throws Exception;
	
	@Delete("delete  from t_facturacion_planilla_cabecera  where id_facturacion_planilla_cabecera = #{p_codFacturacionPlanillaCabecera}")
	@Options(flushCache=true)
	public void eliminarFacturacionPlanillaCabecera(@Param("p_codFacturacionPlanillaCabecera") Integer codFacturacionPlanillaCabecera) throws Exception;
	
}
