package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cliente;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.Facturacion;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import src.com.certicom.certiscan.utils.ExpedienteItem;


public interface FacturacionMapper {

	@Select("select *, idusuario_supervisor as idusuario_jefe, id_facturacion as idFacturacion, documento as doi from t_facturacion where id_facturacion = #{facturacion_id}")
	public Facturacion findById(@Param("facturacion_id") Integer facturacion_id) throws Exception;
	
	@Select("select * from t_facturacion")
	public List<Facturacion> findAll() throws Exception;
	
	@Select("select * from t_facturacion where periodo = #{periodo} LIMIT 5")
	public  List<Facturacion> buscarFacturacionPorPeriodo(@Param("periodo") Date periodo) throws Exception;
	
	@Select("select * from t_facturacion where periodo = #{periodo} and id_producto=#{id_producto} LIMIT 5")
	public  List<Facturacion> buscarFacturacionPorPeriodoPorProducto(@Param("periodo") Date periodo, @Param("id_producto") Integer id_producto) throws Exception;
	
	@Select("select * from t_facturacion where periodo = #{periodo} order by nuevo DESC")
	public List<Facturacion> findByPeriodo(@Param("periodo") Date periodo) throws Exception;
	
	@Insert("insert into t_facturacion ( "
			+ "periodo, id_producto, cliente_negativo, cr, vd, vl,  fuerza_venta, modalidad_venta, codigo_producto,  nombre_producto, codigo_sub_producto,"
			+ "sub_producto, adicional, numero_solicitud, doi, apellido_paterno_cliente, apellido_materno_cliente, nombre_cliente, direccion_cliente,"
			+ "telefono_cliente,  celular_cliente, origen, fecha_rvgl, fecha_registro, fecha_estado, fecha_actual, moneda_solicitada, monto_solicitado,"
			+ "moneda_aprobada, monto_aprobado, fecha_formalizacion_operacion, fecha_estado_comision,  estado_comision, fecha_estado_altamira, estado_altamira,"
			+ "propiedad_producto, numero_contrato, codigo_jefe, jefe, codigo_ejecutivo, ejecutivo, codigo_oficina_asignada, observacion,"
			+ "ultima_persona_atencion, responsable_actual, cliente_vip, pago_haberes, tipo_captacion, canal_venta, colectivo, observacion_riesgos,"
			+ "fondo_aperturado, origen_fondo, procedencia_fondos, segmento_promotor, cliente, razon_social, ruc, precio_cts,"
			+ "monto_descuento_ejecutivo, monto_descuento_supervisor, observacion_descuento, mes_descuento "
			+ ") VALUES ("
			+ "#{periodo}, #{id_producto}, #{cliente_negativo}, #{cr}, #{vd}, #{vl}, #{fuerza_venta}, #{modalidad_venta}, #{codigo_producto}, #{nombre_producto}, #{codigo_sub_producto},"
			+ "#{sub_producto}, #{adicional}, #{numero_solicitud}, #{doi}, #{apellido_paterno_cliente}, #{apellido_materno_cliente}, #{nombre_cliente}, #{direccion_cliente},"
			+ "#{telefono_cliente}, #{celular_cliente}, #{origen}, #{fecha_rvgl}, #{fecha_registro}, #{fecha_estado}, #{fecha_actual}, #{moneda_solicitada}, #{monto_solicitado},"
			+ "#{moneda_aprobada}, #{monto_aprobado}, #{fecha_formalizacion_operacion}, #{fecha_estado_comision}, #{estado_comision}, #{fecha_estado_altamira}, #{estado_altamira},"
			+ "#{propiedad_producto}, #{numero_contrato}, #{codigo_jefe}, #{jefe}, #{codigo_ejecutivo}, #{ejecutivo}, #{codigo_oficina_asignada}, #{observacion},"
			+ "#{ultima_persona_atencion}, #{responsable_actual}, #{cliente_vip}, #{pago_haberes}, #{tipo_captacion}, #{canal_venta}, #{colectivo}, #{observacion_riesgos},"
			+ "#{fondo_aperturado}, #{origen_fondo}, #{procedencia_fondos}, #{segmento_promotor}, #{cliente}, #{razon_social}, #{ruc}, #{precio_cts},"
			+ "#{montoDescuentoEjecutivo},#{montoDescuentoSupervisor},#{observacionDescuento},#{mesDescuento}"
			+ ")")
	public void crearFacturacionALL(Facturacion facturacion) throws Exception;		
	
@Insert("INSERT INTO t_facturacion(id_facturacion_cabecera, tipodocumento, contrato, "
		+ "codigo_producto, codigo_sub_producto, documento, sub_producto, "
		+ "fechaalta, fuerza_venta, codigocliente, nrocontrato, nrotarjeta, "
		+ "situacionaltamira, situaciontarjeta, situacionfacturacion, fechacancelaciontarj, "
		+ "fechabloqueo, fechaactivacion, fechacancelacionprest, fechaliquidacion, "
		+ "ejecutivo, codigo_ejecutivo, jefe, codigo_jefe, comision, comisionjefe, "
		+ "reglanegocio, modoventa, canalventa, fechaformalizacion, moneda, "
		+ "monto, liquidacion, caalc_cnro_solicitud, tc, monto_soles, bono_mi_vivienda, "
		+ "monto_total_soles, validacion_colocacion, periodo, usuario_registro, "
		+ "fecha_registro,comision_certicom,igv,por_comision_ejecutivo,comision_ejecutivo,"
		+ "idproducto,idnegocio,idusuario_ejecutivo,idusuario_supervisor,expediente_id,total_facturar,origen,"
		+ "apellido_paterno_cliente,apellido_materno_cliente,nombre_cliente,monto_solicitado,moneda_aprobada,numero_solicitud,idusuario_ejecutivo_original, idusuario_supervisor_original, "
		+ "id_cargo, razon_social,comision_supervisor,comision_coordinador, idusuario_coordinador, idusuario_back, comision_back ,"
		+ "idusuario_ejecutivo_campo, idusuario_supervisor_campo, comision_ejecutivo_campo, comision_supervisor_campo, modalidad_venta, ubicacion,"
		+ "codigo_oficina, por_comision_supervisor, por_comision_ejecutivo_campo, por_comision_supervisor_campo, "
		+ "por_comision_coordinador, por_comision_back, idplanilla_ejecutivo, idplanilla_supervisor, "
		+ "idplanilla_ejecutivo_campo, idplanilla_supervisor_campo, idplanilla_coordinador, idplanilla_back,rango, estado_validacion,"
		+ "segmento, tipooperacion, rango_dias, ruc_empresa, empresa_ph) "
		+ " "
		+ "VALUES (#{id_facturacion_cabecera}, #{tipodocumento}, #{contrato}, "
		+ "#{codigo_producto}, #{codigo_sub_producto}, #{doi}, #{sub_producto}, "
		+ "#{fechaalta}, #{fuerza_venta}, #{codigocliente}, #{nrocontrato}, #{nrotarjeta}, "
		+ "#{situacionaltamira}, #{situaciontarjeta}, #{situacionfacturacion}, #{fechacancelaciontarj}, "
		+ "#{fechabloqueo}, #{fechaactivacion}, #{fechacancelacionprest}, #{fechaliquidacion}, "
		+ "#{ejecutivo}, #{codigo_ejecutivo}, #{jefe}, #{codigo_jefe}, #{comision}, #{comisionjefe}, "
		+ "#{reglanegocio}, #{modoventa}, #{canalventa}, #{fechaformalizacion}, #{moneda}, "
		+ "#{monto}, #{liquidacion}, #{caalc_cnro_solicitud}, #{tc}, #{monto_soles}, #{bono_mi_vivienda}, "
		+ "#{monto_total_soles}, #{validacion_colocacion}, #{periodo}, #{usuario_sistema}, "
		+ "#{fecha_sistema},#{comision_certicom},#{igv},#{por_comision_ejecutivo},#{comision_ejecutivo},"
		+ "#{idProducto},#{idnegocio},#{idusuario_ejecutivo},#{idusuario_jefe},#{expediente_id},#{total_facturar},#{origen},"
		+ "#{apellido_paterno_cliente},#{apellido_materno_cliente},#{nombre_cliente},#{monto_solicitado},#{moneda_aprobada},#{numero_solicitud},"
		+ "#{idusuario_ejecutivo_original}, #{idusuario_jefe_original}, #{id_cargo}, #{razon_social},"
		+ "#{comision_supervisor}, #{comision_coordinador}, #{idusuario_coordinador}, #{idusuario_back}, #{comision_back},"
		+ "#{idusuario_ejecutivo_campo}, #{idusuario_supervisor_campo}, #{comision_ejecutivo_campo}, #{comision_supervisor_campo},#{modalidad_venta}, "
		+ "#{ubicacion}, #{codigo_oficina} ,#{por_comision_supervisor},#{por_comision_ejecutivo_campo},"
		+ "#{por_comision_supervisor_campo},#{por_comision_coordinador},#{por_comision_back},#{idplanilla_ejecutivo},#{idplanilla_supervisor},"
		+ "#{idplanilla_ejecutivo_campo},#{idplanilla_supervisor_campo},#{idplanilla_coordinador},#{idplanilla_back},#{rango}, #{estado_validacion},"
		+ "#{segmento},#{tipooperacion},#{rango_dias},#{ruc_empresa},#{empresa_ph} ) ")
	public void crearFacturacionALL2(Facturacion facturacion) throws Exception;	

