package br.ortodontech;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class LoginServer extends AsyncTask<String,Void,String> {

    Context context;

    LoginServer (Context ctx) {
        context = ctx;

    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        //String loginUrl = "http://denan.com.br/ortodontech/app/apis/loginPaciente.php";
        String loginUrl = "http://192.168.15.34/api-crud-rest-php/apis/loginPaciente.php";

        if(type.equals("login")) {
            Log.i("mainDamiana", "PerfilActovity:login");
            String email = params[1];
            String senha = params[2];

            try {
                URL url = new URL(loginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(14000);
                httpURLConnection.setConnectTimeout(14000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);


                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("senha","UTF-8")+"="+URLEncoder.encode(senha,"UTF-8");
                bufferedWriter.write(postData);

                Log.i("mainDamiana", "PerfilActovity:postData");

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

                Log.i("mainDamiana", "PerfilActovity:result" + result);
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        // nothing to be done.
    }

    @Override
    protected void onPostExecute(String result) {

        Log.i("mainDamiana", "PerfilActovity:onPostExecute - result: " + result);

        String r = result.replace("\"", "");

        if(r.equals("sucesso!")){
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, r, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
