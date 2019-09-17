package br.ufsc.eficem.eficemrio2018;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GuardaArquivo {
    public void salvaArquivo (Carro carro) throws IOException {
        String timeString = Integer.toString(carro.getTime());
        String diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + "arquivo" + "/";
        File diretorio = new File(diretorioApp);
        diretorio.mkdirs();

        File fileApp = new File(diretorioApp,timeString + ".txt");

        fileApp.createNewFile();

        //Cria o arquivo
        //fileApp.getParentFile().mkdirs();

        //Abre o arquivo
        FileOutputStream fosExt = null;
        fosExt = new FileOutputStream(fileApp);

        //Escreve no arquivo

        //tava assim mas dava erro:         String data = carro.getTime().toString();
        String data = Integer.toString(carro.getTime());
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = String.valueOf(carro.getLap());
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = carro.getSpeed().toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = carro.getDistance().toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = carro.getLatitude().toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = carro.getLongitude().toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        data = carro.getAltitude().toString();
        fosExt.write(data.getBytes());
        data = "\n";
        fosExt.write(data.getBytes());
        ProgressDialog dialog = null;


        //Fechamento do arquivo
        fosExt.close();
    }
}