	@Select("select  usu.idusuario as idusuario_ejecutivo, f.idproducto as idProducto,sum(f.comision_ejecutivo) as comision_ejecutivo, f.id_cargo  "
			+ "from t_facturacion f inner join t_usuario usu on usu.idusuario = f.idusuario_ejecutivo and f.idnegocio = #{idnegocio}  and f.periodo = #{fini} and f.origen = 'FACTURA' "
			+ "group by usu.idusuario , f.idproducto, f.id_cargo ")
	public List<Facturacion> getUsuariosbyFacturacionProducto(ExpedienteFilter expedienteFilter) throws Exception;
	
	@Select("select * from t_facturacion f where f.id_facturacion_cabecera = #{id_cabecera} ")
	public List<Facturacion> getFacturacionbyCabecera(@Param("id_cabecera")Integer id_cabecera) throws Exception;
	
	@Select("select f.id_facturacion as idFacturacion,f.documento as doi,* from t_facturacion f where f.id_facturacion_cabecera = #{id_cabecera} and origen = #{origen}")
	public List<Facturacion> getFacturacionbyOrigenCabecera(@Param("id_cabecera")Integer id_cabecera,@Param("origen")String origen) throws Exception;
	
	public List<Facturacion> obtenerFacturacionbyCabecera(@Param("id_cabecera")Integer id_cabecera ) throws Exception;
	
