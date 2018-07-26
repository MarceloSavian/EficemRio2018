package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.TextView;

public class RunningActivity extends AppCompatActivity {

    private TextView lapsTextView;
    private TextView speedTextView;
    private Chronometer chronometer;

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
        //chronometer = (Chronometer) findViewById(R.id.timeTextView);

        //Pega informações da competição
        Bundle extras = getIntent().getExtras();
        Competicao competicao = new Competicao();
        competicao.recebeInformacoes(savedInstanceState,extras);

        SetLocation getLocation = new SetLocation();
        getLocation.setLocation(this, this,competicao);


    }

}
