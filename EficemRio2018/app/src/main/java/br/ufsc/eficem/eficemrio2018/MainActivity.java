package br.ufsc.eficem.eficemrio2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText competicaoEditText = (EditText) findViewById(R.id.competicaoEditText);
        final EditText numeroDeVoltasEditText = (EditText) findViewById(R.id.numeroDeVoltasEditText);
        final EditText tamanhoDaPistaEditText = (EditText) findViewById(R.id.tamanhoDaPistaEditText);
        final EditText tempoDeProvaEditText = (EditText) findViewById(R.id.tempoDeProvaEditText);
        final EditText testeNumeroEditText = (EditText) findViewById(R.id.testeNumeroEditText);
        final EditText categoriaEditText = (EditText) findViewById(R.id.categoriaEditText);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RunningActivity.class);
                i.putExtra("competicao", competicaoEditText.getText().toString());
                i.putExtra("numeroDeVoltas", numeroDeVoltasEditText.getText().toString());
                i.putExtra("tamanhoDaPista", tamanhoDaPistaEditText.getText().toString());
                i.putExtra("tempoDeProva", tempoDeProvaEditText.getText().toString());
                i.putExtra("testeNumero", testeNumeroEditText.getText().toString());
                i.putExtra("categoria", categoriaEditText.getText().toString());
                startActivity(i);
            }
        });
    }
}
