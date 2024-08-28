package br.ufal.ic.p2.myfood.Modelos.Usuario;

public class Dono extends Usuario {
    String endereco;
    String cpf;
    public Dono(int id, String nome, String email, String senha, String endereco, String cpf) {
        super(id, nome, email, senha);
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }
    public String getCpf(){
        return cpf;
    }
}
