package br.ortodontech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilActivity extends AppCompatActivity {

    EditText editIDdPaciente, editDataNascimento, editNome, editCidade, editEmail, editNickame;
    EditText editSenha, editIrDentista, editUsaAparelho, editTemSangramento, editQtdVezesEscova ;
    EditText editQtdVezesUsaFio, editUsaEnxagueBucal, editFuma, editTemDiabetes, editPontuacao;
    Button btnEditarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        new PerfilServer(this){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.i("mainDamiana", "PerfilActovity:onPostExecute" + jsonObject.getString("nome"));

                    editIDdPaciente = new EditText(getApplicationContext());
                    editIDdPaciente.setText(jsonObject.getString("idPacientes"));

                    editDataNascimento = (EditText) findViewById(R.id.editDataNascimento);
                    editDataNascimento.setText(jsonObject.getString("dataNasc"));

                    editNome = (EditText) findViewById(R.id.editNome);
                    editNome.setText(jsonObject.getString("nome"));

                    editCidade = new EditText(getApplicationContext());
                    editCidade.setText(jsonObject.getString("cidade"));

                    editEmail = (EditText) findViewById(R.id.editEmail);
                    editEmail.setText(jsonObject.getString("email"));

                    editNickame = new EditText(getApplicationContext());
                    editNickame.setText(jsonObject.getString("nickName"));

                    editSenha = (EditText) findViewById(R.id.editSenhaPerfil);
                    editSenha.setText(jsonObject.getString("senha"));

                    editIrDentista = new EditText(getApplicationContext());
                    editIrDentista.setText(jsonObject.getString("irDentista"));

                    editUsaAparelho = new EditText(getApplicationContext());
                    editUsaAparelho.setText(jsonObject.getString("usaAparelho"));

                    editTemSangramento = new EditText(getApplicationContext());
                    editTemSangramento.setText(jsonObject.getString("temSangramento"));

                    editQtdVezesEscova = new EditText(getApplicationContext());
                    editQtdVezesEscova.setText(jsonObject.getString("qtdVezesEscova"));

                    editQtdVezesUsaFio = new EditText(getApplicationContext());
                    editQtdVezesUsaFio.setText(jsonObject.getString("qtdVezesUsaFio"));

                    editUsaEnxagueBucal = new EditText(getApplicationContext());
                    editUsaEnxagueBucal.setText(jsonObject.getString("UsaEnxagueBucal"));

                    editFuma = new EditText(getApplicationContext());
                    editFuma.setText(jsonObject.getString("Fuma"));

                    editTemDiabetes = new EditText(getApplicationContext());
                    editTemDiabetes.setText(jsonObject.getString("TemDiabetes"));

                    editPontuacao = new EditText(getApplicationContext());
                    editPontuacao.setText(jsonObject.getString("pontuacao"));

                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        }.execute(LoginActivity.USUARIO, LoginActivity.SENHA);
        //editNome.setEnabled(false);

        btnEditarPerfil = (Button) findViewById(R.id.btnEditarPerfil);
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPerfil(view);
            }
        });
    }

    public void salvarPerfil(View view) {

        String idPacientes = editIDdPaciente.getText().toString();
        String dataNasc = editDataNascimento.getText().toString();
        String nome = editNome.getText().toString();
        String cidade = editCidade.getText().toString();
        String email = editEmail.getText().toString();
        String nickName = editNickame.getText().toString();
        String senha = editSenha.getText().toString();

        EditarPerfilServer editarPerfil = new EditarPerfilServer(this);
        editarPerfil.execute(idPacientes, dataNasc, nome, cidade, email, nickName, senha);

        //TODO incluir todos os campos no update da API
    }
}
