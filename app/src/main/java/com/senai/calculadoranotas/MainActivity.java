package com.senai.calculadoranotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    private EditText editTextNomeAluno;
    private EditText editTextNomeDisciplina; // Aluno
    private EditText editTextNotaPrimeiraAtividade;
    private EditText editTextNotaSegundaAtividade; // Aluno
    private EditText editTextNotaProva; // Aluno
    private EditText editTextQtdeFaltas; // Aluno

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Utils.NOME_ARQUIVO, Context.MODE_PRIVATE);

        alterarTitulo();

        Button buttonAvaliarAluno = findViewById(R.id.buttonAvaliarAluno);
        buttonAvaliarAluno.setOnClickListener(this);

        Button buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonLimpar.setOnClickListener(this);

        Button buttonDeslogar = findViewById(R.id.buttonDeslogar);
        buttonDeslogar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAvaliarAluno) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            calcularMediaAluno();
        } else if (view.getId() == R.id.buttonLimpar) { // Aluno
            limpar(); // Aluno

        } else if (view.getId() == R.id.buttonDeslogar) {
            deslogar();
        }
    }

    /**
     * Aluno
     */
    private void alterarTitulo() {
        TextView textViewTitulo = findViewById(R.id.textViewTitulo);
        String nomeUsuario = sharedPreferences.getString(Utils.NOME_USUARIO, "");
        String titulo = textViewTitulo.getText().toString().concat(" - ").concat(nomeUsuario);
        textViewTitulo.setText(titulo);
    }

    private void calcularMediaAluno() {
        editTextNomeAluno = findViewById(R.id.editTextNomeAluno);
        editTextNomeDisciplina = findViewById(R.id.editTextNomeDisciplina); // Aluno
        editTextNotaPrimeiraAtividade = findViewById(R.id.editTextNotaPrimeiraAtividade);
        editTextNotaSegundaAtividade = findViewById(R.id.editTextNotaSegundaAtividade); // Aluno
        editTextNotaProva = findViewById(R.id.editTextNotaProva); // Aluno
        editTextQtdeFaltas = findViewById(R.id.editTextQtdeFaltas); // Aluno

        String nome = editTextNomeAluno.getText().toString();
        String disciplina = editTextNomeDisciplina.getText().toString();
        int notaPrimeiraAtividade = Integer.parseInt(editTextNotaPrimeiraAtividade.getText().toString());
        int notaSegundaAtividade = Integer.parseInt(editTextNotaSegundaAtividade.getText().toString());
        int notaProva = Integer.parseInt(editTextNotaProva.getText().toString());
        int qtdeFaltas = Integer.parseInt(editTextQtdeFaltas.getText().toString());

        Aluno aluno = new Aluno(nome, disciplina, notaPrimeiraAtividade, notaSegundaAtividade, notaProva, qtdeFaltas);
        String mensagem = aluno.verificarAlunoAprovado();

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    /**
     * Aluno
     */
    private void limpar() {
        editTextNomeAluno.setText("");
        editTextNomeDisciplina.setText("");
        editTextNotaPrimeiraAtividade.setText("");
        editTextNotaSegundaAtividade.setText("");
        editTextNotaProva.setText("");
        editTextQtdeFaltas.setText("");
    }

    private void deslogar() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Utils.NOME_USUARIO);
        editor.remove(Utils.USUARIO_LOGADO); // Aluno
        editor.apply();

        Intent telaLogin = new Intent(this, LoginActivity.class); // Aluno
        startActivity(telaLogin); // Aluno
        finish(); // Aluno
    }

}