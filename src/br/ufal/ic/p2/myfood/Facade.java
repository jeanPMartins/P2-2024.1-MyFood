package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;
import br.ufal.ic.p2.myfood.Modelos.Sistema;

public class Facade {

    Sistema sistema = new Sistema();

    public void zerarSistema(){
        sistema.zerarSistema();
    }
    public void encerrarSistema(){
        sistema.encerrarSistema();
    }

    public String getAtributoUsuario(int id, String nome) throws UsuarioNaoCadastradoException {
        return sistema.getAtributoUsuario(id, nome);
    }


    //Cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailJaExisteException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    //Dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CpfInvalidoException {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }
    public int login(String email, String senha) throws UsuarioNaoCadastradoException, LoginInvalidoException {
        return sistema.login(email, senha);
    }


}
