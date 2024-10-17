package br.ufal.ic.p2.myfood.Modelos.Usuario;

public class Cliente extends Usuario {
    public Cliente() {}
    public Cliente(int id, String nome, String email, String senha, String endereco) {
        super(id, nome, email, senha, endereco, TipoUsuario.CLIENTE);
    }
}