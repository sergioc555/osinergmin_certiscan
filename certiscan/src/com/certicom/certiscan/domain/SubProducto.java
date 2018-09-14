package com.certicom.certiscan.domain;


public class SubProducto{
	
    
    private Integer id_sub_producto;
    private Integer id_producto;
    private String descripcion;
    private boolean estado; 
    private String codigoBanco;
    //transient
    private Boolean seleccionado = Boolean.FALSE;
    
    public SubProducto(){
    	
    }


	public Integer getId_sub_producto() {
		return id_sub_producto;
	}


	public void setId_sub_producto(Integer id_sub_producto) {
		this.id_sub_producto = id_sub_producto;
	}





	public Integer getId_producto() {
		return id_producto;
	}


	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public Boolean getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	public String getCodigoBanco() {
		return codigoBanco;
	}


	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
    
    

    
    
}
