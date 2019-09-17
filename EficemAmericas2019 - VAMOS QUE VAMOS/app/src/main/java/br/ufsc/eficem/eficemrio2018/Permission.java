package br.ufsc.eficem.eficemrio2018;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

public class Permission {

    public void pedirPermissoes(Context context) {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        /*Cria um vetor tipo string contendo todas as permissioes do app, caso alguma esteja negada, pede-se permissao do usuario*/

        if        (ContextCompat.checkSelfPermission(context, permissions[0]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, permissions[1]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, permissions[2]) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, permissions[3]) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) context, permissions, 1);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults,Context context,TextView textView) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(context, "Erro de permissão!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}