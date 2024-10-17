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
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, UsuarioNaoPodeCriarException, EnderecoInvalidoEmpresaException {
        return sistema.criarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha);
    }
    public String getEmpresasDoUsuario(int id) throws UsuarioNaoPodeCriarException {
        return sistema.getEmpresasDoUsuario(id);
    }
    public String getAtributoEmpresa(int id, String atributo) throws EmpresaNaoCadastradaException, AtributoInvalidoException {
        return sistema.getAtributoEmpresa(id, atributo);
    }
    public int getIdEmpresa(int idDono, String nome, int indice) throws NomeInvalidoException, EmpresaNaoExisteException {
        return sistema.getIdEmpresa(idDono, nome, indice);
    }
    public int criarProduto(int empresa, String nome, float valor, String categoria) throws NomeInvalidoException {
        return sistema.criarProduto(empresa, nome, valor, categoria);
    }
    public void editarProduto(int produto, String nome, float valor, String categoria) throws NomeInvalidoException {
        sistema.editarProduto(produto, nome, valor, categoria);
    }
    public String getProduto(String nome, int empresa, String atributo) throws AtributoInvalidoException {
        return sistema.getProduto(nome, empresa, atributo);
    }
    public String listarProdutos(int empresa){
        return sistema.listarProdutos(empresa);
    }
    public int criarPedido(int cliente, int empresa){
        return sistema.criarPedido(cliente, empresa);
    }
    public int getNumeroPedido(int cliente, int empresa, int indice){
        return sistema.getNumeroPedido(cliente, empresa, indice);
    }
    public void adicionarProduto(int numero, int produto){
        sistema.adicionarProduto(numero, produto);
    }
    public String getPedidos(int numero, String atributo) throws AtributoInvalidoException {
        return sistema.getPedidos(numero, atributo);
    }
    public void fecharPedido(int numero){
        sistema.fecharPedido(numero);
    }
    public void removerProduto(int numero, String produto){
        sistema.removerProduto(numero, produto);
    }
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) throws EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, NomeInvalidoException, UsuarioNaoPodeCriarException, EnderecoInvalidoEmpresaException {
        return sistema.criarEmpresa(tipoEmpresa, dono, nome, endereco, abre, fecha, tipoMercado);
    }
    public void alterarFuncionamento(int mercado, String abre, String fecha){
        sistema.alterarFuncionamento(mercado, abre, fecha);
    }
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, boolean abre24h, int numFuncionario) throws EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, NomeInvalidoException, UsuarioNaoPodeCriarException, EnderecoInvalidoEmpresaException {
        return sistema.criarEmpresa(tipoEmpresa, dono, nome, endereco, abre24h, numFuncionario);
    }
}
