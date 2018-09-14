package src.com.certicom.certiscan.utils;

public class BalanceItem {
	
	//valor fijo puede ser desde base de datos
	private Double tipoCambio= 0.0;
	
	// variable
	private Double precioTotalPEN = 0.0;
	
	private Double cuotaInicialPEN= 0.0;
	private Double finBBVAPEN= 0.0;
	
	private Double precioTotalUSD= 0.0;
	private Double cuotaInicialUSD= 0.0;
	private Double finBBVAUSD= 0.0;

	// variable
	private Double valorFijo1= 0.0;
	
	private Double valorFijo2= 0.0;
	
	// DESCRIPCION - INDICADORES
	
	private Double activoInicial= 0.0;
	private Double activoIcrem= 0.0;
	private Double activofinal= 0.0;
	
	private Double pasivoInicial= 0.0;
	private Double pasivoIcrem= 0.0;
	private Double pasivofinal= 0.0;

	private Double patrimonioInicial= 0.0;
	private Double patrimonioIcrem= 0.0;
	private Double patrimoniofinal= 0.0;
	

	private Double capSocialInicial= 0.0;
	private Double capSocialIcrem= 0.0;
	private Double capSocialfinal= 0.0;
	
	
	private Integer apalancamiento = 0;
	private Double riesgoNeto = 0.0;
	
	public Double getTipoCambio() {
		return tipoCambio;
	}
	
	public Double getPrecioTotalPEN() {
		 precioTotalPEN = getTipoCambio()*getPrecioTotalUSD() ;
		return precioTotalPEN ;
	}
	
	public Double getCuotaInicialPEN() {
		cuotaInicialPEN = getTipoCambio()*getCuotaInicialUSD();
		return cuotaInicialPEN ;
	}
	
	public Double getFinBBVAPEN() {
		finBBVAPEN = getFinBBVAUSD()*getTipoCambio();
		return finBBVAPEN ;
	}
	public Double getPrecioTotalUSD() {
		return precioTotalUSD;
	}
	
	public Double getCuotaInicialUSD() {
		 cuotaInicialUSD =  getPrecioTotalUSD()*getValorFijo1();
		return cuotaInicialUSD;
	}
	
	public Double getFinBBVAUSD() {
		 finBBVAUSD =  getPrecioTotalUSD()*getValorFijo2();
		return finBBVAUSD ;
	}
	
	public Double getValorFijo1() {
		return valorFijo1;
	}
	
	public Double getValorFijo2() {
		return valorFijo2;
	}
	
	// Detalles
	
	public Double getActivoInicial() {
		 activoInicial =  getPasivoInicial()+getPatrimonioInicial();
		return activoInicial ;
	}
	
	public Double getActivoIcrem() {
		 activoIcrem =  getPasivoIcrem()+getPatrimonioIcrem();
		return activoIcrem ;
	}
	public Double getActivofinal() {
		 activofinal = getPasivofinal()+getPatrimoniofinal();
		return activofinal ;
	}
	public Double getPasivoInicial() {
		return pasivoInicial;
	}
	public Double getPasivoIcrem() {
		 pasivoIcrem = getFinBBVAPEN();
		return pasivoIcrem ;
	}
	public Double getPasivofinal() {
		pasivofinal = getPasivoInicial()+getPasivoIcrem();
		return pasivofinal ;
	}
	public Double getPatrimonioInicial() {
		return patrimonioInicial;
	}
	public Double getPatrimonioIcrem() {
		return patrimonioIcrem;
	}
	public Double getPatrimoniofinal() {
		 patrimoniofinal = getPatrimonioInicial()+getPatrimonioIcrem();
		return patrimoniofinal ;
	}
	public Double getCapSocialInicial() {
		return capSocialInicial;
	}
	public Double getCapSocialIcrem() {
		return capSocialIcrem;
	}
	public Double getCapSocialfinal() {
		 capSocialfinal = getCapSocialInicial()+getCapSocialIcrem();
		return capSocialfinal ;
	}
	public Integer getApalancamiento() {
		 apalancamiento = (int) (getPasivofinal()/getActivofinal());
		return apalancamiento ;
	}
	public Double getRiesgoNeto() {
		return riesgoNeto;
	}
	
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public void setPrecioTotalPEN(Double precioTotalPEN) {
		this.precioTotalPEN = precioTotalPEN;
	}
	public void setCuotaInicialPEN(Double cuotaInicialPEN) {
		this.cuotaInicialPEN = cuotaInicialPEN;
	}
	public void setFinBBVAPEN(Double finBBVAPEN) {
		this.finBBVAPEN = finBBVAPEN;
	}
	public void setPrecioTotalUSD(Double precioTotalUSD) {
		this.precioTotalUSD = precioTotalUSD;
	}
	public void setCuotaInicialUSD(Double cuotaInicialUSD) {
		this.cuotaInicialUSD = cuotaInicialUSD;
	}
	public void setFinBBVAUSD(Double finBBVAUSD) {
		this.finBBVAUSD = finBBVAUSD;
	}
	public void setValorFijo1(Double valorFijo1) {
		this.valorFijo1 = valorFijo1;
	}
	public void setValorFijo2(Double valorFijo2) {
		this.valorFijo2 = valorFijo2;
	}
	public void setActivoInicial(Double activoInicial) {
		this.activoInicial = activoInicial;
	}
	public void setActivoIcrem(Double activoIcrem) {
		this.activoIcrem = activoIcrem;
	}
	public void setActivofinal(Double activofinal) {
		this.activofinal = activofinal;
	}
	public void setPasivoInicial(Double pasivoInicial) {
		this.pasivoInicial = pasivoInicial;
	}
	public void setPasivoIcrem(Double pasivoIcrem) {
		this.pasivoIcrem = pasivoIcrem;
	}
	public void setPasivofinal(Double pasivofinal) {
		this.pasivofinal = pasivofinal;
	}
	public void setPatrimonioInicial(Double patrimonioInicial) {
		this.patrimonioInicial = patrimonioInicial;
	}
	public void setPatrimonioIcrem(Double patrimonioIcrem) {
		this.patrimonioIcrem = patrimonioIcrem;
	}
	public void setPatrimoniofinal(Double patrimoniofinal) {
		this.patrimoniofinal = patrimoniofinal;
	}
	public void setCapSocialInicial(Double capSocialInicial) {
		this.capSocialInicial = capSocialInicial;
	}
	public void setCapSocialIcrem(Double capSocialIcrem) {
		this.capSocialIcrem = capSocialIcrem;
	}
	public void setCapSocialfinal(Double capSocialfinal) {
		this.capSocialfinal = capSocialfinal;
	}
	public void setApalancamiento(Integer apalancamiento) {
		this.apalancamiento = apalancamiento;
	}
	public void setRiesgoNeto(Double riesgoNeto) {
		this.riesgoNeto = riesgoNeto;
	}
	
	
	
	
	

	
	
	
	
	
	
	

}
