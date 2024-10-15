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
//# criarPedido(int: cliente, int: empresa)
//# descrição: Cria um novo pedido de um cliente para uma empresa.
//# retorno:  Retorna o número do pedido
//#
//# getNumeroPedido(int: cliente, int: empresa, int indice)
//# descrição: Retorna o pedido de um cliente na empresa, ordenado de forma ao mais antigo vir primeiro no indice
//# retorno:  Retorna o número do pedido.
//#
//# adicionarProduto(int: numero, int produto)
//# descrição: Adciona um produto da empresa ao pedido
//# retorno:  Sem retorno
//#
//# getPedidos(int  numero, String atributo)
//# descrição:  obtém os dados de um pedido pelo id
//# retorno:  retorna uma string com o valor do atributo.
//#
//# fecharPedido(int numero)
//# descrição:  muda o estado do pedido para preparando.
//# retorno:  Sem retorno
//#
//# removerProduto(int  pedido, String produto)
//# descrição:  Remove um produto adicionado com esse nome, mantém outros caso com o mesmo nome.
//# retorno: Sem retorno
}
