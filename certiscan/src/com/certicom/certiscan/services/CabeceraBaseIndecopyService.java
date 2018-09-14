package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.CabeceraBaseIndecopy;
import com.certicom.certiscan.mapper.CabeceraBaseIndecopyMapper;

@Service("cabeceraBaseIndecopyService")
public class CabeceraBaseIndecopyService implements CabeceraBaseIndecopyMapper{

	CabeceraBaseIndecopyMapper cabeceraBaseMapper = (CabeceraBaseIndecopyMapper)ServiceFinder.findBean("cabeceraBaseIndecopyMapper");
	
	
	@Override
	public List<CabeceraBaseIndecopy> findAll() throws Exception {
		// TODO Auto-generated method stub
		return cabeceraBaseMapper.findAll();
	}

	@Override
	public CabeceraBaseIndecopy findByUltimo() throws Exception {
		// TODO Auto-generated method stub
		return cabeceraBaseMapper.findByUltimo();
	}
	
	@Override
	public CabeceraBaseIndecopy findByPeriodo(Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return cabeceraBaseMapper.findByPeriodo(periodo);
	}
	
	@Override
	public void crearCabeceraBaseIndecopy(CabeceraBaseIndecopy cabeceraBaseIndecopy) throws Exception {
		// TODO Auto-generated method stub
		cabeceraBaseMapper.crearCabeceraBaseIndecopy(cabeceraBaseIndecopy);
	}
	
	
	@Override
	public void eliminarCabeceraBaseIndecopy(Integer idcabeceraindecopy) throws Exception {
		// TODO Auto-generated method stub
		cabeceraBaseMapper.eliminarCabeceraBaseIndecopy(idcabeceraindecopy);
	}

	@Override
	public List<CabeceraBaseIndecopy> findByPAGINATOR(Integer first,
			Integer pageSize, Map<String, Object> filters, String sortField,
			String sortOrder) throws Exception {
		// TODO Auto-generated method stub
		if(sortOrder!=null){
			 if(sortOrder.equals("ASCENDING"))
				 sortOrder="ASC";
			 else
				 sortOrder="DESC";
		}
		return cabeceraBaseMapper.findByPAGINATOR(first, pageSize, filters, sortField, sortOrder);
	}

	@Override
	public Integer getCountPAGINATOR(Map<String, Object> filters)
			throws Exception {
		// TODO Auto-generated method stub
		return cabeceraBaseMapper.getCountPAGINATOR(filters);
	}

}
