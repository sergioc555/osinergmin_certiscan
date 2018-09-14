package com.certicom.certiscan.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import src.com.certicom.certiscan.utils.VGeneric;

@ViewScoped
@ManagedBean(name = "lecturasMB")
public class LecturasMB {

	private String PATH_FILE = "";
	private List<VGeneric> listaArchivos = new ArrayList<VGeneric>();

	@PostConstruct
	public void inicia() {
		VGeneric item1 = new VGeneric();
		item1.setVal01("Prevención de lavado de Activos");
		item1.setVal10("resources/lecturas_pdf/Prevencion_del_lavado_de_Activos.pdf");
		
		VGeneric item2 = new VGeneric();
		item2.setVal01("Préstamos de Consumo Fuvex Mayo");
		item2.setVal10("resources/lecturas_pdf/Prestamos_de_Consumo_Fuvex_Mayo.pdf");
		
		
		listaArchivos.add(item1);
		listaArchivos.add(item2);
		
		
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

}
