package com.certicom.certiscan.services;

import java.util.ArrayList;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.mapper.EstadosMapper;

public class EstadosServices implements EstadosMapper {

	EstadosMapper estadosMapper = (EstadosMapper) ServiceFinder.findBean("estadosMapper");

	@Override
	public Estados findById(Integer codigoEstados) throws Exception {
		return estadosMapper.findById(codigoEstados);
	}

	@Override
	public List<Estados> findAll() throws Exception {
		return estadosMapper.findAll();
	}

	@Override
	public List<Estados> listaEstadosActivo() throws Exception {
		return estadosMapper.listaEstadosActivo();
	}

	@Override
	public void crearEstados(Estados estados) throws Exception {
		estadosMapper.crearEstados(estados);
	}

	@Override
	public void actualizarEstados(Estados estados) throws Exception {
		estadosMapper.actualizarEstados(estados);
	}

	@Override
	public void actualizarEstadosBanco(Estados estados) throws Exception {
		estadosMapper.actualizarEstadosBanco(estados);
	}

	@Override
	public void eliminarEstados(Integer id_categoria_estado) throws Exception {
		estadosMapper.eliminarEstados(id_categoria_estado);
	}

	@Override
	public List<Estados> getxCategoria(Integer id_categoria_estado, Integer idProducto) throws Exception {
		return estadosMapper.getxCategoria(id_categoria_estado, idProducto);
	}

	@Override
	public List<Estados> getxCategoriadeBanco(Integer id_categoria_estado, Integer idProducto) throws Exception {
		return estadosMapper.getxCategoriadeBanco(id_categoria_estado, idProducto);
	}

	@Override
	public Estados buscarByEstadoProductoCategoria(Integer idProducto, String descripcion, Integer id_categoria_estado) throws Exception {
		return this.estadosMapper.buscarByEstadoProductoCategoria(idProducto, descripcion, id_categoria_estado);
	}

	@Override
	public Estados buscarByEstadoProductoDescCategoria(Integer idProducto, String descripcion, String descripcion_cat) throws Exception {
		return this.estadosMapper.buscarByEstadoProductoDescCategoria(idProducto, descripcion, descripcion_cat);
	}

	@Override
	public List<Estados> buscarEstadoRepetido(Estados estado) throws Exception {
		return this.estadosMapper.buscarEstadoRepetido(estado);
	}

	@Override
	public List<Estados> buscarIgual(Integer idProducto, String descripcion, Integer id_categoria_estado) throws Exception {
		return this.estadosMapper.buscarIgual(idProducto, descripcion, id_categoria_estado);
	}

	@Override
	public List<Estados> buscarByEstadoCategoria(Integer id_producto, Integer id_categoria_estado) throws Exception {
		return estadosMapper.buscarByEstadoCategoria(id_producto, id_categoria_estado);
	}

	@Override
	public Integer getIdEstadoByProduct(Integer id_producto) throws Exception {
		return estadosMapper.getIdEstadoByProduct(id_producto);
	}

	@Override
	public List<Estados> getxCategoriaFamilia(Integer id_categoria_estado, Integer idProducto) throws Exception {
		// TODO Auto-generated method stub
		return estadosMapper.getxCategoriaFamilia(id_categoria_estado, idProducto);
	}

	@Override
	public List<Estados> getxSubFamiliaxPadre(Integer id_estado) throws Exception {
		// TODO Auto-generated method stub
		return estadosMapper.getxSubFamiliaxPadre(id_estado);
	}

	@Override
	public List<Estados> buscarByProductoCategoria(Integer id_producto, String codigo_categoria) throws Exception {
		return estadosMapper.buscarByProductoCategoria(id_producto, codigo_categoria);
	}

	@Override
	public List<Estados> buscarByProductoCategoriaFamilia(Integer id_producto, String codigo_categoria, String codigo_estado_padre) throws Exception {
		return estadosMapper.buscarByProductoCategoriaFamilia(id_producto, codigo_categoria, codigo_estado_padre);
	}

	public List<Estados> buscarByProductoCategoriaTree(Integer id_producto, String codigo_categoria) throws Exception {
		List<Estados> result = new ArrayList<Estados>();
		for (Estados fam : buscarByProductoCategoria(id_producto, codigo_categoria)) {
			fam.setSubfamilias(buscarByProductoCategoriaFamilia(id_producto, codigo_categoria, fam.getCodigo_estado()));
			result.add(fam);
		}
		return result;
	}

}
