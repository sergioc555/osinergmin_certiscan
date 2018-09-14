package com.certicom.certiscan.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.certiscan.domain.CompresionLectura;

public interface ComprensionLecturaMapper {
	
	@Insert("insert into t_comprension_lectura (idusuario,id_negocio,texto_pdf,periodo,fec_registro) values (#{idusuario},#{id_negocio},#{texto_pdf},#{periodo},#{fec_registro})")
	public void insertarComprensionLectura(CompresionLectura compresionLectura)throws Exception;
	
	@Select("select count(*) from t_comprension_lectura where idusuario = #{idusuario} and texto_pdf = 'Prestamos_de_Consumo_Fuvex_Mayo.pdf'")
	public Integer getLecturasByUser(@Param("idusuario") Integer idusuario) throws Exception;

}
