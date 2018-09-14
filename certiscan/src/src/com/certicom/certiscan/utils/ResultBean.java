package src.com.certicom.certiscan.utils;

public class ResultBean {
	
	private boolean resultado;
	private Integer id_expediente_documento;
	
	private String nombre_archivo;
	private Integer nro_hojas;
	private String nro_expediente;
	
	private Integer nro_archivos_agrupados;
	
	public boolean isResultado() {
		return resultado;
	}
	
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public Integer getId_expediente_documento() {
		return id_expediente_documento;
	}

	public void setId_expediente_documento(Integer id_expediente_documento) {
		this.id_expediente_documento = id_expediente_documento;
	}

	public String getNombre_archivo() {
		return nombre_archivo;
	}

	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}

	public Integer getNro_hojas() {
		return nro_hojas;
	}

	public void setNro_hojas(Integer nro_hojas) {
		this.nro_hojas = nro_hojas;
	}

	public String getNro_expediente() {
		return nro_expediente;
	}

	public void setNro_expediente(String nro_expediente) {
		this.nro_expediente = nro_expediente;
	}

	public Integer getNro_archivos_agrupados() {
		return nro_archivos_agrupados;
	}

	public void setNro_archivos_agrupados(Integer nro_archivos_agrupados) {
		this.nro_archivos_agrupados = nro_archivos_agrupados;
	}

	
	
	
}
