package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.ExpedienteRequisito;

public interface ExpedienteRequisitoMapper {
	
	@Insert("insert into t_expediente_requisito(cod_expediente,cod_requisito,estado)"
			+ "	values (#{cod_expediente},#{cod_requisito},#{estado})")
	public void crearExpedienteRequisito(ExpedienteRequisito expedienteRequisito) throws Exception;
	
	
	@Select("select er.id,r.cod_requisito,r.descripcion as desRequisito,coalesce(er.estado,false)as estado from t_requisito r left join "
			+ "t_expediente_requisito er on (r.cod_requisito=er.cod_requisito and er.cod_expediente  = #{cod_expediente})")
	public List<ExpedienteRequisito> listarRequisitosxExpediente(@Param("cod_expediente") Integer cod_expediente) throws Exception;

	@Update("update t_expediente_requisito set estado = #{estado} where id = #{id} ")
	public void actualizarEstadoReq(@Param("id") Integer id,@Param("estado") boolean estado)throws Exception;
	
}
