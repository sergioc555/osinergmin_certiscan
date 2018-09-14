package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.LogSBS;
import com.certicom.certiscan.mapper.LogSBSMapper;

public class LogSBSServices implements LogSBSMapper{
	
	LogSBSMapper logSBSMapper = (LogSBSMapper)ServiceFinder.findBean("logSBSMapper");

	@Override
	public LogSBS findById(Integer codigoLogSBS) throws Exception {
		// TODO Auto-generated method stub
		return logSBSMapper.findById(codigoLogSBS);
	}

	@Override
	public List<LogSBS> findAll() throws Exception {
		// TODO Auto-generated method stub
		return logSBSMapper.findAll();
	}

	@Override
	public void crearLogSBS(LogSBS logSBS) throws Exception {
		// TODO Auto-generated method stub
		logSBSMapper.crearLogSBS(logSBS);
	}

	@Override
	public void actualizarLogSBS(LogSBS logSBS) throws Exception {
		// TODO Auto-generated method stub
		logSBSMapper.actualizarLogSBS(logSBS);
	}

	@Override
	public void eliminarLogSBS(Integer codigoLogSBS) throws Exception {
		// TODO Auto-generated method stub
		logSBSMapper.eliminarLogSBS(codigoLogSBS);
	}

	@Override
	public List<LogSBS> findByFechaIniFin(Date fec_ini, Date fec_fin)
			throws Exception {
		// TODO Auto-generated method stub
		return logSBSMapper.findByFechaIniFin(fec_ini, fec_fin);
	}

	



}
