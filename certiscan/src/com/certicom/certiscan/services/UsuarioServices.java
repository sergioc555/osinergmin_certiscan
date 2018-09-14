package com.certicom.certiscan.services;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.PlanillaPersonal;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.mapper.UsuarioMapper;

import src.com.certicom.certiscan.utils.ExpedienteFilter;

public class UsuarioServices implements UsuarioMapper{

	UsuarioMapper usuarioMapper=(UsuarioMapper)ServiceFinder.findBean("usuarioMapper");

	@Override
	public List<Usuario> buscarPorLoginClave(Usuario usuario) {
		return usuarioMapper.buscarPorLoginClave(usuario);
	}

	@Override
	public void insertUsuario(Usuario usuario) {
		usuarioMapper.insertUsuario(usuario);
	}

	@Override
	public List<Usuario> getlistaUsuario() {
		return usuarioMapper.getlistaUsuario();
	}
	
	@Override
	public List<Usuario> getlistaEjecutivo() {		
		return usuarioMapper.getlistaEjecutivo();
	}
	
	@Override
	public List<Usuario> getlistaEjecutivoByNegocio(Integer idNegocio) {		
		return usuarioMapper.getlistaEjecutivoByNegocio(idNegocio);
	}	

	@Override
	public List<Usuario> buscarusuario(Usuario usuario) {
		return usuarioMapper.buscarusuario(usuario);
	}

	@Override
	public Usuario buscarPorId(int idusuario) {
		return usuarioMapper.buscarPorId(idusuario);
	}


	@Override
	public void actualizarEstado(Usuario usuario) throws Exception {
		usuarioMapper.actualizarEstado(usuario);
	}

	@Override
	public void actualizar(Usuario usuario) {
		usuarioMapper.actualizar(usuario);
	}
	
	@Override
	public void actualizarLogin(Usuario usuario) {
		usuarioMapper.actualizarLogin(usuario);
	}

	@Override
	public void actualizarPassword(Usuario usuario) {
		usuarioMapper.actualizarPassword(usuario);
	}
	
	
	@Override
	public void actualizarFechaAcceso(Usuario usuario) {
		usuarioMapper.actualizarFechaAcceso(usuario);
	}

	@Override
    public List<Usuario> buscarPorDNI(String dni) {
        return usuarioMapper.buscarPorDNI(dni);
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioMapper.buscarPorNombre(nombre);
    }

    @Override
    public List<Usuario> buscarPorApellido(String apellido) {
        return usuarioMapper.buscarPorApellido(apellido); 
    }

	@Override
	public Usuario buscarPorLogin(String string) throws Exception {
		return usuarioMapper.buscarPorLogin(string);
	}
	

	
	@Override
	public List<Usuario> getlistaUsuario_Perfil(Usuario usuario) {
		return usuarioMapper.getlistaUsuario_Perfil(usuario); 
	}
	
	@Override
	public List<Usuario> getlistaUsuario_byCentroAtencion(Integer id_centro_atencion) {
		return usuarioMapper.getlistaUsuario_byCentroAtencion(id_centro_atencion); 
	}

	@Override
	public void deleteUsuario(Integer idusuario) throws Exception {
		usuarioMapper.deleteUsuario(idusuario);
	}

	@Override
	public List<Usuario> listarUsuariosXPerfilProceso(String proceso)
			throws Exception {
		return usuarioMapper.listarUsuariosXPerfilProceso(proceso);
	}

	@Override
	public List<Usuario> obtenerUsuariosxOficina(Integer idoficina)
			throws Exception {
		return usuarioMapper.obtenerUsuariosxOficina(idoficina);
	}

	@Override
	public void resetearPassword(Usuario usuario) throws Exception {
		usuarioMapper.resetearPassword(usuario);
	}

	@Override
	public void actualizarImagen(Usuario usuario) throws Exception {
		usuarioMapper.actualizarImagen(usuario);
	}

	@Override
	public List<Usuario> listUsuario() throws Exception {		
		return usuarioMapper.listUsuario();
	}

	@Override
	public List<Usuario> listUsuarioActivos() throws Exception {
		return usuarioMapper.listUsuarioActivos();
	}


	@Override
	public void insertaDatosEjecutivoPlanilla(Usuario usuario) throws Exception {
		usuarioMapper.insertaDatosEjecutivoPlanilla(usuario);
		
	}

