package br.com.alencar.jose.layouts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class RelativoActivity extends AppCompatActivity {

    private List<String> credenciais = Arrays.asList(
        "jose_alencar_1403@hotmail.com:zaq123",
        "jose@gmail.com:google",
        "padrao@admin.com:qwe",
        "teste@teste.com:123"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativo);
    }

    public void logar(View view) {
        TextView tvUsuarioIncorreto = (TextView) findViewById(R.id.tvUsuarioIncorreto);
        TextView tvEmptyEmail = (TextView) findViewById(R.id.tvEmptyEmail);
        TextView tvEmptyPassword = (TextView) findViewById(R.id.tvEmptyPassword);
        tvUsuarioIncorreto.setVisibility(View.GONE);
        tvEmptyEmail.setVisibility(View.GONE);
        tvEmptyPassword.setVisibility(View.GONE);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        String txtEmail = etEmail.getText().toString();
        String txtPassword = etPassword.getText().toString();

        boolean isEmailVazio = txtEmail.isEmpty();
        boolean isPasswordVazia = txtPassword.isEmpty();
        boolean isEmailValido = android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();

        if (isEmailVazio) {
            tvEmptyEmail.setVisibility(View.VISIBLE);
            setFocus(etEmail);
        }

        if (isPasswordVazia) {
            tvEmptyPassword.setVisibility(View.VISIBLE);
            setFocus(etPassword);
        }

        if (!isEmailValido) {
            tvUsuarioIncorreto.setVisibility(View.VISIBLE);
            setFocus(etEmail);
        }

        if (!isEmailVazio && !isPasswordVazia && isEmailValido) {
            for (String credencial : credenciais) {
                if (credencial.equals(txtEmail + ":" + txtPassword)) {
                    Intent i = new Intent(this, UsuarioActivity.class);
                    i.putExtra("usuario", txtEmail);
                    startActivity(i);
                }
            }
        }

    }

    private void setFocus(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