	public List<Facturacion> getFacturacionbyCabecera2(@Param("first")Integer  first, @Param("pageSize")Integer pageSize, @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder, @Param("id_cabecera")Integer id_cabecera ) throws Exception;
	public Integer getCountPAGINATOR(@Param("filters") Map<String,Object> filters,@Param("id_cabecera")Integer id_cabecera) throws Exception;
	
	@Update("UPDATE t_facturacion set "
			+ "id_facturacion_cabecera = #{id_facturacion_cabecera}, "
			+ "tipodocumento = #{tipodocumento}, "
			+ "contrato = #{contrato}, "
			+ "codigo_producto = #{codigo_producto}, "
			+ "codigo_sub_producto = #{codigo_sub_producto}, "
			+ "documento = #{doi}, "
			+ "sub_producto = #{sub_producto}, "
			+ "fechaalta = #{fechaalta}, "
			+ "fuerza_venta = #{fuerza_venta}, "
			+ "codigocliente = #{codigocliente}, "
			+ "nrocontrato = #{nrocontrato}, "
			+ "nrotarjeta = #{nrotarjeta}, "
			+ "situacionaltamira = #{situacionaltamira}, "
			+ "situaciontarjeta = #{situaciontarjeta}, "
			+ "situacionfacturacion = #{situacionfacturacion}, "
			+ "fechacancelaciontarj = #{fechacancelaciontarj}, "
			+ "fechabloqueo = #{fechabloqueo}, "
			+ "fechaactivacion = #{fechaactivacion}, "
			+ "fechacancelacionprest = #{fechacancelacionprest}, "
			+ "fechaliquidacion = #{fechaliquidacion}, "
			+ "ejecutivo = #{ejecutivo}, "
			+ "codigo_ejecutivo = #{codigo_ejecutivo}, "
			+ "jefe = #{jefe}, "
			+ "codigo_jefe = #{codigo_jefe},"
			+ " comision = #{comision}, "
			+ "comisionjefe  = #{comisionjefe}, "
			+ "reglanegocio  = #{reglanegocio}, "
			+ "modoventa  = #{modoventa}, "
			+ "canalventa = #{canalventa}, "
			+ "fechaformalizacion = #{fechaformalizacion}, "
			+ "moneda = #{moneda}, "
			+ "monto = #{monto}, "
			+ "liquidacion = #{liquidacion}, "
			+ "caalc_cnro_solicitud = #{caalc_cnro_solicitud}, "
			+ "tc = #{tc}, "
			+ "monto_soles = #{monto_soles}, "
			+ "bono_mi_vivienda = #{bono_mi_vivienda}, "
			+ "monto_total_soles = #{monto_total_soles}, "
			+ "validacion_colocacion = #{validacion_colocacion}, "
			+ "periodo = #{periodo}, "
			+ "usuario_registro = #{usuario_sistema}, "
			+ "fecha_registro = #{fecha_sistema}, "
			+ "comision_certicom = #{comision_certicom}, "
			+ "igv = #{igv}, "
			+ "por_comision_ejecutivo = #{por_comision_ejecutivo}, "
			+ "comision_ejecutivo = #{comision_ejecutivo}, "
			+ "idproducto = #{idProducto}, "
			+ "idnegocio = #{idnegocio}, "
			+ "idusuario_ejecutivo = #{idusuario_ejecutivo},"
			+ "idusuario_supervisor = #{idusuario_jefe}, "
			+ "expediente_id = #{expediente_id}, "
			+ "total_facturar = #{total_facturar}, "
			+ "origen = #{origen}, "
			+ "apellido_paterno_cliente = #{apellido_paterno_cliente}, "
			+ "apellido_materno_cliente = #{apellido_materno_cliente}, "
			+ "nombre_cliente = #{nombre_cliente}, "
			+ "monto_solicitado = #{monto_solicitado}, "
			+ "moneda_aprobada = #{moneda_aprobada}, "
			+ "numero_solicitud  = #{numero_solicitud}, "
            + "id_cargo = #{id_cargo}, "
            + "razon_social = #{razon_social},"
            + "comision_supervisor =#{comision_supervisor},"
            + "idplanilla_ejecutivo =#{idplanilla_ejecutivo},"
            + "idplanilla_supervisor = #{idplanilla_supervisor},"
            + "por_comision_supervisor = #{por_comision_supervisor}, "
            + "estado_validacion = #{estado_validacion},"
            + "ubicacion = #{ubicacion},"
            + "modalidad_venta = #{modalidad_venta} "
			+ "WHERE id_facturacion =  #{idFacturacion} ")	
    public void actualizarFacturacion(Facturacion facturacion) throws Exception;
	
	
	@Delete("delete  from t_facturacion  where id_facturacion = #{facturacion_id}")
	@Options(flushCache=true)
	public void eliminarFacturacion(@Param("facturacion_id") Integer facturacion_id) throws Exception;
	
