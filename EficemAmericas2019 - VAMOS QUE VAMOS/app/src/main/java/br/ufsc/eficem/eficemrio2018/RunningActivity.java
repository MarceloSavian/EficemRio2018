package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.TimerTask;

public class RunningActivity extends AppCompatActivity {

    private TextView lapsTextView;
    private TextView speedTextView;
    private TextView timeTextView;
    private TextView regressiveTimeTextView;
    private TextView lapTimeTextView;
    private TextView[] lapTime1TextView = new TextView[20];

    public TextView getLapTimeTextView() {
        return lapTimeTextView;
    }

    public TextView getRegressiveTimeTextView() {
        return regressiveTimeTextView;
    }

    public TextView getTimeTextView() {
        return timeTextView;
    }

    public TextView getLapsTextView() {
        return lapsTextView;
    }

    public TextView getSpeedTextView() {
        return speedTextView;
    }

    public TextView[] getLapTime1TextView() {
        return lapTime1TextView;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //TextView
        speedTextView = (TextView) findViewById(R.id.speedTextView);
        lapsTextView = (TextView) findViewById(R.id.lapsTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        regressiveTimeTextView = (TextView) findViewById(R.id.regressiveTimeTextView);
        lapTimeTextView = (TextView) findViewById(R.id.lapTimeTextView);



        lapTime1TextView[0] = (TextView) findViewById(R.id.lapTime1TextView);
        lapTime1TextView[1] = (TextView) findViewById(R.id.lapTime2TextView);
        lapTime1TextView[2] = (TextView) findViewById(R.id.lapTime3TextView);
        lapTime1TextView[3] = (TextView) findViewById(R.id.lapTime4TextView);
        lapTime1TextView[4] = (TextView) findViewById(R.id.lapTime5TextView);
        lapTime1TextView[5] = (TextView) findViewById(R.id.lapTime6TextView);
        lapTime1TextView[6] = (TextView) findViewById(R.id.lapTime7TextView);
        lapTime1TextView[7] = (TextView) findViewById(R.id.lapTime8TextView);
        lapTime1TextView[8] = (TextView) findViewById(R.id.lapTime9TextView);
        lapTime1TextView[9] = (TextView) findViewById(R.id.lapTime10TextView);
        lapTime1TextView[10] = (TextView) findViewById(R.id.lapTime11TextView);
        lapTime1TextView[11] = (TextView) findViewById(R.id.lapTime12TextView);
        lapTime1TextView[12] = (TextView) findViewById(R.id.lapTime13TextView);
        lapTime1TextView[13] = (TextView) findViewById(R.id.lapTime14TextView);
        lapTime1TextView[14] = (TextView) findViewById(R.id.lapTime15TextView);
        lapTime1TextView[15] = (TextView) findViewById(R.id.lapTime16TextView);
        lapTime1TextView[16] = (TextView) findViewById(R.id.lapTime17TextView);
        lapTime1TextView[17] = (TextView) findViewById(R.id.lapTime18TextView);
        lapTime1TextView[18] = (TextView) findViewById(R.id.lapTime19TextView);
        lapTime1TextView[19] = (TextView) findViewById(R.id.lapTime20TextView);


        //Para aumentar o número de voltas na tela para n
        //      ->Iniciar vetor lapTime1TextView[n],
        //      ->Fazer a funcao acima até lapTime1TextView[n]
        //      ->Em running_activity.xml
        //              *criar lapTimeNTextView e lapNTextView seguindo os modelos já existentes.

        //Pega informações da competição
        Bundle extras = getIntent().getExtras();
        Competicao competicao = new Competicao();
        competicao.recebeInformacoes(savedInstanceState,extras);

        Carro carro = new Carro();

        Chronometer1 chronometer = new Chronometer1();
        chronometer.iniciaCronometro(this,competicao, carro);

        SetLocation getLocation = new SetLocation();
        getLocation.setLocation(this, this,competicao,chronometer,carro);

    }




}
