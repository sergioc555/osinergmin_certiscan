package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Comentario;

public interface ComentarioMapper {
	
	@Update("update t_comentario set descripcion = #{descripcion},expediente_id= #{expediente_id}, fec_hora = #{fec_hora} where idcomentario= #{idcomentario}")
	@Options(flushCache=true,useCache=true)
	public void actualizarComentario(Comentario comentario) throws Exception;
	
	@Insert("insert into t_comentario (expediente_id, descripcion,fec_hora) values (#{expediente_id}, #{descripcion},#{fec_hora})")
	public void crearComentario(Comentario comentario);
	
	@Delete("delete from t_comentario  where idcomentario = #{idcomentario}")
	@Options(flushCache=true)
	public void eliminarComentario(@Param("idcomentario") Integer idcomentario);
	
	@Select("SELECT * from t_comentario where idcomentario = #{idcomentario}")	
	public List<Comentario> find_ByIdComentario(@Param("idcomentario") Integer idcomentario) throws Exception;
	
	@Select("SELECT * from  t_comentario order by descripcion ")	
	public List<Comentario> findAll() throws Exception;

	@Select("select * from t_comentario where idcomentario = (select max(idcomentario) from t_comentario where expediente_id = #{idexpediente})")	
	public Comentario find_ByIdExpediente(@Param("idexpediente") Integer idexpediente) throws Exception;
}
