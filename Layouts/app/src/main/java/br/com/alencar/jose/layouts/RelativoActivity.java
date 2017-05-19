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

    private TextView tvUsuarioIncorreto;
    private TextView tvEmptyEmail;
    private TextView tvEmptyPassword;
    private TextView tvCredenciaisInvalidas;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativo);

        tvUsuarioIncorreto = (TextView) findViewById(R.id.tvUsuarioIncorreto);
        tvEmptyEmail = (TextView) findViewById(R.id.tvEmptyEmail);
        tvEmptyPassword = (TextView) findViewById(R.id.tvEmptyPassword);
        tvCredenciaisInvalidas = (TextView) findViewById(R.id.tvCredenciaisInvalidas);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validaCampoEmail(v);
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validaCampoPassword(v);
                }
            }
        });
    }

    public void logar(View view) {
        tvUsuarioIncorreto.setVisibility(View.GONE);
        tvEmptyEmail.setVisibility(View.GONE);
        tvEmptyPassword.setVisibility(View.GONE);
        tvCredenciaisInvalidas.setVisibility(View.GONE);

        String txtEmail = etEmail.getText().toString();
        String txtPassword = etPassword.getText().toString();

        if (isCampoVazio(txtPassword)) {
            tvEmptyPassword.setVisibility(View.VISIBLE);
            setFocus(etPassword);
        }

        if (isCampoVazio(txtEmail)) {
            tvEmptyEmail.setVisibility(View.VISIBLE);
            setFocus(etEmail);
            return;
        }

        if (!isEmailValido(txtEmail)) {
            tvUsuarioIncorreto.setVisibility(View.VISIBLE);
            setFocus(etEmail);
            return;
        }

        for (String credencial : credenciais) {
            if (credencial.equals(txtEmail + ":" + txtPassword)) {
                Intent i = new Intent(this, UsuarioActivity.class);
                i.putExtra("usuario", txtEmail);
                startActivity(i);
                return;
            }
        }
        tvCredenciaisInvalidas.setVisibility(View.VISIBLE);

    }

    private void setFocus(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public void validaCampoEmail(View view) {
        String txtEmail = etEmail.getText().toString();
        tvUsuarioIncorreto.setVisibility(View.GONE);
        if (isCampoVazio(txtEmail)) {
            tvEmptyEmail.setVisibility(View.VISIBLE);
            return;
        } else {
            tvEmptyEmail.setVisibility(View.GONE);
        }

        if (isEmailValido(txtEmail)) {
            tvUsuarioIncorreto.setVisibility(View.GONE);
        } else {
            tvUsuarioIncorreto.setVisibility(View.VISIBLE);
        }
    }

    public void validaCampoPassword(View view) {
        String txtPassword = etPassword.getText().toString();
        if (isCampoVazio(txtPassword)) {
            tvEmptyPassword.setVisibility(View.VISIBLE);
        } else {
            tvEmptyPassword.setVisibility(View.GONE);
        }
    }

    private boolean isCampoVazio(String campo) {
        return campo.isEmpty();
    }

    private boolean isEmailValido(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
