package br.ufal.ic.p2.myfood.Modelos.Usuario;

public class Usuario {
    int id;
    String nome;
    String email;
    String senha;
    String endereco;

    public Usuario(int id, String nome, String email, String senha, String endereco) {
        this.email = email;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
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
    public String getEndereco(){
        return endereco;
    }
}