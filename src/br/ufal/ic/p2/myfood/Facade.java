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
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CpfInvalidoException, EmailJaExisteException {
        sistema.criarUsuario(nome, email, senha, endereco, cpf);
    }
    public int login(String email, String senha) throws UsuarioNaoCadastradoException, LoginInvalidoException {
        return sistema.login(email, senha);
    }
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, UsuarioNaoPodeCriarException {
        return sistema.criarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha);
    }
    public String getEmpresasDoUsuario(int id) throws UsuarioNaoPodeCriarException {
        return sistema.getEmpresasDoUsuario(id);
    }
    public String getAtributoEmpresa(int id, String atributo) throws EmpresaNaoCadastradaException, AtributoInvalidoException {
        return sistema.getAtributoEmpresa(id, atributo);
    }
    public int getIdEmpresa(int idDono, String nome, int indice) throws UsuarioNaoCadastradoException, LoginInvalidoException {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }
}
