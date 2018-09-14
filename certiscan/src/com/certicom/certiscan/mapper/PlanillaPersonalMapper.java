package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.PlanillaPersonal;


public interface PlanillaPersonalMapper {

	@Select("select * from t_negocio_responsables e where e.id_negocio_responsable = #{p_planilla_personal}")
	public PlanillaPersonal findById(@Param("p_planilla_personal") Integer codigoPlanillaPersonal) throws Exception;
	
	@Select("select * from t_negocio_responsables")
	public List<PlanillaPersonal> findAll() throws Exception;
	
	@Select("select p.id_negocio_responsable, t.descripcion as desTipoPlanilla, c.descripcion as desCargo, n.descripcion as desNegocio, p.estado, u.codigo_banco, u.codigo_banco_supervisor, "
			+ "(s.nombre || ' ' || s.apellido_paterno || ' ' || s.apellido_materno) as supervisor, (co.nombre || ' ' || co.apellido_paterno || ' ' || co.apellido_materno) as coordinador, p.*  "
			+ " from (((((t_negocio_responsables p left join t_tipo_planilla t on p.id_planilla = t.id_planilla ) "
			+ " 								  left join t_cargo c on p.id_cargo = c.id_cargo)  "
			+ "									  inner join t_negocio n on p.id_negocio = n.id_negocio) "
			+ "									  inner join t_usuario u on u.idusuario = p.idusuario) "
			+ " 								  left join t_usuario s on s.idusuario = p.id_supervisor) "
			+ "									  left join t_usuario co on co.idusuario = p.id_coordinador "
			+ " where p.idusuario = #{idusuario} ")
	public List<NegocioResponsables> planillaxusuario(@Param("idusuario") Integer idusuario) throws Exception;
	
	@Select("select * from t_negocio_responsables where estado = 'TRUE' order by descripcion asc")
	public List<PlanillaPersonal> listaPlanillaPersonalActivo() throws Exception;
	
	@Insert("insert into t_negocio_responsables (idusuario, id_planilla, id_cargo, id_negocio, estado,id_supervisor,id_coordinador) "
			+ "							 values (#{idusuario},#{id_planilla},#{id_cargo},#{id_negocio},#{estado},#{id_supervisor},#{id_coordinador})")
	public void crearPlanillaPersonal(NegocioResponsables planillaPersonal) throws Exception;
	
	@Update("update t_negocio_responsables set idusuario = #{idusuario}, id_planilla = #{id_planilla}, id_cargo = #{id_cargo}, id_negocio = #{id_negocio}, estado = #{estado}, id_supervisor = #{id_supervisor}, id_coordinador =#{id_coordinador}  where id_negocio_responsable = #{id_negocio_responsable}")
	@Options(flushCache=true,useCache=true)
    public void actualizarPlanillaPersonal(NegocioResponsables planillaPersonal) throws Exception;
	
	@Delete("delete  from t_negocio_responsables  where id_negocio_responsable = #{id_negocio_responsable}")
	@Options(flushCache=true)
	public void eliminarPlanillaPersonal(@Param("id_negocio_responsable") Integer id_negocio_responsable) throws Exception;
	
	@Select("select count(*) from t_negocio_responsables where idusuario = #{idusuario} ")	
	public Integer listCantPlanillaPersonal(@Param("idusuario") Integer idusuario) throws Exception;	
	
	@Update("update t_negocio_responsables set estado = #{estado} where id_negocio_responsable = #{id_negocio_responsable} ")
	public void actualizarEstadoPlanillaPersonal(PlanillaPersonal planillaPersonal) throws Exception;
	
	@Select("select count(*) from t_negocio_responsables  where idusuario  = #{idusuario} and id_planilla = #{id_planilla} and id_cargo = #{id_cargo} and id_negocio = #{id_negocio} ")
	public Integer cantUsuarioPlanilla(NegocioResponsables planillaPersonal) throws Exception;
	

	
}
