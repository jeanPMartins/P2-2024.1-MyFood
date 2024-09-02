package br.ufal.ic.p2.myfood.Modelos.Exception;

public class EnderecoJaExisteException extends Exception{
    public EnderecoJaExisteException(){
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
