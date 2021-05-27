package _DENEME._otherpages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class x4 {
    public static void main(String[] args) throws IOException {

        URL link = new URL("https://www.mevlanacay.de");
        HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection();
        urlConnection.setConnectTimeout(2000);
        urlConnection.connect();
        System.out.println(urlConnection.getResponseCode());
        System.out.println(urlConnection.getResponseMessage());
        System.out.println(urlConnection.getHeaderFields());
        urlConnection.disconnect();
    }
}
