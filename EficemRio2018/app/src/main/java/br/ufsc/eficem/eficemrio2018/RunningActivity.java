package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class RunningActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_activity);

        String competicao,categoria, tamanhoDaPista, numeroDeVoltas;
        int tempoDeProva,testeNumero;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            competicao= extras.getString("competicao");
            numeroDeVoltas= extras.getString("numeroDeVoltas");
            tamanhoDaPista = extras.getString("tamanhoDaPista");
            tempoDeProva= extras.getInt("tempoDeProva");
            testeNumero= extras.getInt("testeNumero");
            categoria= extras.getString("categoria");

        } else {
            competicao= (String) savedInstanceState.getSerializable("competicao");
            numeroDeVoltas= (String) savedInstanceState.getSerializable("numeroDeVoltas");
            tamanhoDaPista= (String) savedInstanceState.getSerializable("tamanhoDaPista");
            tempoDeProva= (int) savedInstanceState.getSerializable("tempoDeProva");
            testeNumero= (int) savedInstanceState.getSerializable("testeNumero");
            categoria= (String) savedInstanceState.getSerializable("categoria");
        }

        showSpeed();
        showDistance(tamanhoDaPista, numeroDeVoltas);

    }
    public void showSpeed (){
        TextView speedTextView = (TextView) findViewById(R.id.speedTextView);
        GetSpeed getSpeed = new GetSpeed();
        getSpeed.pedirPermissoes(this,speedTextView);
    }

    public void showDistance (String tamanhoDaPista,String numeroDeVoltas){
        TextView distanceTextView = (TextView) findViewById(R.id.lapsTextView);
        GetLaps getLaps = new GetLaps();
        getLaps.getDistance(this, distanceTextView,Integer.valueOf(numeroDeVoltas),Integer.valueOf(tamanhoDaPista));
    }
}
