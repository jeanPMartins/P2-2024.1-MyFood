package br.ufal.ic.p2.myfood.Modelos.Exception;

public class UsuarioNaoCadastradoException extends Exception{
    public UsuarioNaoCadastradoException(){
        super("Usuario nao cadastrado.");
    }
}
