package br.ufsc.eficem.eficemrio2018;

import android.widget.TextView;

public class Carro {

    private Double latitude;
    private Double longitude;
    private Double altitude;
    private CharSequence time;
    private Double distance;
    private int lap;
    private Float speed;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public CharSequence getTime() {
        return time;
    }

    public void setTime(CharSequence time) {
        this.time = time;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public void showInformation(RunningActivity runningActivity){

        TextView speedTextView = runningActivity.getSpeedTextView();
        TextView lapTextView = runningActivity.getLapsTextView();

        speedTextView.setText(String.valueOf(distance));
        lapTextView.setText(String.valueOf(lap));

    }

}