	@Delete("delete  from t_facturacion  where id_facturacion_cabecera = #{id_facturacion_cabecera}")
	@Options(flushCache=true)
	public void eliminarFacturacionxIdFactCabecera(@Param("id_facturacion_cabecera") Integer id_facturacion_cabecera) throws Exception;
	
	@Insert("INSERT INTO t_facturacion("
			+ "id_facturacion_cabecera, "
			+ "tipodocumento, "
			+ "documento, "
			+ "ejecutivo,"
			+ " codigo_ejecutivo, "
			+ "jefe, "
			+ "codigo_jefe, "
			+ "origen,"
			+ "apellido_paterno_cliente,"
			+ "apellido_materno_cliente,"
			+ "nombre_cliente,"
			+ "monto_soles,"
			+ "comision_ejecutivo,"
			+ "comisionjefe,"
			+ "observacion_descuento,"
			+ "mes_descuento,"
			+ "periodo,"
			
			+ "codigo_producto,"
			+ "codigo_sub_producto,"
			+ "sub_producto,"
			+ "usuario_registro,"
			+ "fecha_registro,"
			+ "idproducto,"
			+ "idnegocio,"
			+ "idusuario_ejecutivo,"
			+ "idusuario_supervisor,"
			+ "id_cargo"
			+ ")"

			+ " "
			+ "VALUES ("
			+ "#{id_facturacion_cabecera}, "
			+ "#{tipodocumento}, "
			+ "#{doi}, "
			+ "#{ejecutivo},"
			+ "#{codigo_ejecutivo}, "
			+ "#{jefe}, "
			+ "#{codigo_jefe}, "
			+ "#{origen},"
			+ "#{apellido_paterno_cliente},"
			+ "#{apellido_materno_cliente},"
			+ "#{nombre_cliente},"
			+ "#{monto_soles},"
			+ "#{comision_ejecutivo},"
			+ "#{comisionjefe},"
			+ "#{observacion_descuento},"
			+ "#{mes_descuento},"
			+ "#{periodo}, "
			
			+ "#{codigo_producto},"
			+ "#{codigo_sub_producto},"
			+ "#{sub_producto},"
			+ "#{usuario_sistema},"
			+ "#{fecha_sistema},"
			+ "#{idProducto},"
			+ "#{idnegocio},"
			+ "#{idusuario_ejecutivo},"
			+ "#{idusuario_jefe},"
			+ "#{id_cargo}"
			+ ")")
		public void insertarDescuento(Facturacion facturacion) throws Exception;		
	
	
	@Select("select f.idusuario_ejecutivo, sum(f.comision_ejecutivo) as comision_ejecutivo, f.id_cargo from t_facturacion f  "
			+ "where f.idnegocio = #{idnegocio} and f.periodo = #{fini} and origen = 'COMPLEMENTO' group by f.idusuario_ejecutivo, f.id_cargo ")
	public List<Facturacion> getComplementos(ExpedienteFilter filter) throws Exception;
	
	@Select("select f.idusuario_ejecutivo, f.idproducto, sum(f.monto_soles) as monto_soles, f.id_cargo from t_facturacion f  "
			+ "where f.idnegocio = #{idnegocio} and f.periodo = #{fini} group by f.idusuario_ejecutivo, f.id_cargo, f.idproducto ")
	public List<Facturacion> getDesembolsado(ExpedienteFilter filter) throws Exception;
	
