package com.senai.calculadoranotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private CheckBox checkBoxLogado;
    private EditText editTextUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(Utils.NOME_ARQUIVO, Context.MODE_PRIVATE);

        editTextUsuario = findViewById(R.id.editTextUsuario);

        Button buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonEntrar.setOnClickListener(this);

        checkBoxLogado = findViewById(R.id.checkBoxLogado);

        verificarUsuarioLogado();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonEntrar) {
            if (checkBoxLogado.isChecked()) {
                salvarUsuarioLogado();
                abrirTelaMain();
            } else {
                abrirTelaMain();
            }
        }
    }

    public void abrirTelaMain() {
        Intent telaMain = new Intent(this, MainActivity.class);
        startActivity(telaMain);
        finish();
    }

    public void salvarUsuarioLogado() {
        String nomeUsuario = editTextUsuario.getText().toString();

        if (nomeUsuario.isEmpty()) {
            Toast.makeText(this, "Preencha o campo usuário", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Utils.USUARIO_LOGADO, true);

            editor.putString(Utils.NOME_USUARIO, nomeUsuario); // Aluno
            editor.apply();
        }
    }

    public void verificarUsuarioLogado() {
        boolean usuarioLogado = sharedPreferences.getBoolean(Utils.USUARIO_LOGADO, false);

        if (usuarioLogado) {
            abrirTelaMain();
        }
    }
}