package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.DocumentoPagina;

public interface DocumentoPaginaMapper {


	@Insert("insert into t_documento_pagina (id_expediente_documento,nro_pagina,peso,descripcion_peso,fecha_subida,id_usuario_creacion,flag,estado_accion,estado) "
			+ " values(#{id_expediente_documento},#{nro_pagina},#{peso},#{descripcion_peso},#{fecha_subida},#{id_usuario_creacion},#{flag},#{estado_accion},#{estado})")
	public void guardarDocumentoPagina(DocumentoPagina documentoPagina) throws Exception;
	
	
	@Select("select * from t_documento_pagina where id_expediente_documento = #{id_expediente_documento} order by nro_pagina asc")
	public List<DocumentoPagina> listByIdExpDoc(@Param("id_expediente_documento") Integer id_expediente_documento) throws Exception;
	
	
	
	@Update("update t_documento_pagina set estado_accion = 'E', estado = false where id_expediente_documento = #{id_expediente_documento}")
	public void updateEstadoEliminadoByIdExpDoc(@Param("id_expediente_documento") Integer id_expediente_documento) throws Exception;
	
	@Update("update t_documento_pagina set estado_accion = 'R', estado = false where id_expediente_documento = #{id_expediente_documento}")
	public void updateEstadoReemplazadoByIdExpDoc(@Param("id_expediente_documento") Integer id_expediente_documento) throws Exception;
	
	
	@Update("update t_documento_pagina set estado_accion = 'AG', estado = false where id_expediente_documento = #{id_expediente_documento}")
	public void updateEstadoAgrupadoByIdExpDoc(@Param("id_expediente_documento") Integer id_expediente_documento) throws Exception;
	
	@Delete("delete from t_documento_pagina  where id_expediente_documento = #{id_expediente_documento}")
	public void eliminarPaginasByIdDocumento(@Param("id_expediente_documento") Integer id_expediente_documento) throws Exception;
	

}
