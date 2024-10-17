package br.ufal.ic.p2.myfood.Modelos.Usuario;

public class Dono extends Usuario {
    String cpf;
    public Dono(){}
    public Dono(int id, String nome, String email, String senha, String endereco, String cpf) {
        super(id, nome, email, senha, endereco);
        this.cpf = cpf;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
}