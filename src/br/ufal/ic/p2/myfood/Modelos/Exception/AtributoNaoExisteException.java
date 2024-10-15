package br.ufal.ic.p2.myfood.Modelos.Exception;

public class AtributoNaoExisteException extends RuntimeException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe");
    }
}
