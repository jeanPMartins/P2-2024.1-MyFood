package br.ufal.ic.p2.myfood.Modelos.Usuario;

public class Usuario {
    int id;
    String nome;
    String email;
    String senha;

    public Usuario(int id, String nome, String email, String senha ){
        this.email = email;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
