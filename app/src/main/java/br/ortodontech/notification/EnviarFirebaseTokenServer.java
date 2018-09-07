package br.ortodontech.notification;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class EnviarFirebaseTokenServer extends AsyncTask<String,Void,String>  {

    Context context;

    public EnviarFirebaseTokenServer(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String token = params[0];
        // String tokenNotificationUrl = "http://192.168.15.34/api-crud-rest-php/firebase/notifications/tokenNotification.php";
        String tokenNotificationUrl = "http://denan.com.br/ortodontech/app/firebase/notifications/tokenNotification.php";

        try {
            URL url = new URL(tokenNotificationUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(14000);
            httpURLConnection.setConnectTimeout(14000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postData = URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");
            bufferedWriter.write(postData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));
            String result = "";
            String line = "";

            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            Log.i("mainDamiana", "EnviarFirebaseTokenServer:" + result);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("mainDamiana", "EnviarFirebaseTokenServer:onPostExecute - result: " + result);
    }
}
