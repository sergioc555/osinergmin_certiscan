package com.certicom.certiscan.mapper;

import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.PlanillaPersonal;
import com.certicom.certiscan.domain.Usuario;

import src.com.certicom.certiscan.utils.ExpedienteFilter;
import sun.security.util.PropertyExpander.ExpandException;

public interface UsuarioMapper {
    public List<Usuario> getlistaUsuario();
    public List<Usuario> getlistaEjecutivo();
    public List<Usuario> getlistaEjecutivoByNegocio(@Param("idNegocio") Integer idNegocio);
	public List<Usuario> buscarPorLoginClave(Usuario usuario);
	public List<Usuario> buscarPorDNI(String dni);
	public List<Usuario> buscarPorNombre(String nombre);
	public List<Usuario> buscarPorApellido(String apellido);
	public List<Usuario> buscarusuario(Usuario usuario);
	public Usuario buscarPorId(int idusuario);
	
	public void insertUsuario(Usuario usuario);
	
	public void actualizarEstado(Usuario usuario) throws Exception;
	public void actualizar(Usuario usuario);
	public void actualizarLogin(Usuario usuario);
	public void actualizarPassword(Usuario usuario);
	public void actualizarFechaAcceso(Usuario usuario);

	public List<Usuario> getlistaUsuario_byCentroAtencion(Integer id_centro_atencion);
	
	public List<Usuario> getlistaUsuario_Perfil(Usuario usuario);
	
	public Usuario buscarPorLogin(String string) throws Exception;
	
	@Select("select * from t_usuario where email =#{correo}")
	public Usuario buscarPorCorreo(@Param("correo") String correo) throws Exception;
	
	@Select("select u.idusuario, (u.nombre || ', ' || u.apellido_paterno || ' ' || u.apellido_materno) as usuarioAsignado,u.id_oficina, u.id_cargo, o.nombre as desOficina, c.descripcion as desCargo from (t_usuario u left join t_oficina o on o.id_oficina = u.id_oficina) inner join t_cargo c on c.id_cargo = u.id_cargo")
	public List<Usuario> listarUsuarios() throws Exception;
	
	@Select("select * from t_usuario where dni =#{dni}")
	public Usuario buscarPorDni(@Param("dni") String dni) throws Exception;
	
	public void deleteUsuario(Integer idusuario) throws Exception;
	
	public List<Usuario> listarUsuariosXPerfilProceso(String proceso) throws Exception;
	
	public List<Usuario> obtenerUsuariosxOficina(Integer idoficina)throws Exception;
	
	@Update("update t_usuario set password = #{login} where idusuario = #{idusuario}")
	public void resetearPassword(Usuario usuario)throws Exception;
	
