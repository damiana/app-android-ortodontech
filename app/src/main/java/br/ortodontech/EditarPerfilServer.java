package br.ortodontech;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

public class EditarPerfilServer extends AsyncTask<String,Void,String> {

    Context context;

    EditarPerfilServer (Context ctx) {
        context = ctx;

    }

    @Override
    protected String doInBackground(String... params) {

        String updateUrl = "http://denan.com.br/ortodontech/app/apis/updatePaciente.php";
        String idPacientes = params[0];
        String dataNasc = params[1];
        String nome = params[2];
        String cidade = params[3];
        String email = params[4];
        String nickName = params[5];
        String senha = params[6];

        try {
            URL url = new URL(updateUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(14000);
            httpURLConnection.setConnectTimeout(14000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);


            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postData = URLEncoder.encode("idPacientes","UTF-8")+"="
                    +URLEncoder.encode(idPacientes,"UTF-8")+"&"
                    +URLEncoder.encode("dataNasc","UTF-8")+"="+URLEncoder.encode(dataNasc,"UTF-8")+"&"
                    +URLEncoder.encode("nome","UTF-8")+"="+URLEncoder.encode(nome,"UTF-8")+"&"
                    +URLEncoder.encode("cidade","UTF-8")+"="+URLEncoder.encode(cidade,"UTF-8")+"&"
                    +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                    +URLEncoder.encode("nickName","UTF-8")+"="+URLEncoder.encode(nickName,"UTF-8")+"&"
                    +URLEncoder.encode("senha","UTF-8")+"="+URLEncoder.encode(senha,"UTF-8")+"&";

            bufferedWriter.write(postData);

            Log.i("mainDamiana", "PerfilActovity:alterarDadosPerfil - postData: " + postData);

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

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Content", result);
            jsonObject.put("Message", httpURLConnection.getResponseMessage());
            jsonObject.put("Length", httpURLConnection.getContentLength());
            jsonObject.put("Type", httpURLConnection.getContentType());

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();


            Log.i("mainDamiana", "PerfilActovity:alterarDadosPerfil - result: " + result);

            return result.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        // nothing to be done.
    }

    @Override
    protected void onPostExecute(String result) {
        String r = result.replace("\"", "");
        Toast.makeText(context, r, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
