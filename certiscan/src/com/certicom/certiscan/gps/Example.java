/* Geodesy by Mike Gavaghan
 * 
 * http://www.gavaghan.org/blog/free-source-code/geodesy-library-vincentys-formula-java/
 * 
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 * BitCoin tips graciously accepted at 1FB63FYQMy7hpC2ANVhZ5mSgAZEtY1aVLf
 */
package com.certicom.certiscan.gps;

import com.certicom.certiscan.gps.Ellipsoid;
import com.certicom.certiscan.gps.GeoKmCalculator;
import com.certicom.certiscan.gps.GeodeticCalculator;
import com.certicom.certiscan.gps.GeodeticCurve;
import com.certicom.certiscan.gps.GeodeticMeasurement;
import com.certicom.certiscan.gps.GlobalCoordinates;
import com.certicom.certiscan.gps.GlobalPosition;

public class Example {
    /**
     * Calculate the destination if we start at: Lincoln Memorial in Washington,
     * D.C --> 38.8892N, 77.04978W and travel at 51.7679 degrees for 6179.016136
     * kilometers
     * 
     * WGS84 reference ellipsoid
     */
    static void TwoDimensionalDirectCalculation() {
        // instantiate the calculator
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        // select a reference elllipsoid
        Ellipsoid reference = Ellipsoid.WGS84;

        // set Lincoln Memorial coordinates
        GlobalCoordinates lincolnMemorial;
        lincolnMemorial = new GlobalCoordinates(38.88922, -77.04978);

        // set the direction and distance
        double startBearing = 51.7679;
        double distance = 6179016.13586;

        // find the destination
        double[] endBearing = new double[1];
        GlobalCoordinates dest = geoCalc.calculateEndingGlobalCoordinates(
                reference, lincolnMemorial, startBearing, distance, endBearing);

        System.out
                .println("Travel from Lincoln Memorial at 51.767921 deg for 6179.016 km");
        System.out.printf("   Destination: %1.4f%s", dest.getLatitude(),
                (dest.getLatitude() > 0) ? "N" : "S");
        System.out.printf(", %1.4f%s\n", dest.getLongitude(),
                (dest.getLongitude() > 0) ? "E" : "W");
        System.out.printf("   End Bearing: %1.2f degrees\n", endBearing[0]);
    }

    /**
     * Calculate the two-dimensional path from
     * 
     * Lincoln Memorial in Washington, D.C --> 38.8892N, 77.04978W
     * 
     * to
     * 
     * Eiffel Tower in Paris --> 48.85889N, 2.29583E
     * 
     * using WGS84 reference ellipsoid
     */
    static void TwoDimensionalCalculation() {
        // instantiate the calculator
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        // select a reference elllipsoid
        Ellipsoid reference = Ellipsoid.WGS84;

        // set Lincoln Memorial coordinates
        GlobalCoordinates lincolnMemorial;
        lincolnMemorial = new GlobalCoordinates(38.88922, -77.04978);

        // set Eiffel Tower coordinates
        GlobalCoordinates eiffelTower;
        eiffelTower = new GlobalCoordinates(48.85889, 2.29583);

        // calculate the geodetic curve
        GeodeticCurve geoCurve = geoCalc.calculateGeodeticCurve(reference,
                lincolnMemorial, eiffelTower);
        double ellipseKilometers = geoCurve.getEllipsoidalDistance() / 1000.0;
        double ellipseMiles = ellipseKilometers * 0.621371192;

        System.out
                .println("2-D path from Lincoln Memorial to Eiffel Tower using WGS84");
        System.out.printf(
                "   Ellipsoidal Distance: %1.2f kilometers (%1.2f miles)\n",
                ellipseKilometers, ellipseMiles);
        System.out.printf("   Azimuth:              %1.2f degrees\n",
                geoCurve.getAzimuth());
        System.out.printf("   Reverse Azimuth:      %1.2f degrees\n",
                geoCurve.getReverseAzimuth());
    }