	@Select("select f.idusuario_ejecutivo, f.idproducto, f.monto_soles, f.comision_ejecutivo, f.id_cargo, "+
			"(f.razon_social) as usuarioAsignado, "+
			"prod.descripcion as nombre_producto from (t_facturacion f  inner join t_producto prod on f.idproducto = prod.id_producto) "+
			"where f.idproducto = #{idproducto} and f.periodo = #{fini} and f.idusuario_ejecutivo = #{idusuario}")
	public List<Facturacion> getDesembolsadoDetallado(ExpedienteFilter filter) throws Exception;
	
	@Select("select f.idusuario_ejecutivo,f.id_cargo, sum(f.comision_ejecutivo) as comision_ejecutivo,sum(f.comisionjefe) as comisionjefe from t_facturacion f  "
			+ "where f.idnegocio = #{idnegocio} and f.periodo = #{fini} and origen = 'DESCUENTO' group by f.idusuario_ejecutivo,f.id_cargo ")
	public List<Facturacion> getDescuentos(ExpedienteFilter filter) throws Exception;
	
	@Update("update t_facturacion set idusuario_ejecutivo = #{idusuario_ejecutivo}, idusuario_supervisor= #{idusuario_jefe} where id_facturacion = #{idFacturacion} ")
	public void modificarFacturacion(Facturacion facturacion)throws Exception;
	
	@Select("select *, idusuario_supervisor as idusuario_jefe, id_facturacion as idFacturacion, documento as doi  from t_facturacion where id_facturacion_cabecera = #{id_facturacion_cabecera} and idusuario_ejecutivo = #{id_usuario} and id_cargo = #{id_cargo} ")
	public Facturacion verifySetSupervisorByCabecera(@Param("id_facturacion_cabecera") Integer id_facturacion_cabecera, @Param("id_usuario") Integer idusuario, @Param("id_cargo") Integer id_cargo)throws Exception;
	
	
	@Select("select  usu.idusuario as idusuario_ejecutivo, f.idproducto as idProducto,sum(f.monto_soles) as comision_ejecutivo, f.id_cargo  "
			+ "from t_facturacion f inner join t_usuario usu on usu.idusuario = f.idusuario_ejecutivo and f.idnegocio = #{idnegocio}  and f.periodo = #{fini} and f.origen = 'FACTURA' "
			+ "group by usu.idusuario , f.idproducto, f.id_cargo ")
	public List<Facturacion> getMontoDesembolsadoByFacturacionProducto(ExpedienteFilter expedienteFilter) throws Exception;
	
	
	
	@Update("update t_facturacion set estado_validacion=#{estado_validacion}  where id_facturacion = #{facturacion_id}")
	@Options(flushCache=true)
	public void updateEstadoevaluacion(@Param("facturacion_id") Integer facturacion_id,@Param("estado_validacion")boolean estado_validacion) throws Exception;
	
	
	
	
	@Update("UPDATE t_facturacion set " 
			+ "comision_back = #{comision_back}, "
			+ "por_comision_back = #{por_comision_back}, "
			+ "idusuario_back = #{idusuario_back}, "
			+ "idplanilla_back = #{idplanilla_back} "
			+ "WHERE id_facturacion =  #{idFacturacion} ")	
	@Options(flushCache=true)
    public void actualizarComisionBackOffice(Facturacion facturacion) throws Exception;
	
	
	@Update("UPDATE t_facturacion set " 
			+ "comision_ejecutivo_campo = #{comision_ejecutivo_campo}, "
			+ "por_comision_ejecutivo_campo = #{por_comision_ejecutivo_campo}, "
			+ "idusuario_ejecutivo_campo = #{idusuario_ejecutivo_campo}, "
			+ "idplanilla_ejecutivo_campo = #{idplanilla_ejecutivo_campo} "
			+ "WHERE id_facturacion =  #{idFacturacion} ")	
	@Options(flushCache=true)
    public void actualizarComisionEjecutivoCampo(Facturacion facturacion) throws Exception;
	
	@Update("UPDATE t_facturacion set " 
			+ "comision_supervisor_campo = #{comision_supervisor_campo}, "
			+ "por_comision_supervisor_campo = #{por_comision_supervisor_campo}, "
			+ "idusuario_supervisor_campo = #{idusuario_supervisor_campo}, "
			+ "idplanilla_supervisor_campo = #{idplanilla_supervisor_campo} "
			+ "WHERE id_facturacion =  #{idFacturacion} ")	
	@Options(flushCache=true)
    public void actualizarComisionSupervisorCampo(Facturacion facturacion) throws Exception;
	
	public List<Facturacion> getUsuariosbyFacturacionProducto2(ExpedienteFilter expedienteFilter) throws Exception;
	
	public List<Facturacion> getUsuariosbyFacturacionCuartiles(ExpedienteFilter expedienteFilter) throws Exception;
	