	@Update("update t_usuario set imagen = #{imagen} where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void actualizarImagen(Usuario usuario)throws Exception;
	
	@Select("select * from t_usuario")
	public List<Usuario> listUsuario() throws Exception;	
	
	@Select("SELECT idusuario,nombre,apellido_paterno,apellido_materno,dni,email,direccion,login,password,estado,fecha_registro,fecha_mod,es_nuevo,id_centro_atencion, "
			+ "id_cargo,id_planilla from t_usuario")
	public List<Usuario> listUsuarioActivos() throws Exception;	

	@Update("update t_usuario set fecha_ingreso = #{fecha_ingreso}, departamento = #{departamento}, "
			+ "provincia = #{provincia}, distrito = #{distrito}, "
			+ "sid_ubigeo = #{sid_ubigeo},id_oficina = #{id_oficina}, "
			+ "id_coordinador= #{id_coordinador}, id_supervisor=#{id_supervisor},"
			+ "id_cargo = #{id_cargo}, id_planilla = #{id_planilla}, "
			+ "fecha_cese = #{fecha_cese},motivo_cese = #{motivo_cese}, "
			+ "funcion = #{funcion}, rpm =#{rpm}, rpc =#{rpc}, telefono = #{telefono}, " 
			+ "estado_planilla = #{estado_planilla}, codigo_banco=#{codigoBanco}, "
			+ "codigo_banco_supervisor=#{codigoBancoSupervisor}, "
			+ "codigo_quality =#{codigo_quality}, "
			+ "sala_dueno =#{sala_dueno}, "
			+ "idproducto =#{idproducto},"
			+ "producto_principal = #{producto_principal},"
			+ "otros_productos =#{otros_productos},"
			+ "negocio_asignado =#{negocio_asignado}, "
			+ "sala =#{sala},"
			+ "estado_banco = #{estado_banco},"
			+ "direccion =#{direccion}, "
			+ "direccion_lat =#{direccion_lat}, "
			+ "direccion_long =#{direccion_long}, "
			+ "lat_ultimo =#{lat_ultimo}, "
			+ "long_ultimo =#{long_ultimo}, "
			+ "estado_gps =#{estado_gps}, "
			+ "imei =#{imei},"
			+ "dotacion =#{dotacion},"
			+ "antiguedad =#{antiguedad} "
			+ "where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void insertaDatosEjecutivoPlanilla(Usuario usuario) throws Exception;
	
	@Select("select * from t_usuario where sala_dueno = true and idproducto = #{p_id_producto} order by nombre, apellido_paterno, apellido_materno")
	public List<Usuario> listUsuarioEnSala(@Param("p_id_producto") Integer id_producto) throws Exception;	
	
	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, " +
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, " +
			"pv.id_ejecutivo_padre as id_ejecutivo_padre " +
			"from t_usuario u right join t_proyeccion_ventas pv on pv.id_ejecutivo = u.idusuario and pv.id_ejecutivo_padre is null where u.sala_dueno = true and pv.tipo_nivel = 1 and " +
			"pv.id_producto = #{p_id_producto} and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo} " +
			"union " +
			"select u.nombre,u.apellido_paterno, u.apellido_materno, 0 as id_proyeccion_venta, #{p_id_negocio} as id_negocio, #{p_id_producto} as idproducto, u.idusuario as id_ejecutivo, " +
			"0 as meta, #{p_periodo} as periodo, null as id_ejecutivo_padre  "+
			"from t_usuario u " + 
			"where u.sala_dueno = true and u.idproducto = #{p_id_producto} and u.idusuario not in (select u.idusuario " +
			"from t_usuario u right join t_proyeccion_ventas pv on pv.id_ejecutivo = u.idusuario and pv.id_ejecutivo_padre is null where u.sala_dueno = true and pv.tipo_nivel = 1 and " +
			"pv.id_producto = #{p_id_producto} and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo})")
	/*@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
			"from t_usuario u right join t_proyeccion_ventas pv on pv.id_ejecutivo = u.idusuario and pv.id_ejecutivo_padre is null where u.sala_dueno = true and u.sala > 0 and "
			+ "pv.id_producto = #{p_id_producto} and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}")*/
	public List<Usuario> listEnSala(@Param("p_id_producto") Integer id_producto, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;	
	
	
	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, " +
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, " +
			"u.sala as idpadre, " +
			"pv.id_ejecutivo_padre as id_ejecutivo_padre " +
			"from t_usuario u inner join t_proyeccion_ventas pv on pv.id_ejecutivo = u.idusuario where pv.tipo_nivel = 2 and " +
			"pv.id_producto = #{p_id_producto} and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo} " +
			"union " +
			"select u.nombre,u.apellido_paterno, u.apellido_materno, 0 as id_proyeccion_venta, #{p_id_negocio} as id_negocio, #{p_id_producto} as idproducto, u.idusuario as id_ejecutivo, " +
			"0 as meta, #{p_periodo} as periodo, u.sala as idpadre,  0 as id_ejecutivo_padre " +
			"from t_usuario u " + 
			"where u.sala > 0 and u.idproducto = #{p_id_producto} and u.idusuario not in (select u.idusuario " +
			"from t_usuario u inner join t_proyeccion_ventas pv on pv.id_ejecutivo = u.idusuario where pv.tipo_nivel = 2 and " +
			"pv.id_producto = #{p_id_producto} and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo})")
	/*@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
			"u.sala as idpadre, "+
			"COALESCE(pv.id_ejecutivo_padre,0) as id_ejecutivo_padre "+
			"from t_usuario u inner join t_proyeccion_ventas pv on pv.id_ejecutivo = u.idusuario where pv.tipo_nivel = 2 and "
			+ "pv.id_producto = #{p_id_producto} and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}")*/
	public List<Usuario> listEnSalaAsignados(@Param("p_id_producto") Integer id_producto, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;
	

	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
			"from t_usuario u, t_proyeccion_ventas pv where pv.id_ejecutivo_padre is null and  "
			+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}")
	public List<Usuario> listEnSalaOnline(@Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;	
	
	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
			"from t_usuario u, t_proyeccion_ventas pv where pv.id_ejecutivo_padre is null and pv.id_producto = #{p_idproducto_padre} and "
			+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}")
	public List<Usuario> listEnSalaOnlineByProducto(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;
	
//	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
//			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
//			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
//			"from t_usuario u, t_proyeccion_ventas pv where pv.id_ejecutivo_padre is null and pv.id_producto = #{p_idproducto_padre} and "
//			+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}")
	public List<Usuario> listEnSalaOnlineByProductoDOT(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo,@Param("valor_dotacion") Integer valor_dotacion) throws Exception;

	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
			"from t_usuario u, t_proyeccion_ventas pv where pv.id_ejecutivo_padre is not null and "
			+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}")
	public List<Usuario> listEnSalaAsignadosOnline(@Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;
	
//	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
//			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
//			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
//			"from t_usuario u, t_proyeccion_ventas pv where id_ejecutivo_padre is not null and pv.id_producto = #{p_idproducto_padre} and "
//			+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}" )
	public List<Usuario> listEnSalaAsignadosOnlineByProductoDOT(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo,
			@Param("valor_dotacion") Integer valor_dotacion) throws Exception;
	
	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
	"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
	"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
	"from t_usuario u, t_proyeccion_ventas pv where id_ejecutivo_padre is not null and pv.id_producto = #{p_idproducto_padre} and "
	+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo}" )
	public List<Usuario> listEnSalaAsignadosOnlineByProducto(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;
	
	
	@Select("select u.nombre,u.apellido_paterno, u.apellido_materno, pv.id_proyeccion_venta as id_proyeccion_venta, "+ 
			"pv.id_negocio as id_negocio, pv.id_producto as idproducto, pv.id_ejecutivo as id_ejecutivo, pv.meta as meta,pv.periodo as periodo, "+
			"pv.id_ejecutivo_padre as id_ejecutivo_padre "+
			"from t_usuario u, t_proyeccion_ventas pv where id_ejecutivo_padre is not null and pv.id_producto = #{p_idproducto_padre} and "
			+ "pv.id_ejecutivo = u.idusuario and pv.id_negocio = #{p_id_negocio} and pv.periodo = #{p_periodo} and u.estado_banco = 'Visible Banco' " )
	public List<Usuario> listEnSalaAsignadosOnlineByProductoPH(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, @Param("p_periodo") Date periodo) throws Exception;
	
	@Select("select * from t_usuario where sala_dueno = true")
	public List<Usuario> listUsuarioEnSalaActivos() throws Exception;	
	
	@Select("select * from t_usuario where sala > 0 and idproducto = #{p_id_producto} order by nombre,apellido_paterno, apellido_materno")
	public List<Usuario> listUsuarioEnSalaAsignados(@Param("p_id_producto") Integer id_producto) throws Exception;	
	
	//lista de Supervisor	
	@Select("select "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as des_responsable, "
			+ "nr.idusuario  from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo)"
			+ "where n.id_negocio = #{id_negocio} and c.id_cargo = 1")
	public List<Usuario> listaSupervisor(@Param("id_negocio") Integer id_negocio) throws Exception;	
	
	//lista de Coordinador
	@Select("select  nr.idusuario, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as des_responsable "
			+ " from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo)"
			+ "where n.id_negocio = #{id_negocio} and c.id_cargo = 2")
	public List<Usuario> listaCoordinador(@Param("id_negocio") Integer id_negocio) throws Exception;		
	
	
	@Update("update t_usuario set id_cargo = #{id_cargo} where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void actualizarCargo(@Param("idusuario") Integer idusuario,@Param("id_cargo") Integer id_cargo)throws Exception;
	
	
	@Update("update t_planilla_personal set idusuario = #{idusuario}, id_planilla = #{id_planilla}, id_cargo = #{id_cargo}, id_negocio = #{id_negocio}, "
			+ "fecha_registro = #{fecha_registro},fecha_ingreso = #{fecha_ingreso} where idusuario = #{idusuario}")
	@Options(flushCache=true,useCache=true)
	public void guardaPlanillaPersonal(PlanillaPersonal planillaPersonal) throws Exception;	
	
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nomSupervisor ,u.id_coordinador ,u.dni,u.fecha_ingreso,u.login,u.password,u.funcion,"
			+ " ub.sdepartamento as departamento, ub.sprovincia  as provincia ,ub.sdistrito as distrito,  tp.descripcion as desTipoPlanilla   "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) "
			+ "inner join t_usuario sp on sp.idusuario = u.id_supervisor "
			+ "left join t_ubigeo ub on ub.sid_ubigeo = u.sid_ubigeo "
			+ "left join t_tipo_planilla tp on tp.id_planilla = u.id_planilla "
			+ " where u.id_negocio = #{id_negocio} "
			+ " and (c.id_cargo = 4 or  c.id_cargo = 9) "
			+ "and u.id_supervisor = #{id_supervisor} and u.estado_planilla = true order by  u.apellido_paterno ,u.apellido_materno, u.nombre")
	public List<Usuario> listaPromotorxNegocio(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	@Select("select u.idusuario, tp.descripcion as desTipoPlanilla, u.nombre, u.apellido_paterno ,u.apellido_materno,u.dni,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, u.departamento, u.provincia, u.distrito, u.funcion,  "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador,u.codigo_banco as codigoBanco , u.codigo_banco_supervisor as codigoBancoSupervisor "
			+ "from ((t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) "
			+ "					 inner join t_usuario sp on sp.idusuario = u.id_coordinador) "
			+ "                  inner join t_tipo_planilla tp on tp.id_planilla = u.id_planilla "
			+ " where u.id_negocio = #{id_negocio} and c.id_cargo in (4,1) and u.estado = 't' and u.estado_planilla = true "
			+ "and u.id_supervisor = #{id_supervisor} order by u.apellido_paterno, u.apellido_materno, u.nombre")
	public List<Usuario> listaPromotorCoordinadorxNegocio(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_coordinador "
			+ " where u.id_negocio = #{id_negocio} and (c.id_cargo = 4 or c.id_cargo = 9) "
			+ "and u.id_supervisor = #{id_supervisor} and u.estado_planilla = true ")
	public List<Usuario> listaPromotorBackxNegocio(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,u.dni, u.fecha_ingreso,  c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, "
			+ "ub.sdepartamento as departamento, ub.sprovincia  as provincia ,ub.sdistrito as distrito, "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nomSupervisor ,u.id_coordinador,u.login,u.password,u.funcion ,tp.descripcion as desTipoPlanilla  "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_supervisor "
			+ "left join t_ubigeo ub on ub.sid_ubigeo = u.sid_ubigeo "
			+ "left join t_tipo_planilla tp on tp.id_planilla = u.id_planilla "
			+ " where u.id_negocio = #{id_negocio} and (c.id_cargo = 4 or  c.id_cargo = 9) order by u.id_supervisor ")
	public List<Usuario> listarEjecutivosxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	
	@Select("select u.*, u.codigo_banco as codigoBanco, tp.descripcion as desTipoPlanilla, u.codigo_banco_supervisor as codigoBancoSupervisor  "
			+ "from (t_usuario u inner join t_negocio_responsables nr on nr.idusuario = u.idusuario) "
			+ "					 inner join t_tipo_planilla tp on tp.id_planilla = u.id_planilla "
			+ "where nr.id_negocio = #{id_negocio} and nr.id_cargo in (1,4) ")
	public List<Usuario> listaSupervisorxIdNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	

	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio "
			+ "from (t_usuario u inner join t_negocio_responsables nr on nr.idusuario = u.idusuario) where nr.id_negocio = #{id_negocio} and nr.id_cargo = 2 ")
	public List<Usuario> listaCoordinadorxIdNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select count(*) from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) "
			+ " "
			+ " where u.id_negocio = #{id_negocio} and (c.id_cargo = 4 or  c.id_cargo = 9 or c.id_cargo = 1 or c.id_cargo = 2) and u.estado_planilla = true ")
	public Integer getEjecutivoxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select * from t_usuario where idusuario =  #{idusuario}")
	public Usuario getUsuario_byIdUsuario(Integer idusuario);
	
	@Select("select COUNT(*) from t_usuario where dni = #{dniUsuario}")
	public int verificarDniCarnetRepetidos(@Param("dniUsuario")String dniUsuario)throws Exception;

	@Select("select COUNT(*) from t_usuario where login = #{login}")
	public int verificarloginRepetido(@Param("login")String login)throws Exception;
	
	@Select("select u.idusuario, u.nombre,u.apellido_paterno,u.apellido_materno "
			+ "from t_usuario u  where u.id_cargo = 4 and u.idusuario !=#{idusuario} ")
	public List<Usuario> listarUsuarioReasignar(@Param("idusuario") Integer idusuario) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, u.departamento, u.provincia, u.distrito, u.funcion,  "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_coordinador "
			+ " where u.id_negocio = #{id_negocio} and c.id_cargo = 4 and u.idusuario !=#{idusuario} "
			+ "and u.id_supervisor = #{id_supervisor} order by u.apellido_paterno, u.apellido_materno, u.nombre")
	public List<Usuario> listaPromotorCoordinadorxNegocioReasignar(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor, @Param("idusuario") Integer idusuario) throws Exception;
	
	@Select("select u.idusuario, u.nombre, u.apellido_paterno ,u.apellido_materno,u.id_negocio,c.id_cargo,c.descripcion as des_cargo, u.id_supervisor, u.departamento, u.provincia, u.distrito, u.funcion,  "
			+ "(sp.nombre || ' ' || sp.apellido_paterno || ' ' || sp.apellido_materno) as nombreCoordinador ,u.id_coordinador "
			+ "from (t_usuario u inner join t_cargo c on c.id_cargo = u.id_cargo) inner join t_usuario sp on sp.idusuario = u.id_coordinador "
			+ " where u.id_negocio = #{id_negocio} and c.id_cargo = 4 "
			+ "and u.id_supervisor = #{id_supervisor} order by u.apellido_paterno, u.apellido_materno, u.nombre")
	public List<Usuario> listaCoordinadorxNegocioReasignar(@Param("id_negocio") Integer id_negocio,@Param("id_supervisor") Integer id_supervisor) throws Exception;
	
	
	@Select("SELECT u.idusuario,u.nombre,u.apellido_paterno,u.apellido_materno,u.dni,u.email,u.direccion,u.login,u.password,u.estado,u.fecha_registro,u.fecha_mod,u.es_nuevo,u.id_centro_atencion, "
			+ "u.id_cargo,u.id_planilla, n.descripcion as  desNegocio ,c.descripcion as desCargo from t_usuario u inner join t_negocio n on u.id_negocio = n.id_negocio "
			+ "left join t_cargo c  on u.id_cargo = c.id_cargo "
			+ " ")
	public List<Usuario> listUsuarioActivosNoCesados() throws Exception;
	
	@Select("select max(idusuario) from t_usuario ") 
	public Integer getLastUser() throws Exception;
	
	@Select("select u.*, "+
			" c.descripcion as desCargo, "+ 
			" u.codigo_banco as codigoBanco, "+ 
			" t.descripcion as desTipoPlanilla,"
			+ "t.id_planilla as id_planilla "+
			" from (((t_negocio_responsables n left join t_usuario u on u.idusuario = n.idusuario) "+ 
			"					 left join t_cargo c on c.id_cargo = n.id_cargo) "+
			"					 left join t_tipo_planilla t on t.id_planilla = n.id_planilla) "+
			"	where u.codigo_banco = #{codigo_banco} and n.id_negocio = #{id_negocio} and n.id_cargo = 4 ")
	public Usuario getUsuariobyCodigoBanco(@Param("codigo_banco") String codigo_banco,@Param("id_negocio") int id_negocio);

	@Select("select u.*, "+
			" c.descripcion as desCargo, "+ 
			" u.codigo_banco as codigoBanco, "+ 
			" t.descripcion as desTipoPlanilla, "
			+ "t.id_planilla as id_planilla "+
			" from (((t_negocio_responsables n left join t_usuario u on u.idusuario = n.idusuario) "+ 
			"					 left join t_cargo c on c.id_cargo = n.id_cargo) "+
			"					 left join t_tipo_planilla t on t.id_planilla = n.id_planilla) "+
			"	where u.codigo_banco_supervisor = #{codigo_banco} and n.id_negocio = #{id_negocio}  and n.id_cargo = 1 ")
	public Usuario getUsuariobyCodigoBancoEje(@Param("codigo_banco") String codigo_banco,@Param("id_negocio") int id_negocio);
	
	public List<Usuario> getUsuariosByFacturacionNegocio(ExpedienteFilter filter) throws Exception;
	
	@Select("select u.*, p.descripcion as desTipoPlanilla from t_usuario u inner join t_tipo_planilla p on u.id_planilla = p.id_planilla where u.id_cargo = 9 and u.id_negocio = #{id_negocio} and u.estado = 't'")
	public List<Usuario> getBackOfficeByNegocio(@Param("id_negocio") Integer id_negocio)throws Exception;
	
	@Select("select u.*, p.descripcion as desTipoPlanilla from t_usuario u inner join t_tipo_planilla p on u.id_planilla = p.id_planilla where u.idusuario= #{idusuario} ")
	public Usuario getUsuarioByIddPlanilla(@Param("idusuario") Integer idusuario)throws Exception ;
	
	@Select("select * from t_usuario where id_cargo = #{id_cargo} and id_negocio = #{id_negocio}")
	public List<Usuario> getUsuariosByCargoAndNegocio(@Param("id_cargo") Integer id_cargo, @Param("id_negocio") Integer id_negocio)throws Exception ;
	
	@Select("select  u.*, p.id_planilla, p.descripcion as desTipoPlanilla  from t_usuario u inner join t_tipo_planilla p on u.id_planilla = p.id_planilla where u.id_negocio = #{id_negocio}")
	public List<Usuario> getUsuariosByNegocio(@Param("id_negocio") Integer id_negocio)throws Exception;
	
	@Select("select email from t_usuario where idusuario = #{idusuario};")
	public String getCorreoByIdUser(@Param("idusuario") int idusuario)throws Exception;
	
	@Select("select distinct ud.*  from t_expediente exp inner join  t_expediente_exclusion_previa ep on exp.expediente_id = ep.expediente_id "
			+ " inner join t_usuario ud on ud.idusuario = ep.idusuario where exp.periodo = #{periodo} and exp.id_producto = #{id_producto} "
			+ " and (exp.tipo_ciiu = 'TRABAJABLE' or  exp.tipo_ciiu = 'POTENCIAL') ")
	public List<Usuario> listUsuarioDepuracionByPeriodoProductoTrabajable(@Param("periodo") Date periodo,@Param("id_producto") Integer id_producto);
	
	@Select("select idusuario from t_expediente_producto where expediente_id = #{expediente_id}")
	public Integer getIdUsuarioAsignadoByExpediente(@Param("expediente_id")Integer expediente_id);
	
	@Select("select  u.*, p.id_planilla, p.descripcion as desTipoPlanilla  from t_usuario u inner join t_tipo_planilla p on u.id_planilla = p.id_planilla "
			+ " where u.id_cargo = 2 and u.id_negocio = #{id_negocio} and u.estado_planilla ")
	public List<Usuario> getUsuarioCoordinadorByNegocio(@Param("id_negocio")Integer id_negocio);
	
	
	@Select("select  u.*, p.id_planilla, p.descripcion as desTipoPlanilla  from t_usuario u inner join t_tipo_planilla p on u.id_planilla = "
			+ "p.id_planilla order by u.nombre, u.apellido_paterno, u.apellido_materno ")
	public List<Usuario> getALLUsuarios()throws Exception;
	
	
	@Select("select u.*, "+
			" c.descripcion as desCargo, "+ 
			" u.codigo_banco as codigoBanco, "+ 
			" t.descripcion as desTipoPlanilla, "
			+ "t.id_planilla as id_planilla "+
			" from (((t_negocio_responsables n left join t_usuario u on u.idusuario = n.idusuario) "+ 
			"					 left join t_cargo c on c.id_cargo = n.id_cargo) "+
			"					 left join t_tipo_planilla t on t.id_planilla = n.id_planilla) "+
			"	where   n.id_negocio = #{id_negocio}  and n.id_cargo = #{id_cargo} ")
	public List<Usuario> getUsuariosNRByCargo(@Param("id_cargo") Integer id_cargo,@Param("id_negocio") int id_negocio);
	
	public List<Usuario> getUsuariosByFacturacionNegocio2(ExpedienteFilter filter) throws Exception;
	
	
	@Select("select u.*, "+
			" c.descripcion as desCargo, "+ 
			" u.codigo_banco as codigoBanco, "+ 
			" t.descripcion as desTipoPlanilla,"
			+ "t.id_planilla as id_planilla "+
			" from (((t_negocio_responsables n left join t_usuario u on u.idusuario = n.idusuario) "+ 
			"					 left join t_cargo c on c.id_cargo = n.id_cargo) "+
			"					 left join t_tipo_planilla t on t.id_planilla = n.id_planilla) "+
			"	where u.dni = #{codigo_banco} and n.id_negocio = #{id_negocio} and n.id_cargo = 4 ")
	public Usuario getUsuariobyCodigoBancoDNI(@Param("codigo_banco") String codigo_banco,@Param("id_negocio") int id_negocio);
	
	
	@Select("select u.*, "+
			" c.descripcion as desCargo, "+ 
			" u.codigo_banco as codigoBanco, "+ 
			" t.descripcion as desTipoPlanilla, "
			+ "t.id_planilla as id_planilla "+
			" from (((t_negocio_responsables n left join t_usuario u on u.idusuario = n.idusuario) "+ 
			"					 left join t_cargo c on c.id_cargo = n.id_cargo) "+
			"					 left join t_tipo_planilla t on t.id_planilla = n.id_planilla) "+
			"	where u.dni = #{codigo_banco} and n.id_negocio = #{id_negocio}  and n.id_cargo = 1 ")
	public Usuario getUsuariobyCodigoBancoEjeDNI(@Param("codigo_banco") String codigo_banco,@Param("id_negocio") int id_negocio);
	
	
	
	@Update("update t_usuario set codigo_quality = #{codigo_quality} , fecha_ingreso = #{fecha_ingreso}, "
			+ "	fecha_cese = #{fecha_cese}, motivo_cese = #{motivo_cese} , dias_vacaciones = #{dias_vacaciones},"
			+ " dias_descanso = #{dias_descanso}, estado_planilla =#{estado_planilla} where dni = #{dni}")
	public void actualizarDatosRptaPlanilla(Usuario usuario)throws Exception;
	
