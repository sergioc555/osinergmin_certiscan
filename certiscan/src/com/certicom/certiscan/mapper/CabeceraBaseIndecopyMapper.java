package com.certicom.certiscan.mapper;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.certiscan.domain.BaseIndecopy;
import com.certicom.certiscan.domain.CabeceraBaseIndecopy;




public interface CabeceraBaseIndecopyMapper {
	
	@Select("select t_cabecera_indecopy. idcabeceraindecopy, t_cabecera_indecopy.idusuario, t_cabecera_indecopy.fecha, t_cabecera_indecopy.numeroregistro, "
			+ "t_cabecera_indecopy.periodo, (t_usuario.nombre || ' ' || t_usuario.apellido_paterno || ' ' || t_usuario.apellido_materno) as nombreUsuario  from t_cabecera_indecopy "
			+ "inner join t_usuario on t_cabecera_indecopy.idusuario=t_usuario.idusuario "
			+ "order by t_cabecera_indecopy.periodo desc")
	public List<CabeceraBaseIndecopy> findAll() throws Exception;
	
	public List<CabeceraBaseIndecopy> findByPAGINATOR(@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder) throws Exception;
	public Integer getCountPAGINATOR(@Param("filters") Map<String,Object> filters)throws Exception;
	
	
	@Select("select * from t_cabecera_indecopy where periodo=#{periodo}")
	public CabeceraBaseIndecopy findByPeriodo(@Param("periodo") Date periodo) throws Exception;
	
	@Select("select * from t_cabecera_indecopy order by fecha DESC OFFSET 0 LIMIT 1")
	public CabeceraBaseIndecopy findByUltimo() throws Exception;
	
	@Insert("insert into t_cabecera_indecopy (idusuario, fecha, numeroregistro,periodo, nombrearchivo) values (#{idUsuario},#{fecha},#{numeroRegistro},#{periodo}, #{nombreArchivo})")
	public void crearCabeceraBaseIndecopy(CabeceraBaseIndecopy cabeceraBaseIndecopy) throws Exception;
		
	@Delete("delete  from t_cabecera_indecopy  where idcabeceraindecopy = #{idcabeceraindecopy}")
	@Options(flushCache=true)
	public void eliminarCabeceraBaseIndecopy(@Param("idcabeceraindecopy") Integer idcabeceraindecopy) throws Exception;
	
}