	@Override
	public List<Usuario> listaSupervisor(Integer id_cargo)
			throws Exception {		
		return usuarioMapper.listaSupervisor(id_cargo);
	}

	@Override
	public List<Usuario> listaCoordinador(Integer id_cargo)
			throws Exception {		
		return usuarioMapper.listaCoordinador(id_cargo);
	}

	@Override
	public void actualizarCargo(Integer idusuario, Integer id_cargo)
			throws Exception {
		usuarioMapper.actualizarCargo(idusuario, id_cargo);
		
	}

	@Override
	public void guardaPlanillaPersonal(PlanillaPersonal planillaPersonal)
			throws Exception {
		usuarioMapper.guardaPlanillaPersonal(planillaPersonal);
		
	}

	@Override
	public List<Usuario> listaPromotorxNegocio(Integer id_negocio,
			Integer id_supervisor) throws Exception {
		return usuarioMapper.listaPromotorxNegocio(id_negocio, id_supervisor);
	}

	@Override
	public Usuario buscarPorDni(String string) throws Exception {
		return usuarioMapper.buscarPorDni(string);
	}

	@Override
	public List<Usuario> listaSupervisorxIdNegocio(Integer id_negocio)
			throws Exception {
		return usuarioMapper.listaSupervisorxIdNegocio(id_negocio);
	}

	@Override
	public List<Usuario> listaCoordinadorxIdNegocio(Integer id_negocio)
			throws Exception {
		return usuarioMapper.listaCoordinadorxIdNegocio(id_negocio);
	}

	@Override
	public List<Usuario> listarEjecutivosxNegocio(Integer id_negocio) throws Exception {
		return usuarioMapper.listarEjecutivosxNegocio(id_negocio);
	}

