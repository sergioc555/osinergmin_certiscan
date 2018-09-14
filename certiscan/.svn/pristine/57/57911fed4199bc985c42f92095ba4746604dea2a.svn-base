package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Oficina;

public interface OficinaMapper {

	@Select("select * from t_oficina e where e.id_oficina = #{p_id_oficina}")
	public Oficina findById(@Param("p_id_oficina") Integer codigoEstado) throws Exception;

	@Select("select * from t_oficina e where e.codigo_oficina = #{codigo} and e.id_negocio=#{id_negocio} LIMIT 1")
	public Oficina findByCodigoByNegocio(@Param("codigo") String codigo, @Param("id_negocio") Integer id_negocio) throws Exception;
	
	
	@Select("select * from t_oficina e where e.codigo_oficina = #{codigo} and e.id_negocio=#{id_negocio} and e.id_territorio = #{id_territorio} LIMIT 1")
	public Oficina findByCodigoByNegocio2(@Param("codigo") String codigo, @Param("id_negocio") Integer id_negocio,@Param("id_territorio") Integer id_territorio) throws Exception;

	@Select("SELECT o.id_oficina, o.nombre, o.direccion, o.encargado, o.departamento, o.provincia, o.estado, o.gerente, o.cargo_gerente, o.fecha_cumpleanos FROM t_oficina o  order by o.nombre")
	public List<Oficina> findAll() throws Exception;

	@Select("SELECT o.id_oficina, o.codigo_oficina, o.id_negocio, o.id_territorio, u.sid_ubigeo , o.nombre, o.direccion, o.encargado, o.departamento, o.provincia, u.sdistrito as distrito, t.descripcion as des_territorio , o.estado, o.gerente, o.cargo_gerente, o.fecha_cumpleanos, o.codigo_oficina " + " FROM t_oficina o LEFT JOIN  t_ubigeo u " + " ON o.sid_ubigeo = u.sid_ubigeo LEFT JOIN  t_territorio t on t.id_territorio= o.id_territorio " + " WHERE o.id_negocio=#{id_negocio} order by codigo_oficina")
	public List<Oficina> findByNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;

	public List<Oficina> findByIdTerritorios(@Param("descripcionTerritorios") List<String> descripcionTerritorios) throws Exception;

	@Select("SELECT o.id_oficina, o.codigo_oficina, o.id_negocio, o.id_territorio, u.sid_ubigeo , o.nombre, o.direccion, o.encargado, o.departamento, o.provincia, o.distrito, t.descripcion as des_territorio , o.estado, o.gerente, o.cargo_gerente, o.fecha_cumpleanos, o.codigo_oficina " + " FROM t_oficina o LEFT JOIN  t_ubigeo u " + " ON o.sid_ubigeo = u.sid_ubigeo LEFT JOIN  t_territorio t on t.id_territorio= o.id_territorio " + " WHERE o.id_negocio=#{id_negocio} order by codigo_oficina")
	public List<Oficina> find_ByNegocio(@Param("id_negocio") Integer id_negocio) throws Exception;

	public List<Oficina> listOficinaxNegocioORTodos(@Param("id_negocio") Integer id_negocio) throws Exception;

	@Insert("insert into t_oficina (codigo_oficina, nombre,direccion, encargado, departamento,provincia, distrito,sid_ubigeo, id_territorio, estado, gerente, cargo_gerente, fecha_cumpleanos, id_negocio) values (#{codigo_oficina}, #{nombre},#{direccion},#{encargado},#{departamento},#{provincia}, #{distrito},#{sid_ubigeo},#{id_territorio}, #{estado}, #{gerente}, #{cargo_gerente}, #{fecha_cumpleanos}, #{id_negocio})")
	public void crearOficina(Oficina oficina) throws Exception;

	@Update("update t_oficina set nombre = #{nombre}, direccion= #{direccion},encargado= #{encargado},departamento= #{departamento},provincia= #{provincia},distrito= #{distrito}, sid_ubigeo= #{sid_ubigeo},id_territorio= #{id_territorio},id_negocio= #{id_negocio},codigo_oficina= #{codigo_oficina}, estado = #{estado}, gerente = #{gerente}, cargo_gerente = #{cargo_gerente}, fecha_cumpleanos = #{fecha_cumpleanos} where id_oficina= #{id_oficina}")
	@Options(flushCache = true, useCache = true)
	public void actualizarOficina(Oficina tipooficina) throws Exception;

	@Update("update t_oficina set id_territorio= #{id_territorio} where id_oficina= #{id_oficina}")
	@Options(flushCache = true, useCache = true)
	public void actualizarOficinaPorTerritorio(Oficina oficina) throws Exception;

	@Delete("delete  from t_oficina  where id_oficina = #{id_oficina}")
	@Options(flushCache = true)
	public void eliminarOficina(@Param("id_oficina") Integer id_oficina) throws Exception;

	@Select("Select * from t_oficina where sid_ubigeo= #{sid_ubigeo}")
	public List<Oficina> obtenerOficinaxUbigeo(@Param("sid_ubigeo") Integer sid_ubigeo) throws Exception;

	@Select("SELECT o.id_oficina, o.codigo_oficina, o.id_negocio, o.id_territorio, o.nombre, o.direccion, o.encargado, o.departamento, o.provincia, o.distrito, t.descripcion as des_territorio , o.estado, o.gerente, o.cargo_gerente, o.fecha_cumpleanos, o.codigo_oficina " + " FROM t_oficina o  " + " INNER JOIN  t_territorio t on t.id_territorio= o.id_territorio " + " WHERE o.id_negocio=#{id_negocio} and t.codigo_territorio=#{codigoTerritorio} order by codigo_oficina")
	public List<Oficina> findByNegocioByTerritorio(@Param("id_negocio") Integer id_negocio, @Param("codigoTerritorio") String codigoTerritorio) throws Exception;

	@Select("Select * from t_oficina where id_negocio= #{id_negocio} and distrito=#{distrito} and" + " provincia=#{provincia} and departamento=#{departamento}")
	public List<Oficina> obtenerOficinaxUbigeoNegocioTerritorio(@Param("id_negocio") Integer id_negocio, @Param("distrito") String distrito, @Param("departamento") String departamento, @Param("provincia") String provincia) throws Exception;

	@Select("SELECT o.id_oficina, o.codigo_oficina, o.id_negocio, o.id_territorio, u.sid_ubigeo , o.nombre, o.direccion, o.encargado, o.departamento, o.provincia, o.distrito, t.descripcion as des_territorio , o.estado, o.gerente, o.cargo_gerente, o.fecha_cumpleanos, o.codigo_oficina " + " FROM t_oficina o LEFT JOIN  t_ubigeo u " + " ON o.sid_ubigeo = u.sid_ubigeo LEFT JOIN  t_territorio t on t.id_territorio= o.id_territorio " + " WHERE o.id_negocio=#{id_negocio} and o.departamento = #{departamento} order by codigo_oficina")
	public List<Oficina> find_ByNegocioByDepartamento(@Param("id_negocio") Integer id_negocio, @Param("departamento") String departamento) throws Exception;
	
	
	@Select("select * from t_oficina where id_negocio = #{id_negocio} order by nombre;")
	public List<Oficina> getByNegocioExclusion(@Param("id_negocio") Integer id_negocio) throws Exception;
	
	
	@Update("update t_oficina set estado_cobertura= #{estado_cobertura} where id_oficina= #{id_oficina}")
	@Options(flushCache = true, useCache = true)
	public void updateEstadoCobertura(Oficina oficina) throws Exception;
}