//	@Select("select distinct sp.* from t_expediente ex inner join t_producto p on p.id_producto =ex.id_producto "
//			+ "	inner join t_expediente_producto ep on ex.expediente_id = ep.expediente_id "
//			+ "	inner join t_usuario sp on sp.idusuario = ep.idusuario_supervisor "
//			+ "	where 	p.id_negocio = #{id_negocio} and ex.periodo = #{periodo} "
//			+ "	and ex.territorio_ofc = #{territorio} ;")
	public List<Usuario> getSupervisoresxTerritoriosByNegocioPeriodo(@Param("id_negocio") int id_negocio,@Param("periodo") Date periodo,
			@Param("territorio")String territorio,@Param("territorios")List<String> territorios) throws Exception;
	
	
	@Select("select distinct u.* from t_negocio_responsables nr inner join t_usuario u "
			+ "	on u.idusuario = nr.idusuario where nr.id_negocio = #{id_negocio} order by u.apellido_paterno,u.apellido_materno,u.nombre;  ")
	public List<Usuario> getUsuariosByNegocioNR(@Param("id_negocio") int id_negocio );
	
	public List<Usuario> getUsuariosCuartiles(ExpedienteFilter filter) throws Exception;
	public List<Usuario> getUsuariosCuartilesxProducto(ExpedienteFilter filter) throws Exception;
	
	public List<Usuario> getUsuariosCuartilesxProductoxAnio(ExpedienteFilter filter) throws Exception;
	
	@Select("select idusuario, (nombre || ', ' || apellido_paterno || ' ' || apellido_materno) as usuarioAsignado, id_cargo from t_usuario")
	public List<Usuario> getListaUsuarios();
	

	public List<Usuario> listEnSalaOnlineByProductoPeriodos(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, @Param("periodos") List<Date> periodos,@Param("valor_dotacion") String valor_dotacion) throws Exception;
	
	
	public List<Usuario> listEnSalaAsignadosOnlineByProductoPeriodos(@Param("p_idproducto_padre") Integer idproducto_padre, @Param("p_id_negocio") Integer id_negocio, 
				@Param("periodos") List<Date> periodos,@Param("valor_dotacion") String valor_dotacion) throws Exception;
	
	public List<Usuario> listarUsuariosGpsPosActual(@Param("id_producto") Integer id_producto) throws Exception;
	
	
	
	
	@Select("select u.* from t_usuario u inner join t_usuarioxperfil up on u.idusuario  = up.idusuario inner join t_perfil p on p.cod_perfil = up.cod_perfil "
			+ "	where u.id_negocio = 11 and p.cod_perfil =68 ")
	public List<Usuario> getVendedoresPYMES() throws Exception;
	
	@Select("select u.idusuario,u.apellido_paterno,u.apellido_materno,u.nombre from t_usuario u where u.idproducto = #{id_producto} and u.imei is not null and u.imei != '' order by u.apellido_paterno,u.apellido_materno,u.nombre; ")
	public List<Usuario> getUsuarioGPSbyProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	

	@Select("select ex.territorio_ofc as territorio, eje.idusuario, eje.antiguedad, "
			+ "	(eje.nombre || ' ' || eje.apellido_paterno || ' ' ||  eje.apellido_materno) as nombre, sum(ex.prestamo_soles) as suma, "
			+ " count(*) as cantidad "
			+ " ,(select pv.meta from t_proyeccion_ventas pv where  pv.id_ejecutivo  = eje.idusuario and pv.tipo_nivel = 2 and pv.periodo = #{periodo} and pv.id_negocio = #{id_negocio}  ) as meta "
			+ " from t_expediente ex inner join t_producto p on ex.id_producto = p.id_producto "
			+ " inner join t_expediente_producto ep on ep.expediente_id = ex.expediente_id "
			+ " inner join t_indicadores_call res on res.id_indicadores_call = ep.id_indicadores_call "
			+ "	inner join t_usuario eje on eje.idusuario = ep.idusuario "
			+ " where "
			+ " ex.territorio_ofc = #{territorio} "
			+ " and p.id_negocio = #{id_negocio} "
			+ " and ex.periodo = #{periodo} "
			+ " and ep.periodo = #{periodo} "
			+ " and ex.tipo_base != 'PROPIO' "
			+ " group by ex.territorio_ofc ,eje.idusuario "
			+ " order by ex.territorio_ofc ,eje.nombre, eje.apellido_paterno, eje.apellido_materno;")
	public List<Usuario> rptBackLog_ListUsersByTerritorio(@Param("territorio") String  territorio, @Param("id_negocio") Integer id_negocio, @Param("periodo") Date periodo)    ;
	
	
	
