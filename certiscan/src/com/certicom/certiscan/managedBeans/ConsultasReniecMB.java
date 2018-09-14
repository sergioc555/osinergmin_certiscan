package com.certicom.certiscan.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.jdbc.dao.ReniecLogic;

import src.com.certicom.certiscan.utils.VGeneric;

@ViewScoped
@ManagedBean(name = "consultaReniecMB")
public class ConsultasReniecMB {

	private String PATH_FILE = "";
	private List<VGeneric> listaArchivos = new ArrayList<VGeneric>();
	private String numdoc;
	@PostConstruct
	public void inicia() {
		VGeneric item1 = new VGeneric();
		item1.setVal01("Prevención de lavado de Activos");
		item1.setVal10("resources/lecturas_pdf/Prevencion_del_lavado_de_Activos.pdf");
		
		VGeneric item2 = new VGeneric();
		item2.setVal01("Préstamos de Consumo Fuvex Mayo");
		item2.setVal10("resources/lecturas_pdf/Prestamos_de_Consumo_Fuvex_Mayo.pdf");
		this.numdoc = "";
		
		listaArchivos.add(item1);
		listaArchivos.add(item2);
		
		
	}
	
	public void consultarReniec(){
		String rutaImagen = null;
		RequestContext context = RequestContext.getCurrentInstance();  
		System.out.println("valor de entrada: "+this.numdoc);
		try {
			rutaImagen = ReniecLogic.getRutaImagenByDni(this.numdoc);
			if(rutaImagen != null && !rutaImagen.trim().isEmpty()){
				System.out.println("ruta imagen: "+rutaImagen);
				  context.addCallbackParam("esValido", Boolean.TRUE);
				  context.addCallbackParam("nrodocumento", this.numdoc);
			} else{
				context.addCallbackParam("esValido", Boolean.FALSE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addCallbackParam("esValido", Boolean.FALSE);
		}
	}

	public void abrirArchivo(VGeneric item) {
		this.PATH_FILE = item.getVal10();
	}

	public void cerrarArchivo(VGeneric item) {
		this.PATH_FILE = "";
	}

	public List<VGeneric> getListaArchivos() {
		return listaArchivos;
	}

	public String getPATH_FILE() {
		return PATH_FILE;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

}
