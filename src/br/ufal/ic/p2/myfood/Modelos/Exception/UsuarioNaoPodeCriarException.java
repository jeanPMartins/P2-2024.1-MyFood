package br.ufal.ic.p2.myfood.Modelos.Exception;

public class UsuarioNaoPodeCriarException extends Exception{
    public UsuarioNaoPodeCriarException(){
        super("Usuario nao pode criar uma empresa");
    }
}
