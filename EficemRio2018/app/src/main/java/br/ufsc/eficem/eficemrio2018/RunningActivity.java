package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class RunningActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_activity);

        String competicao,categoria;
        int numeroDeVoltas, tamanhoDaPista,tempoDeProva,testeNumero;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            competicao= extras.getString("competicao");
            numeroDeVoltas= extras.getInt("numeroDeVoltas");
            tamanhoDaPista = extras.getInt("tamanhoDaPista");
            tempoDeProva= extras.getInt("tempoDeProva");
            testeNumero= extras.getInt("testeNumero");
            categoria= extras.getString("categoria");

        } else {
            competicao= (String) savedInstanceState.getSerializable("competicao");
            numeroDeVoltas= (int) savedInstanceState.getSerializable("numeroDeVoltas");
            tamanhoDaPista= (int) savedInstanceState.getSerializable("tamanhoDaPista");
            tempoDeProva= (int) savedInstanceState.getSerializable("tempoDeProva");
            testeNumero= (int) savedInstanceState.getSerializable("testeNumero");
            categoria= (String) savedInstanceState.getSerializable("categoria");
        }

        showSpeed();

    }
    public void showSpeed (){
        TextView speedTextView = (TextView) findViewById(R.id.speedTextView);
        GetSpeed getSpeed = new GetSpeed();
        getSpeed.pedirPermissoes(this,speedTextView);
    }
}