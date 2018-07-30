package br.ufsc.eficem.eficemrio2018;

import android.os.Bundle;
import android.widget.TextView;

public class Competicao {


    String competicao,categoria, tamanhoDaPista, numeroDeVoltas,testeNumero,tempoDeProva;


    ApiConnect cdb = new ApiConnect();

    String tempoDeProva,testeNumero,competicao,categoria, tamanhoDaPista, numeroDeVoltas;


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

    public String getTempoDeProva() {
        return tempoDeProva;
    }

    public String getTesteNumero() {
        return testeNumero;
    }

    public void recebeInformacoes (Bundle savedInstanceState, Bundle extras){

        if (savedInstanceState == null) {
            competicao= extras.getString("competicao");
            numeroDeVoltas= extras.getString("numeroDeVoltas");
            tamanhoDaPista = extras.getString("tamanhoDaPista");
            tempoDeProva= extras.getString("tempoDeProva");
            testeNumero= extras.getString("testeNumero");
            categoria= extras.getString("categoria");

        } else {
            competicao= (String) savedInstanceState.getSerializable("competicao");
            numeroDeVoltas= (String) savedInstanceState.getSerializable("numeroDeVoltas");
            tamanhoDaPista= (String) savedInstanceState.getSerializable("tamanhoDaPista");
            tempoDeProva= (String) savedInstanceState.getSerializable("tempoDeProva");
            testeNumero= (String) savedInstanceState.getSerializable("testeNumero");
            categoria= (String) savedInstanceState.getSerializable("categoria");
        }
        cdb.setCompeticao();
    }
}