	@Override
	public List<Usuario> listaPromotorCoordinadorxNegocio(Integer id_negocio,
			Integer id_supervisor) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaPromotorCoordinadorxNegocio(id_negocio, id_supervisor);
	}

	@Override
	public Integer getEjecutivoxNegocio(Integer id_negocio) throws Exception {
		return usuarioMapper.getEjecutivoxNegocio(id_negocio);
	}

	@Override
	public Usuario getUsuario_byIdUsuario(Integer idusuario) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuario_byIdUsuario(idusuario);
	}

	@Override
	public int verificarDniCarnetRepetidos(String dniUsuario) throws Exception {
		return usuarioMapper.verificarDniCarnetRepetidos(dniUsuario);
	}

	@Override
	public int verificarloginRepetido(String login) throws Exception {
		return usuarioMapper.verificarloginRepetido(login);
	}

	@Override
	public List<Usuario> listarUsuarioReasignar(Integer idusuario)
			throws Exception {
		return usuarioMapper.listarUsuarioReasignar(idusuario);
	}

	@Override
	public List<Usuario> listUsuarioActivosNoCesados() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listUsuarioActivosNoCesados();
	}

	@Override
	public List<Usuario> listaPromotorBackxNegocio(Integer id_negocio,
			Integer id_supervisor) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaPromotorBackxNegocio(id_negocio, id_supervisor);
	}

	@Override
	public List<Usuario> listaPromotorCoordinadorxNegocioReasignar(
			Integer id_negocio, Integer id_supervisor, Integer idusuario)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaPromotorCoordinadorxNegocioReasignar(id_negocio, id_supervisor, idusuario);
	}
	
	@Override
	public List<Usuario> listaCoordinadorxNegocioReasignar(
			Integer id_negocio, Integer id_supervisor)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listaCoordinadorxNegocioReasignar(id_negocio, id_supervisor);
	}

	@Override
	public Integer getLastUser() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getLastUser();
	}

	@Override
	public Usuario getUsuariobyCodigoBanco(String codigo_banco,int id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariobyCodigoBanco(codigo_banco,id_negocio);
	}

	@Override
	public Usuario getUsuariobyCodigoBancoEje(String codigo_banco,int id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariobyCodigoBancoEje(codigo_banco,id_negocio);
	}

	@Override
	public List<Usuario> getUsuariosByFacturacionNegocio(ExpedienteFilter filter)
			throws Exception {
		return usuarioMapper.getUsuariosByFacturacionNegocio(filter);
	}

	@Override
	public List<Usuario> getBackOfficeByNegocio(Integer id_negocio)
			throws Exception {
		return usuarioMapper.getBackOfficeByNegocio(id_negocio);
	}

	@Override
	public Usuario getUsuarioByIddPlanilla(Integer idusuario) throws Exception {
		return usuarioMapper.getUsuarioByIddPlanilla(idusuario);
	}

	@Override
	public List<Usuario> getUsuariosByCargoAndNegocio(Integer id_cargo,
			Integer id_negocio) throws Exception {
		return usuarioMapper.getUsuariosByCargoAndNegocio(id_cargo, id_negocio);
	}

	@Override
	public List<Usuario> getUsuariosByNegocio(Integer id_negocio)
			throws Exception {
		return usuarioMapper.getUsuariosByNegocio(id_negocio);
	}

	
	public String getCorreoByIdUser(int id) throws Exception{
		return usuarioMapper.getCorreoByIdUser(id);
	}

	@Override
	public List<Usuario> listUsuarioDepuracionByPeriodoProductoTrabajable(
			Date periodo, Integer id_producto) {
		// TODO Auto-generated method stub
		return usuarioMapper.listUsuarioDepuracionByPeriodoProductoTrabajable(periodo, id_producto);
	}

	@Override
	public Integer getIdUsuarioAsignadoByExpediente(Integer expediente_id) {
		// TODO Auto-generated method stub
		return usuarioMapper.getIdUsuarioAsignadoByExpediente(expediente_id);
	}

	@Override
	public List<Usuario> getUsuarioCoordinadorByNegocio(Integer id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuarioCoordinadorByNegocio(id_negocio);
	}

	@Override
	public List<Usuario> getALLUsuarios() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getALLUsuarios();
	}

	@Override
	public List<Usuario> getUsuariosNRByCargo(Integer id_cargo, int id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosNRByCargo(id_cargo, id_negocio);
	}

	@Override
	public List<Usuario> getUsuariosByFacturacionNegocio2(
			ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosByFacturacionNegocio2(filter);
	}

	@Override
	public Usuario getUsuariobyCodigoBancoDNI(String codigo_banco,
			int id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariobyCodigoBancoDNI(codigo_banco, id_negocio);
	}

	@Override
	public Usuario getUsuariobyCodigoBancoEjeDNI(String codigo_banco,
			int id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariobyCodigoBancoEjeDNI(codigo_banco, id_negocio);
	}
	
	
	public boolean actualizarDatosUsuariosPlanilla(List<Usuario> users){
		Boolean resultado = Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =(SqlSessionFactory) ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		UsuarioMapper daoObj = (UsuarioMapper)sqlSession.getMapper(UsuarioMapper.class);
		try {
			for (Usuario u : users) {
				daoObj.actualizarDatosRptaPlanilla(u);
			}
			resultado = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			resultado = Boolean.FALSE;
		} finally{
			sqlSession.close();
		}
		return resultado;
	}

	@Override
	public void actualizarDatosRptaPlanilla(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		usuarioMapper.actualizarDatosRptaPlanilla(usuario);
	}

	@Override
	public List<Usuario> listUsuarioEnSala(Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listUsuarioEnSala(id_producto);
	}

	@Override
	public List<Usuario> listUsuarioEnSalaAsignados(Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listUsuarioEnSalaAsignados(id_producto);
	}

	@Override
	public List<Usuario> getSupervisoresxTerritoriosByNegocioPeriodo(
			int id_negocio, Date periodo, String territorio, List<String> territorios) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getSupervisoresxTerritoriosByNegocioPeriodo(id_negocio, periodo, territorio, territorios);
	}

	@Override
	public List<Usuario> listUsuarioEnSalaActivos() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listUsuarioEnSalaActivos();
	}

	@Override
	public List<Usuario> listEnSala(Integer id_producto, Integer id_negocio, Date periodo)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSala(id_producto, id_negocio, periodo);
	}

	@Override
	public List<Usuario> listEnSalaAsignados(Integer id_producto,
			Integer id_negocio, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaAsignados(id_producto, id_negocio, periodo);
	}

	@Override
	public List<Usuario> listEnSalaOnline(Integer id_negocio, Date periodo)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaOnline(id_negocio, periodo);
	}

	@Override
	public List<Usuario> listEnSalaAsignadosOnline(Integer id_negocio,
			Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaAsignadosOnline(id_negocio, periodo);
	}

	@Override
	public List<Usuario> getUsuariosByNegocioNR(int id_negocio) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosByNegocioNR(id_negocio);
	}

	@Override
	public List<Usuario> listEnSalaOnlineByProducto(Integer idproducto_padre,
			Integer id_negocio, Date periodo) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaOnlineByProducto(idproducto_padre, id_negocio, periodo);
	}

	@Override
	public List<Usuario> listEnSalaAsignadosOnlineByProducto(
			Integer idproducto_padre, Integer id_negocio, Date periodo)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaAsignadosOnlineByProducto(idproducto_padre, id_negocio, periodo);
	}

	@Override
	public List<Usuario> getUsuariosCuartiles(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosCuartiles(filter);
	}

	@Override
	public List<Usuario> getUsuariosCuartilesxProducto(ExpedienteFilter filter)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosCuartilesxProducto(filter);
	}

	@Override
	public List<Usuario> getUsuariosCuartilesxProductoxAnio(
			ExpedienteFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosCuartilesxProductoxAnio(filter);
	}

	@Override
	public Usuario buscarPorCorreo(String correo) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.buscarPorCorreo(correo);
	}

	@Override
	public List<Usuario> listEnSalaAsignadosOnlineByProductoPH(
			Integer idproducto_padre, Integer id_negocio, Date periodo)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaAsignadosOnlineByProductoPH(idproducto_padre, id_negocio, periodo);
	}

	@Override
	public List<Usuario> listEnSalaOnlineByProductoPeriodos(
			Integer idproducto_padre, Integer id_negocio, List<Date> periodos, String valor_dotacion)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaOnlineByProductoPeriodos(idproducto_padre, id_negocio, periodos, valor_dotacion);
	}

	@Override
	public List<Usuario> listEnSalaAsignadosOnlineByProductoPeriodos(
			Integer idproducto_padre, Integer id_negocio, List<Date> periodos,String valor_dotacion)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaAsignadosOnlineByProductoPeriodos(idproducto_padre, id_negocio, periodos,valor_dotacion);
	}

	@Override
	public List<Usuario> listarUsuariosGpsPosActual(Integer id_producto)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listarUsuariosGpsPosActual(id_producto);
	}

	@Override
	public List<Usuario> getVendedoresPYMES() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getVendedoresPYMES();
	}

	@Override
	public List<Usuario> getUsuarioGPSbyProducto(Integer id_producto)
			throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuarioGPSbyProducto(id_producto);
	}

	@Override
	public List<Usuario> rptBackLog_ListUsersByTerritorio(String territorio,
			Integer id_negocio, Date periodo) {
		// TODO Auto-generated method stub
		return usuarioMapper.rptBackLog_ListUsersByTerritorio(territorio, id_negocio, periodo);
	}

	@Override
	public List<Usuario> rptBackLog_ListSupByTerritorio(String territorio,
			Integer id_negocio, Date periodo, List<String> territorios) {
		// TODO Auto-generated method stub
		return usuarioMapper.rptBackLog_ListSupByTerritorio(territorio, id_negocio, periodo, territorios);
	}

	@Override
	public List<String> getDistinctConcesionarioPYMES() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.getDistinctConcesionarioPYMES();
	}

	@Override
	public List<Usuario> listEnSalaAsignadosOnlineByProductoDOT(
			Integer idproducto_padre, Integer id_negocio, Date periodo,
			Integer valor_dotacion) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaAsignadosOnlineByProductoDOT(idproducto_padre, id_negocio, periodo, valor_dotacion);
	}

	@Override
	public List<Usuario> listEnSalaOnlineByProductoDOT(
			Integer idproducto_padre, Integer id_negocio, Date periodo,
			Integer valor_dotacion) throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listEnSalaOnlineByProductoDOT(idproducto_padre, id_negocio, periodo, valor_dotacion);
	}

	@Override
	public List<Usuario> listarUsuarios() throws Exception {
		// TODO Auto-generated method stub
		return usuarioMapper.listarUsuarios();
	}

	@Override
	public List<Usuario> getListaUsuarios() {
		// TODO Auto-generated method stub
		return usuarioMapper.getListaUsuarios();
	}

	@Override
	public List<Usuario> getUsuariosByFilters(Integer idoficina) {
		// TODO Auto-generated method stub
		return usuarioMapper.getUsuariosByFilters(idoficina);
	}
	
	
}
