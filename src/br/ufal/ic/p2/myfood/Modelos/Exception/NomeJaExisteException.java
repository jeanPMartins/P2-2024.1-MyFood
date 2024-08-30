package br.ufal.ic.p2.myfood.Modelos.Exception;

public class NomeJaExisteException extends Exception{
    public NomeJaExisteException(){
        super("Empresa com esse nome ja existe");
    }
}