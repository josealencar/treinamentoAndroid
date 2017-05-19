package br.com.alencar.jose.layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private EditText etConfirmaSenha;
    private TextView tvErroMessage;
    private CheckBox cbRecebeNotificacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etConfirmaSenha = (EditText) findViewById(R.id.etConfirmaSenha);
        tvErroMessage = (TextView) findViewById(R.id.tvErroMessage);
        cbRecebeNotificacoes = (CheckBox) findViewById(R.id.cbRecebeNotificacoes);

        etUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validaUsuarioExistente(etUsuario.getText().toString());
                }
            }
        });
    }

    private boolean validaUsuarioExistente(String usuario) {
        if (StorageUsuario.existeUsuario(usuario)) {
            tvErroMessage.setText(getResources().getString(R.string.usuarioExistente));
            mostraTvErroMessage();
            return true;
        }
        return false;
    }

    public void registrarUsuario(View view) {
        escondeTvErroMessage();
        String usuario = etUsuario.getText().toString();
        String senha = etSenha.getText().toString();
        String confirmaSenha = etConfirmaSenha.getText().toString();
        if (usuario.isEmpty()) {
            tvErroMessage.setText(getResources().getString(R.string.usuarioObrigatorio));
            mostraTvErroMessage();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(usuario).matches()) {
            tvErroMessage.setText(getResources().getString(R.string.usuarioIncorreto));
            mostraTvErroMessage();
            return;
        }
        if (validaUsuarioExistente(usuario)) {
            return;
        }
        if (senha.isEmpty() || senha.length() < 6) {
            tvErroMessage.setText(getResources().getString(R.string.tamanhoMinimoSenha));
            mostraTvErroMessage();
            return;
        }
        if (!senha.equals(confirmaSenha)) {
            tvErroMessage.setText(getResources().getString(R.string.senhaEConfirmaSenhaDivergentes));
            mostraTvErroMessage();
            return;
        }

        boolean recebeNotificacoes = cbRecebeNotificacoes.isChecked();

        StorageUsuario storageUsuario = new StorageUsuario(usuario, senha, recebeNotificacoes);
        StorageUsuario.save(storageUsuario);

        Intent i = new Intent(this, UsuarioActivity.class);
        i.putExtra(StorageUsuario.CHAVE_USUARIO, usuario);
        startActivity(i);
    }

    private void mostraTvErroMessage() {
        tvErroMessage.setVisibility(View.VISIBLE);
    }

    private void escondeTvErroMessage() {
        tvErroMessage.setVisibility(View.GONE);
    }
}
