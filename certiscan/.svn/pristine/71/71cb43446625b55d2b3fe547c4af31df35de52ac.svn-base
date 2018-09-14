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


public interface BaseIndecopyMapper {

	@Select("select * from t_baseindecopy bc where bc.baseIndecopyId = #{baseIndecopyId}")
	public BaseIndecopy findById(@Param("baseIndecopyId") Integer baseIndecopyId) throws Exception;
	
	@Select("select * from t_baseindecopy where idcabeceraindecopy = #{idcabeceraindecopy} and valor=#{valor} LIMIT 1")
	public BaseIndecopy findByNumeroByCabecera(@Param("idcabeceraindecopy") Integer idcabeceraindecopy, @Param("valor") String valor) throws Exception;
	
	@Select("select * from t_baseindecopy bc where tiporegistro<>'CORREO_ELECTRONICO' and bc.idcabeceraindecopy = #{idCabeceraIndecopy}")
	public List<BaseIndecopy> findByIdCabecera(@Param("idCabeceraIndecopy") Integer idCabeceraIndecopy) throws Exception;
	
	@Select("select * from t_baseindecopy order by baseIndecopyId DESC OFFSET 0 LIMIT 1")
	public BaseIndecopy findByUltimo() throws Exception;
	
	@Select("select * from t_baseindecopy")
	public List<BaseIndecopy> findAll() throws Exception;
	
	@Select("select * from t_baseindecopy where valor = #{p_valor} ")
	public List<BaseIndecopy> findByValor(@Param("p_valor") String valor) throws Exception;
	
	@Select("select * from t_baseindecopy b where b.periodo = #{periodo} order by nuevo DESC")
	public List<BaseIndecopy> findByPeriodo(@Param("periodo") Date periodo) throws Exception;
	
	@Select("select * from t_baseindecopy b where b.periodo = #{periodo} limit 5")
	public List<BaseIndecopy> findByPeriodoLimitado(@Param("periodo") Date periodo) throws Exception;
	
	@Select("select * from t_baseindecopy b where extract(month from b.periodo)=#{mes} "
			+ "and  extract(year from b.periodo)=#{anio} order by nuevo DESC")
	public List<BaseIndecopy> findByAnioMes(@Param("anio") Integer annio, @Param("mes") Integer mes) throws Exception;
	
	public List<BaseIndecopy> findByAnioMesPAGINATOR(@Param("anio") Integer annio, @Param("mes") Integer mes, @Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder) throws Exception;
	public Integer getCountBaseIndecopyPAGINATOR(@Param("anio") Integer annio, @Param("mes") Integer mes, @Param("filters") Map<String,Object> filters)throws Exception;
	
	@Select("select count(*) from t_baseindecopy b where extract(month from b.periodo)=#{mes} "
			+ "and  extract(year from b.periodo)=#{anio}")
	public Integer getCountBaseIndecopy(@Param("anio") Integer annio, @Param("mes") Integer mes) throws Exception;
	
	//@Insert("insert into t_baseindecopy (codigo, valor, fecharegistro,tiporegistro,periodo,fechacarga,nuevo,motivo,detalle,idcabeceraindecopy) values (#{codigo},#{valor},#{fecharegistro},#{tiporegistro},#{periodo},now(),#{nuevo},#{motivo},#{detalle},#{idCabeceraIndecopy})")
	public void crearBaseIndecopy(BaseIndecopy baseIndecopy) throws Exception;
		
	@Delete("delete  from t_baseindecopy  where baseIndecopyId = #{baseIndecopyId}")
	@Options(flushCache=true)
	public void eliminarBaseIndecopy(@Param("baseIndecopyId") Integer baseIndecopyId) throws Exception;
	
	
	@Delete("delete  from t_baseindecopy  where idcabeceraindecopy = #{idCabeceraIndecopy}")
	@Options(flushCache=true)
	public void eliminarBaseIndecopyByCabecera(@Param("idCabeceraIndecopy") Integer idCabeceraIndecopy) throws Exception;
	
}
