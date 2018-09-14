package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.ContactoEmpresa;

public interface ContactoEmpresaMapper {

	@Select("select * from t_contacto_empresa where id = #{id}")
	public ContactoEmpresa findById(@Param("id") Integer id) throws Exception;

	@Select("select * from t_contacto_empresa")
	public List<ContactoEmpresa> findAll() throws Exception;

	@Select("select * from t_contacto_empresa where usuario_registro = #{usuario_registro}")
	public List<ContactoEmpresa> findbyUsuario(@Param("usuario_registro") Integer usuario_registro) throws Exception;

	@Select("select * from t_contacto_empresa where id_expediente_producto = #{id_expediente_producto}")
	public List<ContactoEmpresa> findbyExpedienteProducto(@Param("id_expediente_producto") Integer id_expediente_producto) throws Exception;

	@Insert("insert into t_contacto_empresa (apaterno, amaterno, nombres,cargo,telefono,usuario_registro,id_expediente_producto," + "cumpleanos,correo) " + "values (#{apaterno},#{amaterno},#{nombres},#{cargo},#{telefono},#{usuario_registro}, #{id_expediente_producto}," + "#{cumpleanos},#{correo})")
	public void crearContactoEmpresa(ContactoEmpresa contactoEmpresa) throws Exception;

	@Update("update t_contacto_empresa set apaterno = #{apaterno}, " + "amaterno = #{amaterno},  nombres = #{nombres}," + "cargo = #{cargo},telefono = #{telefono}, cumpleanos = #{cumpleanos}, correo = #{correo}, " + "id_expediente_producto=#{id_expediente_producto} where id= #{id}")
	@Options(flushCache = true, useCache = true)
	public void actualizarContactoEmpresa(ContactoEmpresa contactoEmpresa) throws Exception;

	@Delete("delete  from t_contacto_empresa  where id = #{id}")
	@Options(flushCache = true)
	public void eliminarContactoEmpresa(@Param("id") Integer id) throws Exception;

}
