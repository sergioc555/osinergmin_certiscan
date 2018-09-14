package com.certicom.certiscan.gps;

import java.math.BigDecimal;

public class GeoKmCalculator {
    
    public static double DEFAULT_ELEVATION = 0.0;

    private GeodeticCalculator geoCalc = new GeodeticCalculator();
    
    private GlobalPosition posA;
    
    private GlobalPosition posB;
    
    Ellipsoid reference = Ellipsoid.WGS84;
    
    public void setPositionB(String latitud, String longitud, String elevation) {
        this.posB = new GlobalPosition(Double.parseDouble(latitud), Double.parseDouble(longitud), Double.parseDouble(elevation));
    }
    
    public void setPositionA(String latitud, String longitud, String elevation) {
        this.posA = new GlobalPosition(Double.parseDouble(latitud), Double.parseDouble(longitud), Double.parseDouble(elevation));
    }
    
    public void setPositionB(String latitud, String longitud) {
        setPositionB(Double.parseDouble(latitud), Double.parseDouble(longitud), DEFAULT_ELEVATION);
    }
    
    public void setPositionA(String latitud, String longitud) {
        setPositionA(Double.parseDouble(latitud), Double.parseDouble(longitud), DEFAULT_ELEVATION);
    }
    
    public void setPositionB(double latitud, double longitud, double elevation) {
        this.posB = new GlobalPosition(latitud, longitud, elevation);
    }
    
    public void setPositionA(double latitud, double longitud, double elevation) {
        this.posA = new GlobalPosition(latitud, longitud, elevation);
    }
    
    public void setPositionB(double latitud, double longitud) {
        setPositionB(latitud, longitud, DEFAULT_ELEVATION);
    }
    
    public void setPositionA(double latitud, double longitud) {
        setPositionA(latitud, longitud, DEFAULT_ELEVATION);
    }
    
    public void setReference(Ellipsoid reference) {
        this.reference = reference;
    }
    
    public double getDistance() {
        return geoCalc.calculateGeodeticCurve(this.reference, this.posB, this.posA).getEllipsoidalDistance();
    }
    
    public double getDistanceKm() {
        return geoCalc.calculateGeodeticCurve(this.reference, this.posB, this.posA).getEllipsoidalDistance() / 1000;
    }
    
    public boolean isDentroRango(double extra) {
        if (getDistance() > extra) {
            return false;
        } else {
            return true;
        }
    }
    
    public String toString() {
        String ret = "";
        int entero = (int) (getDistance() / 1000);
        double diference = 0.0;
        if (entero > 0) {
            diference = getDistance() % (entero * 1000);
        }
        if (getDistance() > 1000.0 && diference > 0.0) {
            ret = String.valueOf(entero);
            if (entero > 2) {
                ret += " kilometros";
            } else {
                ret += " kilometro";
            }
            ret += " y " 
                    + new BigDecimal(String.valueOf(diference)).setScale(0, BigDecimal.ROUND_HALF_UP).toString() 
                    + " metros.";
        } else {
            if (entero == 1) {
                ret = String.valueOf(entero) + " kilometro.";
            } else {
                ret = new BigDecimal(String.valueOf(getDistance())).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
                ret += " metros.";
            }
        }
        
        return ret;
    }
}