	@Select("select n.descripcion as des_negocio,p.descripcion as des_producto , n.id_negocio as idnegocio, p.id_producto as idProducto, "
			+ "sum(f.monto_soles) as monto_facturado from t_facturacion f inner join t_producto p"
			+ " on p.id_producto = f.idproducto inner join t_negocio n on n.id_negocio = f.idnegocio "
			+ " WHERE cast(extract(year from f.periodo) as character varying) = #{anio} group by n.id_negocio, p.id_producto "
			+ " order by n.descripcion,p.descripcion ")
	public List<Facturacion> listarProductosFacturadosByAnio(@Param("anio") String anio) throws Exception;
	
	
	public List<Facturacion> listarProductosFacturadosByAnioByProductoByNegocio(ExpedienteFilter expedienteFilter) throws Exception;
	
	
	@Select("select n.id_negocio as idnegocio, p.id_producto as idProducto ,f.periodo, sum(f.monto_soles) as monto_facturado ,  "
			+ " sum(f.comision_ejecutivo+f.comisionjefe+coalesce(f.comision_ejecutivo_campo,0)+coalesce(f.comision_supervisor_campo,0)+coalesce(f.comision_back,0)+coalesce(f.comision_coordinador,0)) as monto_pagado, "
			//+ " count(case when f.monto_soles is not null and f.monto_soles >0 then 1 else  0  end) as cant_operaciones "  
			//+ " count(case when (  f.idusuario_ejecutivo_original is not null) then 1 else  null  end) as cant_operaciones "
			+" count(case when ( f.periodo >= '2015-01-01' and f.periodo <= '2015-04-01' and  f.idusuario_ejecutivo_original is not null) then 1 "
			+"	       when f.periodo >= '2015-05-01' then 1 else  null end) as cant_operaciones "
			+ " from t_facturacion f inner join t_producto p "
			+ " on p.id_producto = f.idproducto inner join t_negocio n on n.id_negocio = f.idnegocio "
			+ " WHERE cast(extract(year from f.periodo) as character varying) = #{anio} "
			+ " group by n.id_negocio, p.id_producto ,f.periodo "
			+ " order by n.descripcion,p.descripcion ,f.periodo")
	public List<Facturacion> listarTotalFacturadosPagadoByAnio(@Param("anio") String anio) throws Exception;
	
	
	@Select("select u.idusuario as idusuario_ejecutivo,f.periodo ,sum(f.monto_soles) as monto_facturado, count(*) as cant_operaciones  "
			+ " from t_facturacion f inner join t_usuario u on f.idusuario_ejecutivo =  u.idusuario "
			+ " where f.idusuario_ejecutivo is not null and monto_soles is not null "
			+ " and cast(extract(year from f.periodo) as character varying) = #{anio} "
//			+ "	and f.idproducto !=6 "
			+ " and idnegocio = #{idnegocio} and f.idproducto = #{idproducto} "
			+ " group by u.idusuario,f.periodo "
			+ " order by u.idusuario,f.periodo ")	
public List<Facturacion> listarTotalFacturadosxEjecutivoByAnio(ExpedienteFilter filter) throws Exception;
	
