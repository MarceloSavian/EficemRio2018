package br.ufsc.eficem.eficemrio2018;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ApiConnect {
    public void setCompeticao(){
        try {
            URL myURL = new URL("http://dobkovski.com:8080/api/insert.php?nomcomp=rio&catcom=eletrico&numvol=1&tampis=150&tempro=200&numtes=2");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
        }
        catch (IOException e) {
        }
    }
}
