package br.ortodontech;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class QuizServer extends AsyncTask<String,Void,String> {

    Context context;
    QuizActivity quizActivity;

    QuizServer(Context ctx) {
        context = ctx;
        quizActivity = new QuizActivity();
    }

    @Override
    protected String doInBackground(String... params) {

        String quizUrl = "http://denan.com.br/ortodontech/app/apis/quiz.php";

        try {
            URL url = new URL(quizUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(14000);
            httpURLConnection.setConnectTimeout(14000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);


            OutputStream outputStream = httpURLConnection.getOutputStream();

//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//            String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
//                    +URLEncoder.encode("senha","UTF-8")+"="+URLEncoder.encode(senha,"UTF-8");
//            bufferedWriter.write(postData);

//            bufferedWriter.flush();
//            bufferedWriter.close();
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

//    protected void onPostExecute(String result) {
//
//        Log.i("mainDamiana", "QuizServer:onPostExecute:: result" + result);
//
//        try {
//            JSONObject jsonObject = new JSONObject(result);
//            Log.i("mainDamiana", "QuizServer:onPostExecute" + jsonObject.getString("pergunta"));
//            quizActivity.txtPergunta.setText(jsonObject.getString("pergunta"));
//
//        } catch (JSONException e) {
//            e.getStackTrace();
//        }
//    }
}
