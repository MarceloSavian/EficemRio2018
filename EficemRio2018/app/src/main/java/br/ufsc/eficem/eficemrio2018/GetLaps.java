package br.ufsc.eficem.eficemrio2018;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GetLaps {

    boolean isFirstLocationFinded = false;
    private double totalDistance = 0;
    private double latitude1;
    private double latitude2;
    private double longitude1;
    private double longitude2;
    private float[] distance = new float[1];


    public void setLatitude1(double latitude1) {
        this.latitude1 = latitude1;
    }

    public void setLatitude2(double latitude2) {
        this.latitude2 = latitude2;
    }

    public void setLongitude1(double longitude1) {
        this.longitude1 = longitude1;
    }

    public void setLongitude2(double longitude2) {
        this.longitude2 = longitude2;
    }


    public Double getDistance(){

        if (!isFirstLocationFinded){
            if (totalDistance == 0){
                isFirstLocationFinded = true;
                return totalDistance;
            }
            else {
                Location.distanceBetween(latitude2,longitude2,latitude1,longitude1,distance);
                totalDistance = distance[0] + totalDistance;
                isFirstLocationFinded = true;
                return totalDistance;
            }
        }
        else {
            Location.distanceBetween(latitude1,longitude1,latitude2,longitude2,distance);
            totalDistance = distance[0] + totalDistance;
            isFirstLocationFinded = false;
            return totalDistance;
        }
    }

    public int getLaps(double Distance,Competicao competicao) {

        int i;
        int TotalLaps = Integer.valueOf(competicao.getNumeroDeVoltas());
        int TotalDistance = Integer.valueOf(competicao.getTamanhoDaPista());


        if (Distance < TotalDistance) {
            i = 0;
            return i;
        } else{
            for (i = 2; i < TotalLaps; i++) {
                if (Distance < (TotalDistance * i)) {
                    i = i-1;
                    break;
                }
            }
            return i;
        }
    }

}
