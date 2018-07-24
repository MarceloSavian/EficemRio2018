package br.ufsc.eficem.eficemrio2018;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GetLaps {

    boolean isFirstLocationFinded;
    private double totalDistance = 0;
    private double[] latlon,latlonNew;
    private float[] distance = new float[1];

    public void getDistance(final Context context, final TextView LapTextView, final int TotalLaps, final int TotalDistance){
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    if (!isFirstLocationFinded){
                        if (totalDistance == 0){
                            latlon = new double[]{location.getLatitude(), location.getLongitude()};
                            isFirstLocationFinded = true;
                        }else {
                            latlon = new double []{location.getLatitude(), location.getLongitude()};
                            Location.distanceBetween(latlonNew[0],latlonNew[1],latlon[0],latlon[1],distance);
                            totalDistance = distance[0] + totalDistance;
                            isFirstLocationFinded = true;
                        }
                    }else {
                        latlonNew = new double[]{location.getLatitude(), location.getLongitude()};
                        Location.distanceBetween(latlon[0],latlon[1],latlonNew[0],latlonNew[1],distance);

                        totalDistance = distance[0] + totalDistance;
                        isFirstLocationFinded = false;
                    }
                    ShowLaps(totalDistance,LapTextView,TotalLaps, TotalDistance);
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

    private void ShowLaps(double Distance, TextView LapTextView, int TotalLaps, int TotalDistance) {

        int i;

        if (Distance < TotalDistance) {
            LapTextView.setText("0");
        } else{
            for (i = 2; i < TotalLaps; i++) {
                if (Distance < (TotalDistance * i)) {
                    LapTextView.setText(String.valueOf((i-1)));
                    break;
                }
            }

        }
    }

}
