package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.widget.TextView;

public class Competicao {

    String competicao,categoria, tamanhoDaPista, numeroDeVoltas;
    int tempoDeProva,testeNumero;

    public String getCompeticao() {
        return competicao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTamanhoDaPista() {
        return tamanhoDaPista;
    }

    public String getNumeroDeVoltas() {
        return numeroDeVoltas;
    }

    public int getTempoDeProva() {
        return tempoDeProva;
    }

    public int getTesteNumero() {
        return testeNumero;
    }

    public void recebeInformacoes (Bundle savedInstanceState, Bundle extras){

        if (savedInstanceState == null) {
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
    }

}
