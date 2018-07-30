package br.ufsc.eficem.eficemrio2018;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnect {
    public void setCompeticao() {
        URL myURL = null;
        try {
            myURL = new URL("http://dobkovski.com:8080/api/insert.php?nomcomp=rio&catcom=eletrico&numvol=1&tampis=150&tempro=200&numtes=2");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try{
            HttpURLConnection myConn = (HttpURLConnection) myURL.openConnection();
            myConn.setRequestMethod("POST");
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
