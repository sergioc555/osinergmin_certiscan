package com.certicom.certiscan.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ObservacionContactoMapper {

	@Insert("insert into t_observacion_contacto (id_indicadores_call,observacion) values (#{p_id_indicadores_call},#{p_observacion})")
    public void asignarExpedienteAPromotor(@Param("p_id_indicadores_call") Integer p_id_indicadores_call, @Param("p_observacion") String p_observacion) throws Exception;
}
