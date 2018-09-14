package com.certicom.certiscan.domain;

public class Stats{
     
    private String season;
     
    private double lteautoriz;
    private double rgoactual;
    private double rgopropop;
    private String fech_vto;
    
    private int win;
     
    private int loss;
 
    public Stats() {}
 
    public Stats(String season, int win, int loss) {
        this.season = season;
        this.win = win;
        this.loss = loss;
    }
 
    public int getWin() {
        return win;
    }
 
    public void setWin(int win) {
        this.win = win;
    }
 
    public int getLoss() {
        return loss;
    }
 
    public void setLoss(int loss) {
        this.loss = loss;
    }
 
    public String getSeason() {
        return season;
    }
 
    public void setSeason(String season) {
        this.season = season;
    }

	

	public double getLteautoriz() {
		return lteautoriz;
	}

	public void setLteautoriz(double lteautoriz) {
		this.lteautoriz = lteautoriz;
	}

	public double getRgoactual() {
		return rgoactual;
	}

	public void setRgoactual(double rgoactual) {
		this.rgoactual = rgoactual;
	}

	public double getRgopropop() {
		return rgopropop;
	}

	public void setRgopropop(double rgopropop) {
		this.rgopropop = rgopropop;
	}

	public String getFech_vto() {
		return fech_vto;
	}

	public void setFech_vto(String fech_vto) {
		this.fech_vto = fech_vto;
	}
	
	
    
    
    
}