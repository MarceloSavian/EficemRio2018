package br.ufsc.eficem.eficemrio2018;

import android.location.Location;

public class GetLaps {

    int hue= 0;
    boolean isFirstLocationFinded = false;
    private double totalDistance = 0;
    private double latitude1;
    private double latitude2;
    private double longitude1;
    private double longitude2;
    private float[] distance = new float[1];
    boolean[] setLapTime = new boolean[100];;


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

    public int getLaps(double Distance, Competicao competicao, Chronometer1 chronometer1) {

        int i;
        int TotalLaps = Integer.valueOf(competicao.getNumeroDeVoltas());
        int TotalDistance = Integer.valueOf(competicao.getTamanhoDaPista());

        if (Distance < TotalDistance) {
            for (i=0;i<TotalLaps;i++){
                setLapTime[i] = true;
            }
            i = 0;
            return i;
        } else{
            for (i = 2; i < TotalLaps; i++) {
                if (Distance < (TotalDistance * i)) {
                    i = i-1;
                    if(setLapTime[i]){
                        chronometer1.setSomaTempo(chronometer1.getTempoBanco());
                        setLapTime[i] = false;
                    }
                    break;
                }
            }
            return i;
        }
    }
}
