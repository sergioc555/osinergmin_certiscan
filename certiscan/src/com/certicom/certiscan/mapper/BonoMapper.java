package com.certicom.certiscan.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Bono;


public interface BonoMapper {
	
	@Select("select b.* , (u.nombre || ' ' || u.apellido_paterno || ' ' || u.apellido_materno) as nombre_completo, "
			+ "(c.mes || '-' ||  c.anio || ' ' || c.fecini || ' ' || c.fecfin) as des_ciclo, tb.descripcion as desTipoBono "+ 
			"from  ((t_bono b inner join t_usuario u on u.idusuario = b.idusuario) "+
			"   inner join t_ciclo c on b.cod_ciclo = c.cod_ciclo ) inner join t_tipo_bono tb on tb.idtipobono = b.idtipobono "+ 
			"	where b.idusuario = #{idusuario}" )
	public List<Bono>listBonoByUsuario(Bono bono)throws Exception;
	
	public void registraBono(Bono bono)throws Exception;
	
	@Update("update t_bono set idusuario = #{idusuario},observacion=#{observacion},monto=#{monto},idtipobono=#{idtipobono},fec_registro=#{fec_registro},usu_registro=#{usu_registro},cod_ciclo=#{cod_ciclo},anio=#{anio},mes=#{mes} where id_bono= #{id_bono}")
	public void actualizaBono(Bono bono)throws Exception;
	
	@Select("SELECT "+
			"b.cod_personal, "+
			"t.descripImpresion, "+ 
			"'PES' as pes, "+
			"ROUND((SELECT SUM(a.monto) FROM tb_bono a WHERE b.cod_personal = a.cod_personal AND a.idTipBono = b.idTipBono AND a.fec_fact = #{fecha}), 2) AS montoTotal,"+
			"'N' AS n1, "+
			"'N' AS n2 "+
			"FROM tb_bono b inner join tb_tipobono t on b.idTipBono = t.idTipBono "+
			"WHERE b.fec_fact = #{fecha} "+ 
			"AND t.descripImpresion = #{descripcion} "+
			"GROUP BY b.cod_personal, b.idTipBono "+
			"ORDER BY b.cod_personal, b.idTipBono")
	public List<Bono> listarxTipoBono( @Param("fecha")String fecha, @Param("descripcion") String descripcion)throws Exception;
	
	@Select("select * from t_bono where cod_ciclo = #{p_codCiclo}")
	public List<Bono> listarxIdCiclo(@Param("p_codCiclo")Integer codCiclo) throws Exception;
	
	@Select("select sum(monto) from t_bono where idusuario = #{idusuario} and periodo = #{periodo}")
	public BigDecimal obtenerMonto(@Param("idusuario") Integer idusuario,@Param("periodo") Date periodo)throws Exception;
	
	
	@Update("delete from t_bono where id_facturacion_cabecera = #{idFacturacionCabecera} and  periodo = #{periodo} ")
	public void eliminarBonosByIdFacturacionCabecera(@Param("idFacturacionCabecera") Integer idFacturacionCabecera,@Param("periodo") Date periodo)throws Exception;
	
	@Update("delete from t_bono where id_bono = #{idbono}")
	public void eliminarBono(@Param("idbono") Integer idbono)throws Exception;
}
