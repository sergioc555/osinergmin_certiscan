package com.certicom.certiscan.mapper; 

import java.util.List; 

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.SubProducto;
import com.certicom.certiscan.domain.Usuario;

public interface SubProductoMapper{ 
	
	//lista para subProducto por Producto
	@Select("select t_sub_producto.*, t_sub_producto.codigo_banco as codigoBanco from t_sub_producto where id_producto =#{id_producto} order by descripcion asc")
	//@Select("select sp.descripcion from t_sub_producto sp inner join t_producto p on sp.id_producto = p.id_producto order by sp.descripcion asc")
	public List<SubProducto> listSubProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
/*	@Select("select from t_sub_producto where id_producto =  #{id_producto}")
	public List<SubProducto> listSubProducto(@Param("id_producto") Integer id_producto) throws Exception;*/
		
	//No permite eliminar un SubProducto Asociado a un Producto devuelve la cantidad de Producto asociado
	@Select("select count(*) from t_sub_producto where id_producto =  #{id_producto} order by descripcion asc")
	public Integer listSubProductoxProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	@Insert("insert into t_sub_producto (id_producto, descripcion, codigo_banco, estado) values(#{id_producto}, #{descripcion}, #{codigoBanco}, #{estado})")
	public void insertSubProducto(SubProducto subProducto) throws Exception;
	
	@Delete("delete from t_sub_producto where id_sub_producto = #{id_sub_producto}")
	@Options(useCache = true, flushCache= true)
	public void deleteSubProducto(@Param("id_sub_producto") Integer id_sub_producto) throws Exception;
	
	@Update("update t_sub_producto set id_producto= #{id_producto}, descripcion = #{descripcion}, codigo_banco = #{codigoBanco}, estado = #{estado} where id_sub_producto = #{id_sub_producto}")
	@Options(useCache = true, flushCache= true)
	public void updateSubProducto(SubProducto subProducto) throws Exception;	
	

} 
