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
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Test;
import com.certicom.certiscan.services.TestServices;

@ManagedBean(name="testMB")
@ViewScoped
public class TestMB extends GenericBeans implements Serializable{

	private List<Test> listaTest;
	private Test testSelec;	
	private Boolean editar;
	private Test test;
	
	private TestServices testServices;	
	
	public TestMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.testSelec = new Test();	
		this.editar = Boolean.FALSE;
		this.test = new Test();
		this.testServices = new TestServices();
		
		try {
			this.listaTest = this.testServices.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void guardarTest(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.testSelec.setDescripcion(this.testSelec.getDescripcion().trim().toUpperCase());
			this.testSelec.setEstado(this.testSelec.isEstado());
			
			if(this.editar) {
				this.testServices.actualizarTest(this.testSelec);
				FacesUtils.showFacesMessage("Test ha sido actualizado", 3);
			}else{
				this.testSelec.setEstado(Boolean.TRUE);
				this.testServices.crearTest(this.testSelec);
				FacesUtils.showFacesMessage("Test ha sido creado", 3);
			}
			
			this.testSelec = new Test();
			this.editar = Boolean.FALSE;
			
			this.listaTest = this.testServices.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cambiarTest(Test test){
		
		   if(test.isEstado()){
			   test.setEstado(Boolean.FALSE);
		   }else{
			   test.setEstado(Boolean.TRUE);
		   }
		   
		   try {
			   this.testServices.actualizarTest(test);
			   FacesUtils.showFacesMessage("Test  modificado correctamente",Constante.INFORMACION);
			   this.listaTest = this.testServices.findAll();
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void nuevoTest(){
		this.testSelec = new Test();
		this.editar = Boolean.FALSE;
	}

	public void editarTest(Test test){
		this.testSelec = test;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarTest(Test test){
		this.testSelec = test;
	}
	
	
	public void confirmaEliminar(){
		try {
		
			this.testServices.eliminarTest(this.testSelec.getCod_estado());
			FacesUtils.showFacesMessage("Test ha sido eliminado", 3);
			
			this.listaTest = this.testServices.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

	
	public List<Test> getlistaTest() {
		return listaTest;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public void setlistaTest(List<Test> listaTest) {
		this.listaTest = listaTest;
	}

	public Test gettestSelec() {
		return testSelec;
	}

	public void settestSelec(Test testSelec) {
		this.testSelec = testSelec;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

}