//	@Select("select sp.idusuario, "
//			+ "	(sp.nombre || ' ' || sp.apellido_paterno || ' ' ||  sp.apellido_materno) as nombre, sum(ex.prestamo_soles) as suma, "
//			+ " (select pv.meta from t_proyeccion_ventas pv where  pv.id_ejecutivo  = sp.idusuario and pv.tipo_nivel = 1 and pv.periodo = #{periodo} and pv.id_negocio = #{id_negocio}  ) as meta, "
//			+ " count(distinct eje.idusuario) as cantidad "
//			+ " from t_expediente ex inner join t_producto p on ex.id_producto = p.id_producto "
//			+ " inner join t_expediente_producto ep on ep.expediente_id = ex.expediente_id "
//			+ " inner join t_indicadores_call res on res.id_indicadores_call = ep.id_indicadores_call "
//			+ "	inner join t_usuario eje on eje.idusuario = ep.idusuario"
//			+ " inner join t_usuario sp  on eje.id_supervisor = sp.idusuario  "
//			+ " where "
//			+ " ex.territorio_ofc is not null and trim(ex.territorio_ofc) !='' "
//			+ " and p.id_negocio = #{id_negocio} "
//			+ " and ex.periodo = #{periodo} "
//			+ " and ep.periodo = #{periodo} "
//			+ " group by sp.idusuario "
//			+ " order by sp.nombre, sp.apellido_paterno, sp.apellido_materno ")
	public List<Usuario> rptBackLog_ListSupByTerritorio(@Param("territorio") String  territorio, @Param("id_negocio") Integer id_negocio, @Param("periodo") Date periodo, @Param("territorios")List<String> territorios)    ;
	
	@Select("select distinct upper(u.concesionario) from t_usuario u inner join t_usuarioxperfil up on u.idusuario  = up.idusuario inner join t_perfil p on p.cod_perfil = up.cod_perfil "
			+ "	where u.id_negocio = 11 and p.cod_perfil =68 "
			+ "	and (u.concesionario is not null and trim(u.concesionario)!='') ")
	public List<String> getDistinctConcesionarioPYMES() throws Exception;
	
	public List<Usuario> getUsuariosByFilters(@Param("cod_oficina")Integer idoficina);
	
}
