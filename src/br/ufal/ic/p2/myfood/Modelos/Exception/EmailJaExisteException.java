package br.ufal.ic.p2.myfood.Modelos.Exception;

public class EmailJaExisteException extends Exception{
    public EmailJaExisteException(){
        super("Conta com esse email ja existe");
    }
}
