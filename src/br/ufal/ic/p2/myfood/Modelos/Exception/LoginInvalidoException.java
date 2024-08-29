package br.ufal.ic.p2.myfood.Modelos.Exception;

public class LoginInvalidoException extends Exception{
    public LoginInvalidoException(){
        super("Login ou senha invalidos");
    }
}
