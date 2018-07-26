package br.ufsc.eficem.eficemrio2018;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class SetLocation {

    Boolean isFirstLocationFinded = true;

    public void setLocation(final Context context, final RunningActivity runningActivity, final Competicao competicao){

        final Carro carro = new Carro();
        final GetLaps getLaps = new GetLaps();

        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {

                public void onLocationChanged(Location location) {

                    //Insere informações
                    carro.setLatitude(location.getLatitude());
                    carro.setAltitude(location.getAltitude());
                    carro.setLongitude(location.getLongitude());
                    carro.setSpeed(location.getSpeed());

                    //Insere distancia percorrida
                    if(isFirstLocationFinded){
                        getLaps.setLatitude1(carro.getLatitude());
                        getLaps.setLongitude1(carro.getLongitude());
                        carro.setDistance(getLaps.getDistance());
                        isFirstLocationFinded = false;
                    }else{
                        getLaps.setLatitude2(carro.getLatitude());
                        getLaps.setLongitude2(carro.getLongitude());
                        carro.setDistance(getLaps.getDistance());
                        isFirstLocationFinded = true;
                    }
                    carro.setLap(getLaps.getLaps(carro.getDistance(), competicao));
                    carro.showInformation(runningActivity);

                }

                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch(SecurityException ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
