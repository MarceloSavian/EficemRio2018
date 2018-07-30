package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.TimerTask;

public class RunningActivity extends AppCompatActivity {

    private TextView lapsTextView;
    private TextView speedTextView;
    private TextView timeTextView;
    private TextView regressiveTimeTextView;
    private TextView lapTimeTextView;

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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_activity);

        //TextView
        speedTextView = (TextView) findViewById(R.id.speedTextView);
        lapsTextView = (TextView) findViewById(R.id.lapsTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        regressiveTimeTextView = (TextView) findViewById(R.id.regressiveTimeTextView);
        lapTimeTextView = (TextView) findViewById(R.id.lapTimeTextView);


        //Pega informações da competição
        Bundle extras = getIntent().getExtras();
        Competicao competicao = new Competicao();
        competicao.recebeInformacoes(savedInstanceState,extras);

        Chronometer1 chronometer = new Chronometer1();
        chronometer.iniciaCronometro(this,competicao);

        SetLocation getLocation = new SetLocation();
        getLocation.setLocation(this, this,competicao,chronometer);

    }




}
