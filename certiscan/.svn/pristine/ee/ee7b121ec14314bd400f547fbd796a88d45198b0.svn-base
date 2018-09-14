package com.certicom.certiscan.services;

import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.SubProducto;
import com.certicom.certiscan.mapper.SubProductoMapper;

public class SubProductoServices implements SubProductoMapper{
	
	SubProductoMapper subProductoMapper = (SubProductoMapper)ServiceFinder.findBean("subProductoMapper");
	
/*	@Override
	public List<SubProducto> listSubProducto(id_producto) throws Exception {		
		return subProductoMapper.listSubProducto(id_producto);
	}*/
	
	@Override
	public List<SubProducto> listSubProducto(Integer id_producto) throws Exception {		
		return subProductoMapper.listSubProducto(id_producto);
	}

	@Override
	public Integer listSubProductoxProducto(Integer id_producto)
			throws Exception {		
		return subProductoMapper.listSubProductoxProducto(id_producto);
	}

	@Override
	public void insertSubProducto(SubProducto subProducto) throws Exception {
		subProductoMapper.insertSubProducto(subProducto);
		
	}

	@Override
	public void deleteSubProducto(Integer id_sub_producto) throws Exception {
		subProductoMapper.deleteSubProducto(id_sub_producto);
		
	}

	@Override
	public void updateSubProducto(SubProducto subProducto) throws Exception {
		subProductoMapper.updateSubProducto(subProducto);
		
	}





}
