package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Modelos.Exception.NomeInvalidoException;
import br.ufal.ic.p2.myfood.Modelos.Exception.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.myfood.Modelos.Sistema;

public class Facade {

    Sistema sistema = new Sistema();

    public void zerarSistema(){

    }

    public String getAtributoUsuario(int id, String nome) throws UsuarioNaoCadastradoException {
        return sistema.getAtributoUsuario(id, nome);
    }


    //Cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException {
        sistema.criarUsuario(nome, email, senha, endereco);
    }

    //Dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }



}