	// Respuesta
	
	
	@Select(" select (u.apellido_paterno || ' ' ||  u.apellido_materno || ' ' ||  u.nombre) as nombre_ejecutivo, "
			+ " (j.apellido_paterno || ' ' ||  j.apellido_materno || ' ' ||  j.nombre) as jefe, "
			+ " count(distinct ex.expediente_id) as empresas_asig, count(distinct case when ec.expediente_id is  not  null then  ec.expediente_id else null end) as empresas_trabajdas, "
			+ " count(distinct ex.expediente_id) - count(distinct case when ec.expediente_id is  not  null then  ec.expediente_id else null end) as saldo_empresasxtrabajar "
			+ " from t_expediente_producto ep inner join t_expediente ex on "
			+ " ex.expediente_id = ep.expediente_id  inner join t_usuario u on u.idusuario = ep.idusuario "
			+ " left join t_usuario j on j.idusuario = u.sala "
			+ " left join  t_expediente_cuentas ec on ec.expediente_id = ex.expediente_id "
			+ " where ex.id_producto = #{idproducto} "
			+ " and ex.periodo = #{periodo} "
			+ " and u.estado group by u.idusuario, j.idusuario "
			+ " order by 4 desc ")
	public List<ExpedienteItem> getCantidadEmpresaPH(@Param("idproducto") Integer idproducto,@Param("periodo") Date periodo) throws Exception;
	
	
//	@Select(" select  (u.apellido_paterno || ' ' ||  u.apellido_materno || ' ' ||  u.nombre) as nombre_ejecutivo,"
//			+ "(j.apellido_paterno || ' ' ||  j.apellido_materno || ' ' ||  j.nombre) as jefe, "
//			+ " count(case when rango ='1' then f.id_facturacion else null end) as r1_cantidad, "
//			+ " sum(case when rango ='1' then f.comision_ejecutivo else 0 end) r1_comision, "
//			+ " count(case when rango ='2' then f.id_facturacion else null end) as r2_cantidad, "
//			+ " sum(case when rango ='2' then f.comision_ejecutivo else 0 end) r2_comision "
//			+ ",count(case when rango ='3' then f.id_facturacion else null end) as r3_cantidad, "
//			+ " sum(case when rango ='3' then f.comision_ejecutivo else 0 end) r3_comision "
//			+ ",count(case when rango ='4' then f.id_facturacion else null end) as r4_cantidad, "
//			+ " sum(case when rango ='4' then f.comision_ejecutivo else 0 end) as r4_comision, "
//			+ " count(f.id_facturacion) cantidad_total, sum(f.comision_ejecutivo) as comision_total "
//			+ " from t_facturacion f inner JOIN t_usuario u ON "
//			+ " f.idusuario_ejecutivo = u.idusuario "
//			+ " left join t_usuario j on j.idusuario = u.sala "
//			+ "  where periodo = #{periodo} and f.idproducto = #{idproducto} "
//			+ " and rango is not null GROUP BY   u.idusuario ,j.idusuario order by  10 desc;")
	@Select(" select  (u.apellido_paterno || ' ' ||  u.apellido_materno || ' ' ||  u.nombre) as nombre_ejecutivo, "
			+ "	 (j.apellido_paterno || ' ' ||  j.apellido_materno || ' ' ||  j.nombre) as jefe, "
			+ "  count(case when rango ='1' then   f.id_facturacion else null end) as r1_cantidad, "
			+ "  sum(case when rango ='1' then f.comision_ejecutivo else 0 end) r1_comision, "
			+ "  sum(case when rango ='1' then tc.pago_banco else 0 end) r1_fact, "
			+ "  count(case when rango ='2' then  f.id_facturacion else null end) as r2_cantidad, "
			+ "  sum(case when rango ='2' then  f.comision_ejecutivo else 0 end) r2_comision , "
			+ "  sum(case when rango ='2' then  tc.pago_banco else 0 end) as r2_fact "
			+ "  ,count(case when rango ='3' then f.id_facturacion else null end) as r3_cantidad, "
			+ "  sum(case when rango ='3' then f.comision_ejecutivo else 0 end) r3_comision, "
			+ "  sum(case when rango ='3' then  tc.pago_banco else 0 end) as r3_fact "
			+ "	 ,count(case when rango ='4' then f.id_facturacion else null end) as r4_cantidad, "
			+ "	 sum(case when rango ='4' then f.comision_ejecutivo else 0 end) as r4_comision, "
			+ "  sum(case when rango ='4' then  tc.pago_banco else 0 end) as r4_fact , "
			+ "	 count(f.id_facturacion) cantidad_total, sum(f.comision_ejecutivo) as comision_total , sum(tc.pago_banco) as fact_total "
			+ "  from t_facturacion f inner JOIN t_usuario u ON "
			+ "  f.idusuario_ejecutivo = u.idusuario "
			+ "  left join t_tablero_comisiones tc  on (tc.id_producto = f.idproducto and tc.periodo = f.periodo  "
			+ "  and tc.id_cargo = 4 and tc.funcion = f.rango) "
			+ "  left join t_usuario j on j.idusuario = u.sala "
			+ "  where f.periodo = #{periodo} and f.idproducto = #{idproducto} "
			+ "  and rango is not null GROUP BY   u.idusuario ,j.idusuario order by  16 desc;")
	public List<ExpedienteItem> getRankingEjecutivoPH(@Param("idproducto") Integer idproducto,@Param("periodo") Date periodo) throws Exception;
	
	
	@Select("select f.ruc_empresa as ruc, f.empresa_ph as nombre_empresa, (u.apellido_paterno || ' ' ||  u.apellido_materno || ' ' ||  u.nombre) as nombre_ejecutivo,"
			+ "(j.apellido_paterno || ' ' ||  j.apellido_materno || ' ' ||  j.nombre) as jefe, "
			+ " count(case when rango ='2' then f.id_facturacion else null end) as r2_cantidad, "
			+ " sum(case when rango ='2' then f.comision_ejecutivo else 0 end) r2_comision "
			+ ",count(case when rango ='3' then f.id_facturacion else null end) as r3_cantidad, "
			+ " sum(case when rango ='3' then f.comision_ejecutivo else 0 end) r3_comision "
			+ ",count(case when rango ='4' then f.id_facturacion else null end) as r4_cantidad, "
			+ " sum(case when rango ='4' then f.comision_ejecutivo else 0 end) as r4_comision, "
			+ " count(f.id_facturacion) cantidad_total, sum(f.comision_ejecutivo) as comision_total "
			+ " from t_facturacion f inner JOIN t_usuario u ON "
			+ " f.idusuario_ejecutivo = u.idusuario "
			+ " left join t_usuario j on j.idusuario = u.sala "
			+ "  where periodo = #{periodo} and f.idproducto = #{idproducto} "
			+ " and rango is not null "
			+ " GROUP BY   f.ruc_empresa, f.empresa_ph, u.idusuario ,j.idusuario order by  12 desc;")
	public List<ExpedienteItem> getRankingEmpresaPH(@Param("idproducto") Integer idproducto,@Param("periodo") Date periodo) throws Exception;
	
	
	
