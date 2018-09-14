package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Cliente;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.services.ClienteServices;
import com.certicom.certiscan.services.MenuServices;

@ManagedBean(name="clienteMB")
@ViewScoped
public class ClienteMB extends GenericBeans implements Serializable{

	private Cliente cliente;
	private List<Cliente> listaCliente;
	private Boolean editar;
	private ClienteServices clienteService;
	private RequestContext context;
	private Log log;
	private LogMB logmb;
	private MenuServices menuServices;
	
	public ClienteMB(){
		;
	}
	
	@PostConstruct
	public void inicia(){
		this.cliente = new Cliente();
		this.editar = Boolean.FALSE;
		clienteService = new ClienteServices();
		this.context = RequestContext.getCurrentInstance();
		this.menuServices = new MenuServices();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:cliente");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			this.listaCliente = this.clienteService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iniciaNuevoCliente(){
		this.editar = Boolean.FALSE;
		this.cliente = new Cliente();
	}
	
	public void iniciaEditarCliente(Cliente clie){
		//System.out.println("editando cliente");
		this.cliente = clie;
		this.editar = Boolean.TRUE;
	}
	
	public void guardarCliente(){
		//System.out.println("insertando nuevo cliente");
		try {
			
			if(this.editar){
				//System.out.println("editando cliente");
				log.setAccion("UPDATE");
	            log.setDescripcion("Se actualiza el cliente: " + this.cliente.getRazonsocial_cliente());
	            logmb.insertarLog(log);
				this.clienteService.actualizarCliente(this.cliente);
			}else{
				//System.out.println("creando cliente");
				log.setAccion("INSERT");
	            log.setDescripcion("Se inserta el cliente : " + this.cliente.getRazonsocial_cliente());
	            logmb.insertarLog(log);
				this.clienteService.crearCliente(this.cliente);
			}
			
			FacesUtils.showFacesMessage("Cliente guardado correctamente",Constante.INFORMACION);
			this.listaCliente = this.clienteService.findAll();
			this.context.update("grlGeneral"); 
		} catch (Exception e) {
			//System.out.println("Error al insertar/editar cliente"+ e.getMessage());
			e.printStackTrace();
		}
		this.editar = Boolean.FALSE;
	}
	
	public void eliminarCliente(){
		try {
			
			log.setAccion("DELETE");
			log.setDescripcion("Se eliminó el cliente: " + this.cliente.getRazonsocial_cliente());
			logmb.insertarLog(log);
			
			this.clienteService.eliminarCliente(this.cliente.getRuc_cliente());
			this.listaCliente = this.clienteService.findAll();
			FacesUtils.showFacesMessage("Cliente eliminado",Constante.INFORMACION);
			this.context.update("grlGeneral");
		} catch (Exception e) {
			//System.out.println("Error eliminando cliente" + e.getMessage() );
			e.printStackTrace();
		}
	}
	

	
	/*################-------getters ys setters-------####################*/
	
	public Cliente getCliente() {
		return cliente;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

}
