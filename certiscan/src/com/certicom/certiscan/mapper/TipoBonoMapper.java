package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.TipoBono;

public interface TipoBonoMapper {

	@Select("select * from t_tipo_bono e where e.idtipobono = #{p_idtipobono}")
	public TipoBono findById(@Param("p_idtipobono") Integer idTipoBono) throws Exception;
	
	@Select("select * from t_tipo_bono")
	public List<TipoBono> findAll() throws Exception;
	
	@Insert("insert into t_tipo_bono (descripcion) values (#{descripcion})")
	public void crearTipoBono(TipoBono tipoBono) throws Exception;
	
	@Update("update t_tipo_bono set descripcion = #{descripcion} where idtipobono= #{idtipobono}")
	@Options(flushCache=true,useCache=true)
    public void actualizarTipoBono(TipoBono tipoBono) throws Exception;
	
	@Delete("delete from t_tipo_bono  where idtipobono = #{p_idtipobono}")
	@Options(flushCache=true)
	public void eliminarTipoBono(@Param("p_idtipobono") Integer idTipoBono) throws Exception;
	
}
