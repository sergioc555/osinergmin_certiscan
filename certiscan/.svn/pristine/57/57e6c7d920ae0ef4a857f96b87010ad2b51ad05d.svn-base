package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.Cliente;

public interface ClienteMapper {

	/**
	 * @Desc: listar todos los clientes
	 * @return : una lista de clientes
	 * @throws Exception
	 */
	@Select("select * from t_cliente c order by c.razonsocial_cliente asc")
	public List<Cliente> findAll() throws Exception;
	
	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select * from t_cliente c where c.ruc_cliente = #{p_rucCliente}")
	public Cliente findById(@Param("p_rucCliente") String clienteRuc) throws Exception;
	
	/**
	 * @Desc: elimnar un cliente enviandole su ruc
	 * @param clienteRuc: ruc del cliente
	 * @throws Exception
	 */
	@Delete("delete  from t_cliente  where ruc_cliente = #{p_rucCliente}")
	@Options(flushCache=true)
	public void eliminarCliente(@Param("p_rucCliente") String clienteRuc) throws Exception;
	
	
	/**
	 * @Desc: inserta un cliente, los parametros debe ser exatamente iguales en nombre que los atributos de la clase
	 * para poder pasar el objeto, de lo contrario habra que pasar parametro por parametro
	 * @param cliente: objeto cliente
	 * @return: void
	 * @throws Exception
	 */
	@Insert("insert into t_cliente (ruc_cliente, razonsocial_cliente,direccion_cliente,contacto_cliente,telefono_cliente) values (#{ruc_cliente},#{razonsocial_cliente},#{direccion_cliente},#{direccion_cliente},#{telefono_cliente})")
	public void crearCliente(Cliente cliente) throws Exception;
	
	
	
	/**
	 * @Desc: actualiza cliente, igual los parametros deben ser exavmtente iguales a los atributos de la clase cliente
	 * @return: void
	 * @throws Exception
	 */
	@Update("update t_cliente set ruc_cliente= #{ruc_cliente}, razonsocial_cliente=#{razonsocial_cliente},direccion_cliente=#{direccion_cliente} ,contacto_cliente=#{direccion_cliente},telefono_cliente=#{telefono_cliente} where ruc_cliente= #{ruc_cliente}")
	//@Update("update t_cliente set razonsocial_cliente=#{razonsocial_cliente},direccion_cliente=#{direccion_cliente} ,contacto_cliente=#{direccion_cliente},telefono_cliente=#{telefono_cliente}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCliente(Cliente cliente) throws Exception;
	
	
	

	
	
}
