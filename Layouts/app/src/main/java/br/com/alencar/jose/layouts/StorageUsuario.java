package br.com.alencar.jose.layouts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jose on 19/05/17.
 */

public class StorageUsuario {
    public static final String CHAVE_USUARIO = "usuario";
    private String usuario;
    private String senha;
    private boolean recebeNovidades;
    private static List<StorageUsuario> usuarios = new ArrayList<>(
            Arrays.asList(
                    new StorageUsuario("jose_alencar_1403@hotmail.com", "zaq123"),
                    new StorageUsuario("jose@gmail.com", "google"),
                    new StorageUsuario("padrao@admin.com", "qwe"),
                    new StorageUsuario("teste@teste.com", "123")
            ));

    private StorageUsuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public StorageUsuario(String usuario, String senha, boolean recebeNovidades) {
        this.usuario = usuario;
        this.senha = senha;
        this.recebeNovidades = recebeNovidades;
    }

    public static boolean existeUsuario(String nome) {
        for (StorageUsuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public static boolean loginCorreto(String nome, String senha) {
        for (StorageUsuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nome) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static void save(StorageUsuario usuario) {
        usuarios.add(usuario);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isRecebeNovidades() {
        return recebeNovidades;
    }
}