    /**
     * Calculate the three-dimensional path from
     * 
     * Pike's Peak in Colorado --> 38.840511N, 105.0445896W, 4301 meters
     * 
     * to
     * 
     * Alcatraz Island --> 37.826389N, 122.4225W, sea level
     * 
     * using WGS84 reference ellipsoid
     */
    static void ThreeDimensionalCalculation() {
        // instantiate the calculator
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        // select a reference elllipsoid
        Ellipsoid reference = Ellipsoid.WGS84;

        // set Pike's Peak position
        GlobalPosition pikesPeak;
        pikesPeak = new GlobalPosition(38.840511, -105.0445896, 4301.0);

        // set Alcatraz Island coordinates
        GlobalPosition alcatrazIsland;
        alcatrazIsland = new GlobalPosition(37.826389, -122.4225, 0.0);

        // calculate the geodetic measurement
        GeodeticMeasurement geoMeasurement;
        double p2pKilometers;
        double p2pMiles;
        double elevChangeMeters;
        double elevChangeFeet;

        geoMeasurement = geoCalc.calculateGeodeticMeasurement(reference,
                pikesPeak, alcatrazIsland);
        p2pKilometers = geoMeasurement.getPointToPointDistance() / 1000.0;
        p2pMiles = p2pKilometers * 0.621371192;
        elevChangeMeters = geoMeasurement.getElevationChange();
        elevChangeFeet = elevChangeMeters * 3.2808399;

        System.out
                .println("3-D path from Pike's Peak to Alcatraz Island using WGS84");
        System.out.printf(
                "   Point-to-Point Distance: %1.2f kilometers (%1.2f miles)\n",
                p2pKilometers, p2pMiles);
        System.out.printf(
                "   Elevation change:        %1.1f meters (%1.1f} feet)\n",
                elevChangeMeters, elevChangeFeet);
        System.out.printf("   Azimuth:                 %1.2f degrees\n",
                geoMeasurement.getAzimuth());
        System.out.printf("   Reverse Azimuth:         %1.2f degrees\n",
                geoMeasurement.getReverseAzimuth());
    }
    
    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371; // kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    static public void main(String[] args) {
//         TwoDimensionalDirectCalculation();
//        
//         System.out.println();
//        
//         TwoDimensionalCalculation();
//        
//         System.out.println();
//        
//         ThreeDimensionalCalculation();
        
         System.out.println();
        double latitud = -12.098506;
        double longitud = -77.017695;

        double latitud2 = -12.098905;
        double longitud2 = -77.017159;

        double latitud3 = -12.098064;
        double longitud3 = -77.01727;

        double latitud4 = -12.098082;
        double longitud4 = -77.018091;

        double latitud5 = -12.098294;
        double longitud5 = -77.018527;
        
        double latitud6 = -12.110359;
        double longitud6 = -77.025836;

        GeodeticCalculator geoCalc = new GeodeticCalculator();

        Ellipsoid reference = Ellipsoid.WGS84;

        GlobalPosition pointA = new GlobalPosition(latitud, longitud, 0.0); // Point A

        GlobalPosition userPos = new GlobalPosition(latitud2, longitud2, 0.0); // Point B
        GlobalPosition userPos2 = new GlobalPosition(latitud3, longitud3, 0.0); // Point C
        GlobalPosition userPos3 = new GlobalPosition(latitud4, longitud4, 0.0); // Point D
        GlobalPosition userPos4 = new GlobalPosition(latitud5, longitud5, 0.0); // Point E
        GlobalPosition userPos5 = new GlobalPosition(latitud6, longitud6, 0.0); // Point E

        double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); // Distance between Point A and Point B
        double distance2 = geoCalc.calculateGeodeticCurve(reference, userPos2, pointA).getEllipsoidalDistance(); // Distance between Point A and Point C
        double distance3 = geoCalc.calculateGeodeticCurve(reference, userPos3, pointA).getEllipsoidalDistance(); // Distance between Point A and Point D
        double distance4 = geoCalc.calculateGeodeticCurve(reference, userPos4, pointA).getEllipsoidalDistance(); // Distance between Point A and Point E

//        System.out.println(distance);

        GeoKmCalculator geo = new GeoKmCalculator();
        geo.setPositionA(latitud, longitud, 0.0);
        geo.setPositionB(latitud6, longitud6, 0.0);
        System.out.println(geo.getDistance());
        System.out.println(geo.getDistanceKm());
        System.out.println(geo.toString());

    }
}
