package com.certicom.certiscan.mapper;

import java.util.List;

import javax.print.DocFlavor.STRING;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.Usuario;

public interface NegocioResponsablesMapper {

	@Select("select * from t_negocio_responsables e where e.id_negocio_responsable = #{id_negocio_responsable}")
	public NegocioResponsables findById(@Param("id_negocio_responsable") Integer id_negocio_responsable) throws Exception;
	
	@Select("select * from t_negocio_responsables")
	public List<NegocioResponsables> findAll() throws Exception;
	
	//cambiar
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, u.dni as dni_resp, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado,u.estado as estado_sistema,u.estado_planilla from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo)"
			+ "where n.id_negocio = #{id_negocio}")
	public List<NegocioResponsables> listaResponsables(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Insert("insert into t_negocio_responsables (id_negocio, idusuario, id_cargo,estado) values (#{id_negocio},#{idusuario},#{id_cargo},#{estado})")
	public void crearNegocioResponsables(NegocioResponsables negocioResponsables) throws Exception;
	
	@Update("update t_negocio_responsables set id_negocio = #{id_negocio}, idusuario = #{idusuario}, id_cargo=#{id_cargo}, estado = #{estado} where id_negocio_responsable= #{id_negocio_responsable}")
	@Options(flushCache=true,useCache=true)
    public void actualizarNegocioResponsables(NegocioResponsables negocioResponsables) throws Exception;
	
	@Delete("delete  from t_negocio_responsables  where id_negocio_responsable = #{id_negocio_responsable}")
	@Options(flushCache=true)
	public void eliminarNegocioResponsables(@Param("id_negocio_responsable") Integer id_negocio_responsable) throws Exception;
	
	@Update("update t_negocio_responsables set estado = #{estado} where id_negocio_responsable= #{id_negocio_responsable}")
	@Options(flushCache=true,useCache=true)
    public void actualizarEstado(NegocioResponsables negocioResponsables) throws Exception;

	@Select("select count(*) from t_negocio_responsables where id_negocio = #{id_negocio} and idusuario = #{idusuario} and id_cargo = #{id_cargo} ")
	public Integer cantResponsablesxNegocio(NegocioResponsables negocioResponsables) throws Exception;
	
	
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, u.departamento, u.provincia, u.distrito, u.funcion, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "where n.id_negocio = #{id_negocio} and nr.id_cargo = 1 and u.estado_planilla = true order by  u.nombre ,u.apellido_paterno ,apellido_materno ")
	public List<NegocioResponsables> listaSupervisorxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "where n.id_negocio = #{id_negocio} and nr.id_cargo = 1  and nr.id_negocio_responsable= #{id_negocio_responsable}")
	public List<NegocioResponsables> listaSupervisorxNegocioxResponsable(@Param("id_negocio") Integer id_negocio, @Param("id_negocio_responsable") Integer id_negocio_responsable) throws Exception;

	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, u.dni as dni_responsable, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "where n.id_negocio = #{id_negocio} and nr.id_cargo = 9")
	public List<NegocioResponsables> listaBackxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "where n.id_negocio = #{id_negocio} and nr.id_cargo = 4")
	public List<NegocioResponsables> listaEjecutivoxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "where n.id_negocio = #{id_negocio} and nr.id_cargo = 1")
	public Integer getCountEjecutivosxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	
	public List<NegocioResponsables> rptRelacionPromotores ( @Param("id_negocio")Integer id_negocio) throws Exception;
	
	@Select("select idusuario from t_negocio_responsables where id_negocio_responsable= #{id_negocio_responsable} ")
	public Integer obtenerIdUsuario(@Param("id_negocio_responsable") Integer id_negocio_responsable) throws Exception;
	

	@Select("select * from t_negocio_responsables where idusuario= #{idusuario} ")
	public List<NegocioResponsables> getNegocioResponsableByUser(@Param("idusuario") Integer idusuario) throws Exception;
	
	
	@Update("update t_negocio_responsables set id_cargo = #{id_cargo},"
			+ "								   id_planilla = #{id_planilla},"
			+ "								   id_supervisor = #{id_supervisor},"
			+ "								   id_coordinador = #{id_coordinador}"
			+ "where id_negocio_responsable= #{id_negocio_responsable}")
	@Options(flushCache=true,useCache=true)
    public void updateNegocioResponsablesSomeFields(NegocioResponsables negocioResponsables) throws Exception;
	
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, u.departamento, u.provincia, u.distrito, u.funcion, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from (((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "where n.id_negocio = #{id_negocio} ")
	public List<NegocioResponsables> listaUsuarioxNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	
	
	@Select("select nr.id_negocio_responsable, nr.id_negocio, n.descripcion as des_negocio, nr.idusuario, u.departamento, u.provincia, u.distrito, u.funcion, "
			+ "(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable, nr.id_cargo, c.descripcion as des_cargo, "
			+ " nr.estado from ((((t_negocio_responsables nr inner join t_negocio n on nr.id_negocio = n.id_negocio) "
			+ "inner join t_usuario u on u.idusuario = nr.idusuario) inner join t_cargo c on c.id_cargo = nr.id_cargo) "
			+ "inner join t_usuarioxperfil up on up.idusuario = u.idusuario) "
			+ "where n.id_negocio = #{id_negocio} and nr.id_cargo = 1 and up.cod_perfil = #{cod_perfil}")
	public List<NegocioResponsables> listaSupervisorPHxNegocio(@Param("id_negocio") Integer id_negocio, @Param("cod_perfil") Integer cod_perfil) throws Exception;
	
	public List<NegocioResponsables> rptReporteLectura ( @Param("id_negocio")Integer id_negocio ,@Param("examen")String id_examen,@Param("id_lectura")Integer idlectura ) throws Exception;
	
	@Select("select  n.id_negocio, n.descripcion as des_negocio, u.idusuario, u.departamento, u.provincia, u.distrito, u.funcion, "
			+ " (u.apellido_paterno || ' ' || u.apellido_materno || ' ' || u.nombre ) as responsable, u.id_cargo, c.descripcion as des_cargo, "
			+ " u.estado from ((t_usuario u inner join t_negocio n on u.id_negocio = n.id_negocio) "
			+ " inner join t_cargo c on c.id_cargo = u.id_cargo) "
			+ "where u.id_negocio = #{id_negocio} and u.id_cargo = 1 and u.estado_planilla = true "
			+ "and u.idusuario !=#{id_supervisor} order by  u.apellido_paterno ,u.apellido_materno ,nombre ")
	public List<NegocioResponsables> listaSupervisorxNegocioReasignar(@Param("id_negocio") Integer id_negocio, @Param("id_supervisor") Integer id_suervisor) throws Exception;
	
	
	@Select("select distinct u.*,(u.nombre || ' ' || u.apellido_paterno || ' ' || apellido_materno) as responsable from t_negocio_responsables nr inner join t_usuario u "
			+ "	on u.idusuario = nr.idusuario where nr.id_negocio = #{id_negocio} order by u.apellido_paterno,u.apellido_materno,u.nombre;  ")
	public List<NegocioResponsables> getUsuariosByNegocioNR(@Param("id_negocio") int id_negocio );
	
}
