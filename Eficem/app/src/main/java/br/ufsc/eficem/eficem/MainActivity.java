package br.ufsc.eficem.eficem;

import android.app.ProgressDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static double totalDistance = 0, eficiencia = 0, velocidade, velocidadeIdeal, x, distanciaTotal = 11, tempoTotal = 28, tempoProva = 0;
    private double[] latlon,latlonNew;
    private float[] distance = new float[1];
    private Integer volta = -1, numeroDeVoltas = 14;
    private String file = "arquivo";
    private boolean isFirstLocationFinded;
    private static boolean isRunning;
    private static Handler handler;
    private static long initialTime;
    private static final long MILLIS_IN_SEC = 1000L;
    private static final int SECS_IN_MIN = 60;
    private Button btnVoltas;
    private static TextView txtTempo;
    private TextView txtVelocidade, txtVolta, txtEficiencia, txtVelocidadeIdeal;

    //PERMISSÕES E CONFIGS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedirPermissoes();
        configurarServico();
        //usar nomes em ingles
//usar butterknife nas dependecias pra tirar o findviewbyid
        txtEficiencia = (TextView) findViewById(R.id.txtEficiencia);
        txtVelocidade = (TextView) findViewById(R.id.txtVelocidade); //usar nome completo. txtVelocidade = textViewVelocidade
        txtVolta = (TextView) findViewById(R.id.txtVolta);
        txtTempo = (TextView)findViewById(R.id.txtTempo);
        btnVoltas = (Button) findViewById(R.id.btnVoltas);
        txtVelocidadeIdeal = (TextView) findViewById(R.id.txtVelocidadeIdeal);
        handler = new Handler();

        btnVoltas.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                volta = volta + 1;
                if(!isRunning) {
                    isRunning = true;
                    initialTime = System.currentTimeMillis();
                    handler.postDelayed(runnable, MILLIS_IN_SEC);
                }else if (volta >= 15){
                    handler.removeCallbacks(runnable);
                    txtTempo.setText("00:00");
                }
                adicionaVolta();
            }
        });
    }

    private final static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long seconds = (System.currentTimeMillis() - initialTime) / MILLIS_IN_SEC;
                txtTempo.setText(String.format("%02d:%02d", seconds / SECS_IN_MIN, seconds % SECS_IN_MIN));
                handler.postDelayed(runnable, MILLIS_IN_SEC);
                tempoProva = (double) seconds;
            }
        }
    };

    private void pedirPermissoes() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {

        }

        }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configurarServico();
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void configurarServico(){
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {

                    velocidade = atualizar(location);
                    if (!isFirstLocationFinded){
                        if (totalDistance == 0){
                            latlon = new double[]{location.getLatitude(), location.getLongitude()};
                            isFirstLocationFinded = true;
                        }else {
                            latlon = new double []{location.getLatitude(), location.getLongitude()};
                            Location.distanceBetween(latlonNew[0],latlonNew[1],latlon[0],latlon[1],distance);
                            totalDistance = (distance[0]/1000) + totalDistance;
                            isFirstLocationFinded = true;
                        }
                    }else {
                        latlonNew = new double[]{location.getLatitude(), location.getLongitude()};
                        Location.distanceBetween(latlon[0],latlon[1],latlonNew[0],latlonNew[1],distance);

                        totalDistance = (distance[0]/1000) + totalDistance;
                        isFirstLocationFinded = false;
                    }
                    eficiencia = Eficiencia(totalDistance, velocidade);
                    imprimeEficiencia(eficiencia);

                    if (volta>=0){
                        try {
                            salvaArquivo(tempoProva,velocidade,totalDistance,latlon[0],latlon[1],eficiencia,volta,velocidadeIdeal);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch(SecurityException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //CALCULOS

    private double Eficiencia (Double distanciaProva, Double velocidade){

        velocidadeIdeal = ((distanciaTotal - distanciaProva)/ (((tempoTotal - (tempoProva/60))/60)))*1.4;

        x = velocidadeIdeal - velocidade;
        eficiencia = Math.pow(Math.E, x*x*-0.1);
        eficiencia = eficiencia*100;

        imprimeVelocidadeIdeal(velocidadeIdeal);

        return eficiencia;
    }

    public void adicionaVolta (){
        if (volta < numeroDeVoltas){
            txtVolta.setText(volta.toString());
        }else if (volta == numeroDeVoltas){
            txtVolta.setText("Ultima Volta");
        }else if (volta > numeroDeVoltas){
            txtVolta.setText("A prova acabou!!");
        }
    }

    public double atualizar(Location location) {

        Double velocidade = (location.getSpeed())*3.6;
        txtVelocidade.setText(velocidade.toString());
        return velocidade;
    }

    public void imprimeEficiencia (Double eficiencia){
        txtEficiencia.setText(eficiencia.toString());
    }

    private void  imprimeVelocidadeIdeal (Double velocidadeIdeal){
        txtVelocidadeIdeal.setText(velocidadeIdeal.toString());
    }

    private double calculaDistancia(double lat1, double lng1, double lat2, double lng2) {
        //double earthRadius = 3958.75;//miles
        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist * 1000; //em metros
    }

    //CRIAÇÃO DE ARQUIVOS PARA ARMAZENAMENTO DE DADOS

    public void salvaArquivo (Double Tempo, Double Velocidade, Double Distancia, Double Latitude, Double Longitude, Double Eficiencia, Integer Volta, Double VelocidadeIdeal) throws IOException {
        String diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + "arquivo" + "/";
        File diretorio = new File(diretorioApp);
        File FileApp = new File(diretorioApp,"arquivo.txt");
        diretorio.mkdirs();

        //Cria o arquivo
        FileApp.getParentFile().mkdirs();

        //Abre o arquivo
        FileOutputStream fosExt = null;
        fosExt = new FileOutputStream(FileApp);

        //Escreve no arquivo
        String data = Tempo.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = Volta.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = Velocidade.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = Distancia.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = Latitude.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = Longitude.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = Eficiencia.toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = VelocidadeIdeal.toString();
        fosExt.write(data.getBytes());
        ProgressDialog dialog = null;


        //Fechamento do arquivo
        fosExt.close();
    }

}