	@Select(" select  (u.apellido_paterno || ' ' ||  u.apellido_materno || ' ' ||  u.nombre) as nombre_ejecutivo, "
			+ " sum(f.monto_soles) as desembolsado_total, "
			+ " count(f.id_facturacion) cantidad_total, sum(f.comision_ejecutivo) as comision_total "
			+ " from t_facturacion f inner JOIN t_usuario u ON "
			+ " f.idusuario_ejecutivo = u.idusuario "
			+ " where periodo = #{periodo} and f.idproducto = #{idproducto} "
			+ " and monto_soles is not null and monto_soles >0   GROUP BY   u.idusuario  order by 4 desc;")
	public List<ExpedienteItem> getResPlanConsolidadoBP(@Param("idproducto") Integer idproducto,@Param("periodo") Date periodo) throws Exception;
	
	
	 @Select("select  (u.apellido_paterno || ' ' ||  u.apellido_materno || ' ' ||  u.nombre) as nombre_ejecutivo,tipooperacion, "
	 		+ " count(case when segmento = 'PYMES' and rango_dias ='90-180' then f.id_facturacion else null end) as r2_cantidad, "
	 		+ " sum(case when segmento = 'PYMES' and rango_dias ='90-180' then f.comision_ejecutivo else 0 end) r2_comision, "
	 		+ " sum(case when segmento = 'PYMES' and rango_dias ='90-180' then f.monto_soles else 0 end) r2_desembolsado, "
	 		+ " count(case when segmento = 'PYMES' and rango_dias ='181-360' then f.id_facturacion else null end) as r3_cantidad, "
	 		+ " sum(case when segmento = 'PYMES' and rango_dias ='181-360' then f.comision_ejecutivo else 0 end) r3_comision, "
	 		+ " sum(case when segmento = 'PYMES' and rango_dias ='181-360' then f.monto_soles else 0 end) r3_desembolsado, "
	 		+ " count(case when segmento = 'PYMES' and rango_dias ='MAYOR 360' then f.id_facturacion else null end) as r4_cantidad, "
	 		+ " sum(case when segmento = 'PYMES' and rango_dias ='MAYOR 360' then f.comision_ejecutivo else 0 end) as r4_comision, "
	 		+ " sum(case when segmento = 'PYMES' and rango_dias ='MAYOR 360' then f.monto_soles else 0 end) as r4_desembolsado, "
	 		+ " count(case when segmento = 'BEC' and rango_dias ='31-180' then f.id_facturacion else null end) as r5_cantidad, "
	 		+ " sum(case when segmento = 'BEC' and rango_dias ='31-180' then f.comision_ejecutivo else 0 end) as r5_comision, "
	 		+ " sum(case when segmento = 'BEC' and rango_dias ='31-180' then f.monto_soles else 0 end) as r5_desembolsado, "
	 		+ " count(case when segmento = 'BEC' and rango_dias ='181-360' then f.id_facturacion else null end) as r6_cantidad, "
	 		+ " sum(case when segmento = 'BEC' and rango_dias ='181-360' then f.comision_ejecutivo else 0 end) as r6_comision, "
	 		+ " sum(case when segmento = 'BEC' and rango_dias ='181-360' then f.monto_soles else 0 end) as r6_desembolsado, "
	 		+ " count(case when segmento = 'BEC' and rango_dias ='361-MAS' then f.id_facturacion else null end) as r7_cantidad, "
	 		+ " sum(case when segmento = 'BEC' and rango_dias ='361-MAS' then f.comision_ejecutivo else 0 end) as r7_comision, "
	 		+ " sum(case when segmento = 'BEC' and rango_dias ='361-MAS' then f.monto_soles else 0 end) as r7_desembolsado, "
	 		+ " count(f.id_facturacion) cantidad_total, sum(f.comision_ejecutivo) as comision_total "
	 		+ "  from t_facturacion f inner JOIN t_usuario u ON "
	 		+ "  f.idusuario_ejecutivo = u.idusuario  where periodo = #{periodo} and f.idnegocio =#{idnegocio} "
	 		+ " and monto_soles is not null and monto_soles>0 "
	 		+ " and segmento is not null   GROUP BY   u.idusuario,tipooperacion "
	 		+ " order by 22 desc, 1  desc; ")
	  public List<ExpedienteItem> getResPlanConsolidadPYMES(@Param("idnegocio") Integer idnegocio,@Param("periodo") Date periodo) throws Exception;
}
