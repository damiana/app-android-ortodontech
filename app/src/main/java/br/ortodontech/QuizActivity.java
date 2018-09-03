package br.ortodontech;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {

    Button btnAlternativaA, btnAlternativaB, btnAlternativaC;
    TextView txtPergunta;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //btnAlternativaA = (Button) findViewById(R.id.btnAlternativa_a);
        btnAlternativaB = (Button) findViewById(R.id.btnAlternativa_b);
        btnAlternativaC = (Button) findViewById(R.id.btnAlternativa_c);

        new QuizServer(this){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i("mainDamiana", "QuizServer:onPostExecute:: result" + result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.i("mainDamiana", "QuizServer:onPostExecute" + jsonObject.getString("pergunta"));
                    quizActivity.txtPergunta = (TextView) findViewById(R.id.txtPergunta);
                    quizActivity.txtPergunta.setText(jsonObject.getString("pergunta"));

                    quizActivity.btnAlternativaA = (Button) findViewById(R.id.btnAlternativa_a);
                    quizActivity.btnAlternativaA.setText(jsonObject.getString("alternativa_a"));

                    final String resposta = jsonObject.getString("resposta");

                    quizActivity.btnAlternativaA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("mainDamiana:: ", resposta);
                            if (!resposta.equalsIgnoreCase("A")) {
                                quizActivity.btnAlternativaB.setEnabled(false);
                                quizActivity.btnAlternativaC.setEnabled(false);
                                Toast.makeText(QuizActivity.this, "Voce Errou!",
                                        Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(QuizActivity.this, "Parabens! Voce Acertou!",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });


                    quizActivity.btnAlternativaB = (Button) findViewById(R.id.btnAlternativa_b);
                    quizActivity.btnAlternativaB.setText(jsonObject.getString("alternativa_b"));

                    quizActivity.btnAlternativaB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("mainDamiana:: ", resposta);
                            if (!resposta.equalsIgnoreCase("B")) {
                                quizActivity.btnAlternativaA.setEnabled(false);
                                quizActivity.btnAlternativaC.setEnabled(false);
                                Toast.makeText(QuizActivity.this, "Voce Errou!",
                                        Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(QuizActivity.this, "Parabens! Voce Acertou!",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    quizActivity.btnAlternativaC = (Button) findViewById(R.id.btnAlternativa_c);
                    quizActivity.btnAlternativaC.setText(jsonObject.getString("alternativa_c"));

                    quizActivity.btnAlternativaC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("mainDamiana:: ", resposta);
                            if (!resposta.equalsIgnoreCase("C")) {
                                quizActivity.btnAlternativaA.setEnabled(false);
                                quizActivity.btnAlternativaB.setEnabled(false);
                                Toast.makeText(QuizActivity.this, "Voce Errou!",
                                        Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(QuizActivity.this, "Parabens! Voce Acertou!",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        }.execute();

//        btnAlternativaA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("mainDamiana:: ", "Alternativa A PORAAAAA");
//                onQuiz(view);
//
//            }
//        });

        btnAlternativaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "Alternativa B");

            }
        });

        btnAlternativaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "Alternativa C");

            }
        });
    }

    public void onQuiz (View view) {
        //txtPergunta = (TextView) findViewById(R.id.txtPergunta);
        //txtPergunta.setText("adaddada");
//        QuizServer quizServer = new QuizServer(this);
//        quizServer.execute();

    }
}
